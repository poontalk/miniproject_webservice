package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.Authority;
import org.itsci.miniproject.model.Service;
import org.itsci.miniproject.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {
    @Autowired
    private AuthorityService authorityService;

    @RequestMapping("/list")
    public ResponseEntity listAuthority(){
        try {
            List<Authority> authorities = authorityService.getAllAuthority();
            return new ResponseEntity<>(authorities, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed List Authority!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{authorityId}")
    public  ResponseEntity getAuthorityById (@PathVariable("authorityId")String authorityId){
        try {
            Authority authority = authorityService.getAuthorityById(authorityId);
            return new ResponseEntity<>(authority,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get authority by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/add")
    public ResponseEntity addAuthority(@RequestBody Map<String,String> map){
        try {
            Authority authority = authorityService.saveAuthority(map);
            return new ResponseEntity<>(authority,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Add Authority",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateAuthority(@RequestBody Authority authority){
        try {
            Authority updatedAuthority = authorityService.updateAuthority(authority);
            return new ResponseEntity<>(updatedAuthority,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update Authority",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{authorityId}")
    public ResponseEntity deleteAuthority (@PathVariable("authorityId")String authorityId){
        try {
            authorityService.deleteAuthority(authorityId);
            return  new ResponseEntity<>("This" + authorityId + "deleted" ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Delete Authority",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbycontainname/{role}")
    public ResponseEntity getAuthorityByContainingName(@PathVariable("role")String role){
        try {
            List<Authority> authorities = authorityService.getAuthoritiesByRoleContainingName(role);
            return new ResponseEntity<>(authorities, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Authority by role",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
