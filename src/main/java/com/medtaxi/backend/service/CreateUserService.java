package com.medtaxi.backend.service;

import com.medtaxi.backend.exceptions.UserException;
import com.medtaxi.backend.model.User;
import com.medtaxi.backend.repo.UserRepo;
import com.medtaxi.model.OperationDtoRequest;
import com.medtaxi.model.OperationDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateUserService {
    @Autowired
    UserRepo userRepo;

    public OperationDtoResponse addUser(OperationDtoRequest operationDtoRequest) {
        OperationDtoResponse response = new OperationDtoResponse();

        // Mappatura dell'oggetto
        User user = map(operationDtoRequest);

        // Controllo condizionale sull'ID
        if (user.getId() != null && userRepo.existsById(user.getId())) {
            throw new UserException("User already exists with ID: " + user.getId());
        }

        // Salvataggio dell'utente
        User saved = userRepo.save(user);

        // Preparazione della risposta
        response.setSuccess(Boolean.TRUE);
        response.setMessage("User created successfully with id: " + saved.getId());
        return response;
    }

    private User map(OperationDtoRequest r) {
        User user = new User();
        user.setId(r.getUserId().get().longValue());
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
