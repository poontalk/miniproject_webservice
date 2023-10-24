package org.itsci.miniproject.service;

import org.itsci.miniproject.model.Service;

import java.util.List;
import java.util.Map;

public interface ServicesService {
    List<Service> getAllServices();

    Service getServicebyId(String serviceId);

    Service saveService(Map<String,String> map);

    Service updateService(Service service);

    void deleteService(String serviceId);

    List<Service> getServicesByServiceNameContainingName(String serviceName);

    Service getServiceByName(String serviceName);
}
