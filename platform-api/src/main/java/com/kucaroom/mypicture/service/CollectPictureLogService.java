package com.kucaroom.mypicture.service;import com.kucaroom.mypicture.domain.CollectPictureLog;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;public interface CollectPictureLogService {    CollectPictureLog create(Integer userId,Integer pictureItemId);    Page<CollectPictureLog> list(Integer userId, Pageable pageable);}