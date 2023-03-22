package com.somelight.project.api.service;

import com.somelight.project.api.request.KeywordRequest;
import com.somelight.project.api.response.MovieOneResponse;
import com.somelight.project.api.response.MovieResultResponse;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{

    @Value("${KMDB.movieKey}")
    private String movieKey;
    public MovieOneResponse requestMovie(int result, List<KeywordRequest> keywordlist, String content) {

        String movieUrl = "http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2&detail=N&listCount=500&ServiceKey=" + movieKey;
        String searchWord = null;
        int genre = 0;
        Comparator<KeywordRequest> comparator = new Comparator<KeywordRequest>() {
            @Override
            public int compare(KeywordRequest A, KeywordRequest B) {
                return B.getCnt() - A.getCnt();
            }
        };
        if (keywordlist != null) {
            Collections.sort(keywordlist, comparator);
            searchWord = keywordlist.get(0).getKeyword();
        }
        else {
            if (result == 2) searchWord = "썸";
            else searchWord = "솔로";
        }

        return null;
    }

}
