package com.medtaxi.backend.service;

import com.medtaxi.backend.exceptions.UserException;
import com.medtaxi.backend.model.User;
import com.medtaxi.backend.repo.UserRepo;
import com.medtaxi.backend.utils.Map;
import com.medtaxi.model.OperationDtoRequest;
import com.medtaxi.model.OperationDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateUserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private Map map;

    public OperationDtoResponse update(Integer userId, OperationDtoRequest operationDtoRequest) {
        Optional<User> userOpt = userRepo.findById((long) userId);
        User newUser = map.map(operationDtoRequest);

        if (userOpt.isPresent()) {
            userRepo.save(newUser);
        } else {
            throw new UserException("User not found during update");
        }
        OperationDtoResponse operationDtoResponse = new OperationDtoResponse();
        operationDtoResponse.setSuccess(Boolean.TRUE);
        operationDtoResponse.setMessage("User updated successfully");
        return operationDtoResponse;
    }

    public OperationDtoResponse delete(Integer userId, OperationDtoRequest operationDtoRequest) {
        Optional<User> userOpt = userRepo.findById((long) userId);
        if (userOpt.isPresent()) {
            User userDeleted = userOpt.get();
            userRepo.delete(userDeleted);
        } else {
            throw new UserException("User not found during delete");
        }
        OperationDtoResponse operationDtoResponse = new OperationDtoResponse();
        operationDtoResponse.setSuccess(Boolean.TRUE);
        operationDtoResponse.setMessage("User deleted successfully");
        return operationDtoResponse;
    }
}
