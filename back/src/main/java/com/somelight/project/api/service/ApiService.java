package com.somelight.project.api.service;

import com.somelight.project.api.request.KeywordRequest;
import com.somelight.project.api.response.MovieOneResponse;
import com.somelight.project.api.response.MovieResultResponse;
import com.somelight.project.db.enitity.Book;
import com.somelight.project.db.enitity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiService {

    Movie requestMovie(int result, String keywrod, String content);
    Book requestBook(int result, String content);
}
