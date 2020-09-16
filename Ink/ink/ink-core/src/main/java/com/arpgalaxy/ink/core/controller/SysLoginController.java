package com.arpgalaxy.ink.core.controller;

//import cn.dblearn.blog.auth.service.SysCaptchaService;
//import cn.dblearn.blog.auth.service.SysUserTokenService;
//import cn.dblearn.blog.common.Result;
//import cn.dblearn.blog.common.base.AbstractController;
//import cn.dblearn.blog.common.exception.enums.ErrorEnum;
//import cn.dblearn.blog.entity.sys.SysUser;
//import cn.dblearn.blog.entity.sys.form.SysLoginForm;
//import cn.dblearn.blog.mapper.sys.SysUserMapper;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.toolkit.IOUtils;
import com.arpgalaxy.ink.core.service.LoginService;
import com.google.code.kaptcha.Producer;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.UUID;

/**
 * SysLoginController
 *
 * @author bobbi
 * @date 2018/10/19 18:47
 * @email 571002217@qq.com
 * @description
 */
@RestController
public class SysLoginController {


    @Autowired
    private LoginService loginService;
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {

        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
//        String uuid = UUID.randomUUID().toString();
        //获取图片验证码
        BufferedImage image = loginService.getCaptcha(request);


        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
    @GetMapping("test")
    public void test(HttpServletResponse response, HttpServletRequest request) throws IOException {

        System.out.println(request.getSession().getId());
        System.out.println(request.getSession().getAttribute("Captcha"));
    }

//    @PostMapping("/admin/sys/login")
//    public Result login(@RequestBody SysLoginForm form) {
//        boolean captcha=sysCaptchaService.validate(form.getUuid(),form.getCaptcha());
//        if(!captcha){
//            // 验证码不正确
//            return Result.error(ErrorEnum.CAPTCHA_WRONG);
//        }
//
//        // 用户信息
//        SysUser user = sysUserMapper.selectOne(new QueryWrapper<SysUser>()
//                .lambda()
//                .eq(SysUser :: getUsername,form.getUsername()));
//        if(user ==null || !user.getPassword().equals(new Sha256Hash(form.getPassword(),user.getSalt()).toHex())){
//            // 用户名或密码错误
//            return Result.error(ErrorEnum.USERNAME_OR_PASSWORD_WRONG);
//        }
//        if(user.getStatus() ==0){
//            return Result.error("账号已被锁定，请联系管理员");
//        }
//
//        //生成token，并保存到redis
//        return sysUserTokenService.createToken(user.getUserId());
//    }
//
//    /**
//     * 退出
//     */
//    @PostMapping("/sys/logout")
//    public Result logout() {
//        sysUserTokenService.logout(getUserId());
//        return Result.ok();
//    }
}
