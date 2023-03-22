package com.somelight.project.api.service;

import com.somelight.project.api.request.KeywordRequest;
import com.somelight.project.api.response.MovieOneResponse;
import com.somelight.project.api.response.MovieResultResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ApiService {

    MovieOneResponse requestMovie(int result, List<KeywordRequest> keywordlist, String content);
}
