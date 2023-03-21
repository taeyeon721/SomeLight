package com.somelight.project.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.repository.query.QueryLookupStrategy;

@Getter
@Setter
@Builder
public class KeywordRequest {
    String keyword;
    int cnt;

    public static KeywordRequest of(String keyword, int cnt) {
        KeywordRequest res = KeywordRequest.builder()
                .keyword(keyword)
                .cnt(cnt)
                .build();
        return res;
    }
}
