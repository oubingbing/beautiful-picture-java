package com.kucaroom.mypicture.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PictureForm {

    private String title;

    private String content;

    @NotEmpty(message = "图集封面不能为空！")
    private String coverPicture;

    @NotEmpty(message = "图集不能为空！")
    private String pictures;
}