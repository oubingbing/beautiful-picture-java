package com.kucaroom.mypicture.DTO;

import com.kucaroom.mypicture.domain.PictureItem;
import lombok.Data;

import java.util.List;

@Data
public class PictureDTO {
    private String title;

    private String content;

    private String coverPicture;

    private List<PictureItem> pictureItems;
}
