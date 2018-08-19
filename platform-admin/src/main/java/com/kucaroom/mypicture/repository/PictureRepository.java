package com.kucaroom.mypicture.repository;

import com.kucaroom.mypicture.domain.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture,Integer> {
}
