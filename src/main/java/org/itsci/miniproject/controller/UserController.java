package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.User;
import org.itsci.miniproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity getListAllMembers(){
        try {
            List<User> users = userService.getAllUsers();
            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed List User!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/listfirstnameandlastname")
    public ResponseEntity getFirstNameAndLastName(){
        try {
            List<User> users = userService.getFirstNameandLastName();
            return new ResponseEntity<>(users, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed List barbers!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/getbyid/{userId}")
    public  ResponseEntity doProfileDetail(@PathVariable("userId")String userId){
        try {
            User user = userService.getUserbyId(userId);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity doRegister(@RequestBody Map<String,String> map){
        try {
            User user = userService.saveUser(map);
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Add User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateNoPassword")
    public ResponseEntity doEditProfileNoChangePassword (@RequestBody Map<String,String> map){
        try {
            userService.doEditProfile(map);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity doEditProfile (@RequestBody Map<String,String> map){
        try {
            User updatedUser = userService.updateUser(map);
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

        @GetMapping("/getuserbylogin/{loginId}")
    public ResponseEntity getUserByLoginId(@PathVariable("loginId")Long loginId){
        try {
            User user = userService.getUserByLoginId(loginId);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by LoginId",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("getRolebyrole/{role}/{role2}")
    public ResponseEntity getRoleByLoginId(@PathVariable("role")String role,@PathVariable("role2")String role2){
        try {
            List<User> users = userService.getUsersByRole(role,role2);
            return new ResponseEntity<>(users, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("failed to get Role",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
