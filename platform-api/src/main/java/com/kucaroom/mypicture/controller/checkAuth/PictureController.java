package com.kucaroom.mypicture.controller.checkAuth;import com.kucaroom.mypicture.DTO.PictureDTO;import com.kucaroom.mypicture.convert.CollectPictureLogToRO;import com.kucaroom.mypicture.convert.PictureDTOPictureRO;import com.kucaroom.mypicture.convert.PictureItemToPictureItemRO;import com.kucaroom.mypicture.convert.ViewPictureLogToRO;import com.kucaroom.mypicture.domain.*;import com.kucaroom.mypicture.enums.DownloadPictureType;import com.kucaroom.mypicture.enums.PictureStatusEnum;import com.kucaroom.mypicture.enums.ViewPictureType;import com.kucaroom.mypicture.responseObject.*;import com.kucaroom.mypicture.service.*;import com.kucaroom.mypicture.service.impl.DownloadPictureLogServiceImpl;import com.kucaroom.mypicture.service.impl.PictureItemServiceImpl;import com.kucaroom.mypicture.service.impl.ViewPictureLogServiceImpl;import com.kucaroom.mypicture.util.ResponseData;import com.kucaroom.mypicture.util.ResponseUtil;import lombok.extern.slf4j.Slf4j;import net.sf.json.JSONObject;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageRequest;import org.springframework.data.domain.Sort;import org.springframework.web.bind.annotation.*;import javax.servlet.http.HttpServletRequest;import java.util.ArrayList;import java.util.List;@Slf4j@RestController@RequestMapping("/picture")public class PictureController {    @Autowired    private PictureService pictureService;    @Autowired    private PictureItemServiceImpl pictureItemService;    @Autowired    private ViewPictureLogServiceImpl viewPictureLogService;    @Autowired    private CollectPictureLogService collectPictureLogService;    @Autowired    private DownloadPictureLogServiceImpl downloadPictureLogService;    /**     * 获取图集列表     *     * @author yezi     * @param page     * @param size     * @return     */    @GetMapping("/list")    public ResponseData<List<PictureRO>> list(@RequestParam(value = "pageNumber",defaultValue = "0")Integer page,                                              @RequestParam(value = "pageSize",defaultValue = "10")Integer size){        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Integer appId = 74;        Page<PictureDTO> pictureDTOPage = pictureService.findByAppId(pageRequest,appId,PictureStatusEnum.PICTURE_UP.getCode());        List<PictureRO> result = PictureDTOPictureRO.convert(pictureDTOPage.getContent());        return ResponseUtil.success(result);    }    /**     * 获取图集详情信息     *     * @author yezi     * @param id     * @return     */    @GetMapping("/detail/{id}")    public ResponseData<List<PictureItemRO>> pictureDetail(@PathVariable("id") Integer id,                                                           @RequestParam(value = "pageNumber",defaultValue = "0")Integer page,                                                           @RequestParam(value = "pageSize",defaultValue = "10")Integer size,                                                           HttpServletRequest request){        Integer userId = (Integer)request.getAttribute("userId");        PageRequest pageRequest = new PageRequest(page,size);        List<PictureItemRO> list = pictureItemService.pictureItems(id,pageRequest);        return ResponseUtil.success(pictureItemService.checkCollect(userId,list));    }    /**     * 新增浏览数据     *     * @author yezi     * @param pictureId     * @return     */    @PostMapping("/view/{pictureId}")    public ResponseData<ViewPictureLog> viewPictureLog(@PathVariable("pictureId") Integer pictureId,                                                       @RequestBody JSONObject jsonObject,                                                       HttpServletRequest request){        Integer viewType = (Integer) jsonObject.get("type");        Integer userId = (Integer)request.getAttribute("userId");        ViewPictureLog log = viewPictureLogService.create(userId,pictureId,viewType);        return ResponseUtil.success(log);    }    /**     * 收藏图片     *     * @autho     * @param pictureId     * @param request     * @return     */    @PostMapping("/collect/{pictureId}")    public ResponseData<CollectPictureLog> collectPicture(@PathVariable("pictureId") Integer pictureId,                                                          HttpServletRequest request){        Integer userId = (Integer)request.getAttribute("userId");        CollectPictureLog log = collectPictureLogService.create(userId,pictureId);        return ResponseUtil.success(log);    }    /**     * 取消收藏图片     *     * @autho     * @param pictureId     * @param request     * @return     */    @PostMapping("/collect/{pictureId}/cancel")    public ResponseData<CollectPictureLog> cancelCollectPicture(@PathVariable("pictureId") Integer pictureId,                                                          HttpServletRequest request){        Integer userId = (Integer)request.getAttribute("userId");        CollectPictureLog log = collectPictureLogService.cancel(userId,pictureId);        return ResponseUtil.success(log);    }    /**     * 记录下载日志     *     * @author yezi     * @param pictureId     * @param request     * @return     */    @PostMapping("/download_log/{pictureId}")    public ResponseData<DownLoadPictureLog> recordDownloadLog(@PathVariable("pictureId")Integer pictureId,                                                              @RequestBody JSONObject jsonObject,                                                              HttpServletRequest request){        Integer userId = (Integer)request.getAttribute("userId");        Integer type = (Integer) jsonObject.get("type");        DownLoadPictureLog log = downloadPictureLogService.create(userId,pictureId,type);        return ResponseUtil.success(log);    }    /**     * 浏览历史     *     * @author yezi     * @param request     * @param page     * @param size     * @return     */    @GetMapping("/view/history")    public ResponseData<List<ViewPictureLog>> viewPictureLogs(HttpServletRequest request,                                                              @RequestParam(value = "pageNumber",defaultValue = "0")Integer page,                                                              @RequestParam(value = "pageSize",defaultValue = "10")Integer size){        Integer userId = (Integer) request.getAttribute("userId");        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Integer type = ViewPictureType.PICTURE_ITEM.getCode();        Page<ViewPictureLog> list = viewPictureLogService.list(userId,type,pageRequest);        List<ViewPictureLogRO> result = ViewPictureLogToRO.convert(list.getContent());        List<ViewPictureLogRO> viewPictureLogROList = viewPictureLogService.checkCollect(userId,viewPictureLogService.getPictureInfo(result));        return ResponseUtil.success(viewPictureLogROList);    }    /**     * 下载历史     *     * @author yezi     * @param request     * @param page     * @param size     * @return     */    @GetMapping("/download/history")    public ResponseData<DownLoadPictureLog> downloadPictureLogs(HttpServletRequest request,                                                        @RequestParam(value = "pageNumber",defaultValue = "0")Integer page,                                                        @RequestParam(value = "pageSize",defaultValue = "10")Integer size){        Integer userId = (Integer) request.getAttribute("userId");        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Page<DownLoadPictureLog> list = downloadPictureLogService.list(userId,pageRequest);        List<DownloadPictureRO> result = downloadPictureLogService.getPictureInfo(list.getContent());        List<DownloadPictureRO> downloadPictureROList = downloadPictureLogService.checkCollect(userId,result);        return ResponseUtil.success(downloadPictureROList);    }    /**     * 收藏历史     *     * @author yezi     * @param request     * @param page     * @param size     * @return     */    @GetMapping("/collect/history")    public ResponseData<List<CollectPictureLogRO>> collectPictureLogs(HttpServletRequest request,                                                               @RequestParam(value = "pageNumber",defaultValue = "0")Integer page,                                                               @RequestParam(value = "pageSize",defaultValue = "10")Integer size){        Integer userId = (Integer) request.getAttribute("userId");        Sort sort = new Sort(Sort.Direction.DESC, "createAt");        PageRequest pageRequest = new PageRequest(page,size,sort);        Page<CollectPictureLog> list = collectPictureLogService.list(userId,pageRequest);        List<CollectPictureLogRO> result = CollectPictureLogToRO.convert(list.getContent());        return ResponseUtil.success(result);    }}