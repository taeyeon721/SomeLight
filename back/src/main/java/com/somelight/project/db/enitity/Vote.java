package com.somelight.project.db.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "vote")
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int voteId;
    private int userId;
    private int articleId;
    private int voteResult;

    @Builder
    public  Vote(int userId, int articleId, int voteResult) {
        this.userId = userId;
        this.articleId = articleId;
        this.voteResult = voteResult;
    }
}
