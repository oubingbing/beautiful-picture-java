package com.kucaroom.mypicture.service.impl;

import com.kucaroom.mypicture.DTO.AdminDTO;
import com.kucaroom.mypicture.domain.Admin;
import com.kucaroom.mypicture.enums.AdminStatusEnum;
import com.kucaroom.mypicture.enums.ResponseEnum;
import com.kucaroom.mypicture.exception.WebApiException;
import com.kucaroom.mypicture.repository.AdminRepository;
import com.kucaroom.mypicture.service.AdminService;
import com.kucaroom.mypicture.util.KeyUtil;
import com.kucaroom.mypicture.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * 注册用户
     *
     * @author yezi
     * @param adminDTO
     * @return
     */
    @Override
    public AdminDTO create(AdminDTO adminDTO) {
        AdminDTO admin = findByEmail(adminDTO.getEmail());
        if(admin != null){
            throw new WebApiException(ResponseEnum.USER_EXIST);
        }
        Admin adminUser = new Admin();
        BeanUtils.copyProperties(adminDTO,adminUser);

        String salt = KeyUtil.generateKey(6);
        String activeToken = KeyUtil.generateKey(32);
        String password = MD5Util.encrypt(adminDTO.getPassword(),salt);

        adminUser.setPassword(password);
        adminUser.setAvatar("http:baidu.com");
        adminUser.setSalt(salt);
        adminUser.setActiveToken(activeToken);
        adminUser.setStatus(AdminStatusEnum.ADMIN_NOT_ACTIVE.getCode());

        Admin result = adminRepository.save(adminUser);
        BeanUtils.copyProperties(result,adminDTO);

        return adminDTO;
    }

    @Override
    public AdminDTO findByEmail(String email) {
        Admin admin = adminRepository.findByEmail(email);
        if(admin != null){
            AdminDTO adminDTO = new AdminDTO();
            BeanUtils.copyProperties(admin,adminDTO);
            return adminDTO;
        }

        return null;
    }

    @Override
    public String getPassword(String email) {
        AdminDTO adminDTO = findByEmail(email);
        if(adminDTO == null){
            return null;
        }else{
            return adminDTO.getPassword();
        }
    }
}
