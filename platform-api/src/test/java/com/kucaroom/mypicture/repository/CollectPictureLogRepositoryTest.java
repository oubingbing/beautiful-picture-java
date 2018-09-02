package com.kucaroom.mypicture.repository;import com.kucaroom.mypicture.domain.CollectPictureLog;import org.junit.Assert;import org.junit.Test;import org.junit.runner.RunWith;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.test.context.junit4.SpringRunner;@SpringBootTest@RunWith(SpringRunner.class)public class CollectPictureLogRepositoryTest {    @Autowired    private CollectPictureLogRepository repository;    @Test    public void create(){        CollectPictureLog collectPictureLog = new CollectPictureLog();        collectPictureLog.setPictureItemId(100);        collectPictureLog.setUserId(7607);        CollectPictureLog log = repository.save(collectPictureLog);        Assert.assertNotNull(log);        Assert.assertEquals(collectPictureLog.getUserId(),log.getUserId());    }}