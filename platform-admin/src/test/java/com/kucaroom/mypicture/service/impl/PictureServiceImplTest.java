package com.kucaroom.mypicture.service.impl;import com.kucaroom.mypicture.DTO.PictureDTO;import com.kucaroom.mypicture.domain.Picture;import com.kucaroom.mypicture.domain.PictureItem;import com.kucaroom.mypicture.enums.PictureStatusEnum;import com.kucaroom.mypicture.service.PictureService;import lombok.extern.slf4j.Slf4j;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Pageable;import org.springframework.test.context.junit4.SpringRunner;import org.springframework.transaction.annotation.Transactional;import org.springframework.util.CollectionUtils;import java.util.ArrayList;import java.util.List;@Slf4j@SpringBootTest@RunWith(SpringRunner.class)public class PictureServiceImplTest {    @Autowired    private PictureService pictureService;    @Test    public void findByStatus() {        Pageable pageable = new PageRequest(0,10);        Page<PictureDTO> result = pictureService.findByStatus(pageable,74,PictureStatusEnum.PICTURE_UP.getCode());        log.info(result.getContent().toString());        Assert.assertEquals(false,CollectionUtils.isEmpty(result.getContent()));        Pageable pageable1 = new PageRequest(0,10);        Page<PictureDTO> result1 = pictureService.findByStatus(pageable1,74,PictureStatusEnum.PICTURE_DOWN.getCode());        log.info(result1.getContent().toString());        Assert.assertEquals(true,CollectionUtils.isEmpty(result1.getContent()));    }    @Test    public void findById() {        PictureDTO pictureDTO = pictureService.findById(3);        Assert.assertNotNull(pictureDTO);    }    @Test    public void create() {        PictureDTO picture = new PictureDTO();        picture.setAppId(74);        picture.setContent("测试");        picture.setTitle("美人计");        picture.setCoverPicture("u=1025809368,3179591467&fm=27&gp=0.jpg");        picture.setStatus(PictureStatusEnum.PICTURE_UP.getCode());        List<PictureItem> pictureItemList = new ArrayList<>();        PictureItem pictureItem1 = new PictureItem();        pictureItem1.setPicture("u=1574739706,1439120121&fm=27&gp=0.jpg");        pictureItemList.add(pictureItem1);        PictureItem pictureItem2 = new PictureItem();        pictureItem2.setPicture("u=1574739706,1439120121&fm=27&gp=0.jpg");        pictureItemList.add(pictureItem2);        picture.setPictureItems(pictureItemList);        PictureDTO pictureDTO = pictureService.create(picture);        Assert.assertNotNull(pictureDTO);    }    @Test    public void deleteById() {        Integer result = pictureService.deleteById(74,39);        log.info("结果:{}",result);        Assert.assertNotEquals((Integer)0,result);    }    @Test    public void updateStatus() {        Picture result = pictureService.updateStatus(74,36,1);        log.info("结果：{}",result);        Assert.assertEquals((Integer) 1,result.getStatus());    }}