package com.somelight.project.db.repository;

import com.somelight.project.db.enitity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByResult(int result);
}
