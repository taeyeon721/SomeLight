package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ArticleService {
    Page<Article> findArticles(Pageable pageable);
    Article getArticleByArticleId(int articleId);
    Article registerStory(String email, String content, int result);
}
