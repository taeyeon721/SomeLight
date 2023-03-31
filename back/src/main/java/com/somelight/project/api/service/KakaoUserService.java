package com.somelight.project.api.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
@Service
public interface KakaoUserService {

    HashMap<String, Object> getKakaoAccessToken(String code);
    HashMap<String, Object> getKakaoUserInfo(String token) throws Exception;

    //Boolean validationIdToken(String id_token);
}
