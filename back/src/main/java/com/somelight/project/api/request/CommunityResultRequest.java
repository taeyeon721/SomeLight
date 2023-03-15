package com.somelight.project.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CommunityResultRequest {
    private class Keyword {
        String keyword;
        int frequency;
    }
    String content;
    int result;
    List<Keyword> keywordList;

    public static CommunityResultRequest of(String content, int result, List<Keyword> keywordList){
        CommunityResultRequest res = CommunityResultRequest.builder()
                .content(content)
                .result(result)
                .keywordList(keywordList)
                .build();
        return res;
    }
}
