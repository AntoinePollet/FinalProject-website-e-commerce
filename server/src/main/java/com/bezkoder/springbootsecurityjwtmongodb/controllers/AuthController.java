package com.bezkoder.springbootsecurityjwtmongodb.controllers;

import java.util.*;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.bezkoder.springbootsecurityjwtmongodb.models.*;
import com.bezkoder.springbootsecurityjwtmongodb.payload.request.UserUpdateInfo;
import com.bezkoder.springbootsecurityjwtmongodb.repository.ShoppingCartRepository;
import com.bezkoder.springbootsecurityjwtmongodb.service.ArticleService;
import com.bezkoder.springbootsecurityjwtmongodb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.springbootsecurityjwtmongodb.payload.request.LoginRequest;
import com.bezkoder.springbootsecurityjwtmongodb.payload.request.SignupRequest;
import com.bezkoder.springbootsecurityjwtmongodb.payload.response.JwtResponse;
import com.bezkoder.springbootsecurityjwtmongodb.payload.response.MessageResponse;
import com.bezkoder.springbootsecurityjwtmongodb.repository.RoleRepository;
import com.bezkoder.springbootsecurityjwtmongodb.repository.UserRepository;
import com.bezkoder.springbootsecurityjwtmongodb.security.jwt.JwtUtils;
import com.bezkoder.springbootsecurityjwtmongodb.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {


        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

/*
        List<String> pictures = new ArrayList() {{
            add("null");
        }};

        List<String> color = new ArrayList() {{
            add("blue");
        }};

        Article test = new Article("testName", "testDescription", pictures, "testCategory", color,999, 4.0, true, true);
        List<Article> articles = new ArrayList() {{
            add(test);
        }};*/

        List<Article> article = null;
        List<String> articleS = new ArrayList() {{

        }};
        ShoppingCart shoppingCart = new ShoppingCart(articleS,new Date(),user.getUsername(),1.0);



        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        shoppingCartRepository.save(shoppingCart);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
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

    @GetMapping("/find/{username}")
    public ResponseEntity<User> getUserByUsername (@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }



}
