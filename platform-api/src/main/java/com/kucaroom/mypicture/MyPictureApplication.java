package com.kucaroom.mypicture;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = "com.kucaroom.mypicture.mapper")
public class MyPictureApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MyPictureApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(MyPictureApplication.class, args);
    }
}
