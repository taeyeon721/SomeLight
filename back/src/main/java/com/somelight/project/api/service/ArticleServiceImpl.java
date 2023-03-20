package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.repository.ArticleRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public Article createArticle(int userId, String content /*, int result */){
        LocalDateTime created_date = LocalDateTime.now();
        Article article = Article.builder()
                .userId(userId)
                .content(content)
                .isChanged(false)
                .result((int) (Math.random()*3))
                // .result(result)
                .isExposure(false)
                .createdDate(created_date)
                .build();
        articleRepository.save(article);
        return article;
    }

}
