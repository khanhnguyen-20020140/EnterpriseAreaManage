package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblDmLoaihinhDuan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDmLoaihinhDuanRepository extends JpaRepository<TblDmLoaihinhDuan, Long>, JpaSpecificationExecutor<TblDmLoaihinhDuan> { 
    List<TblDmLoaihinhDuan> findByLoaihinhDuan(String loaihinhDuan);
}