package com.kucaroom.mypicture.repository;

import com.kucaroom.mypicture.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Integer> {
}
