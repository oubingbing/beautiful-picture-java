package com.kucaroom.mypicture.DTO;

import com.kucaroom.mypicture.domain.PictureItem;
import lombok.Data;

import java.util.List;

@Data
public class PictureDTO {

    private Integer id;

    private Integer appId;

    private String title;

    private String content;

    private String coverPicture;

    private Integer viewNum = 0;

    private Integer downLoadNum = 0;

    private Integer shareNum = 0;

    private Integer status;

    private List<PictureItem> pictureItems;
}
