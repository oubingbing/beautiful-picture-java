package com.kucaroom.mypicture.service.impl;

import com.kucaroom.mypicture.DTO.AboutUsDTO;
import com.kucaroom.mypicture.domain.AboutUs;
import com.kucaroom.mypicture.repository.AboutUsRepository;
import com.kucaroom.mypicture.responseObject.AboutUsRO;
import com.kucaroom.mypicture.service.AboutUsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutUsServiceImpl implements AboutUsService {

    @Autowired
    private AboutUsRepository aboutUsRepository;

    @Override
    public AboutUsDTO findByAppId(Integer appId){
        AboutUs aboutUs = aboutUsRepository.findByAppId(appId);
        if(aboutUs != null){
            AboutUsDTO aboutUsDTO = new AboutUsDTO();
            BeanUtils.copyProperties(aboutUs,aboutUsDTO);

            return aboutUsDTO;
        }

        return null;
    }
}
