package com.kucaroom.mypicture.controller;import com.kucaroom.mypicture.DTO.PictureDTO;import com.kucaroom.mypicture.convert.PictureDTOPictureRO;import com.kucaroom.mypicture.domain.Picture;import com.kucaroom.mypicture.enums.PictureStatusEnum;import com.kucaroom.mypicture.responseObject.PictureRO;import com.kucaroom.mypicture.service.PictureService;import com.kucaroom.mypicture.util.ResponseData;import com.kucaroom.mypicture.util.ResponseUtil;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.web.bind.annotation.*;import java.util.List;@Slf4j@RestController@RequestMapping("/picture")public class PictureController {    @Autowired    private PictureService pictureService;    @GetMapping("/list")    public ResponseData<List<PictureRO>> list(@RequestParam(value = "page",defaultValue = "0")Integer page,                                              @RequestParam(value = "size",defaultValue = "10")Integer size){        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Integer appId = 74;        Page<PictureDTO> pictureDTOPage = pictureService.findByAppId(pageRequest,appId,PictureStatusEnum.PICTURE_UP.getCode());        log.info("数据：{}",pictureDTOPage.getContent().toString());        List<PictureRO> result = PictureDTOPictureRO.convert(pictureDTOPage.getContent());        return ResponseUtil.success(result);    }    @GetMapping("/detail/{id}")    public ResponseData<Picture> pictureDetail(@PathVariable("id") Integer id){        PictureDTO pictureDTO = pictureService.findById(id);        PictureRO pictureRO = PictureDTOPictureRO.convert(pictureDTO);        return ResponseUtil.success(pictureRO);    }}