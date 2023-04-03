package com.somelight.project.api.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.somelight.project.db.enitity.Book;
import com.somelight.project.db.enitity.Movie;
import com.somelight.project.db.repository.BookRepository;
import com.somelight.project.db.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

@Service
public class ApiServiceImpl implements ApiService{

    @Value("${KMDB.movieKey}")
    private String movieKey;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private BookRepository bookRepository;

    public Movie requestMovie(int result, String keyword, String content) throws IOException {
        Movie movie = new Movie();
        if (keyword == null) {
            List<Movie> movieList = movieRepository.findAllByResult(result);
            movie = movieList.get((int) (Math.random() * (movieList.size() - 1)));
            return movie;
        }
        String genre;
        if (result == 2) genre = "로맨스";
        else genre = "코메디";
        StringBuilder urlBuilder = new StringBuilder("http://api.koreafilm.or.kr/openapi-data2/wisenut/search_api/search_json2.jsp?collection=kmdb_new2");
        /*URL*/
        urlBuilder.append("&" + URLEncoder.encode("ServiceKey","UTF-8") + "=" + URLEncoder.encode(movieKey, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("query","UTF-8") + "=" + URLEncoder.encode(keyword, "UTF-8"))
                .append("&" + URLEncoder.encode("detail", "UTF-8") + "=" + URLEncoder.encode("Y","UTF-8"))
                .append("&" + URLEncoder.encode("genre", "UTF-8") + "=" + URLEncoder.encode(genre, "UTF-8"))
                .append("&" + URLEncoder.encode("listCount","UTF-8") + "=500");
        System.out.println(urlBuilder);
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) { rd = new BufferedReader(new InputStreamReader(conn.getInputStream())); }
        else { rd = new BufferedReader(new InputStreamReader(conn.getErrorStream())); }

        StringBuilder sb = new StringBuilder(); String line;
        while ((line = rd.readLine()) != null) { sb.append(line); } rd.close();
        conn.disconnect();

        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(String.valueOf(sb));
        JsonObject jsonObject = element.getAsJsonObject();
        int movieCnt = jsonObject.get("TotalCount").getAsInt();
        JsonObject movieArray = (JsonObject) jsonObject.get("Data").getAsJsonArray().get(0);
        JsonArray movieResult = movieArray.get("Result").getAsJsonArray();

        if (movieCnt == 0) {
            List<Movie> movieList = movieRepository.findAllByResult(result);
            movie = movieList.get((int) (Math.random() * (movieList.size() - 1)));
            return movie;
        }
        double check = 0;
        for (int idx = 0; idx < movieCnt; idx++) {
            if (movieResult.get(idx).getAsJsonObject().get("posters").getAsString() == null) continue;
            JsonObject plots = (JsonObject) movieResult.get(idx).getAsJsonObject().get("plots");
            JsonObject plot = (JsonObject) plots.get("plot").getAsJsonArray().get(0);
            String plotText = plot.getAsJsonObject().get("plotText").getAsString();
            double similar = similarity(content, plotText);
            if (check < similar) {
                movie.setTitle(movieResult.get(idx).getAsJsonObject().get("title").getAsString());
                movie.setMovieImage(movieResult.get(idx).getAsJsonObject().get("posters").getAsString().split("[|]")[0]);
                check = similar;
            }
        }
        return movie;
    }

    public Book requestBook(int result, String content){
        Book book = new Book();
        double check = 0;
        List<Book> bookList = bookRepository.findAllByResult(result);
        for (Book books : bookList) {
            String description = books.getDescription();
            double similar = similarity(content, description);
            if (check < similar) {
                book.setTitle(books.getTitle());
                book.setBookImage(books.getBookImage());
                check = similar;
            }
        }
        return book;
    }

    private double similarity(String s1, String s2) {
        String longer = s1, shorter = s2;

        if (s1.length() < s2.length()) {
            longer = s2;
            shorter = s1;
        }

        int longerLength = longer.length();
        if (longerLength == 0) return 1.0;
        return (longerLength - editDistance(longer, shorter)) / (double) longerLength;
    }
    private int editDistance(String s1, String s2) {
        s1 = s1.toLowerCase();
        s2 = s2.toLowerCase();
        int[] costs = new int[s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            int lastValue = i;
            for (int j = 0; j <= s2.length(); j++) {
                if (i == 0) {
                    costs[j] = j;
                } else {
                    if (j > 0) {
                        int newValue = costs[j - 1];

                        if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                            newValue = Math.min(Math.min(newValue, lastValue), costs[j]) + 1;
                        }

                        costs[j - 1] = lastValue;
                        lastValue = newValue;
                    }
                }
            }
            if (i > 0) costs[s2.length()] = lastValue;
        }
        return costs[s2.length()];
    }
}
