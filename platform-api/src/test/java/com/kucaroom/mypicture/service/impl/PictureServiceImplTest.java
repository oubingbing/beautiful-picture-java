package com.kucaroom.mypicture.service.impl;import com.kucaroom.mypicture.DTO.PictureDTO;import com.kucaroom.mypicture.enums.PictureStatusEnum;import com.kucaroom.mypicture.service.PictureService;import lombok.extern.slf4j.Slf4j;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.test.context.junit4.SpringRunner;import javax.imageio.ImageIO;import java.awt.image.BufferedImage;import java.io.File;import java.io.FileInputStream;import java.io.IOException;import java.io.InputStream;import java.net.URL;import static org.junit.Assert.*;@Slf4j@SpringBootTest@RunWith(SpringRunner.class)public class PictureServiceImplTest {    @Autowired    private PictureService pictureService;    @Test    public void findByAppId() {        PageRequest pageRequest = new PageRequest(0,10);        Integer appId = 74;        Page<PictureDTO> pictureDTOPage = pictureService.findByAppId(pageRequest,appId,PictureStatusEnum.PICTURE_UP.getCode());        log.info("分页数据：{}",pictureDTOPage);        //Assert.assertNotNull(pictureDTOPage);    }    @Test    public void findById() {    }}