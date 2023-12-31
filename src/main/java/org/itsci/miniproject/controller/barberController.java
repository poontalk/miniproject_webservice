package org.itsci.miniproject.controller;

import jakarta.persistence.Tuple;
import jakarta.persistence.TupleElement;
import org.itsci.miniproject.model.*;
import org.itsci.miniproject.service.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/barber")
public class barberController {
    @Autowired
    private BarberService barberService;

    @RequestMapping("/list")
    public ResponseEntity listAllMembers() {
        try {
            List<Barber> barbers = barberService.getAllBarber();
            return new ResponseEntity<>(barbers, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed List barbers!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{barberId}")
    public ResponseEntity getBarber(@PathVariable("barberId") String barberId) {
        try {
            Barber barber = barberService.getBarberById(barberId);
            return new ResponseEntity<>(barber, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity addBarber(@RequestBody Map<String, String> map) {
        try {
            Barber barber = barberService.saveBarber(map);
            return new ResponseEntity<>(barber, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed Add Barber", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity doEditBarber(@RequestBody Barber barber) {
        try {
            Barber updatedBarber = barberService.updateBarber(barber);
            return new ResponseEntity<>(updatedBarber, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed update Barber", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{barberId}")
    public ResponseEntity deleteBarber(@PathVariable("barberId") String barberId) {
        try {
            barberService.deleteByTableId(barberId);
            return new ResponseEntity<>("This " + barberId + " deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed Delete Barber", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAuthority/{barberId}")
    public ResponseEntity deleteAuthorityLogin(@PathVariable("barberId") String barberId) {
        try {
            barberService.deleteAuthorityLoginById(barberId);
            return new ResponseEntity<>("This " + barberId + " deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed Delete Barber", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getcountbarber")
    public ResponseEntity getCountBarber() {
        try {
            Integer barber = Math.toIntExact(barberService.getBarberCount());
            return new ResponseEntity<>(barber, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findAvailableBarbers/{localtime}")
    public ResponseEntity getAvailableBarbers(@PathVariable("localtime") LocalDateTime localtime) {
        try {
            List<Barber> barbers = barberService.findAvailableBarbers(localtime);
            return new ResponseEntity<>(barbers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyUserid/{userId}")
    public ResponseEntity getBarberByUserId(@PathVariable("userId") String userId) {
        try {
            Barber barber = barberService.getBarberByUserId(userId);
            return new ResponseEntity<>(barber, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get User by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
