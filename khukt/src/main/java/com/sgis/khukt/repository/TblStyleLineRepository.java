package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblStyleLine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblStyleLineRepository extends JpaRepository<TblStyleLine, Long>, JpaSpecificationExecutor<TblStyleLine> {
    public List<TblStyleLine> findAllOrderByID();
}