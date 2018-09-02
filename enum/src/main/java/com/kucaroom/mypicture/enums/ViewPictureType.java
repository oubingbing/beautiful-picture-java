package com.kucaroom.mypicture.enums;

import lombok.Getter;

@Getter
public enum ViewPictureType {

    PICTURE(1,"图集"),
    PICTURE_ITEM(1,"图片"),
    ;

    private Integer code;
    private String message;
    ViewPictureType(Integer code,String message){
        this.code = code;
        this.message = message;
    }
}
