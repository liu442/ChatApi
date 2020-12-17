package com.steve.setting.Interceptor;


import com.steve.common.constant.Constant;
import com.steve.setting.Interceptor.ApiInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;

/**
 *
 * @author Steve
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {


    private final ApiInterceptor apiInterceptor;

    @Autowired
    public WebMvcConfig(ApiInterceptor apiInterceptor) {
        this.apiInterceptor = apiInterceptor;
    }

    /**
     * 拦截器配置
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链  可以写多个 registry.
        registry.addInterceptor(apiInterceptor)
                .addPathPatterns("/" + Constant.API_PATH + "/**")
                .excludePathPatterns(
                        "/"+Constant.API_PATH+"/user/login"
                );
        super.addInterceptors(registry);
    }


}
