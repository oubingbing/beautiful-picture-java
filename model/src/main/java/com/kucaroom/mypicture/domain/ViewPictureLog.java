package com.kucaroom.mypicture.domain;

import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Map;

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

    private Integer number;

    private String createAt;
}
