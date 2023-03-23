package com.somelight.project.db.repository;

import com.somelight.project.db.enitity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findAllByResult(int result);
}
