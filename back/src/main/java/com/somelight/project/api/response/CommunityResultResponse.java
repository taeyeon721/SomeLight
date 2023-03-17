package com.somelight.project.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CommunityResultResponse {
    private class Keyword {
        String keyword;
        int frequency;
    }
    int result;
    List<Keyword> keywordList;

    public static CommunityResultResponse of(int result, List<Keyword> keywordList){
        CommunityResultResponse res = CommunityResultResponse.builder()
                .result(result)
                .keywordList(keywordList)
                .build();
        return res;
    }
}
