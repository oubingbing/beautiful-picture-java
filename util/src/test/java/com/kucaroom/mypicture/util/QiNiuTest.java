package com.kucaroom.mypicture.util;import lombok.extern.slf4j.Slf4j;import org.junit.Assert;import org.junit.Test;@Slf4jpublic class QiNiuTest {    @Test    public void getTokenTest(){        String key = "dJVFK8ibSzHKWhcVOupqzn22EKJ9QXqjLIqqDsqn";        String secret = "8E4WsVfVUWG99422USPRaqQQ7t8Gg8jjNYNQelMe";        String buckName = "beautiful-picture";        String token = QiNiuUtil.getToken(key,secret,buckName);        log.info("七牛token：{}",token);        Assert.assertNotNull(token);    }}