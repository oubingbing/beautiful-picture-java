package com.kucaroom.mypicture.mapper;import lombok.extern.slf4j.Slf4j;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringRunner;import static org.junit.Assert.*;@Slf4j@SpringBootTest@RunWith(SpringRunner.class)public class CollectPictureLogMapperTest {    @Autowired    private CollectPictureLogMapper mapper;    @Test    public void deleteByPictureItemId() {        Integer result = mapper.deleteByPictureItemId(89);        log.info("结果：{}",result);        Assert.assertNotEquals((Integer)0,result);    }    @Test    public void deleteByPictureItemId1() {    }}