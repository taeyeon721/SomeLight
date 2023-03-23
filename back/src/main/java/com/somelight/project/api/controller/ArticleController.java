package com.somelight.project.api.controller;

import com.somelight.project.api.request.ArticleUpdateRequest;
import com.somelight.project.api.response.ArticleDetailResponse;
import com.somelight.project.api.service.ArticleService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.api.service.VoteService;
import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")

public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;
    @Autowired
    private VoteService voteService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @CrossOrigin("*")
    @GetMapping()
    public ResponseEntity<Page<Article>> getArticleList(@RequestParam(required = false, defaultValue = "1", value = "page") int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 15);
        Page<Article> articles = articleService.findArticles(pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleDetail(@Nullable Authentication authentication,
                                              @PathVariable("articleId") int articleId) {
        Article article = articleService.getArticleByArticleId(articleId);
        ArticleDetailResponse res = null;
        double redPercent;
        double greenPercent;
        int redCnt = article.getRedCount();
        int greenCnt = article.getGreenCount();
        if (redCnt == 0 && greenCnt == 0) {
            redPercent = 0;
            greenPercent = 0;
        } else {
            redPercent = (double)redCnt/ (greenCnt + redCnt) * 100;
            greenPercent = (double)greenCnt / (greenCnt + redCnt) * 100;

        }

        int userId = 0;
        if (authentication != null) {
            String email = (String) authentication.getCredentials();
            userId = userService.getUserId(email);
        }
        Vote vote = voteService.getVoteByArticleIdAndUserId(articleId, userId);


        if (vote == null) {
            return ResponseEntity.status(200)
                    .body(ArticleDetailResponse.of(article, new Vote(), redPercent, greenPercent));
        } else {
            return ResponseEntity.status(200)
                    .body(ArticleDetailResponse.of(article, vote, redPercent, greenPercent));
        }
    }

    @CrossOrigin("*")
    @PutMapping("/{articleId}")
    public ResponseEntity<?> updateArticle(Authentication authentication,
                                           @PathVariable("articleId") int articleId,
                                           @RequestBody ArticleUpdateRequest req) {
        System.out.println(req);
        String email = (String) authentication.getCredentials();
        int userId = userService.getUserId(email);
        Article article = articleService.getArticleByArticleId(articleId);
        Vote vote = voteService.getVoteByArticleIdAndUserId(articleId, userId);
        if (userId == article.getUserId()){
            if (req.getVoteResult() != 0) {
                return new ResponseEntity<>("수정할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
            }
        }
        if (req.isChanged() != article.isChanged() || req.isExposure() != article.isExposure()) {
            if (userId != article.getUserId()) {
                return new ResponseEntity<>("수정할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
            } else {
                articleService.updateArticle(req.isChanged(), req.isExposure(), articleId);
            }
        }
        if (req.getVoteResult() != 0) {
            voteService.updateVote(userId, articleId, req.getVoteResult());
            articleService.updateVote(articleId, req.getVoteResult(), vote);
            Vote newVote = voteService.getVoteByArticleIdAndUserId(articleId, userId);
        }
        Article newArticle = articleService.getArticleByArticleId(articleId);

        double redPercent;
        double greenPercent;
        int redCnt = newArticle.getRedCount();
        int greenCnt = newArticle.getGreenCount();
        if (redCnt == 0 && greenCnt == 0) {
            redPercent = 0;
            greenPercent = 0;
        } else {
            redPercent = (double)redCnt/ (greenCnt + redCnt) * 100;
            greenPercent = (double)greenCnt / (greenCnt + redCnt) * 100;

        }

        if (vote == null) {
            return ResponseEntity.status(200)
                    .body(ArticleDetailResponse.of(article, new Vote(), redPercent, greenPercent));
        } else {
            return ResponseEntity.status(200)
                    .body(ArticleDetailResponse.of(article, vote, redPercent, greenPercent));
        }
    }

    @CrossOrigin("*")
    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(Authentication authentication,
                                           @PathVariable("articleId") int articleId) {
        int userId;
        String email = (String) authentication.getCredentials();
        userId = userService.getUserId(email);
        Article article = articleService.getArticleByArticleId(articleId);
        if (userId != article.getUserId())
            return new ResponseEntity<>("수정할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
        articleService.deleteArticle(articleId);
        voteService.deleteVoteList(articleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
