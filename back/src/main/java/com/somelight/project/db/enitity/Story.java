package com.somelight.project.db.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "story")
public class Story {
    @Id
    private int story_id;
    private int user_id;
    private String content;
    private int result;
    private boolean isChanged;
    private LocalDateTime created_date;
    private boolean isExposure;

}
