package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.model.Owner;
import org.itsci.miniproject.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/owner")
public class OwnerController {
    @Autowired
    private OwnerService ownerService;

    @RequestMapping("/list")
    public ResponseEntity getListOwners(){
        try {
            List<Owner> owners = ownerService.getListOwner();
            return new ResponseEntity<>(owners, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateLogin(@RequestBody Map<String,String> map){
        try {
            Owner updatedOwner = ownerService.editShopProfile(map);
            return new ResponseEntity<>(updatedOwner,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/showShopProfile/{ownerId}")
    public ResponseEntity showShopProfile(@PathVariable("ownerId")String ownerId){
        try {
            Owner owners = ownerService.showShopProfile(ownerId);
            return new ResponseEntity<>(owners, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/addweekend")
    public ResponseEntity addWeekend(@RequestBody String weekend){
        try {
            Owner updatedOwner = ownerService.addWeekend(weekend);
            return new ResponseEntity<>(updatedOwner,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update User",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
