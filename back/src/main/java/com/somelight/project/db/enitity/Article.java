package com.somelight.project.db.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int articleId;
    private int userId;
    private String content;
    private int result;
    private boolean isChanged;
    private LocalDateTime createdDate;
    private boolean isExposure;

    @Builder
    public Article(int userId, String content, int result, LocalDateTime createdDate){
        this.userId = userId;
        this.content = content;
        this.result = result;
        this.createdDate = createdDate;
    }
}
