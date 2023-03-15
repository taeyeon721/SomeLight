package com.somelight.project.api.controller;

import com.somelight.project.api.response.UserInfoResponse;
import com.somelight.project.api.service.CommunityService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.db.enitity.Story;
import com.somelight.project.db.enitity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userservice;
    @Autowired
    private CommunityService communityService;

    @GetMapping()
    public ResponseEntity<UserInfoResponse> getUserInfo (@Nullable Authentication authentication) {
        String email = null;
        User user = null;
        List<Story> user_stories = new ArrayList<>();
        if (authentication != null) {
            email = (String) authentication.getCredentials();
            user = userservice.getUserId(email);
            user_stories = communityService.getUserStories(user.getUser_id());
        }
        UserInfoResponse res = UserInfoResponse.of(user_stories, user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
