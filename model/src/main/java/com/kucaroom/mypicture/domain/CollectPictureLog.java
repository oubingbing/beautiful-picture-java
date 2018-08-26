package com.kucaroom.mypicture.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "collect_picture_log")
public class CollectPictureLog {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private Integer pictureItemId;

    private String createAt;
}
