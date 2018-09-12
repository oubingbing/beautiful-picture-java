package com.kucaroom.mypicture.controller.checkAuth;

import com.kucaroom.mypicture.DTO.PictureDTO;
import com.kucaroom.mypicture.DTO.QiNiuAccountDTO;
import com.kucaroom.mypicture.converter.PictureDTOPictureRes;
import com.kucaroom.mypicture.converter.PictureFormToPictureDTO;
import com.kucaroom.mypicture.domain.Picture;
import com.kucaroom.mypicture.enums.ResponseEnum;
import com.kucaroom.mypicture.exception.WebApiException;
import com.kucaroom.mypicture.form.PictureForm;
import com.kucaroom.mypicture.responseObject.PictureItemRes;
import com.kucaroom.mypicture.responseObject.PictureRes;
import com.kucaroom.mypicture.service.PictureItemsService;
import com.kucaroom.mypicture.service.PictureService;
import com.kucaroom.mypicture.service.QiNiuService;
import com.kucaroom.mypicture.util.PageUtil;
import com.kucaroom.mypicture.util.ResponseData;
import com.kucaroom.mypicture.util.ResponseUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/picture")
public class PictureController {

    @Autowired
    private PictureService pictureService;

    @Autowired
    private PictureItemsService pictureItemsService;

    @Autowired
    private QiNiuService qiNiuService;

    @GetMapping("")
    public ModelAndView pictureList(HttpServletRequest request){
        Integer appId = (Integer) request.getAttribute("appId");
        QiNiuAccountDTO qiNiuAccountDTO = qiNiuService.findAccountByAppId(appId);
        Map<String,Object> map = new HashMap<>();
        map.put("domain",qiNiuAccountDTO.getDomain());
        map.put("uploadDomain",qiNiuAccountDTO.getUrl());
        return new ModelAndView("/admin/pictures",map);
    }

    @ResponseBody
    @GetMapping("/list")
    public ResponseData<List<PictureDTO>> pictureList(@RequestParam(value = "pageNumber",defaultValue = "0")Integer pageNumber,
                                                      @RequestParam(value = "pageSize",defaultValue = "20")Integer pageSize,
                                                      HttpServletRequest request){
        Integer appId = (Integer)request.getAttribute("appId");


        Sort sort = new Sort(Sort.Direction.DESC, "createAt");
        PageRequest pageRequest = new PageRequest(pageNumber-1,pageSize,sort);
        Page<PictureDTO> pictureDTOS = pictureService.findByAppId(pageRequest,appId);
        List<PictureRes> pictureResList = PictureDTOPictureRes.convert(pictureDTOS.getContent());

        return ResponseUtil.success(PageUtil.convert(pictureResList,pictureDTOS));
    }

    @GetMapping("/detail")
    public ModelAndView detailView(@RequestParam(value = "id")Integer id,HttpServletRequest request){
        Integer appId = (Integer)request.getAttribute("appId");
        QiNiuAccountDTO qiNiuAccountDTO = qiNiuService.findAccountByAppId(appId);
        Map<String,Object> map = new HashMap<>();
        map.put("domain",qiNiuAccountDTO.getDomain());
        map.put("uploadDomain",qiNiuAccountDTO.getUrl());
        map.put("id",id);
        return new ModelAndView("/admin/pictureDetail",map);
    }

    @ResponseBody
    @GetMapping("/{id}/detail")
    public ResponseData<List<PictureItemRes>> detail(@PathVariable(value = "id")Integer id){
        List<PictureItemRes> list = pictureItemsService.findByPictureId(id);
        return ResponseUtil.success(list);
    }

    @Transactional
    @ResponseBody
    @DeleteMapping("/picture_item/delete/{id}")
    public ResponseData deletePictureDetail(@PathVariable(value = "id")Integer id,HttpServletRequest request){
        Integer appId = (Integer)request.getAttribute("appId");
        Boolean result = pictureItemsService.delete(appId,id);
        return ResponseUtil.success(true);
    }

    @Transactional
    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public ResponseData deletePicture(@PathVariable(value = "id")Integer id,HttpServletRequest request){
        Integer appId = (Integer)request.getAttribute("appId");
        Integer result = pictureService.deleteById(appId,id);
        return ResponseUtil.success(result);
    }

    @Transactional
    @ResponseBody
    @PostMapping("/status/{id}")
    public ResponseData changePictureStatus(@PathVariable(value = "id")Integer id,
                                            @RequestBody JSONObject jsonObject,
                                            HttpServletRequest request){
        Integer status = (Integer) jsonObject.get("status");
        Integer appId = (Integer)request.getAttribute("appId");
        Picture picture = pictureService.updateStatus(appId,id,status);
        PictureDTO pictureDTO = new PictureDTO();
        BeanUtils.copyProperties(picture,pictureDTO);

        return ResponseUtil.success(pictureDTO);
    }

    /**
     * 上传图集界面
     *
     * @author yezi
     * @return
     */
    @GetMapping("/upload")
    public ModelAndView uploadPicture(HttpServletRequest request){
        Integer appId = (Integer)request.getAttribute("appId");
        QiNiuAccountDTO qiNiuAccountDTO = qiNiuService.findAccountByAppId(appId);
        Map<String,Object> map = new HashMap<>();
        map.put("domain",qiNiuAccountDTO.getDomain());
        map.put("uploadDomain",qiNiuAccountDTO.getUrl());
        map.put("zone",qiNiuAccountDTO.getRegion());

        return new ModelAndView("/admin/upload",map);
    }

    /**
     * 上传图集
     *
     * @author yezi
     * @param pictureForm
     * @param bindingResult
     * @return
     */
    @Transactional
    @ResponseBody
    @PostMapping("/upload")
    public ResponseData upload(@Valid PictureForm pictureForm, BindingResult bindingResult,HttpServletRequest request){
        Integer appId = (Integer)request.getAttribute("appId");
        if(bindingResult.hasErrors()){
            throw new WebApiException(ResponseEnum.PICTURE_UPLOAD_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }

        PictureDTO pictureDTO = PictureFormToPictureDTO.convert(pictureForm);
        pictureDTO.setAppId(appId);
        PictureDTO result = pictureService.create(pictureDTO);
        if(result == null){
            throw new WebApiException(ResponseEnum.PICTURE_CREATE_ERROR);
        }

        //装换数据格式
        PictureRes response = PictureDTOPictureRes.convert(result);

        return ResponseUtil.success(response);
    }


}
