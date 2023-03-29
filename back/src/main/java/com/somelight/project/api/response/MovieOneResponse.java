package com.somelight.project.api.response;

import lombok.Data;

import java.util.Date;

@Data
public class MovieOneResponse {
    public String title;
    public String link;
    public String image;
    public String subtitle;
    public Date pubDate;
    public String director;
    public String actor;
    public float userRating;
}
