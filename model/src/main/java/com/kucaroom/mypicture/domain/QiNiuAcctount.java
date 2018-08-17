package com.kucaroom.mypicture.domain;import lombok.Data;import javax.persistence.*;@Data@Entity@Table(name = "qiniu_accounts")public class QiNiuAcctount {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Integer id;    /** 所属小程序 */    private Integer appId;    /** 七牛key */    private String accessKey;    /** 密钥 */    private String secretKey;    /** 存储桶 */    private String bucketName;    /** 获取token地址 */    private String url = "";    /** 七牛token */    private String token;    /** 过期时间 默认是一天 */    private String expireAt;    private String createAt;}