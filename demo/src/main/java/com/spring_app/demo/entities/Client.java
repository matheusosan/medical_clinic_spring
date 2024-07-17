package com.spring_app.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "tb_name")
    String name;

    @Column(name = "email")
    String email;

    @Column(name = "tb_phone_number")
    String phoneNumber;

    @Column(name = "tb_cpf", unique = true)
    String cpf;

    public Client(String cpf, String phone_number, String email, String name) {
        this.cpf = cpf;
        this.phoneNumber = phone_number;
        this.email = email;
        this.name = name;
    }

    public Client() {
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static class Builder {
        private String name;
        private String email;
        private String phoneNumber;
        private String cpf;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withCpf(String cpf) {
            this.cpf = cpf;
            return this;
        }

        public Client build() {
            Client client = new Client();
            client.setName(this.name);
            client.setEmail(this.email);
            client.setPhoneNumber(this.phoneNumber);
            client.setCpf(this.cpf);
            return client;
        }
    }
}
