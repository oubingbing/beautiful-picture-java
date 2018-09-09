package com.kucaroom.mypicture.controller.checkAuth;

import com.kucaroom.mypicture.domain.WeChatApp;
import com.kucaroom.mypicture.enums.ResponseEnum;
import com.kucaroom.mypicture.exception.ApiException;
import com.kucaroom.mypicture.service.WeChatAppService;
import com.kucaroom.mypicture.util.ResponseData;
import com.kucaroom.mypicture.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class WeChatAppController {
    @Autowired
    private WeChatAppService weChatAppService;

    @GetMapping("/share")
    public ResponseData<Map> info(HttpServletRequest request){
        Integer appId = (Integer) request.getAttribute("appId");
        WeChatApp weChatApp =weChatAppService.findByAppId(appId);
        if(weChatApp == null){
            throw new ApiException(ResponseEnum.APP_NOT_EXIST);
        }

        Map<String,Object> map = new HashMap<>();
        map.put("shareImage",weChatApp.getShareImage());
        map.put("shareWord",weChatApp.getShareWord());

        return ResponseUtil.success(map);
    }
}
