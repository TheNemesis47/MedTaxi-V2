package com.medtaxi.backend.service;

import com.medtaxi.backend.exceptions.UserException;
import com.medtaxi.backend.model.User;
import com.medtaxi.backend.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetUserByIdService {
    @Autowired
    private UserRepo userRepo;

    public User getUserById(int userId) {
        return userRepo.findById((long) userId)
                .orElseThrow(() -> new UserException("User not found with ID: " + userId));
    }
}
