package com.somelight.project.api.response;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.Vote;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ArticleDetailResponse {
    int articleId;
    String content;
    int result;
    boolean isChanged;
    LocalDateTime createdDate;
    boolean isExposure;
    double redPercent;
    double greenPercent;
    int voteResult;

    public static ArticleDetailResponse of(Article article, Vote vote, double redPercent, double greenPercent) {

        ArticleDetailResponse res = ArticleDetailResponse.builder()
                .articleId(article.getArticleId())
                .content(article.getContent())
                .result(article.getResult())
                .isChanged(article.isChanged())
                .createdDate(article.getCreatedDate())
                .isExposure(article.isExposure())
                .redPercent(redPercent)
                .greenPercent(greenPercent)
                .voteResult(vote.getVoteResult())
                .build();
        return res;

    }
}
