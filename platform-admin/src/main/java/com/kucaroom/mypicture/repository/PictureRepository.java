package com.kucaroom.mypicture.repository;

import com.kucaroom.mypicture.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PictureRepository extends JpaRepository<Picture,Integer>,JpaSpecificationExecutor<Picture> {

}
