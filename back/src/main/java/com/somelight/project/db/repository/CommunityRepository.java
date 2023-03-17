package com.somelight.project.db.repository;

import com.somelight.project.db.enitity.Story;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface CommunityRepository extends JpaRepository<Story, Integer> {

    Optional <List<Story>> getStoriesByUserId(int userId);


    Page<Story> findAll(Pageable pageable);
}
