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
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int story_id;
    private int user_id;
    private String content;
    private int result;
    private boolean isChanged;
    private LocalDateTime created_date;
    private boolean isExposure;

    @Builder
    public Story(int user_id, String content, int result, LocalDateTime created_date){
        this.user_id = user_id;
        this.content = content;
        this.result = result;
        this.created_date = created_date;
    }
}
