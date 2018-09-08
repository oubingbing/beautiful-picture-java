package com.kucaroom.mypicture.repository;

import com.kucaroom.mypicture.domain.AboutUs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AboutUsRepository extends JpaRepository<AboutUs,Integer> {
    AboutUs findByAppId(Integer appId);
}
