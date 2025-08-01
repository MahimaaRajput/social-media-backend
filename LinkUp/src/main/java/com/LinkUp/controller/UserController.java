package com.LinkUp.controller;

import com.LinkUp.Exceptions.UserException;
import com.LinkUp.model.User;
import com.LinkUp.repository.UserRepository;
import com.LinkUp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class UserController {


    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;


    @GetMapping("/api/allusers")
    public List<User> getusers() {
        return userRepository.findAll();
    }

    @GetMapping("/api/user/id/{myid}")
    public User getUserbyId(@PathVariable Integer myid) throws Exception {
        return userService.findUserById(myid);
    }

    @GetMapping("/api/user/email/{email}")
    public User getUserByEmail(@PathVariable String email) throws UserException {
        return userService.findUserByEmail(email);
    }

    @PutMapping("/api/user")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User updateduser) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.updateUser(reqUser.getId(), updateduser);
    }

    @PutMapping("/api/users/follow/{followingId}")
    public User followUser(@RequestHeader("Authorization") String jwt, @PathVariable Integer followingId) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
        return userService.followUser(reqUser.getId(), followingId);
    }

    @GetMapping("user/search")
    public List<User> searchUser(@RequestParam("query") String query) {

        return userService.searchUser(query);
    }

    @DeleteMapping("api/user/delete")
    public String deleteUser(@RequestHeader("Authorization") String jwt) throws UserException {
        User reqUser = userService.findUserByJwt(jwt);
          return userService.deleteUser(reqUser.getId());

    }

    @GetMapping("/api/users/profile")
    public User getUserFromToken(@RequestHeader("Authorization") String jwt) {
        System.out.println("jwt--------" + jwt);
        User user = userService.findUserByJwt(jwt);
        user.setPassword(null);
        return user;

    }
}


