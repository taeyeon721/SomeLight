package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ArticleService {

    Page<Article> findArticles(boolean isExposure, Pageable pageable);
    Article getArticleByArticleId(int articleId);
    Article createArticle(int userId, String content, int result);
    Article updateArticle(boolean isChanged, boolean isExposure, int articleId);
    Article updateVote(int articleId, int voteResultReq, Vote vote);
    void deleteArticle(int articleId);

}
