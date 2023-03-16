package com.somelight.project.api.controller;

import com.somelight.project.api.request.CommunityResultRequest;
import com.somelight.project.api.response.CommunityResultResponse;
import com.somelight.project.api.service.CommunityService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.db.enitity.Story;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community")
public class CommunityController {

    private CommunityService communityService;
    private UserService userService;

    @Autowired
    public CommunityController(CommunityService communityService, UserService userService) {
        this.communityService = communityService;
        this.userService = userService;
    }


    @PostMapping()
    public ResponseEntity<?> registerContent(
            @Nullable Authentication authentication,
            CommunityResultRequest communityResultRequest) {
        CommunityResultResponse res = null;
        if (authentication != null) {
            String email = (String) authentication.getCredentials();
            Story story = communityService.registerStory(email, communityResultRequest.getContent(),
                    communityResultRequest.getResult());
        }

//        if (communityResultRequest.getResult() == 1) {
//            return
//        }
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
}
