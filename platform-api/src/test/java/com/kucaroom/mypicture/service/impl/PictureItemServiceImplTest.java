package com.kucaroom.mypicture.service.impl;import com.kucaroom.mypicture.domain.PictureItem;import com.kucaroom.mypicture.service.PictureItemService;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringRunner;import static org.junit.Assert.*;@SpringBootTest@RunWith(SpringRunner.class)public class PictureItemServiceImplTest {    @Autowired    private PictureItemService service;    @Test    public void findById() {        PictureItem pictureItem = service.findById(100);        Assert.assertNotNull(pictureItem);    }}