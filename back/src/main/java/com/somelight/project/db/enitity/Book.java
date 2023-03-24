package com.somelight.project.db.enitity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private int result;
    private String title;
    @Column(length = 1500)
    private String description;
    private String bookImage;

}
