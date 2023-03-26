package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    int getUserId(String email);
    List<Article> getUserArticles(int userId, Pageable pageable);
}
