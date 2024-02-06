package com.whiletruebackend.domain.Member.service;

import com.whiletruebackend.domain.Member.dto.NotionAccessToken;
import com.whiletruebackend.global.utils.WebClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Base64;

@Service
public class MemberServiceImpl implements MemberService {

    @Value("${oauth.client_id}")
    private String OAUTH_CLIENT_ID;

    @Value("${oauth.client_secret}")
    private String OAUTH_CLIENT_SECRET;

    @Value("${oauth.redirect_uri}")
    private String REDIRECT_URI;

    private final String NotionTokenEndPoint = "https://api.notion.com/v1/oauth/token";

    @Override
    public void requestAccessToken(String accessCode) {

        String encoded = Base64.getEncoder().encodeToString(String.format("%s:%s", OAUTH_CLIENT_ID, OAUTH_CLIENT_SECRET).getBytes());
        WebClient webClient = WebClientUtils.createTokenRequestWebClient(NotionTokenEndPoint, encoded);

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", accessCode);
        formData.add("redirect_uri", REDIRECT_URI);

        NotionAccessToken notionAccessToken = webClient.post()
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(NotionAccessToken.class)
                .block();

        System.out.println(notionAccessToken);

    }
}
