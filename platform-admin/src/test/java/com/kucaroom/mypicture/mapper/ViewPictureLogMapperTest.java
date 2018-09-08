package com.kucaroom.mypicture.mapper;import com.kucaroom.mypicture.enums.ViewPictureTypeEnum;import lombok.extern.slf4j.Slf4j;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringRunner;@Slf4j@SpringBootTest@RunWith(SpringRunner.class)public class ViewPictureLogMapperTest {    @Autowired    private ViewPictureLogMapper mapper;    @Test    public void deleteByPictureIdAndType() {        Integer result = mapper.deleteByPictureIdAndType(102,ViewPictureTypeEnum.PICTURE_ITEM.getCode());        log.info("结果:{}",result);        Assert.assertNotEquals((Integer)0,result);    }}