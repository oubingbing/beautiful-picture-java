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

    private Integer userId;

    private String createAt;

    @OneToOne(targetEntity = PictureItem.class)
    @JoinColumn(name = "pictureItemId",referencedColumnName="id",insertable = false,updatable = false,nullable = true)
    private PictureItem pictureInfo;
}
