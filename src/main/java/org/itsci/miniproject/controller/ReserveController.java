package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.Login;
import org.itsci.miniproject.model.Reserve;
import org.itsci.miniproject.service.ReserveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reserve")
public class ReserveController {
    @Autowired
    private ReserveService reserveService;

    @RequestMapping("/list")
    public ResponseEntity listReserve(){
        try {
            List<Reserve> reserves = reserveService.getAllReserves();
            return new ResponseEntity<>(reserves, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{reserveId}")
    public  ResponseEntity getReserveById (@PathVariable("reserveId")String reserveId){
        try {
            Reserve reserve = reserveService.getReserveById(reserveId);
            return new ResponseEntity<>(reserve,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Reserve by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity doReserveService (@RequestBody Map<String,String> map){
        try {
            Reserve reserve = reserveService.saveReserve(map);
            return new ResponseEntity<>(reserve,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Add Reserve Service",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateConfirmPayment(@RequestBody Reserve reserve){
        try {
            Reserve updatedReserve = reserveService.updateReserve(reserve);
            return new ResponseEntity<>(updatedReserve,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update Reserve",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{reserveId}")
    public ResponseEntity deleteReserve (@PathVariable("reserveId")String reserveId){
        try {
            reserveService.deleteReserve(reserveId);
            return  new ResponseEntity<>("This" + reserveId + "deleted" ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Delete Reserve",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyReserve/{customerId}")
    public  ResponseEntity getReserveByCustomer ( @PathVariable("customerId")String customerId){
        try {
            List<Reserve> reserve = reserveService.findReserveByStatusAndCustomerId(customerId);
            return new ResponseEntity<>(reserve,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Reserve by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listforbarber")
    public ResponseEntity listReserveForBarber(){
        try {
            List<Reserve> reserves = reserveService.findReserveForBarber();
            return new ResponseEntity<>(reserves, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listforcustomer/{customerId}")
    public ResponseEntity listReserveForBarber(@PathVariable("customerId") String customerId){
        try {
            List<Reserve> reserves = reserveService.getReserveByCustomerId(customerId);
            return new ResponseEntity<>(reserves, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getReceipt/{receiptId}")
    public ResponseEntity getReceipt(@PathVariable("receiptId") String receiptId){
        try {
            Reserve reserves = reserveService.getReceipt(receiptId);
            return new ResponseEntity<>(reserves, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/confirmpayment/{reserveId}")
    public ResponseEntity doConfirmPayment(@PathVariable("reserveId") String reserveId){
        try {
            Reserve updatedReserve = reserveService.updateConfirmPayment(reserveId);
            return new ResponseEntity<>(updatedReserve,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update Reserve",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/canceljob/{reserveId}")
    public ResponseEntity cancelJob(@PathVariable("reserveId") String reserveId){
        try {
            Reserve updatedReserve = reserveService.cancelJob(reserveId);
            return new ResponseEntity<>(updatedReserve,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update Reserve",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
