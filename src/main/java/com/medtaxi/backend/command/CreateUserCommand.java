package com.medtaxi.backend.command;

import com.medtaxi.backend.enums.OperationTypeEnum;
import com.medtaxi.backend.exceptions.UserException;
import com.medtaxi.backend.service.CreateUserService;
import com.medtaxi.model.OperationDtoRequest;
import com.medtaxi.model.OperationDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateUserCommand {

    @Autowired
    private CreateUserService createUserService;

    public OperationDtoResponse execute(OperationDtoRequest operationDtoRequest) {
        // Verifica se operationType è nullo o non valido
        if (operationDtoRequest.getOperationType() == null) {
            throw new UserException("Invalid or missing operation type: " + operationDtoRequest.getOperationType());
        }

        // Converte operationType e verifica se è CREATE
        OperationTypeEnum operationType = OperationTypeEnum.valueOf(operationDtoRequest.getOperationType().toUpperCase());
        if (operationType != OperationTypeEnum.CREATE) {
            throw new UserException("Operation type not supported: " + operationType);
        }

        // Se l'operazione è valida, delega al service
        return createUserService.addUser(operationDtoRequest);
    }
}
