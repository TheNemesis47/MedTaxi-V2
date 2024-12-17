package com.medtaxi.backend.controller;

import com.medtaxi.api.AmbulanceApi;
import com.medtaxi.api.CompaniesApi;
import com.medtaxi.api.UserApi;
import com.medtaxi.backend.command.CreateUserCommand;
import com.medtaxi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MedTaxiController implements AmbulanceApi, UserApi, CompaniesApi {

    private final CreateUserCommand createUserCommand;

    @Autowired
    public MedTaxiController(CreateUserCommand createUserCommand) {
        this.createUserCommand = createUserCommand;
    }

    @Override
    public ResponseEntity<OperationDtoResponse> createUser(OperationDtoRequest operationDtoRequest) {
        OperationDtoResponse response = createUserCommand.execute(operationDtoRequest);
        return ResponseEntity.status(201).body(response);
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return UserApi.super.getAllUsers();
    }

    @Override
    public ResponseEntity<User> getUserById(Integer userId) {
        return UserApi.super.getUserById(userId);
    }

    @Override
    public ResponseEntity<OperationDtoResponse> updateUser(Integer userId, OperationDtoRequest operationDtoRequest) {
        return UserApi.super.updateUser(userId, operationDtoRequest);
    }

    @Override
    public ResponseEntity<OperationDtoResponse> createCompany(OperationDtoRequest operationDtoRequest) {
        return CompaniesApi.super.createCompany(operationDtoRequest);
    }

    @Override
    public ResponseEntity<List<Company>> getCompanies(String filter) {
        return CompaniesApi.super.getCompanies(filter);
    }

    @Override
    public ResponseEntity<OperationDtoResponse> createAmbulance(OperationDtoRequest operationDtoRequest) {
        return AmbulanceApi.super.createAmbulance(operationDtoRequest);
    }

    @Override
    public ResponseEntity<List<Ambulance>> getAmbulances(String filter) {
        return AmbulanceApi.super.getAmbulances(filter);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return AmbulanceApi.super.getRequest();
    }
}