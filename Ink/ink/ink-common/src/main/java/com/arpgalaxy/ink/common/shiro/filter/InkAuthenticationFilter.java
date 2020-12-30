package com.arpgalaxy.ink.common.shiro.filter;

import com.arpgalaxy.ink.common.shiro.token.InkAuthenticationToken;
import com.arpgalaxy.ink.common.utils.response.InkStatus;
import com.arpgalaxy.ink.common.utils.response.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author arpgalaxy
 * @date 2020/10/13
 * @email 13605560342@163.com
 * @description
 */
public class InkAuthenticationFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) request);

        if(StringUtils.isEmpty(token)){
            return null;
        }

        return new InkAuthenticationToken(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        System.out.println("filter   isAccessAllowed ");
        if(((HttpServletRequest) request).getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        System.out.println("filter   onAccessDenied ");
        //获取请求token，如果token不存在，直接返回401
        String token = getRequestToken((HttpServletRequest) request);
        System.out.println(token);
        if(StringUtils.isEmpty(token) || "null".equals(token)) {
            System.out.println("token   null ");
            ObjectMapper mapper = new ObjectMapper();
            //使用writeValueAsString()方法将Java对象解析成json格式
            String str = mapper.writeValueAsString(new ResponseEntity<Throwable>(InkStatus.CORE_OK, "token is null", null));
            response.getWriter().print(str);
            return false;
        }
            return executeLogin(request, response);


    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        System.out.println("filter   onLoginFailure ");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=utf-8");
//        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
//        httpResponse.setHeader("Access-Control-Allow-Origin", HttpContextUtils.getOrigin());
        try {
            //处理登录失败的异常
            e.printStackTrace();
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            ObjectMapper mapper = new ObjectMapper();
            //使用writeValueAsString()方法将Java对象解析成json格式
            String str = mapper.writeValueAsString(new ResponseEntity<Throwable>(InkStatus.CORE_OK,"token为null", throwable));
            response.getWriter().print(str);
            httpResponse.getWriter().print(str);
        } catch (Exception e1) {

        }

        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        //从header中获取token
        String token = httpRequest.getHeader("auth-Token");

        //如果header中不存在token，则从参数中获取token
        if(StringUtils.isEmpty(token)){
            token = httpRequest.getParameter("authToken");
        }

        return token;
    }

}
