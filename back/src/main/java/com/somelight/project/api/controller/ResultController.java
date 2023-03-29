package com.somelight.project.api.controller;

import com.somelight.project.api.request.KeywordRequest;
import com.somelight.project.api.request.ResultRequest;
import com.somelight.project.api.response.ResultResponse;
import com.somelight.project.api.service.ApiService;
import com.somelight.project.api.service.ArticleService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.Book;
import com.somelight.project.db.enitity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/result")
public class ResultController {
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ApiService apiService;
    @CrossOrigin("*")
    @PostMapping()
    public ResponseEntity<ResultResponse> registerArticle(@Nullable Authentication authentication,
                                                         @RequestBody ResultRequest resultRequest) {
        String content = resultRequest.getContent();
        int result = resultRequest.getResult();
        List<KeywordRequest> keywordRequestList = resultRequest.getKeyword();
        Comparator<KeywordRequest> comparator = new Comparator<KeywordRequest>() {
            @Override
            public int compare(KeywordRequest A, KeywordRequest B) {
                return B.getCnt() - A.getCnt();
            }
        };
        keywordRequestList.sort(comparator);
        String keyword = keywordRequestList.get(0).getKeyword();

        int userId = 0;
        if (authentication != null) {
            String email = (String) authentication.getCredentials();
            userId = userService.getUserId(email);
        }
        Article article = articleService.createArticle(userId, content, result);
        ResultResponse res = ResultResponse.of(article, keywordRequestList, null, null, null, null);
        if (article.getResult() != 1) {
            Movie movie = apiService.requestMovie(result, keyword, content);
            res.setMovie(movie.getTitle());
            res.setMovieImage(movie.getMovieImage());

            Book book = apiService.requestBook(result, content);
            res.setBook(book.getTitle());
            res.setBookImage(book.getBookImage());
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
