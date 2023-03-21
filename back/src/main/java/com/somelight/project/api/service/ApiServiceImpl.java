package com.somelight.project.api.service;

import com.somelight.project.api.request.KeywordRequest;
import com.somelight.project.api.response.MovieResponse;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{
    @Value("${naver.clientId}")
    private String clientId;
    @Value("${naver.clientSecret}")
    private String clientSecret;
    @Value("${naver.movieUrl}")
    private String movieUrl;

    public MovieResponse requestMovie(int result, List<KeywordRequest> keyword) {
        final HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", clientId);
        headers.set("X-Naver-Client-Secret", clientSecret);
        Comparator<KeywordRequest> comparator = new Comparator<KeywordRequest>() {
            @Override
            public int compare(KeywordRequest A, KeywordRequest B) {
                return B.getCnt() - A.getCnt();
            }
        };
        Collections.sort(keyword, comparator);
        final HttpEntity<String> entity = new HttpEntity<>(headers);
        return null;
    }

}
