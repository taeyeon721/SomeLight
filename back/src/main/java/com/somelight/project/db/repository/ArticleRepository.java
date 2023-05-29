package com.somelight.project.db.repository;

import com.somelight.project.db.enitity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    Optional<Article> findByArticleId(int articleId);
    Page<Article> findAllByUserId(int userId, Pageable pageable);
    Page<Article> findAllByIsExposureOrderByArticleIdDesc(boolean isExposure, Pageable pageable);

}
