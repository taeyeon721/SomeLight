package com.somelight.project.api.response;

import com.somelight.project.db.enitity.Story;
import com.somelight.project.db.enitity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class UserInfoResponse {

    String nickname;
    String email;
    List<Story> story;

    public static UserInfoResponse of(List<Story> story, User user) {
        UserInfoResponse res = UserInfoResponse.builder()
                .nickname(user.getNickname())
                .email(user.getEmail())
                .story(story)
                .build();
        return res;
    }
}
