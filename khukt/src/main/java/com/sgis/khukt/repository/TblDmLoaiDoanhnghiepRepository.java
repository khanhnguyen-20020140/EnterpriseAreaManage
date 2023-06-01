package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblDmLoaiDoanhnghiep;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDmLoaiDoanhnghiepRepository extends JpaRepository<TblDmLoaiDoanhnghiep, Long>, JpaSpecificationExecutor<TblDmLoaiDoanhnghiep> {
    List<TblDmLoaiDoanhnghiep> findByLoaiDoanhnghiep(String loaiDoanhnghiep);
}