package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Book;
import com.somelight.project.db.enitity.Movie;
import org.springframework.stereotype.Service;

@Service
public interface ApiService {

    Movie requestMovie(int result, String keyword, String content);
    Book requestBook(int result, String content);
}
