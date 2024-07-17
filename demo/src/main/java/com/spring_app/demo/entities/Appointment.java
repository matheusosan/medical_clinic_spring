package com.spring_app.demo.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "tb_appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "tb_data_agendada")
    Instant dataAgendada;

    @ManyToOne()
    @JoinColumn(name = "service_id")
    Service service;

    @ManyToOne
    @JoinColumn(name = "client_id")
    Client client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDataAgendada() {
        return dataAgendada;
    }

    public void setDataAgendada(Instant dataAgendada) {
        this.dataAgendada = dataAgendada;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
