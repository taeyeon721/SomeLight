package com.somelight.project.api.controller;

import com.somelight.project.api.service.ArticleService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;

    @Autowired
    public UserController(UserService userService, ArticleService articleService) {
        this.userService = userService;
        this.articleService = articleService;
    }
    @CrossOrigin("*")
    @GetMapping()
    public ResponseEntity<Page<Article>> getUserArticles(Authentication authentication,
                                                         @RequestParam(value = "page", defaultValue = "1") int page) {
        String email = (String) authentication.getCredentials();
        int userId = userService.getUserId(email);
        Pageable pageable = PageRequest.of(page-1, 15);
        Page<Article> userStories = userService.getUserArticles(userId, pageable);
        return new ResponseEntity<>(userStories, HttpStatus.OK);
    }
    @CrossOrigin("*")
    @GetMapping("/info")
    public ResponseEntity<User> getUserInfo(Authentication authentication) {
        String email = (String) authentication.getCredentials();
        User res = userService.getUser(email);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
