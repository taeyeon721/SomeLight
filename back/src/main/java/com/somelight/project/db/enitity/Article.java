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
    private int redCount;
    private int greenCount;

    @Builder
    public Article(int userId, String content, int result, boolean isChanged, LocalDateTime createdDate, boolean isExposure, int redCount, int greenCount){
        this.userId = userId;
        this.content = content;
        this.result = result;
        this.isChanged = isChanged;
        this.createdDate = createdDate;
        this.isExposure = isExposure;
        this.redCount = redCount;
        this.greenCount = greenCount;
    }

//    public double getRedRatio() {
//        return (double)redCount / (greenCount + redCount) * 100;
//    }
//    public double getGreenRatio() {
//        return (double)greenCount / (greenCount + redCount);
//    }
}
