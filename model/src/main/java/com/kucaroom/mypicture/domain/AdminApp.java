package com.kucaroom.mypicture.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "admin_app")
public class AdminApp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** 管理员 */
    private Integer adminId;

    /** 小程序 */
    private Integer appId;

    /** 是否已删除 */
    private Date deletedAt;
}
