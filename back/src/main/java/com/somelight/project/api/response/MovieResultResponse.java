package com.somelight.project.api.response;

import lombok.Data;
import java.util.Date;
import java.util.List;

@Data
public class MovieResultResponse {
    private int display;
    private List<MovieOneResponse> movieOneResponses;
}
