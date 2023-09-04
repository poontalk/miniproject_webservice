package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.User;
import org.itsci.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public ResponseEntity listUser(){
        try {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed List User!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/listfirstnameandlastname")
    public ResponseEntity getFirstNameandLastName(){
        try {
            List<User> users = userService.getFirstNameandLastName();
            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed List barbers!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getbyid/{userId}")
    public  ResponseEntity getUserById (@PathVariable("userId")String userId){
        try {
            User user = userService.getUserbyId(userId);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity addUser(@RequestBody Map<String,String> map){
        try {
            User user = userService.saveUser(map);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Add User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody User user){
        try {
            User updatedUser = userService.updateUser(user);
            return new ResponseEntity<>(updatedUser,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser (@PathVariable("userId")String userId){
        try {
            userService.deleteUser(userId);
            return  new ResponseEntity<>("This" + userId + "deleted" ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Delete User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbycontainname/{firstName}")
    public ResponseEntity getUserByContainingName(@PathVariable("firstName")String firstName){
        try {
            List<User> users = userService.getUsersByFirstNameContainingName(firstName);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by Name",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
