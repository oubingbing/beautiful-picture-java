package com.kucaroom.mypicture.repository;import com.kucaroom.mypicture.domain.WeChatApp;import lombok.extern.slf4j.Slf4j;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringRunner;import static org.junit.Assert.*;@Slf4j@SpringBootTest@RunWith(SpringRunner.class)public class WeChatAppRepositoryTest {    @Autowired    private WeChatAppRepository repository;    @Test    public void findByAdminId() {        WeChatApp app = repository.findByAdminId(11);        log.info("内容:{}",app.toString());        Assert.assertNotNull(app);    }}