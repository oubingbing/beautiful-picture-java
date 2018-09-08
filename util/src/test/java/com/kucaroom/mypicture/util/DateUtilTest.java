package com.kucaroom.mypicture.util;import lombok.extern.slf4j.Slf4j;import org.junit.Assert;import org.junit.Test;import java.text.SimpleDateFormat;import java.util.Date;@Slf4jpublic class DateUtilTest {    @Test    public void getDayBegin() throws Exception {        Date date = DateUtil.getDayBegin();        log.info("结果:{}",DateUtil.dateToString(date));    }    @Test    public void getDayEnd() throws Exception {        Date date = DateUtil.getDayEnd();        log.info("结果:{}",DateUtil.dateToString(date));    }    @Test    public void getBeginDayOfYesterday() throws Exception {        Date date = DateUtil.getBeginDayOfYesterday();        log.info("结果:{}",DateUtil.dateToString(date));    }    @Test    public void getEndDayOfYesterDay() throws Exception {        Date date = DateUtil.getEndDayOfYesterDay();        log.info("结果:{}",DateUtil.dateToString(date));    }    @Test    public void currentDateTest(){        log.info("当前时间：{}",DateUtil.currentDate());    }    @Test    public void compareDateTest(){        String source = "2018-08-18 16:39:48";        String target = "2018-08-18 15:56:18";        Boolean result = DateUtil.compareDate(source,target);        Assert.assertEquals(true,result);        String source1 = "2018-08-1 12:23:00";        String target1 = "2018-10-01 12:23:00";        Boolean result1 = DateUtil.compareDate(source1,target1);        Assert.assertEquals(false,result1);    }    @Test    public void currentDateObjTest(){        Date date = DateUtil.addSecond(120);        log.info("日期：{}",date);    }}