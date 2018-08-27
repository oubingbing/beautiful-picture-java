package com.kucaroom.mypicture.service.impl;import com.kucaroom.mypicture.domain.PictureItem;import com.kucaroom.mypicture.repository.PictureItemRepository;import com.kucaroom.mypicture.service.PictureItemService;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Service;import java.util.Optional;@Servicepublic class PictureItemServiceImpl implements PictureItemService {    @Autowired    private PictureItemRepository repository;    @Override    public PictureItem findById(Integer id) {        Optional<PictureItem> pictureItemOptional = repository.findById(id);        if(pictureItemOptional.isPresent()){            PictureItem pictureItem = pictureItemOptional.get();            return pictureItem;        }        return null;    }}