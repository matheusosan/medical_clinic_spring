package com.spring_app.demo.services;

import com.spring_app.demo.dtos.AppointmentRequestDTO;
import com.spring_app.demo.entities.Appointment;
import com.spring_app.demo.entities.Client;
import com.spring_app.demo.entities.Service;
import com.spring_app.demo.exceptions.ScheduleExceptions.OutOfWorkingPeriodException;
import com.spring_app.demo.repositories.AppointmentRepository;
import com.spring_app.demo.repositories.ClientRepository;
import com.spring_app.demo.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;

import com.spring_app.demo.utils.BusinessHoursUtil;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ServiceService serviceService;


    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(AppointmentRequestDTO dto) {
        boolean isOpeningHours = BusinessHoursUtil.isOpeningHours(dto.getDataAgendada());

        String dayOfWeek = BusinessHoursUtil.getDayOfWeek(dto.getDataAgendada());

        if(dayOfWeek.equals("SUNDAY")) {
            throw new OutOfWorkingPeriodException("A data agendada deve ser de segunda a sábado.");
        }

        if(!isOpeningHours) {
            throw new OutOfWorkingPeriodException("Horário agendado está fora do horário de funcionamento!");
        }

        Client client = clientService.findById(dto.getClientId());
        Service service = serviceService.findById(dto.getServiceId());

        Appointment newAppointment = new Appointment();
        newAppointment.setService(service);
        newAppointment.setClient(client);
        newAppointment.setDataAgendada(dto.getDataAgendada());

       return appointmentRepository.save(newAppointment);
    }

}
