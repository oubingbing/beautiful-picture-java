package com.kucaroom.mypicture.service.impl;import com.kucaroom.mypicture.DTO.UserDTO;import com.kucaroom.mypicture.domain.CollectPictureLog;import com.kucaroom.mypicture.domain.PictureItem;import com.kucaroom.mypicture.enums.ResponseEnum;import com.kucaroom.mypicture.exception.ApiException;import com.kucaroom.mypicture.repository.CollectPictureLogRepository;import com.kucaroom.mypicture.service.CollectPictureLogService;import com.kucaroom.mypicture.service.PictureItemService;import com.kucaroom.mypicture.service.UserService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.domain.Specification;import org.springframework.stereotype.Service;import javax.persistence.criteria.CriteriaBuilder;import javax.persistence.criteria.CriteriaQuery;import javax.persistence.criteria.Predicate;import javax.persistence.criteria.Root;import java.util.ArrayList;import java.util.List;@Servicepublic class CollectPictureLogServiceImpl implements CollectPictureLogService {    @Autowired    private CollectPictureLogRepository repository;    @Autowired    private UserService userService;    @Autowired    private PictureItemService pictureItemService;    /**     * 收藏图片     *     * @author yezi     * @param userId     * @param pictureItemId     * @return     */    @Override    public CollectPictureLog create(Integer userId, Integer pictureItemId) {        UserDTO userDTO = userService.finById(userId);        if(userDTO == null){            throw new ApiException(ResponseEnum.USER_NOT_EXIST);        }        PictureItem pictureItem = pictureItemService.findById(pictureItemId);        if(pictureItem == null){            throw new ApiException(ResponseEnum.PICTURE_ITEM_NOT_EXIST);        }        CollectPictureLog collectPictureLog = new CollectPictureLog();        collectPictureLog.setUserId(userId);        collectPictureLog.setPictureItemId(pictureItemId);        CollectPictureLog log = repository.save(collectPictureLog);        if(log == null){            throw new ApiException(ResponseEnum.PICTURE_COLLECT_FAIL);        }        return log;    }    /**     * 查询浏览列表     *     * @author yezi     * @param userId     * @param pageable     * @return     */    @Override    public Page<CollectPictureLog> list(Integer userId, Pageable pageable) {        Specification<CollectPictureLog> specification = new Specification<CollectPictureLog>() {            @Override            public Predicate toPredicate(Root<CollectPictureLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {                List<Predicate> predicateList = new ArrayList<>();                predicateList.add(criteriaBuilder.equal(root.get("userId"),userId));                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));            }        };        Page<CollectPictureLog> pages = repository.findAll(specification,pageable);        return pages;    }}