package com.somelight.project.api.controller;

import com.somelight.project.api.service.ArticleService;
import com.somelight.project.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

//    @PostMapping()
//    public ResponseEntity<?> registerContent(
//            @Nullable Authentication authentication,
//            CommunityResultRequest communityResultRequest) {
//        CommunityResultResponse res = null;
//        if (authentication != null) {
//            String email = (String) authentication.getCredentials();
//            Article article = articleService.registerStory(email, communityResultRequest.getContent(),
//                    communityResultRequest.getResult());
//        }
//
////        if (communityResultRequest.getResult() == 1) {
////            return
////        }
//        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
//    }
}
