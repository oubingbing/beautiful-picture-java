package com.kucaroom.mypicture.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "down_load_picture_log")
public class DownLoadPictureLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer pictureItemId;

    private String createAt;
}
