package com.medtaxi.backend.command;

import com.medtaxi.backend.service.GetUserByIdService;
import com.medtaxi.backend.utils.Map;
import com.medtaxi.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetUserByIdCommand {
    @Autowired
    private GetUserByIdService service;
    @Autowired
    private Map map;

    public User execute(Integer userId) {
        return map.map(service.getUserById(userId));
    }
}
