package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.repository.ArticleRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public Page<Article> findArticles(Pageable pageable) {
        Page<Article> articles = null;
        articles = articleRepository.findAll(pageable);
        return articles;
    }

//    public List<Article> getUserArticles(int userId, Pageable pageable){
//        List<Article> articles = null;
//        articles = articleRepository.findAllByUserId(userId, pageable);
//        return articles;
//    }
    @Override
    public Article getArticleByArticleId(int articleId) {
        Article article = null;
        article = articleRepository.findByArticleId(articleId);
        return article;
    }

    public Article registerStory(String email, String content, int result){
        int userId = userRepository.findByEmail(email).get().getUserId();
        LocalDateTime created_date = LocalDateTime.now();
        Article story = Article.builder()
                .userId(userId)
                .content(content)
                .result(result)
                .createdDate(created_date)
                .build();
        articleRepository.save(story);
        return story;
    }
}
