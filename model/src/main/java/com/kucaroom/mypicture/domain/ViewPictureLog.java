package com.kucaroom.mypicture.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "view_picture_log")
public class ViewPictureLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer userId;

    private Integer pictureId;

    private Integer type;

    private String createAt;
}
