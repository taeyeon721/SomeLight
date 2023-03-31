package com.somelight.project.api.service;

import com.somelight.project.api.response.MovieOneResponse;
import com.somelight.project.api.response.MovieResultResponse;
import com.somelight.project.db.enitity.Book;
import com.somelight.project.db.enitity.Movie;
import com.somelight.project.db.repository.BookRepository;
import com.somelight.project.db.repository.MovieRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{

    //@Value("${KMDB.movieKey}")
   // private String movieKey;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BookRepository bookRepository;

    public Movie requestMovie(int result, String keyword, String content) {
        Movie movie = new Movie();
        if (keyword == null) {
            List<Movie> movieList = movieRepository.findAllByResult(result);
            movie = movieList.get((int) (Math.random() * (movieList.size() - 1)));
            return movie;
        }

        List<Movie> movieList = movieRepository.findAllByResult(result);
        movie = movieList.get((int) (Math.random() * (movieList.size() - 1)));
        return movie;
    }

    public Book requestBook(int result, String content){
        Book book = new Book();
        double check = 0;
        List<Book> bookList = bookRepository.findAllByResult(result);
        for (Book books : bookList) {
            String description = books.getDescription();
            double similar = similarity(content, description);
            if (check < similar) {
                book.setTitle(books.getTitle());
                book.setBookImage(books.getBookImage());
                check = similar;
            }
        }
        return book;
    }

    private double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;

        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }

        int longerLength = longer.length();
        if (longerLength == 0) return 1.0;
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }
    private int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int[] costs = new int[s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];

                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }

                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
}
