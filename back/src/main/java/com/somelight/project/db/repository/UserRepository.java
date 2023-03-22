package com.somelight.project.db.repository;

import com.somelight.project.db.enitity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByNickname(String nickname);
    Optional<User> findByEmail(String email);

}
