package com.kucaroom.mypicture.service.impl;import com.kucaroom.mypicture.DTO.PictureDTO;import com.kucaroom.mypicture.convert.PictureToPictureDTO;import com.kucaroom.mypicture.domain.Picture;import com.kucaroom.mypicture.enums.ResponseEnum;import com.kucaroom.mypicture.exception.ApiException;import com.kucaroom.mypicture.repository.PictureRepository;import com.kucaroom.mypicture.service.PictureService;import org.springframework.beans.BeanUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageImpl;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.domain.Specification;import org.springframework.stereotype.Service;import org.springframework.util.CollectionUtils;import javax.persistence.criteria.CriteriaBuilder;import javax.persistence.criteria.CriteriaQuery;import javax.persistence.criteria.Predicate;import javax.persistence.criteria.Root;import java.util.ArrayList;import java.util.List;import java.util.Optional;@Servicepublic class PictureServiceImpl implements PictureService {    @Autowired    private PictureRepository pictureRepository;    /**     * 获取图集的分页列表信息     *     * @author yezi     * @param pageable     * @param appId     * @param status     * @return     */    @Override    public Page<PictureDTO> findByAppId(Pageable pageable,Integer appId,Integer status) {        Specification<Picture> specification = new Specification<Picture>() {            //测试            @Override            public Predicate toPredicate(Root<Picture> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {                List<Predicate> predicateList = new ArrayList<>();                predicateList.add(criteriaBuilder.equal(root.get("status"),status));                predicateList.add(criteriaBuilder.equal(root.get("appId"),appId));                predicateList.add(criteriaBuilder.isNull(root.get("deletedAt")));                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));            }        };        Page<Picture> picturePage = pictureRepository.findAll(specification,pageable);        //数据转化        List<PictureDTO> pictureList = PictureToPictureDTO.convert(picturePage.getContent());        Page<PictureDTO> pictureDTOS = new PageImpl<PictureDTO>(pictureList,pageable,picturePage.getTotalElements());        return pictureDTOS;    }    /**     * 获取单个图集信息     *     * @author yezi     * @param id     * @return     */    @Override    public PictureDTO findById(Integer id) {        Optional<Picture> pictureOptional = pictureRepository.findById(id);        Picture picture = pictureOptional.get();        if(picture == null){            return null;        }        PictureDTO pictureDTO = new PictureDTO();        BeanUtils.copyProperties(picture,pictureDTO);        return pictureDTO;    }    @Override    public Integer incrementViewNumber(Integer pictureId){        Optional<Picture> pictureOptional = pictureRepository.findById(pictureId);        Picture picture = pictureOptional.get();        if(picture == null){            throw new ApiException(ResponseEnum.PICTURE_NOT_EXIST);        }        picture.setViewNum(picture.getViewNum() + 1);        Picture result = pictureRepository.save(picture);        if(result == null){            throw new ApiException(ResponseEnum.PICTURE_UPDATE_VIEW_NUMBER_FAIL);        }        return result.getViewNum();    }}