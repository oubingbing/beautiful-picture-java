package com.kucaroom.mypicture.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    /** 用户的昵称 */
    private String username;

    /** 用户头像 */
    private String avatar;

    /** 用户邮箱 */
    private String email;

    /** 用户密码 */
    private String password;

    /** 记住登录的token */
    private String rememberToken;

    /** 加密盐值 */
    private String salt;

    /** 用户手机号码 */
    private String mobile;

    /** 账号激活码 */
    private String activeToken;

    /** 激活码失效时间 */
    private Date token_expire;

    /** 用户状态,0未激活，1=已激活*/
    private Integer status;

    /** 是否已删除 */
    private Date deletedAt;
}
