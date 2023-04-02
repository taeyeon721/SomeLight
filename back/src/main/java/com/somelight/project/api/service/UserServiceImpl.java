package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Article;
import com.somelight.project.db.enitity.User;
import com.somelight.project.db.repository.ArticleRepository;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public int getUserId(String email){
        User user = userRepository.findByEmail(email);
        return user.getUserId();
    }

    @Override
    public Page<Article> getUserArticles(int userId, Pageable pageable){
        Page<Article> articles = null;
        articles = articleRepository.findAllByUserId(userId, pageable);
        return articles;
    }

    @Override
    public User getUser(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }
}
