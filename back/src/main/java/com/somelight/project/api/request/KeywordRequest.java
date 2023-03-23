package com.somelight.project.api.request;

import lombok.*;
import org.springframework.data.repository.query.QueryLookupStrategy;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KeywordRequest {
    String keyword;
    int cnt;

    @Builder
    public static KeywordRequest of(String keyword, int cnt) {
        KeywordRequest res = KeywordRequest.builder()
                .keyword(keyword)
                .cnt(cnt)
                .build();
        return res;
    }
}
