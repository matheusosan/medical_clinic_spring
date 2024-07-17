package com.spring_app.demo.dtos;

import java.time.Instant;

public class AppointmentRequestDTO {

    Instant dataAgendada;
    Long serviceId;
    Long clientId;

    public Instant getDataAgendada() {
        return dataAgendada;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public Long getClientId() {
        return clientId;
    }
}
