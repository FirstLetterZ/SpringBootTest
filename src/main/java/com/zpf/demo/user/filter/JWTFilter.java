package com.zpf.demo.user.filter;

import com.zpf.demo.global.Constants;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.BearerToken;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTFilter extends BasicHttpAuthenticationFilter {
    private BearerToken touristToken = new BearerToken(Constants.TOKEN_TOURIST);

    //保持登录状态
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        boolean result;
        try {
            result = executeLogin(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        }
        return result;
    }


    //修改认证字段，会对默认逻辑下生成UsernamePasswordToken产生影响，对应username
    @Override
    protected String getAuthzHeader(ServletRequest request) {
        HttpServletRequest httpRequest = WebUtils.toHttp(request);
        return httpRequest.getHeader(Constants.HEAD_TOKEN);
    }

    //配置跨域，配置解码
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    //修改生成的token，默认逻辑下生成UsernamePasswordToken
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String tokenString = getAuthzHeader(request);
        if (tokenString == null || tokenString.length() == 0) {
            return touristToken;
        } else {
            return new BearerToken(tokenString);
        }
    }
}
