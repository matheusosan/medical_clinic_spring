package com.spring_app.demo.dtos;

import java.math.BigDecimal;

public class ServiceRequestDTO {

    String name;
    BigDecimal price;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
