package com.kucaroom.mypicture.service.impl;

import com.kucaroom.mypicture.DTO.PictureDTO;
import com.kucaroom.mypicture.domain.ViewPictureLog;
import com.kucaroom.mypicture.service.PictureService;
import com.kucaroom.mypicture.service.ViewPictureLogService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class ViewPictureLogServiceImplTest {

    @Autowired
    private ViewPictureLogService viewPictureLogService;

    @Autowired
    private PictureService pictureService;

    @Test
    public void create() throws Exception {
        Integer userId = 7611;
        Integer pictureId = 34;

        PictureDTO pictureDTO1 = pictureService.findById(pictureId);
        Assert.assertNotNull(pictureDTO1);

        ViewPictureLog viewPictureLog = viewPictureLogService.create(userId,pictureId);
        Assert.assertNotNull(viewPictureLog);

        PictureDTO pictureDTO2 = pictureService.findById(pictureId);
        Assert.assertNotNull(pictureDTO2);

        Assert.assertEquals((Integer) (pictureDTO1.getViewNum()+1),pictureDTO2.getViewNum());
    }

}