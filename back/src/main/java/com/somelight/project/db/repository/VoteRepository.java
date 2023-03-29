package com.somelight.project.db.repository;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    Optional<Vote> findByArticleIdAndUserId(int articleId, int userId);

    @Transactional
    @Modifying(clearAutomatically = true)
    void deleteAllByArticleId(int articleId);

}
