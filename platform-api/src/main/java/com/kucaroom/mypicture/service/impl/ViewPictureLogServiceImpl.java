package com.kucaroom.mypicture.service.impl;

import com.kucaroom.mypicture.DTO.PictureDTO;
import com.kucaroom.mypicture.DTO.UserDTO;
import com.kucaroom.mypicture.domain.CollectPictureLog;
import com.kucaroom.mypicture.domain.PictureItem;
import com.kucaroom.mypicture.domain.ViewPictureLog;
import com.kucaroom.mypicture.enums.CollectPictureType;
import com.kucaroom.mypicture.enums.ResponseEnum;
import com.kucaroom.mypicture.enums.ViewPictureType;
import com.kucaroom.mypicture.exception.ApiException;
import com.kucaroom.mypicture.mapper.CollectPictureMapper;
import com.kucaroom.mypicture.mapper.PictureItemMapper;
import com.kucaroom.mypicture.mapper.ViewPictureLogMapper;
import com.kucaroom.mypicture.repository.ViewPictureLogRepository;
import com.kucaroom.mypicture.responseObject.PictureItemRO;
import com.kucaroom.mypicture.responseObject.ViewPictureLogRO;
import com.kucaroom.mypicture.service.PictureService;
import com.kucaroom.mypicture.service.UserService;
import com.kucaroom.mypicture.service.ViewPictureLogService;
import com.kucaroom.mypicture.util.PictureInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ViewPictureLogServiceImpl implements ViewPictureLogService{
    @Autowired
    private ViewPictureLogRepository viewPictureLogRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private ViewPictureLogMapper viewPictureLogMapper;

    @Autowired
    private PictureItemMapper pictureItemMapper;

    @Autowired
    private CollectPictureMapper collectPictureMapper;

    /**
     * 新建查看记录
     *
     * @param userId
     * @param pictureId
     * @return
     */
    @Override
    public ViewPictureLog create(Integer userId, Integer pictureId,Integer type) {
        UserDTO user = userService.finById(userId);
        if(user == null){
            throw new ApiException(ResponseEnum.USER_NOT_EXIST);
        }

        if(ViewPictureType.PICTURE.getCode() == type){
            PictureDTO pictureDTO = pictureService.findById(pictureId);
            if(pictureDTO == null){
                throw new ApiException(ResponseEnum.PICTURE_NOT_EXIST);
            }
            pictureService.incrementViewNumber(pictureId);
        }

        ViewPictureLog userViewLog = viewPictureLogMapper.findByUserViewLog(userId,pictureId,type);
        if(userViewLog == null){
            ViewPictureLog viewLog = new ViewPictureLog();
            viewLog.setUserId(userId);
            viewLog.setPictureId(pictureId);
            viewLog.setType(type);
            viewLog.setNumber(1);
            return viewPictureLogRepository.save(viewLog);
        }

        userViewLog.setNumber(userViewLog.getNumber()+1);
        return viewPictureLogRepository.save(userViewLog);
    }

    /**
     * 查询浏览列表
     *
     * @author yezi
     * @param userId
     * @param pageable
     * @return
     */
    @Override
    public Page<ViewPictureLog> list(Integer userId,Integer type, Pageable pageable) {

        Specification<ViewPictureLog> specification = new Specification<ViewPictureLog>() {
            @Override
            public Predicate toPredicate(Root<ViewPictureLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(criteriaBuilder.equal(root.get("userId"),userId));
                predicateList.add(criteriaBuilder.equal(root.get("type"),type));

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        Page<ViewPictureLog> pages = viewPictureLogRepository.findAll(specification,pageable);

        return pages;
    }

    /**
     * 获取图片信息
     *
     * @author yeiz
     * @param list
     * @return
     */
    public List<ViewPictureLogRO> getPictureInfo(List<ViewPictureLogRO> list){
        List<Integer> ids = new ArrayList<>();
        for(ViewPictureLogRO viewPictureLogRO:list){
            ids.add(viewPictureLogRO.getPictureId());
        }

        if(!ids.isEmpty()){
            List<PictureItem> pictureItems = pictureItemMapper.findByInIds(StringUtils.strip(ids.toString(),"[]"));
            if(!pictureItems.isEmpty()){
                for (ViewPictureLogRO viewPictureLogROItem:list){
                    for (PictureItem item:pictureItems){
                        if(viewPictureLogROItem.getPictureId() == item.getId()){
                            viewPictureLogROItem.setPictureInfo(PictureInfoUtil.setPictureInfo(item));
                        }
                    }
                }
            }
        }

        return list;
    }

    /**
     * 检测图片是否已被用户收藏
     *
     * @param userId
     * @param viewPictureLogROList
     * @return
     */
    public List<ViewPictureLogRO> checkCollect(Integer userId, List<ViewPictureLogRO> viewPictureLogROList){
        List<Integer> pictureItemIds = new ArrayList<>();
        for (ViewPictureLogRO viewPictureLogRO:viewPictureLogROList){
            pictureItemIds.add(viewPictureLogRO.getPictureId());
        }

        if(!pictureItemIds.isEmpty()){
            List<CollectPictureLog> collectPictureLogList = collectPictureMapper.findByIds(userId,StringUtils.strip(pictureItemIds.toString(),"[]"));
            if(!collectPictureLogList.isEmpty()){
                for (CollectPictureLog collectItem:collectPictureLogList){
                    for (ViewPictureLogRO viewPictureLogRO:viewPictureLogROList){
                        if(collectItem.getPictureItemId() == viewPictureLogRO.getPictureId()){
                            viewPictureLogRO.setCollect(CollectPictureType.COLLECT.getCode());
                        }
                    }
                }
            }
        }

        return viewPictureLogROList;
    }
}
