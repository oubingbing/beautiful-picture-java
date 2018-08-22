package com.kucaroom.mypicture.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kucaroom.mypicture.DTO.PictureDTO;
import com.kucaroom.mypicture.domain.PictureItem;
import com.kucaroom.mypicture.form.PictureForm;

import java.util.ArrayList;
import java.util.List;

public class PictureFormToPictureDTO {
    public static PictureDTO convert(PictureForm pictureForm){
        Gson gson = new Gson();
        PictureDTO pictureDTO = new PictureDTO();
        pictureDTO.setTitle(pictureForm.getTitle());
        pictureDTO.setCoverPicture(pictureForm.getCoverPicture());
        pictureDTO.setContent(pictureForm.getContent());

        List<PictureItem> pictureItems = new ArrayList<>();
        pictureItems = gson.fromJson(pictureForm.getPictures(),new TypeToken<List<PictureItem>>(){}.getType());

        pictureDTO.setPictureItems(pictureItems);

        return pictureDTO;
    }
}
