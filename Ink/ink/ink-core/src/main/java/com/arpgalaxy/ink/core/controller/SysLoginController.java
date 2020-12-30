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
import com.arpgalaxy.ink.common.utils.PageUtils;
import com.arpgalaxy.ink.common.utils.TokenGenerator;
import com.arpgalaxy.ink.common.utils.response.InkStatus;
import com.arpgalaxy.ink.common.utils.response.R;
import com.arpgalaxy.ink.common.utils.response.ResponseEntity;
import com.arpgalaxy.ink.core.entity.SysUserEntity;
import com.arpgalaxy.ink.core.service.LoginService;
import com.arpgalaxy.ink.core.service.impl.SysUserServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;


/**
 * SysLoginController
 *
 * @author bobbi
 * @date 2018/10/19 18:47
 * @email 571002217@qq.com
 * @description
 */
@RestController
@Api(value = "Swagger Test Control",
        description = "演示Swagger用法的Control类",
        tags = "Swagger Test Control Tag")
// 写在controller类定义上方
@CrossOrigin
public class SysLoginController {

    @Autowired
    private SysUserServiceImpl sysUserService;

    @Autowired
    private LoginService loginService;
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request,@RequestParam("cap_count")String cap_count) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
//        String uuid = UUID.randomUUID().toString();
        //获取图片验证码
        BufferedImage image = loginService.getCaptcha(request,cap_count);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }
    @PostMapping("/core/sys/login")
    public ResponseEntity login(HttpServletRequest request,@RequestBody Map form) {
        boolean validate = loginService.validate(request,form.get("cap_code").toString());
        if (!validate){
            return new ResponseEntity(InkStatus.COMMON_ERROR,"验证码错误");
        }
        SysUserEntity sysUserEntity = sysUserService.getOne(new QueryWrapper<SysUserEntity>()
                    .lambda()
                    .eq(SysUserEntity::getUsername, form.get("username").toString()));

        if(sysUserEntity ==null || !sysUserEntity.getPassword().equals(new Sha256Hash(form.get("password").toString(),sysUserEntity.getSalt()).toHex())){
            System.out.println("用户名或密码错误");
            // 用户名或密码错误
            return new ResponseEntity(InkStatus.CORE_ERROR,"用户名或密码错误");
        }

        String token = loginService.auth(sysUserEntity.getUserId());
        return new ResponseEntity<String>(InkStatus.CORE_OK,"正常", token);
    }
    @PostMapping("/core/sys/isLogined")
    public ResponseEntity isLogined(HttpServletRequest request) {
        return new ResponseEntity<Boolean>(InkStatus.CORE_OK,"没有登录", SecurityUtils.getSubject().isAuthenticated());
    }

    /**
     * 注销
     */
    @PostMapping("/core/sys/logout")
    public ResponseEntity logout(HttpServletRequest request) {
        String token = request.getHeader("auth-token");
        SecurityUtils.getSubject().logout();
        boolean logout = loginService.logout(token);
        return new ResponseEntity<Boolean>(InkStatus.CORE_OK,"正常",logout);
    }

    @RequestMapping("/core/sys/test")
    @RequiresPermissions("user:test")
    @RequiresRoles("超级管理员")
    public ResponseEntity test() {

        return new ResponseEntity<String >(InkStatus.CORE_OK,"正常","测试");
    }


}
