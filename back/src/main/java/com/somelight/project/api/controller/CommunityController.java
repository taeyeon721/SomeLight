package com.somelight.project.api.controller;

import com.somelight.project.api.request.CommunityResultRequest;
import com.somelight.project.api.response.CommunityResultResponse;
import com.somelight.project.api.service.CommunityService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.db.enitity.Story;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping()
    public ResponseEntity<Page<Story>> getArticleList(@RequestParam(required = false, defaultValue = "1", value = "page") int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 15);
        Page<Story> articles = communityService.findStories(pageable);
        return new ResponseEntity<>(articles, HttpStatus.ACCEPTED);
    }
}
