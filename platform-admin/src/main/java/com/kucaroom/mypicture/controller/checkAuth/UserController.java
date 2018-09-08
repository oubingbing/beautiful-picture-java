package com.kucaroom.mypicture.controller.checkAuth;

import com.kucaroom.mypicture.DTO.UserDTO;
import com.kucaroom.mypicture.converter.UserDTOToUserRes;
import com.kucaroom.mypicture.mapper.UserMapper;
import com.kucaroom.mypicture.responseObject.UserRes;
import com.kucaroom.mypicture.service.UserService;
import com.kucaroom.mypicture.util.DateUtil;
import com.kucaroom.mypicture.util.PageUtil;
import com.kucaroom.mypicture.util.ResponseData;
import com.kucaroom.mypicture.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @ResponseBody
    @GetMapping("/statistics")
    public ResponseData<Map<String,Integer>> countUser(HttpServletRequest request){
        Integer appId = (Integer)request.getAttribute("appId");
        Integer todayUser = userMapper.countUserByAppIdAndDate(appId, DateUtil.dateToString(DateUtil.getDayBegin()),DateUtil.dateToString(DateUtil.getDayEnd()));
        Integer yesterday = userMapper.countUserByAppIdAndDate(appId, DateUtil.dateToString(DateUtil.getBeginDayOfYesterday()),DateUtil.dateToString(DateUtil.getEndDayOfYesterDay()));
        Integer total = userMapper.countUserByAppId(appId);

        Map<String,Integer> map = new HashMap<>();
        map.put("today",todayUser);
        map.put("yesterday",yesterday);
        map.put("total",total);

        return ResponseUtil.success(map);
    }

    @GetMapping("")
    public ModelAndView userView(){

        return new ModelAndView("/admin/user");
    }

    @ResponseBody
    @GetMapping("/list")
    public ResponseData<List<UserRes>> users(@RequestParam(value = "pageNumber",defaultValue = "0")Integer pageNumber,
                                             @RequestParam(value = "pageSize",defaultValue = "20")Integer pageSize){
        Sort sort = new Sort(Sort.Direction.DESC, "createAt");
        PageRequest pageable = new PageRequest(pageNumber-1,pageSize,sort);
        Integer appId = 74;
        Page<UserDTO> userDTOS = userService.users(appId,pageable);
        List<UserRes> userRes = UserDTOToUserRes.convert(userDTOS.getContent());

        return ResponseUtil.success(PageUtil.convert(userRes,userDTOS));
    }
}
