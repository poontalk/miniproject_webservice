package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.model.User;
import org.itsci.miniproject.response.LoginResponse;
import org.itsci.miniproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    @RequestMapping("/list")
    public ResponseEntity listLogin(){
        try {
            List<Login> logins = loginService.getAllLogins();
            return new ResponseEntity<>(logins,HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{loginId}")
    public  ResponseEntity getLoginById (@PathVariable("loginId")String loginId){
        try {
            Login login = loginService.getLoginById(loginId);
            return new ResponseEntity<>(login,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Login by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity addLogin(@RequestBody Map<String,String> map){
        try {
            Login login = loginService.saveLogin(map);
            return new ResponseEntity<>(login,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Add login",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateLogin(@RequestBody Login login){
        try {
            Login updatedLogin = loginService.updateLogin(login);
            return new ResponseEntity<>(updatedLogin,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{loginId}")
    public ResponseEntity deleteLogin (@PathVariable("loginId")String loginId){
        try {
            loginService.deleteLogin(loginId);
            return  new ResponseEntity<>("This" + loginId + "deleted" ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Delete User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbycontainname/{userName}")
    public ResponseEntity getUserByContainingName(@PathVariable("userName")String userName){
        try {
            List<Login> logins = loginService.getLoginsByUserNameContainingName(userName);
            return new ResponseEntity<>(logins, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by Name",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/loginUserName")
    public ResponseEntity<?> loginUser(@RequestBody Login login)
    {
        LoginResponse loginResponse = loginService.loginUser(login);
        return ResponseEntity.ok(loginResponse);
    }

    @PutMapping("/{loginId}/authority/{authorityId}")
    public Login assignAuthorityToLogin(@PathVariable Long loginId, @PathVariable Integer authorityId){
        return loginService.assignAuthorityToLogin(loginId,authorityId);
    }
}
