package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblDmLoaihinhKhucn;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDmLoaihinhKhucnRepository extends JpaRepository<TblDmLoaihinhKhucn, Long>, JpaSpecificationExecutor<TblDmLoaihinhKhucn> { 
    List<TblDmLoaihinhKhucn> findByTen(String ten);
}