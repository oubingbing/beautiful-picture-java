package com.kucaroom.mypicture.enums;

import lombok.Getter;

@Getter
public enum DownloadPictureTypeEnum {
    PICTURE(1,"封面图片"),
    PICTURE_ITEM(2,"图片"),
    ;

    private Integer code;
    private String message;
    DownloadPictureTypeEnum(Integer code, String message){
        this.code = code;
        this.message = message;
    }
}
