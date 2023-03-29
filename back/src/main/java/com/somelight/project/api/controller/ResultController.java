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
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.Charset;
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
                                                          @RequestBody String content) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json"));

        HttpEntity entity = new HttpEntity(content, httpHeaders);

        RestTemplate restTemplate = new RestTemplate();
        ResultRequest resultRequest = restTemplate.exchange("http://localhost:5000", HttpMethod.POST, entity, ResultRequest.class).getBody();

        int result = resultRequest.getResult();
        List<KeywordRequest> keywordRequestList = resultRequest.getKeyword();

        int userId = 0;
        if (authentication != null) {
            String email = (String) authentication.getCredentials();
            userId = userService.getUserId(email);
        }
        Article article = articleService.createArticle(userId, content, result);
        ResultResponse res = ResultResponse.of(article, keywordRequestList, null, null, null, null);

        String keyword = keywordRequestList.get(0).getKeyword();

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
