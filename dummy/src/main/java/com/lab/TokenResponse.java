package com.lab;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TokenResponse {
    @JsonProperty("access_token")
    private String accessToken;
    //private String tokenType;
    @JsonProperty("expires_in")
    private long expiresIn;
}
