package com.kucaroom.mypicture.repository;import com.kucaroom.mypicture.domain.QiNiuAcctount;import org.springframework.data.jpa.repository.JpaRepository;public interface QiNiuAccountRepository extends JpaRepository<QiNiuAcctount,Integer> {    QiNiuAcctount findByAppId(Integer appId);    QiNiuAcctount findByType(Integer type);}