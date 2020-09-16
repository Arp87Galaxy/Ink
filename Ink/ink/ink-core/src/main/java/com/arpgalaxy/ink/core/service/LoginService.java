package com.arpgalaxy.ink.core.service;

import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;

public interface LoginService {
    BufferedImage getCaptcha(HttpServletRequest request);
}
