package com.kucaroom.mypicture.repository;

import com.kucaroom.mypicture.domain.ViewPictureLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface ViewPictureLogRepository extends JpaRepository<ViewPictureLog,Integer>,JpaSpecificationExecutor<ViewPictureLog> {

}
