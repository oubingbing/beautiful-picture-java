package com.kucaroom.mypicture.responseObject;import com.fasterxml.jackson.annotation.JsonProperty;import lombok.Data;@Datapublic class PictureItemRes {    @JsonProperty("id")    private Integer id;    @JsonProperty("pictureId")    private String pictureId;    @JsonProperty("pictureUrl")    private String picture;    @JsonProperty("view")    private Integer viewNum;    @JsonProperty("downLoad")    private Integer downLoadNum;    @JsonProperty("share")    private Integer shareNum;    @JsonProperty("status")    private Integer status;}