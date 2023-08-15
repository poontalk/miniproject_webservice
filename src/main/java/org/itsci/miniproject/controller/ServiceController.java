package org.itsci.miniproject.controller;

import org.itsci.miniproject.model.Service;
import org.itsci.miniproject.model.User;
import org.itsci.miniproject.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/service")
public class ServiceController {
    @Autowired
    private ServicesService servicesService;

    @RequestMapping("/list")
    public ResponseEntity listService(){
        try {
            List<Service> services = servicesService.getAllServices();
            return new ResponseEntity<>(services, HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed List service!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbyid/{serviceId}")
    public  ResponseEntity getServiceById (@PathVariable("serviceId")String serviceId){
        try {
            Service service = servicesService.getServicebyId(serviceId);
            return new ResponseEntity<>(service,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get service by Id ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/add")
    public ResponseEntity addService(@RequestBody Map<String,String> map){
        try {
            Service service = servicesService.saveService(map);
            return new ResponseEntity<>(service,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Add Service",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateService(@RequestBody Service service){
        try {
            Service updatedService = servicesService.updateService(service);
            return new ResponseEntity<>(updatedService,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed update Service",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{serviceId}")
    public ResponseEntity deleteService (@PathVariable("serviceId")String serviceId){
        try {
            servicesService.deleteService(serviceId);
            return  new ResponseEntity<>("This" + serviceId + "deleted" ,HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return  new ResponseEntity<>("Failed Delete Service",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getbycontainname/{serviceName}")
    public ResponseEntity getServiceByContainingName(@PathVariable("serviceName")String serviceName){
        try {
            List<Service> services = servicesService.getServicesByServiceNameContainingName(serviceName);
            return new ResponseEntity<>(services, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("Failed to get Service by Name",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}