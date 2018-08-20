package com.kucaroom.mypicture.repository;

import com.kucaroom.mypicture.domain.Picture;
import com.kucaroom.mypicture.enums.PictureStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@Slf4j
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class PictureRepositoryTest {

    @Autowired
    private PictureRepository pictureRepository;

    @Test
    public void createTest(){
        Picture picture = new Picture();
        picture.setAppId(74);
        picture.setAvatar("微信图片_20180820093317.jpg");
        picture.setContent("测试");
        picture.setTitle("美人计");
        picture.setCoverPicture("u=1025809368,3179591467&fm=27&gp=0.jpg");
        picture.setStatus(PictureStatusEnum.PICTURE_UP.getCode());
        Picture result = pictureRepository.save(picture);
        Assert.assertNotNull(result);
        Assert.assertEquals(result.getAvatar(),picture.getAvatar());
    }

    @Test
    public void oneToManyTest(){
        Optional<Picture> picture = pictureRepository.findById(3);
        Picture picture1 = picture.get();
        log.info("结果：{}",picture1);
        Assert.assertNotNull(picture1);
    }
}