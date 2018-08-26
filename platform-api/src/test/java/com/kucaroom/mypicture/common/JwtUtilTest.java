package com.kucaroom.mypicture.common;

import com.kucaroom.mypicture.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtUtilTest {

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void create() throws Exception {
        String token = jwtUtil.create("huiyi");
        log.info("token:{}",token);
    }

    @Test
    public void parse() throws Exception {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJodWl5aSIsImlzcyI6InN5c3RlbSIsImV4cCI6MTUzNTI2MTQ4MH0.KLFE8ZaMnQnenSCunm1j-7HWn9RxkEtOshISZASmH5Y";
        String subject = jwtUtil.parse(token);
        log.info("subject:{}",subject);
    }


}