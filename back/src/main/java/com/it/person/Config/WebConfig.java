package com.it.person.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebConfig implements WebMvcConfigurer {
//    @Autowired
//    AuthenticationInterceptor authenticationInterceptor;
//    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        registry.addInterceptor(authenticationInterceptor)
////                .addPathPatterns("/**");
////    }
//    @Bean
//    public AuthenticationInterceptor authenticationInterceptor() {
//        return new AuthenticationInterceptor();
//    }
}
