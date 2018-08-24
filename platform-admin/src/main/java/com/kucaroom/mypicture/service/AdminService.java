package com.kucaroom.mypicture.service;

import com.kucaroom.mypicture.DTO.AdminDTO;
import com.kucaroom.mypicture.domain.Admin;

public interface AdminService {

    AdminDTO create(AdminDTO adminDTO);

    /**
     * 根据email查询用户
     *
     * @auhtor
     *
     * @param email
     * @return
     */
    AdminDTO findByEmail(String email);

    /**
     * 获取用户密码
     *
     * @param email
     * @return
     */
    String getPassword(String email);
}
