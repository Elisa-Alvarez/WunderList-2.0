package com.todolist.wunderlist2.controllers;


import com.todolist.wunderlist2.model.User;
import com.todolist.wunderlist2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    //http://localhost:2020/users/all
    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> locateAllUsers() {
        List<User> totalUsers = userService.findEveryUser();

        return new ResponseEntity<>(totalUsers, HttpStatus.OK);
    }

    //http://localhost:2020/users/:userid

    @GetMapping(value = "user/{userid}", produces = "application/json")
    public ResponseEntity<?> findUser(@PathVariable long userid) {
        User foundUser = userService.findUser(userid);

        return new ResponseEntity<>(foundUser, HttpStatus.OK);
    }

    // http://localhost:2020/users/search/:letter  will bring up any user containing that letter
    @GetMapping(value = "search/{letter}", produces = "application/json")
    public ResponseEntity<?> findnamelikeUser (@PathVariable String letter)
    {
        List<User> name = userService.findnamelikeUser(letter);
        return new ResponseEntity<>(name,HttpStatus.OK);
    }

    // http://localhost:2020/users/:username   must type in exsact username

    @GetMapping(value = "/{username}", produces = "application/json")
    public ResponseEntity<?> findUserByUsername(@PathVariable String username) {
        User u = userService.findUserByUsername(username);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }


    //http://localhost:2020/users/sign-up

    @PostMapping(value = "/sign-up", consumes = "application/json")
    public ResponseEntity<?> createNewUser(@Valid @RequestBody User addeduser) throws URISyntaxException {
        addeduser.setUserid(0);
        addeduser = userService.save(addeduser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userid}")
                .buildAndExpand(addeduser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    //http://localhost:2020/useers/update/:userid

    @PutMapping(value = "/update/{userid}", consumes = "application/json")
    public ResponseEntity<?> updateUser(@Valid @RequestBody User saveupdateduser, @PathVariable long userid) {
        saveupdateduser.setUserid(userid);
        userService.save(saveupdateduser);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // http://localhost:2020/users/userpatch/:userid

    @PatchMapping(value = "/userpatch/{userid}", consumes = "application/json")
    public ResponseEntity<?> updateUserField(@RequestBody User userfeildupdate, @PathVariable long userid) {
        userService.update(userfeildupdate, userid);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // http://localhost:5280/users/:userid

    @DeleteMapping(value = "/{userid}")
    public ResponseEntity<?> deleteUser(@PathVariable long userid) {
        userService.delete(userid);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
