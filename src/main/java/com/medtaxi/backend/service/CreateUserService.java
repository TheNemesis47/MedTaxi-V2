package com.medtaxi.backend.service;

import com.medtaxi.backend.exceptions.UserException;
import com.medtaxi.backend.model.User;
import com.medtaxi.backend.repo.UserRepo;
import com.medtaxi.backend.utils.Map;
import com.medtaxi.model.OperationDtoRequest;
import com.medtaxi.model.OperationDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CreateUserService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Map map;

    public OperationDtoResponse addUser(OperationDtoRequest operationDtoRequest) {
        OperationDtoResponse response = new OperationDtoResponse();

        // Mappatura dell'oggetto
        User user = map.map(operationDtoRequest);

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
}
