package com.kucaroom.mypicture.enums;import lombok.Getter;@Getterpublic enum PictureStatusEnum {    PICTURE_UP(1,"图片上架"),    PICTURE_DOWN(2,"图片下架"),    ;    private Integer code;    private String message;    PictureStatusEnum(Integer code,String message){        this.code = code;        this.message = message;    }}