package com.kucaroom.mypicture.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void countUserByAppIdAnd() throws Exception {
        Integer result = userMapper.countUserByAppIdAndDate(74,"2018-08-25","2018-08-27");
        log.info("结果：{}",result);
    }

}