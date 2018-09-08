package com.kucaroom.mypicture.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ShareForm {

    @NotEmpty(message = "分享的封面图片不能为空")
    String shareImage;

    @NotEmpty(message = "分享的文字不能为空")
    String shareWord;
}
