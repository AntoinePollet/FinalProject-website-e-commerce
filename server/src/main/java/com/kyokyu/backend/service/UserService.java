package com.kyokyu.backend.service;


import com.kyokyu.backend.models.Address;
import com.kyokyu.backend.models.Article;
import com.kyokyu.backend.models.User;
import com.kyokyu.backend.models.UserInfos;
import com.kyokyu.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> findAllUser() {
        return userRepository.findAll();
    }


    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new Error("User by username " + username + " was not found"));
    }


    public User updateUserInfos(String id, UserInfos userInfos) {
        Optional<User> userData = userRepository.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            _user.setFirstname(userInfos.getFirstname());
            _user.setLastname(userInfos.getLastname());
            _user.setSexe(userInfos.getSexe());
            _user.setAddress(userInfos.getAddress());

            return userRepository.save(_user);
        }
        return null;
    }



}
