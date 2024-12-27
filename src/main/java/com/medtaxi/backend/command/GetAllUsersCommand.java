package com.medtaxi.backend.command;

import com.medtaxi.backend.service.GetAllUsersService;
import com.medtaxi.backend.utils.Map;
import com.medtaxi.model.OperationDtoRequest;
import com.medtaxi.model.OperationDtoResponse;
import com.medtaxi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GetAllUsersCommand {
    private final GetAllUsersService getAllUsersService;
    private final Map map;

    @Autowired
    public GetAllUsersCommand(GetAllUsersService getAllUsersService,
                              Map map) {
        this.getAllUsersService = getAllUsersService;
        this.map = map;
    }

    public List<User> execute() {

        List<com.medtaxi.backend.model.User> dbUsers = getAllUsersService.getAllUsers();
        List<User> usersList = new ArrayList<>();

        for (com.medtaxi.backend.model.User dbUser : dbUsers) {
            User mappedUser = map.map(dbUser);
            usersList.add(mappedUser);
        }

        return usersList;
    }
}
