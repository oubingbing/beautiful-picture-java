package com.kucaroom.mypicture.repository;

import com.kucaroom.mypicture.domain.ViewPictureLog;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ViewPictureLogRepositoryTest {

    @Autowired
    private ViewPictureLogRepository viewPictureLogRepository;

    @Test
    public void create(){
        ViewPictureLog log = new ViewPictureLog();
        log.setUserId(7611);
        log.setPictureId(34);

        ViewPictureLog result = viewPictureLogRepository.save(log);
        Assert.assertEquals(log.getPictureId(),result.getPictureId());
    }
}