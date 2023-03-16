package com.somelight.project.api.controller;

import com.somelight.project.db.enitity.User;
import com.somelight.project.api.service.KakaoUserServiceImpl;
import com.somelight.project.db.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class KakaoUserController {

    private KakaoUserServiceImpl kakaoUserServiceimpl;
    private UserRepository userRepository;

    @CrossOrigin("*")
    @GetMapping("/kakao")
    public ResponseEntity<HashMap<String, Object>> kakaoLogin(@RequestParam String code) {
        HashMap<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            // 인가코드 받아서 토큰 발급
            System.out.println("code : " + code);
            HashMap<String, Object> tokenInfo = kakaoUserServiceimpl.getKakaoAccessToken(code);

            String access_token = (String) tokenInfo.get("access_token");
            String id_token = (String) tokenInfo.get("id_token");

            // 발급받은 access_token 받아서 kakaoUserInfo(email, nickname) 가져오기
            System.out.println("id_token : " + id_token);
            HashMap<String, Object> kakaoUserInfo = kakaoUserServiceimpl.getKakaoUserInfo(access_token);

//          System.out.println((String) kakaoUserInfo.get("email"));
//          System.out.println((String) kakaoUserInfo.get("nickname"));

            resultMap.put("nickname", kakaoUserInfo.get("nickname"));
            resultMap.put("userEmail", kakaoUserInfo.get("email"));

            // TODO : db에 저장되어있지만 토큰은 없을 때 테스트 해야됨
            // email이 현재 DB에 저장있지 않으면 DB에 저장
            if (userRepository.findByEmail((String) kakaoUserInfo.get("email")).isEmpty()) {
                resultMap.put("newUser", 1);

                userRepository.save(User.builder()
                            .email((String) kakaoUserInfo.get("email"))
                            .nickname((String)kakaoUserInfo.get("nickname"))
                            .build());
            } else {
                resultMap.put("newUser", 0);
            }

            resultMap.put(("token"), id_token);
            resultMap.put(("message"), "토큰 발급 완료");
            status = HttpStatus.OK;

        } catch (Exception e) {
            resultMap.put(("message"), e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<HashMap<String, Object>>(resultMap, status);
    }

    @CrossOrigin("*")
    @PostMapping("/kakao")
    public ResponseEntity<?> login(Authentication authentication, @RequestBody User user) {

        HashMap<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;

        try {
            System.out.println(user.toString());

            System.out.println("Data 1 : " + authentication.getPrincipal()); //닉네임
            System.out.println("Data 2 : " + authentication.getCredentials()); //이메일

            userRepository.save(User.builder()
                    .email((String) authentication.getCredentials())
                    .nickname((String) authentication.getPrincipal())
                    .build());

            resultMap.put("userEmail", authentication.getCredentials());
            status = HttpStatus.OK;
            resultMap.put(("message"), "회원 저장");

        } catch (Exception e) {
            System.out.println("에러발생@@@@@@@@@@@@@@@@@");
            resultMap.put(("message"), e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<HashMap<String, Object>>(resultMap, status);
    }
}
