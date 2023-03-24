package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.Vote;
import com.somelight.project.db.repository.ArticleRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> findArticles(Boolean isExposure, Pageable pageable) {
        Page<Article> articles = null;
        articles = articleRepository.findAllByIsExposure(isExposure, pageable);
        return articles;
    }
    @Override
    public Article getArticleByArticleId(int articleId) {
        Article article = null;
        article = articleRepository.findByArticleId(articleId).orElseThrow();
        return article;
    }

    @Override
    @Transactional
    public Article createArticle(int userId, String content, int result) {
        LocalDateTime nowDate = LocalDateTime.now();
        Article article = Article.builder()
                .userId(userId)
                .content(content)
                .result(result)
                .isChanged(null)
                .createdDate(nowDate)
                .isExposure(false)
                .redCount(0)
                .greenCount(0)
                .build();
        articleRepository.save(article);
        return article;
    }

    @Override
    @Transactional
    public Article updateArticle(Boolean isChanged, Boolean isExposure, int articleId) {
        Article article = articleRepository.findByArticleId(articleId).orElseThrow();
        article.setIsChanged(isChanged);
        article.setIsExposure(isExposure);

        articleRepository.save(article);
        return article;
    }

    @Override
    @Transactional
    public Article updateVote(int articleId, int voteResultReq, Vote vote) {
        Article article = articleRepository.findByArticleId(articleId).orElseThrow();
        int greenCount = article.getGreenCount();
        int redCount = article.getRedCount();
        int greenCnt;
        int redCnt;
        if (voteResultReq == 1) {
            greenCnt = greenCount + 1;
            if (vote != null) {
                redCnt = redCount - 1;
                article.setRedCount(redCnt);
            }
            article.setGreenCount(greenCnt);
            articleRepository.save(article);
        } else {
            redCnt = redCount + 1;
            if (vote != null) {
                greenCnt = greenCount - 1;
                article.setGreenCount(greenCnt);
            }
            article.setRedCount(redCnt);
            articleRepository.save(article);
        }
        return article;
    }

    @Override
    @Transactional
    public void deleteArticle(int articleId) {
        Article article = articleRepository.findByArticleId(articleId).orElseThrow();
        articleRepository.delete(article);
    }
}
