package com.arpgalaxy.ink.core.service;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

public interface LoginService {
    BufferedImage getCaptcha(HttpServletRequest request, String cap_count);


    boolean validate(HttpServletRequest request, String cap_code);


    String auth(Long userId);

    Long isLogined(String authToken);

    boolean logout(String token);
}
