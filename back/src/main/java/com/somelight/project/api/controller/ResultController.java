package com.somelight.project.api.controller;

import com.somelight.project.api.request.KeywordRequest;
import com.somelight.project.api.response.ResultResponse;
import com.somelight.project.api.service.ApiService;
import com.somelight.project.api.service.ArticleService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.db.enitity.Article;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.somelight.project.api.response.ResultResponse.*;

@RestController
@RequestMapping("/result")
public class ResultController {
    private UserService userService;
    private ArticleService articleService;
    private ApiService apiService;

    @PostMapping()
    public ResponseEntity<ResultResponse> registerArticle(@Nullable Authentication authentication,
                                                          @RequestPart(value = "content") String content,
                                                          @RequestPart(value = "result") int result,
                                                          @RequestPart(value = "keyword") List<KeywordRequest> keyword){
        int userId = 0;
        if (authentication != null) {
            String email = (String) authentication.getCredentials();
            userId = userService.getUserId(email);
        }
        Article article = articleService.createArticle(userId, content, result);
        ResultResponse res = ResultResponse.of(article, keyword, null, null, null, null);
        if (article.getResult() == 2)
        {
            
        }
        else if (article.getResult() == 0)
        {

        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
