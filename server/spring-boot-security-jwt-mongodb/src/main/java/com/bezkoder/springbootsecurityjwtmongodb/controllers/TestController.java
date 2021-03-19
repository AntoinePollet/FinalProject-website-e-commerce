package com.bezkoder.springbootsecurityjwtmongodb.controllers;

import com.bezkoder.springbootsecurityjwtmongodb.models.Article;
import com.bezkoder.springbootsecurityjwtmongodb.models.ERole;
import com.bezkoder.springbootsecurityjwtmongodb.models.Role;
import com.bezkoder.springbootsecurityjwtmongodb.models.User;
import com.bezkoder.springbootsecurityjwtmongodb.payload.request.SignupRequest;
import com.bezkoder.springbootsecurityjwtmongodb.payload.request.UserUpdateInfo;
import com.bezkoder.springbootsecurityjwtmongodb.payload.response.MessageResponse;
import com.bezkoder.springbootsecurityjwtmongodb.repository.RoleRepository;
import com.bezkoder.springbootsecurityjwtmongodb.repository.UserRepository;
import com.bezkoder.springbootsecurityjwtmongodb.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    RoleRepository roleRepository;


    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserUpdateInfo userUpdateInfo) {

        Optional<User> userData = userRepository.findByUsername(userUpdateInfo.getUsername());

        if (userData.isPresent()) {
            // Create new user's account
            User userD = userData.get();
            userD.setEmail(userUpdateInfo.getEmail());
            userD.setPassword(encoder.encode(userUpdateInfo.getPassword()));

           userRepository.save(userD);

           return ResponseEntity.ok(new MessageResponse("Update registered successfully!"));
        }else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username doesn't exist!"));
        }

    }
}

