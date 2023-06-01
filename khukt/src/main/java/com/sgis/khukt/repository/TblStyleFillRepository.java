package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblStyleFill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblStyleFillRepository extends JpaRepository<TblStyleFill, Long>, JpaSpecificationExecutor<TblStyleFill> {
    public List<TblStyleFill> findAllOrderByID();
}