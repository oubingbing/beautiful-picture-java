package com.kucaroom.mypicture.util;import org.springframework.data.domain.Page;import java.util.List;public class PageUtil {    public static PageData convert(List data, Page page){        PageData pageData = new PageData();        pageData.setContent(data);        pageData.setTotalNumber(page.getTotalPages()*page.getSize());        pageData.setPageNumber(page.getNumber());        pageData.setPageSize(page.getSize());        return pageData;    }}