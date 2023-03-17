package com.somelight.project.db.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int storyId;
    private int userId;
    private String content;
    private int result;

    private boolean isChanged;
    private LocalDateTime createdDate;

    private boolean isExposure;

    @Builder
    public Story(int userId, String content, int result, LocalDateTime createdDate){
        this.userId = userId;
        this.content = content;
        this.result = result;
        this.createdDate = createdDate;
    }
}
