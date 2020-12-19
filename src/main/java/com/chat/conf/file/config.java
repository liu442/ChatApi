package com.chat.conf.file;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * @author
 * @date 2020/3/20
 */
@Configuration
public class config {
    @Bean
    public MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory=new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("2MB");
        // 总上传数据大小
        factory.setMaxRequestSize("5MB");
        return factory.createMultipartConfig();
    }
}
