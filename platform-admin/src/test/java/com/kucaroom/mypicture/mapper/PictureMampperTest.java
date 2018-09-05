package com.kucaroom.mypicture.mapper;import lombok.extern.slf4j.Slf4j;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringRunner;import org.springframework.transaction.annotation.Transactional;import static org.junit.Assert.*;@Slf4j@SpringBootTest@RunWith(SpringRunner.class)public class PictureMampperTest {    @Autowired    private PictureMapper mapper;    @Test    @Transactional    public void deleteByIdAndAppId() {        Integer result = mapper.deleteByIdAndAppId(39,74);        log.info("结果：{}",result);        Assert.assertNotEquals((Integer)0,result);    }}