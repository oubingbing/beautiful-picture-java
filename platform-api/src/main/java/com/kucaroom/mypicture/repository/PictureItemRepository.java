package com.kucaroom.mypicture.repository;import com.kucaroom.mypicture.domain.PictureItem;import org.springframework.data.jpa.repository.JpaRepository;import org.springframework.data.jpa.repository.JpaSpecificationExecutor;public interface PictureItemRepository extends JpaRepository<PictureItem,Integer>,JpaSpecificationExecutor<PictureItem> {}