package com.somelight.project.api.response;

import com.somelight.project.db.enitity.Article;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResultResponse {
    Article article;
    List<String> keyword;
    String movie;
    String movieImage;
    String book;
    String bookImage;
    public static ResultResponse of(Article article, List<String> keyword, String movie, String movieImage, String book, String bookImage){
        ResultResponse res = ResultResponse.builder()
                .article(article)
                .keyword(keyword)
                .movie(movie)
                .movieImage(movieImage)
                .book(book)
                .bookImage(bookImage)
                .build();
        return res;
    }

}
