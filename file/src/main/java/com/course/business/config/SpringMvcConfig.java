package com.course.business.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: xyl
 * @time: 2020/11/29 21:23
 * @description:
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Value("${file.path}")
    private String FILE_PATH;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/f/**").addResourceLocations("file:"+FILE_PATH);
    }
}
