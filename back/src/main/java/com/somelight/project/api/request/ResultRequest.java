package com.somelight.project.api.request;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultRequest {
    int result;
    List<String> keyword;
}
