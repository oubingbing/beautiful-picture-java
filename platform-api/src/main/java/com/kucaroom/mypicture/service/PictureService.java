package com.kucaroom.mypicture.service;import com.kucaroom.mypicture.DTO.PictureDTO;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;public interface PictureService {    Page<PictureDTO> findByAppId(Pageable pageable,Integer appId,Integer status);    PictureDTO findById(Integer id);    Integer incrementViewNumber(Integer pictureId);}