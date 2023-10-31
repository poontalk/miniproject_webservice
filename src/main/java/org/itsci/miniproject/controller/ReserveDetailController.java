package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.*;
import org.itsci.miniproject.service.ReserveDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reserveDetail")
public class ReserveDetailController {
    @Autowired
    private ReserveDetailService reserveDetailService;

    @RequestMapping("/list")
    public ResponseEntity listReserveDetail(){
        try {
            List<ReserveDetail> reserveDetails = reserveDetailService.getAllData();
            return new ResponseEntity<>(reserveDetails, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{reserveDetailId}")
    public  ResponseEntity getReserveDetailById (@PathVariable("reserveDetailId")String reserveDetailId){
        try {
            ReserveDetail reserveDetails = reserveDetailService.getReserveDetailById(reserveDetailId);
            return new ResponseEntity<>(reserveDetails,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get ReserveDetail by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity doReserveService (@RequestBody Map<String,String> map){
        try {
            ReserveDetail reserveDetails = reserveDetailService.saveReserveDetail(map);
            return new ResponseEntity<>(reserveDetails,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Add ReserveDetail",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateConfirmPayment(@RequestBody ReserveDetail reserveDetail){
        try {
            ReserveDetail updatedReserveDetail = reserveDetailService.updateReserveDetail(reserveDetail);
            return new ResponseEntity<>(updatedReserveDetail,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update ReserveDetail",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{reserveDetailId}")
    public ResponseEntity deleteReserve (@PathVariable("reserveDetailId")String reserveDetailId){
        try {
            reserveDetailService.deleteReserveDetail(reserveDetailId);
            return  new ResponseEntity<>("This" + reserveDetailId + "deleted" ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Delete ReserveDetail",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyreserveId/{reserveId}")
    public  ResponseEntity getReserveDetailByReserveId (@PathVariable("reserveId")String reserveId){
        try {
            List<ReserveDetail> reserveDetails = reserveDetailService.findReserveDetailByReserveId(reserveId);
            return new ResponseEntity<>(reserveDetails,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get ReserveDetail by reserve Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/listreservedetailbystatus")
    public ResponseEntity listReserveDetailByStatus(){
        try {
            List<ReserveDetail> reserveDetails = reserveDetailService.findReserveDetailByStatus();
            return new ResponseEntity<>(reserveDetails, HttpStatus.OK);
        }catch (Exception e){

            e.printStackTrace();
            return new ResponseEntity<>("Failed get List", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
