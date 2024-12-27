package com.medtaxi.backend.utils;

import com.medtaxi.model.OperationDtoRequest;
import com.medtaxi.model.User;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class Map {

    public User map(com.medtaxi.backend.model.User user) {
        User r = new User();
        r.setId(user.getId());
        r.setName(user.getName());
        r.setSurname(user.getSurname());
        r.setEmail(user.getEmail());
        r.setDate(user.getBirthDate());
        r.setPhone(user.getPhoneNumber());
        r.setAddress(user.getAddress());
        r.setCity(user.getCity());
        return r;
    }

    public com.medtaxi.backend.model.User map(OperationDtoRequest r) {
        com.medtaxi.backend.model.User user = new com.medtaxi.backend.model.User();
        user.setId(r.getUserId());
        user.setName(r.getAdditionalInfo().get("name"));
        user.setSurname(r.getAdditionalInfo().get("surname"));
        user.setEmail(r.getAdditionalInfo().get("email"));
        user.setBirthDate(LocalDate.parse(r.getAdditionalInfo().get("birthdate")));
        user.setPhoneNumber(r.getAdditionalInfo().get("phoneNumber"));
        user.setAddress(r.getAdditionalInfo().get("address"));
        user.setCity(r.getAdditionalInfo().get("city"));
        user.setPassword(r.getAdditionalInfo().get("password"));
        return user;
    }
}
