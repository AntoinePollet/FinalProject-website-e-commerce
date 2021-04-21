package com.kyokyu.backend.controllers;

import com.kyokyu.backend.models.User;
import com.kyokyu.backend.models.UserInfos;
import com.kyokyu.backend.repository.UserRepository;
import com.kyokyu.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserRepository userRepository;


    Logger logger= LoggerFactory.getLogger(ArticleController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/findByUsername/{username}")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserByUsername (@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        logger.info("getUserByUsername : {}", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers () {
        List<User> users = userService.findAllUser();
        logger.info("getAllUsers : {}", users);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<User> getUserById (@PathVariable("id") String id) {
        User user = userService.findById(id);
        logger.info("getUserByUsername : {}", user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @PutMapping("/updateInfos/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> updateUserInfosById(@PathVariable("id") String id, @RequestBody UserInfos userInfos) {

        User updateUserById = userService.updateUserInfos(id, userInfos);

        if(updateUserById == null){
            logger.info("user not found : {}", updateUserById);
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND);
        }
        logger.info("user infos updated : {}", updateUserById);
        return new ResponseEntity<>(updateUserById, HttpStatus.OK);
    }



}
