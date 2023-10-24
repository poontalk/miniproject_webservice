package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.Customer;
import org.itsci.miniproject.model.User;
import org.itsci.miniproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @RequestMapping("/list")
    public ResponseEntity listUser(){
        try {
            List<Customer> customers = customerService.getAllCustomers();
            return new ResponseEntity<>(customers, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed List User!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{customerId}")
    public  ResponseEntity getCustomerById (@PathVariable("customerId")String customerId){
        try {
            Customer customer = customerService.getCustomerById(customerId);
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Customer by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity addCustomer(@RequestBody Map<String,String> map){
        try {
            Customer customer = customerService.saveCustomer(map);
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Add Customer",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity deleteCustomer (@PathVariable("customerId")String customerId){
        try {
            customerService.deleteCustomer(customerId);
            return  new ResponseEntity<>("This" + customerId + "deleted" , HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Delete Customer",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyuserid/{userId}")
    public  ResponseEntity getCustomerByUserId (@PathVariable("userId")String userId){
        try {
            Customer customer = customerService.getCustomerByUserId(userId);
            return new ResponseEntity<>(customer,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Customer by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
