package com.kucaroom.mypicture.domain;import lombok.Data;import javax.persistence.*;@Data@Entity@Table(name = "picture_items")public class PictureItem {    @Id    @GeneratedValue(strategy = GenerationType.IDENTITY)    private Integer id;    /** 所属图集 */    private Integer pictureId;    /** 标题 */    private String title = "";    /** 内容 */    private String content = "";    /** 图片 */    private String picture;    /** 浏览人数 */    private Integer viewNum = 0;    /** 下载封面人数 */    private Integer downLoadNum = 0;    /** 分享人数 */    private Integer shareNum = 0;    /** 类型 */    private Integer type = 0;    /** 状态*/    private Integer status = 0;    private String createAt;}