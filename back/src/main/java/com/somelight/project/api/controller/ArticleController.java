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

    private ArticleService articleService;
    private UserService userService;
    private VoteService voteService;

    @Autowired
    public ArticleController(ArticleService articleService, UserService userService, VoteService voteService) {
        this.articleService = articleService;
        this.userService = userService;
        this.voteService =  voteService;
    }

    @CrossOrigin("*")
    @GetMapping()
    public ResponseEntity<Page<Article>> getArticleList(@RequestParam(required = false, defaultValue = "1", value = "page") int pageNo) {
        Pageable pageable = PageRequest.of(pageNo-1, 8);
        Page<Article> articles = articleService.findArticles(true, pageable);
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleDetailResponse> getArticleDetail(@Nullable Authentication authentication,
                                              @PathVariable("articleId") int articleId) {
        Article article = articleService.getArticleByArticleId(articleId);
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
        String email = (String) authentication.getCredentials();
        int userId = userService.getUserId(email);
        Article article = articleService.getArticleByArticleId(articleId);
        Vote vote = voteService.getVoteByArticleIdAndUserId(articleId, userId);
        int voteResult;
        if (vote == null) {
            voteResult = 0;
        } else {
            voteResult = vote.getVoteResult();
        }

        if (userId == article.getUserId()){
            if (req.getVoteResult() != 0) {
                return new ResponseEntity<>("수정할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
            }
        }
        if (req.getIsChanged() != article.getIsChanged() || req.getIsExposure() != article.getIsExposure()) {
            if (userId != article.getUserId()) {
                return new ResponseEntity<>("수정할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
            } else {
                articleService.updateArticle(req.getIsChanged(), req.getIsExposure(), articleId);
                if (!req.getIsExposure()) {
                    voteService.deleteVoteList(articleId);
                }
            }
        }
        if (req.getVoteResult() != 0 && req.getVoteResult() != voteResult) {
            voteService.updateVote(userId, articleId, req.getVoteResult());
            articleService.updateVote(articleId, req.getVoteResult(), vote);
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

        Vote newVote = voteService.getVoteByArticleIdAndUserId(articleId, userId);
        if (newVote == null) {
            return ResponseEntity.status(200)
                    .body(ArticleDetailResponse.of(newArticle, new Vote(), redPercent, greenPercent));
        } else {
            return ResponseEntity.status(200)
                    .body(ArticleDetailResponse.of(newArticle, newVote, redPercent, greenPercent));
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
