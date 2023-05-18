package com.example.mvc.chap05.dto.sns;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class KakaoUserDTO {

    private long id;

    //JSON 키값과 매칭
    @JsonProperty("connected_at")
    private LocalDateTime connectedAt;

    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @Setter
    @Getter
    @ToString
    public static class KakaoAccount {

        private String email;

        private Profile profile;

        @Setter
        @Getter
        @ToString
        public static class Profile{

            private String nickname;

            @JsonProperty("profile_image_url")
            private String profileImageUrl;
        }

    }
}
