package com.kucaroom.mypicture.service;

import com.kucaroom.mypicture.DTO.AboutUsDTO;

public interface AboutUsService {
    AboutUsDTO findByAppId(Integer appId);
}
