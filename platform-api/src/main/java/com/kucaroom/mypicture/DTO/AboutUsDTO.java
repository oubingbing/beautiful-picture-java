package com.kucaroom.mypicture.DTO;

import lombok.Data;

@Data
public class AboutUsDTO {
    private Integer id;

    private Integer appId;

    private String logo;

    private String description;

    private String publicNumber;

    private String WeChatNumber;

    private String phone;

    private String email;

    private String qrCode;

    private String createAt;
}
