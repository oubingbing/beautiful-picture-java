package com.kucaroom.mypicture.controller.checkAuth;import com.kucaroom.mypicture.DTO.PictureDTO;import com.kucaroom.mypicture.convert.PictureDTOPictureRO;import com.kucaroom.mypicture.domain.CollectPictureLog;import com.kucaroom.mypicture.domain.DownLoadPictureLog;import com.kucaroom.mypicture.domain.Picture;import com.kucaroom.mypicture.domain.ViewPictureLog;import com.kucaroom.mypicture.enums.PictureStatusEnum;import com.kucaroom.mypicture.responseObject.PictureRO;import com.kucaroom.mypicture.service.CollectPictureLogService;import com.kucaroom.mypicture.service.DownloadPictureLogService;import com.kucaroom.mypicture.service.PictureService;import com.kucaroom.mypicture.service.ViewPictureLogService;import com.kucaroom.mypicture.util.ResponseData;import com.kucaroom.mypicture.util.ResponseUtil;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.web.bind.annotation.*;import javax.servlet.http.HttpServletRequest;import javax.xml.ws.Response;import java.util.List;@Slf4j@RestController@RequestMapping("/picture")public class PictureController {    @Autowired    private PictureService pictureService;    @Autowired    private ViewPictureLogService viewPictureLogService;    @Autowired    private CollectPictureLogService collectPictureLogService;    @Autowired    private DownloadPictureLogService downloadPictureLogService;    /**     * 获取图集列表     *     * @author yezi     * @param page     * @param size     * @return     */    @GetMapping("/list")    public ResponseData<List<PictureRO>> list(@RequestParam(value = "page",defaultValue = "0")Integer page,                                              @RequestParam(value = "size",defaultValue = "10")Integer size){        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Integer appId = 74;        Page<PictureDTO> pictureDTOPage = pictureService.findByAppId(pageRequest,appId,PictureStatusEnum.PICTURE_UP.getCode());        log.info("数据：{}",pictureDTOPage.getContent().toString());        List<PictureRO> result = PictureDTOPictureRO.convert(pictureDTOPage.getContent());        return ResponseUtil.success(result);    }    /**     * 获取图集详情信息     *     * @author yezi     * @param id     * @return     */    @GetMapping("/detail/{id}")    public ResponseData<Picture> pictureDetail(@PathVariable("id") Integer id){        PictureDTO pictureDTO = pictureService.findById(id);        PictureRO pictureRO = PictureDTOPictureRO.convert(pictureDTO);        return ResponseUtil.success(pictureRO);    }    /**     * 新增浏览数据     *     * @author yezi     * @param pictureId     * @return     */    @PostMapping("/view/{pictureId}")    public ResponseData<ViewPictureLog> viewPictureLog(@PathVariable("pictureId") Integer pictureId,                                                       HttpServletRequest request){        Integer userId = (Integer)request.getAttribute("userId");        ViewPictureLog log = viewPictureLogService.create(userId,pictureId);        return ResponseUtil.success(log);    }    /**     * 收藏图片     *     * @autho     * @param pictureId     * @param request     * @return     */    @PostMapping("/collect/{pictureId}")    public ResponseData<CollectPictureLog> collectPicture(@PathVariable("pictureId") Integer pictureId,                                                            HttpServletRequest request){        Integer userId = (Integer)request.getAttribute("userId");        CollectPictureLog log = collectPictureLogService.create(userId,pictureId);        return ResponseUtil.success(log);    }    /**     * 记录下载日志     *     * @author yezi     * @param pictureId     * @param request     * @return     */    @PostMapping("/download_log/{pictureId}")    public ResponseData<DownLoadPictureLog> recordDownloadLog(@PathVariable("pictureId")Integer pictureId,                                                              HttpServletRequest request){        Integer userId = (Integer)request.getAttribute("userId");        DownLoadPictureLog log = downloadPictureLogService.create(userId,pictureId);        return ResponseUtil.success(log);    }    /**     * 浏览历史     *     * @author yezi     * @param request     * @param page     * @param size     * @return     */    @GetMapping("/view/history")    public ResponseData<ViewPictureLog> viewPictureLogs(HttpServletRequest request,                                                        @RequestParam(value = "page",defaultValue = "0")Integer page,                                                        @RequestParam(value = "size",defaultValue = "10")Integer size){        Integer userId = (Integer) request.getAttribute("userId");        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Page<ViewPictureLog> list = viewPictureLogService.list(userId,pageRequest);        return ResponseUtil.success(list);    }    /**     * 下载历史     *     * @author yezi     * @param request     * @param page     * @param size     * @return     */    @GetMapping("/download/history")    public ResponseData<DownLoadPictureLog> downloadPictureLogs(HttpServletRequest request,                                                        @RequestParam(value = "page",defaultValue = "0")Integer page,                                                        @RequestParam(value = "size",defaultValue = "10")Integer size){        Integer userId = (Integer) request.getAttribute("userId");        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Page<DownLoadPictureLog> list = downloadPictureLogService.list(userId,pageRequest);        return ResponseUtil.success(list);    }    /**     * 收藏历史     *     * @author yezi     * @param request     * @param page     * @param size     * @return     */    @GetMapping("/collect/history")    public ResponseData<DownLoadPictureLog> collectPictureLogs(HttpServletRequest request,                                                               @RequestParam(value = "page",defaultValue = "0")Integer page,                                                               @RequestParam(value = "size",defaultValue = "10")Integer size){        Integer userId = (Integer) request.getAttribute("userId");        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Page<CollectPictureLog> list = collectPictureLogService.list(userId,pageRequest);        return ResponseUtil.success(list);    }}