package com.somelight.project.api.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.somelight.project.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

@Service
public class KakaoUserServiceImpl implements KakaoUserService {
    @Value("${clientId}")
    private String clientId;
    @Value("${clientSecret}")
    private String clientSecret;
    @Value("${redirectUri}")
    private String redirectUri;

    public HashMap<String, Object> getKakaoAccessToken(String code) {
        String access_token = "";
        String id_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        HashMap<String, Object> tokenInfo = new HashMap<>();

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=" + clientId); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri=" + redirectUri);
            sb.append("&code=" + code);
            sb.append("&client_secret=" + clientSecret);
            bw.write(sb.toString());
            bw.flush();

            System.out.println(sb);
            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            System.out.println("response body : " + result);
            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_token = element.getAsJsonObject().get("access_token").getAsString();
            id_Token = element.getAsJsonObject().get("id_token").getAsString();

            tokenInfo.put(("access_token"), access_token);
            tokenInfo.put(("id_token"), id_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tokenInfo;
    }

    /*
     * access_token을 받으면 kakao server에게 사용자정보 조회
     * */
    public HashMap<String, Object> getKakaoUserInfo(String token) throws Exception {

        String reqURL = "https://kapi.kakao.com/v2/user/me";

        HashMap<String, Object> kakaoUserInfo = new HashMap<>();

        //access_token을 이용하여 사용자 정보 조회
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리로 JSON파싱
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject()
                    .get("has_email").getAsBoolean();
            String email = "";
            String nickname = element.getAsJsonObject().get("properties").getAsJsonObject()
                    .get("nickname").getAsString();
            if (hasEmail) {
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject()
                        .get("email").getAsString();
            }

            kakaoUserInfo.put("email", email);
            kakaoUserInfo.put("nickname", nickname);
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return kakaoUserInfo;
    }

    public Boolean validationIdToken(String id_token){
        String reqURL = "https://kapi.kakao.com/oauth/tokeninfo";
        String kakaoRestapiKey = "8147c85395148371709b2199642f9108";
        String certification = "https://kauth.kakao.com";


        // 1. base64로 디코딩
        String payloadJWT  = id_token.split("[.]")[1];
        Base64.Decoder decoder = Base64.getUrlDecoder();

        String result = new String(decoder.decode(payloadJWT));
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(result);

        // 2. 페이로드의 iss 값이 https://kauth.kakao.com와 일치하는지 확인
        String iss = element.getAsJsonObject().get("iss").getAsString();

        // 3. 페이로드의 aud(REST API 키값) 값이 서비스 앱 키와 일치하는지 확인
        String aud = element.getAsJsonObject().get("aud").getAsString();


        System.out.println(iss+ " "+aud);

        if (iss.equals(certification) && aud.equals(kakaoRestapiKey)){
            System.out.println("===============유효성 검사 통과!!!==================");
            return true;
        }
        System.out.println("********************************************************************");

        return false;
    }

}
