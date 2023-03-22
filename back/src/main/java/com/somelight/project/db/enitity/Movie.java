package com.somelight.project.db.enitity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Movie")
public class Movie {
    @Id
    private int movieId;
    private int result;
    private Item[] items;
    @Data
    static class Item {
        public String title;
        public String link;
        public String image;
        public String subtitle;
    }
}
