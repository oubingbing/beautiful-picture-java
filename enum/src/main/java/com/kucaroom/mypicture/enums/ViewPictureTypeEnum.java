package com.kucaroom.mypicture.enums;

import lombok.Getter;

@Getter
public enum ViewPictureTypeEnum {

    PICTURE(1,"图集"),
    PICTURE_ITEM(2,"图片"),
    ;

    private Integer code;
    private String message;
    ViewPictureTypeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
