package com.kucaroom.mypicture.service;

import com.kucaroom.mypicture.domain.ViewPictureLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ViewPictureLogService {
    ViewPictureLog create(Integer userId,Integer pictureId,Integer viewType);
    Page<ViewPictureLog> list(Integer userId,Integer type, Pageable pageable);
}
