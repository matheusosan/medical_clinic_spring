package com.spring_app.demo.services;

import com.spring_app.demo.dtos.ServiceRequestDTO;
import com.spring_app.demo.entities.Service;
import com.spring_app.demo.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public List<Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Service createService(ServiceRequestDTO dto) {

        Service newService = new Service();

        newService.setName(dto.getName());
        newService.setPrice(dto.getPrice());

        return serviceRepository.save(newService);

    }
}
