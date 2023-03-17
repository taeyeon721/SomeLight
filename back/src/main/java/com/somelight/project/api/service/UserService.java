package com.somelight.project.api.service;

import com.somelight.project.db.enitity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User getUserId(String email);
}
