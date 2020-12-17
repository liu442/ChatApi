package com.steve.setting.filter;


import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * 实现过滤器，所有request进行包装  让所有的request经过这里 / 防止流读取一次后就没有了, 所以需要将流继续写出去  在 RequestWrapper 类中复制了流
 */
@Configuration
@WebFilter(urlPatterns = "/*", filterName = "apiChannelFilter")
public class ApiChannelFilter implements Filter {
    private static final Set<String> ALLOWED_PATHS = Collections.unmodifiableSet(new HashSet<>(
            Arrays.asList("/api/file/upload","/api/file/uploadFiles")));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--------------过滤器初始化------------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);

        if(allowedPath){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            // 防止流读取一次后就没有了, 所以需要将流继续写出去
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            ServletRequest requestWrapper = new   RequestWrapper(httpServletRequest);
            filterChain.doFilter(requestWrapper, servletResponse);
        }


    }

    @Override
    public void destroy() {
        System.out.println("--------------过滤器销毁------------");
    }
}
