package com.somelight.project.api.controller;

import com.somelight.project.api.response.ArticleDetailResponse;
import com.somelight.project.api.service.ArticleService;
import com.somelight.project.api.service.UserService;
import com.somelight.project.api.service.VoteService;
import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.User;
import com.somelight.project.db.enitity.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleDetail(@PathVariable("articleId") int articleId) {
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
        String email = null;
        int userId = 0;
        if (email != null) {
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

//    @DeleteMapping("/{articleId}")
//    public ResponseEntity<?> deleteArticle(@PathVariable("articleId") int articleId) {
////        String userEmail = (String) authentication.getCredentials();
//        String email = "garong1997@naver.com";
//        int userId;
//        if (email != null) {
//            userId = userService.getUserId(email);
//        } else {
//            userId = 0;
//        }
//        Article article = articleService.getArticleByArticleId(articleId);
//        if (userId != article.getArticleId())
//            return new ResponseEntity<>("수정할 권한이 없습니다.", HttpStatus.BAD_REQUEST);
//        articleService.deleteArticle(articleId);
//        voteService.deleteVoteList(articleId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
