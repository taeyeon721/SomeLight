package com.somelight.project.api.service;

import com.somelight.project.db.enitity.User;
import com.somelight.project.db.repository.CommunityRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private CommunityRepository communityRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserId(String email){
        User user = userRepository.findByEmail(email).orElse(null);
        return user;
    }
}
