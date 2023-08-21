package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Service;
import org.itsci.miniproject.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Service
public class ServicesServiceImpl implements ServicesService{

    @Autowired
    private ServiceRepository serviceRepository;
    @Override
    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    @Override
    public Service getServicebyId(String serviceId) {
        return serviceRepository.getReferenceById(serviceId);
    }

    @Override
    public Service saveService(Map<String, String> map) {
        String serviceId = generateServiceId();
        String serviceName = map.get("serviceName");
        String price = map.get("price");
        String timespend = map.get("timespend");

        Service service = new Service(serviceId,serviceName,Double.parseDouble(price),Integer.parseInt(timespend));
        return serviceRepository.save(service);
    }

    @Override
    public Service updateService(Service service) {
        return serviceRepository.save(service);
    }

    @Override
    public void deleteService(String serviceId) {
        Service service = serviceRepository.getReferenceById(serviceId);
        serviceRepository.delete(service);
    }

    @Override
    public List<Service> getServicesByServiceNameContainingName(String serviceName) {
        return serviceRepository.getServicesByServiceNameContainingIgnoreCase(serviceName);
    }

    public long getAccountCount(){
        try{
            return serviceRepository.count();
        }catch (Exception e){
            return 0;
        }
    }

    public String generateServiceId(){
        String result = "" + (getAccountCount() + 1);
        while (result.length() < 4){
            result = "0" + result;
        }
        result = "S"+ result;
        return result;
    }
}
