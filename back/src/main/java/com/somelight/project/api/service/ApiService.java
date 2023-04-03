package com.somelight.project.api.service;

import com.somelight.project.db.enitity.Book;
import com.somelight.project.db.enitity.Movie;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

@Service
public interface ApiService {

    Movie requestMovie(int result, String keyword, String content) throws IOException;
    Book requestBook(int result, String content);
}
