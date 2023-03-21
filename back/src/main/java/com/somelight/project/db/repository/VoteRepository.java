package com.somelight.project.db.repository;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Vote findByArticleIdAndUserId(int articleId, int userId);
}
