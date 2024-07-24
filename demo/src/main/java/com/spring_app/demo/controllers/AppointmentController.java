package com.spring_app.demo.controllers;

import com.spring_app.demo.dtos.AppointmentRequestDTO;
import com.spring_app.demo.entities.Appointment;
import com.spring_app.demo.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/appointment")
@CrossOrigin(value = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping()
    List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping()
    ResponseEntity<String> createAppointment(@RequestBody AppointmentRequestDTO dto) {
        Appointment newAppointment = appointmentService.createAppointment(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAppointment).toUri();

        return ResponseEntity.created(location).body("Agendamento realizado!");

    }
}
