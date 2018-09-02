package com.kucaroom.mypicture.service.impl;

import com.kucaroom.mypicture.DTO.PictureDTO;
import com.kucaroom.mypicture.DTO.UserDTO;
import com.kucaroom.mypicture.domain.ViewPictureLog;
import com.kucaroom.mypicture.enums.ResponseEnum;
import com.kucaroom.mypicture.enums.ViewPictureType;
import com.kucaroom.mypicture.exception.ApiException;
import com.kucaroom.mypicture.repository.ViewPictureLogRepository;
import com.kucaroom.mypicture.service.PictureService;
import com.kucaroom.mypicture.service.UserService;
import com.kucaroom.mypicture.service.ViewPictureLogService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.List;

@Slf4j
@Service
public class ViewPictureLogServiceImpl implements ViewPictureLogService{
    @Autowired
    private ViewPictureLogRepository viewPictureLogRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PictureService pictureService;

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

        ViewPictureLog viewLog = new ViewPictureLog();
        viewLog.setUserId(userId);
        viewLog.setPictureId(pictureId);
        viewLog.setType(type);

        return viewPictureLogRepository.save(viewLog);
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
    public Page<ViewPictureLog> list(Integer userId, Pageable pageable) {

        Specification<ViewPictureLog> specification = new Specification<ViewPictureLog>() {
            @Override
            public Predicate toPredicate(Root<ViewPictureLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                predicateList.add(criteriaBuilder.equal(root.get("userId"),userId));

                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };

        Page<ViewPictureLog> pages = viewPictureLogRepository.findAll(specification,pageable);

        return pages;
    }
}
