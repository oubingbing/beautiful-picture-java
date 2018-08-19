package com.kucaroom.mypicture.repository;

import com.kucaroom.mypicture.domain.Picture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PictureRepositoryTest {
    @Test
    public void create(){
        Picture picture = new Picture();
        picture.setAppId(74);
        picture.setAvatar("");
    }
}