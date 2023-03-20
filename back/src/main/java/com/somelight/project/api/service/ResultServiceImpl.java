package com.somelight.project.api.service;

import com.somelight.project.db.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultServiceImpl implements ResultService{

    @Autowired
    private ArticleRepository articleRepository;

}
