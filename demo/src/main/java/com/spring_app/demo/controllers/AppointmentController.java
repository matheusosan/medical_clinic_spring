package com.spring_app.demo.controllers;

import com.spring_app.demo.dtos.AppointmentRequestDTO;
import com.spring_app.demo.entities.Appointment;
import com.spring_app.demo.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping()
    List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    @PostMapping()
    Appointment createAppointment(@RequestBody AppointmentRequestDTO dto) {
        return appointmentService.createAppointment(dto);
    }
}
