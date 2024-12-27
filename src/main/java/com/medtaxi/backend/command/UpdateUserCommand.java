package com.medtaxi.backend.command;

import com.medtaxi.backend.service.UpdateUserService;
import com.medtaxi.model.OperationDtoRequest;
import com.medtaxi.model.OperationDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserCommand {

    @Autowired
    private UpdateUserService updateUserService;

    public OperationDtoResponse execute(Integer userId, OperationDtoRequest operationDtoRequest) {
        String operation = operationDtoRequest.getOperationType();
        OperationDtoResponse response = new OperationDtoResponse();
        switch (operation) {
            case "UPDATE":
                response = updateUserService.update(userId, operationDtoRequest);
                break;
            case "DELETE":
                response = updateUserService.delete(userId, operationDtoRequest);
                break;
            default:
                throw new RuntimeException("OperationType error");
        }
        return response;
    }
}
