package com.an.gradlespringboot.intercept;

import com.an.gradlespringboot.service.TokenService;
import com.an.gradlespringboot.utils.SpringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        System.out.println("preHandle="+token);
        TokenService tokenService = SpringUtil.getBean(TokenService.class); // 在拦截器里  TokenService 还未被加载，所以autowired无效
        if (!StringUtils.isEmpty(token) && tokenService.verifyToken(token)){ //token 验证通过
            System.out.println("token验证通过!="+token);
            return true; //放行
        }
        returnTokenFail(response);
        return false;
    }


    /**
     * 返回异常结果
     * @param response
     */
    private void returnTokenFail(HttpServletResponse response){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            Map<String, Object> result = new HashMap<>();
            result.put("code",401);
            result.put("msg","用户token无效");
            result.put("data", "");
            String json = new ObjectMapper().writeValueAsString(result);
            response.getWriter().print(json);
        } catch (IOException e){

        } finally {

        }
    }
}
