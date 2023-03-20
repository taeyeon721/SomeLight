package com.somelight.project.api.controller;

import com.somelight.project.api.service.ArticleService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.db.enitity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping()
    public ResponseEntity<Page<Article>> getArticleList(@RequestParam(required = false, defaultValue = "1", value = "page") int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 15);
        Page<Article> articles = articleService.findArticles(pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }
}
