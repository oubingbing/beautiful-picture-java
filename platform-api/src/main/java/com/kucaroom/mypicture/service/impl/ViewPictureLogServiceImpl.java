package com.kucaroom.mypicture.service.impl;

import com.kucaroom.mypicture.DTO.PictureDTO;
import com.kucaroom.mypicture.DTO.UserDTO;
import com.kucaroom.mypicture.domain.ViewPictureLog;
import com.kucaroom.mypicture.enums.ResponseEnum;
import com.kucaroom.mypicture.exception.ApiException;
import com.kucaroom.mypicture.repository.ViewPictureLogRepository;
import com.kucaroom.mypicture.service.PictureService;
import com.kucaroom.mypicture.service.UserService;
import com.kucaroom.mypicture.service.ViewPictureLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewPictureLogServiceImpl implements ViewPictureLogService{
    @Autowired
    private ViewPictureLogRepository viewPictureLogRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PictureService pictureService;

    /**
     * 新建查看记录
     *
     * @param userId
     * @param pictureId
     * @return
     */
    @Override
    public ViewPictureLog create(Integer userId, Integer pictureId) {
        UserDTO user = userService.finById(userId);
        if(user == null){
            throw new ApiException(ResponseEnum.USER_NOT_EXIST);
        }

        PictureDTO pictureDTO = pictureService.findById(pictureId);
        if(pictureDTO == null){
            throw new ApiException(ResponseEnum.PICTURE_NOT_EXIST);
        }

        ViewPictureLog log = new ViewPictureLog();
        log.setUserId(userId);
        log.setPictureId(pictureId);

        pictureService.incrementViewNumber(pictureId);

        return viewPictureLogRepository.save(log);
    }
}
