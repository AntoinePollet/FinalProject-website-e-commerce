package com.kyokyu.backend.controllers;

import com.kyokyu.backend.models.*;
import com.kyokyu.backend.payload.request.LoginRequest;
import com.kyokyu.backend.payload.request.SignupRequest;
import com.kyokyu.backend.payload.request.UserUpdateInfo;
import com.kyokyu.backend.payload.response.JwtResponse;
import com.kyokyu.backend.payload.response.MessageResponse;
import com.kyokyu.backend.repository.RoleRepository;
import com.kyokyu.backend.repository.ShoppingCartRepository;
import com.kyokyu.backend.repository.UserRepository;
import com.kyokyu.backend.security.jwt.JwtUtils;
import com.kyokyu.backend.security.services.UserDetailsImpl;
import com.kyokyu.backend.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
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

    Logger logger= LoggerFactory.getLogger(ArticleController.class);


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

        logger.info("user signedIn : {}", loginRequest.getUsername());
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


        List<String> articleS = new ArrayList() {{

        }};
        ShoppingCart shoppingCart = new ShoppingCart(articleS,new Date(),user.getUsername(),1.0);

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found ! Default User Role used."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        new RuntimeException("Error: Unauthorized role !");
                        break;
                    case "mod":
                        new RuntimeException("Error: Unauthorized role !");
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

        logger.info("new user registered successfully: {}", signUpRequest.getUsername());
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PutMapping("/changePassword")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserUpdateInfo userUpdateInfo) {

        Optional<User> userData = userRepository.findByUsername(userUpdateInfo.getUsername());

        if (userData.isPresent()) {
            // Create new user's account
            User userD = userData.get();
            userD.setPassword(encoder.encode(userUpdateInfo.getPassword()));

            userRepository.save(userD);
            logger.info("password reset: {}", userUpdateInfo.getUsername());
            return ResponseEntity.ok(new MessageResponse("Update registered successfully!"));
        }else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username doesn't exist!"));
        }
    }





}
