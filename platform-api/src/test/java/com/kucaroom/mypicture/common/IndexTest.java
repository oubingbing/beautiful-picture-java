package com.kucaroom.mypicture.common;import com.auth0.jwt.interfaces.DecodedJWT;import com.kucaroom.mypicture.config.JwtConfig;import com.kucaroom.mypicture.config.UrlConfig;import com.kucaroom.mypicture.util.JwtUtil;import lombok.extern.slf4j.Slf4j;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringRunner;@RunWith(SpringRunner.class)@SpringBootTest@Slf4jpublic class IndexTest {    @Autowired    private UrlConfig urlConfig;    @Autowired    private JwtConfig jwtConfig;    @Test    public void getConfig(){        log.info("获取配置文件中的配置属性：{}",urlConfig.getWeChatUrl());    }    @Test    public void jwtTest(){    }    @Test    public void parseJwtTest(){       /* JwtUtil jwtUtil = new JwtUtil(jwtConfig.getSecretKey());        String token = jwtUtil.create("o4jKK5Rvf5Kw_Ro_Xt5HeKMy4PPw");        String parseResult = jwtUtil.parse(token);        log.info("解析结果："+parseResult);*/    }}