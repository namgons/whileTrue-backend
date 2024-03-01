package com.whiletruebackend.domain.Member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OAuthRedirectController {

    @GetMapping("/member/oauth/redirect")
    public String redirect() {
        return "redirect";
    }
}
