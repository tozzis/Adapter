package com.sit.cloudnative.UserService.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  // show users all
  @GetMapping("/user")
  public ResponseEntity<List<User>> getAllUser() {
    List<User> user = userService.getAllUser();
    return new ResponseEntity<List<User>>(user,HttpStatus.OK);
  }

  // show user by id
  @GetMapping("/user/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
      User user = userService.getUserById(id);
    return new ResponseEntity<User>(user,HttpStatus.OK);
  }

  // new user
  @PostMapping("/user")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    userService.createUser(user);
    return new ResponseEntity<User>(user,HttpStatus.OK);
  }

  // delete user by user_id
  @DeleteMapping("/user/{id}")
  public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return new ResponseEntity<Long>(id,HttpStatus.OK);
  }
 
}