package com.imooc.mall;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class IntercepterConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserLoginIntercepter())
                .addPathPatterns("/**").
                excludePathPatterns("/error","/carts","/user/login","/user/register",
                        "/category","/product","/products/*","/cart/*","/user/msg",
                        "/cart/*/*","/shipping/*","/shipping/*/*");
    }
}
