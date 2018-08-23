package com.kucaroom.mypicture.service.impl;import com.kucaroom.mypicture.DTO.PictureDTO;import com.kucaroom.mypicture.converter.PictureToPictureDTO;import com.kucaroom.mypicture.domain.Picture;import com.kucaroom.mypicture.domain.PictureItem;import com.kucaroom.mypicture.enums.PictureStatusEnum;import com.kucaroom.mypicture.enums.ResponseEnum;import com.kucaroom.mypicture.exception.WebApiException;import com.kucaroom.mypicture.repository.PictureItemsRepository;import com.kucaroom.mypicture.repository.PictureRepository;import com.kucaroom.mypicture.service.PictureService;import lombok.extern.slf4j.Slf4j;import org.springframework.beans.BeanUtils;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.data.domain.Page;import org.springframework.data.domain.PageImpl;import org.springframework.data.domain.Pageable;import org.springframework.data.jpa.domain.Specification;import org.springframework.stereotype.Service;import javax.persistence.criteria.CriteriaBuilder;import javax.persistence.criteria.CriteriaQuery;import javax.persistence.criteria.Predicate;import javax.persistence.criteria.Root;import java.util.ArrayList;import java.util.List;import java.util.Optional;@Slf4j@Servicepublic class PictureServiceImpl implements PictureService {    @Autowired    private PictureRepository pictureRepository;    @Autowired    private PictureItemsRepository pictureItemsRepository;    @Override    public PictureDTO create(PictureDTO pictureDTO){        Picture picture = new Picture();        BeanUtils.copyProperties(pictureDTO,picture);        picture.setAvatar("test");        picture.setStatus(PictureStatusEnum.PICTURE_DOWN.getCode());        Picture pictureResult = pictureRepository.save(picture);        if(pictureResult == null){            throw new WebApiException(ResponseEnum.PICTURE_CREATE_ERROR);        }        List<PictureItem> pictureItemList = new ArrayList<>();        for (PictureItem pictureItem:pictureDTO.getPictureItems()){            pictureItem.setPictureId(pictureResult.getId());            pictureItemList.add(pictureItem);            log.info("picture_item：{}",pictureItem.toString());        }        List<PictureItem> pictureItemResults = pictureItemsRepository.saveAll(pictureItemList);        if(pictureItemResults.size() != pictureDTO.getPictureItems().size()){            throw new WebApiException(ResponseEnum.PICTURE_CREATE_ERROR);        }        BeanUtils.copyProperties(picture,pictureDTO);        pictureDTO.setPictureItems(pictureItemResults);        return pictureDTO;    }    @Override    public Page<PictureDTO> findByStatus(Pageable pageable,Integer appId, Integer status) {        Specification<Picture> pictureSpecification = new Specification<Picture>() {            @Override            public Predicate toPredicate(Root<Picture> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {                List<Predicate> predicateList = new ArrayList<>();                predicateList.add(criteriaBuilder.equal(root.get("status"),status));                predicateList.add(criteriaBuilder.equal(root.get("appId"),appId));                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));            }        };        Page<Picture> picturePage = pictureRepository.findAll(pictureSpecification,pageable);        //数据转化        List<PictureDTO> pictureList = PictureToPictureDTO.convert(picturePage.getContent());        Page<PictureDTO> pictureDTOS = new PageImpl<PictureDTO>(pictureList,pageable,picturePage.getTotalElements());        return pictureDTOS;    }    @Override    public PictureDTO findById(Integer id) {        Optional<Picture> pictureOP = pictureRepository.findById(id);        Picture picture = pictureOP.get();        PictureDTO pictureDTO = new PictureDTO();        BeanUtils.copyProperties(picture,pictureDTO);        return pictureDTO;    }}