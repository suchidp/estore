package com.estore.controller;

import com.estore.jwtutil.JwtTokenUtil;
import com.estore.model.User;
import com.estore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;




@Slf4j
@RestController
public class UserController {

    @Autowired
    AuthenticationManager authManager;
    @Autowired
    JwtTokenUtil jwtUtil;
    @Autowired
    private UserService service;

    /*  To Register new USER
     */
    @PostMapping("/register")
    public User saveUser(@RequestBody User user){

        return service.save(user);
    }



    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable int id) {
        User user = service.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}