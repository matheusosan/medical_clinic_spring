package com.spring_app.demo.dtos;

import java.time.LocalDate;

public class ClientRequestDTO {

    String name;
    String cpf;
    String email;
    String phoneNumber;
    LocalDate birthDate;

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}
