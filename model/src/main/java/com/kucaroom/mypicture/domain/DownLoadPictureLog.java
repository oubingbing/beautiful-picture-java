package com.kucaroom.mypicture.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "download_picture_log")
public class DownLoadPictureLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer pictureItemId;

    private Integer type;

    private Integer number = 0;

    private String createAt;
}
