package com.kucaroom.mypicture.service;

import com.kucaroom.mypicture.domain.ViewPictureLog;
import org.springframework.data.domain.Page;

public interface ViewPictureLogService {
    ViewPictureLog create(Integer userId,Integer pictureId);
}
