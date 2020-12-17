package com.steve.setting.Interceptor;

import com.steve.common.annotation.SignVerify;
import com.steve.common.constant.Constant;
import com.steve.common.result.ResultCode;
import com.steve.common.result.ResultResponse;
import com.steve.pojo.User;
import com.steve.utils.systemUtil.JsonUtils;
import com.steve.utils.systemUtil.MD5Utils;
import com.steve.utils.systemUtil.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.steve.setting.filter.RequestWrapper;
import java.util.SortedMap;

/**
 * @author Steve
 * @date 2020/1/14
 */
@Slf4j
@Component
public class ApiInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(ApiInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String reqUrl = request.getRequestURI();
        String token = request.getHeader("token");
        String sign = request.getHeader("sign");
        if (StringUtils.isBlank(token) || StringUtils.isBlank(sign)) {
            logger.error("有必填参数为空，参数：token: {} ,sign: {}", token, sign);
            JsonUtils.write(response, ResultResponse.fail(ResultCode.TOKEN_INVALID));
            return false;
        }
        //判断token是存在
        if (!Constant.API_TOKEN_MAP.containsKey(token)) {
            logger.error("token不存在，参数：token: {} ,sign: {}", token, sign);
            JsonUtils.write(response, ResultResponse.fail(ResultCode.TOKEN_INVALID));
            return false;
        }
        ResultResponse results = validateToken(token);
        if (results.getCode()!=0) {
            logger.error("token无效，参数：token: {} ,sign: {}", token, sign);
            JsonUtils.write(response, ResultResponse.fail(ResultCode.TOKEN_INVALID));
            return false;
        }

        boolean isHandlerMethod = handler.getClass().isAssignableFrom(HandlerMethod.class);
        if (isHandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            SignVerify signVerify = handlerMethod.getMethod().getAnnotation(SignVerify.class);
            if (signVerify != null && !signVerify.value()) {
                //存在无需验证签名的注解并且value为false，则直接放过
                log.info("存在无需验证签名的注解并且value为false，则直接放过拦截：reqUrl: {},token: {} ",reqUrl,token );
                return true;
            }
        }
        //signKey 是在登陆的时候生成一个随机字符串返回给前端
        User user = (User) results.getResult();
        //后面尝试用公钥 私钥
        String signKey = user.getSignKey();
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBodyString();
        logger.info("body值："+body);
        SortedMap<String, Object> map = JsonUtils.jsonToSortMap(body);
        map.put("token", token);
        String fieldStr = SignUtil.getParamByOb(map);
        logger.info("fieldStr拼接值："+fieldStr);
        String mySign = MD5Utils.getMD532(fieldStr + "signKey=" + signKey);
        if (!StringUtils.equals(mySign, sign)) {
            JsonUtils.write(response, ResultResponse.fail(ResultCode.SIGN_ERROR));
            logger.error("签名错误，传入签名：{}，生成签名： {}", sign, mySign);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }


    /**
     * 验证token是否有效
     *
     * @param token token
     * @return
     */
    private static ResultResponse validateToken(String token) {
        try {
            if (!Constant.API_TOKEN_MAP.containsKey(token)) {
                logger.error("token不存在，参数：token: {}", token);
                return ResultResponse.fail(ResultCode.TOKEN_INVALID);
            }
            User user = Constant.API_TOKEN_MAP.get(token);
            if(user==null){
                logger.error("token失效，参数：token: {}", token);
                return ResultResponse.fail(ResultCode.TOKEN_INVALID);
            }
            return ResultResponse.ok(user);
        }catch (Exception e){
            return ResultResponse.fail();
        }
    }
}
