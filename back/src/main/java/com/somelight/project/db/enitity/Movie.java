package com.somelight.project.db.enitity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId;
    private int result;
    private String title;
    private String movieImage;

}
