package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblDoanhnghiep;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDoanhnghiepRepository extends JpaRepository<TblDoanhnghiep, Integer>, JpaSpecificationExecutor<TblDoanhnghiep> {
    List<TblDoanhnghiep> findByTen(String ten);
}