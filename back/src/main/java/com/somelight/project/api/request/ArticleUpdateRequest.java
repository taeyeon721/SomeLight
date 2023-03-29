package com.somelight.project.api.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleUpdateRequest {

    Boolean isChanged;
    Boolean isExposure;
    @Nullable
    int voteResult;

}
