package com.somelight.project.api.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultRequest {
    String content;
    int result;
    List<KeywordRequest> keyword;
}
