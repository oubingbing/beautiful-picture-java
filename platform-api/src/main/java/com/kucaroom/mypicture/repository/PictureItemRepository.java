package com.kucaroom.mypicture.repository;import com.kucaroom.mypicture.domain.PictureItem;import org.springframework.data.jpa.repository.JpaRepository;public interface PictureItemRepository extends JpaRepository<PictureItem,Integer> {}