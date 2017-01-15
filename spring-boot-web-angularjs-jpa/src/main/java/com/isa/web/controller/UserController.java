package com.isa.web.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.isa.web.data.model.User;
import com.isa.web.exception.CustomErrorType;
import com.isa.web.service.UserService;
 
@RestController
@RequestMapping(path = "/user")
public class UserController {
 
    public static final Logger LOG = LoggerFactory.getLogger(UserController.class);
 
    @Autowired
    UserService userService; 
 
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findAllUsers();
        if (users.isEmpty()) {
        	return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.ok(users);
    }
 
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        LOG.info("Fetching User with id {}", id);
        User user = userService.findById(id);
		if (user == null) {
			LOG.error("User with id {} not found.", id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new CustomErrorType("User with id " + id + " not found"));
		}
        
        return ResponseEntity.ok(user);
    }
 
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        LOG.info("Creating User : {}", user);
		if (userService.isUserExist(user)) {
			LOG.error("Unable to create. A User with name {} already exist", user.getUsername());
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(new CustomErrorType("Unable to create. A User with name " + user.getUsername() + " already exist."));
		}
		
        userService.saveUser(user);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
        return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
    }
 
    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        LOG.info("Updating User with id {}", id);
        User currentUser = userService.findById(id);
        if (currentUser == null) {
            LOG.error("Unable to update. User with id {} not found.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body(new CustomErrorType("Unable to upate. User with id " + id + " not found."));
        }
 
        currentUser.setUsername(user.getUsername());
        currentUser.setAddress(user.getAddress());
        currentUser.setEmail(user.getEmail());
 
        userService.updateUser(currentUser);
        return ResponseEntity.ok(currentUser);
    }
 
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        LOG.info("Fetching & Deleting User with id {}", id);
 
        User user = userService.findById(id);
        if (user == null) {
            LOG.error("Unable to delete. User with id {} not found.", id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            		.body(new CustomErrorType("Unable to delete. User with id " + id + " not found."));
        }
        
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
 
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        LOG.info("Deleting All Users");
 
        userService.deleteAllUsers();
        return ResponseEntity.noContent().build();
    }
}