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
    private ServiceRepository serviceRepository;
    @Autowired
    private ClientRepository clientRepository;


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

        Optional<Client> client = clientRepository.findById(dto.getClientId());
        Optional<Service> service = serviceRepository.findById(dto.getServiceId());

        if(client.isEmpty() || service.isEmpty()) {
            return null;
        }

        Appointment newAppointment = new Appointment();
        newAppointment.setService(service.get());
        newAppointment.setClient(client.get());
        newAppointment.setDataAgendada(dto.getDataAgendada());

       return appointmentRepository.save(newAppointment);
    }

}
