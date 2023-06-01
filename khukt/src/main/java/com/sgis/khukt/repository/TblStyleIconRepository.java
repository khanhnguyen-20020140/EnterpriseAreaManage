package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblStyleIcon;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblStyleIconRepository extends JpaRepository<TblStyleIcon, Long>, JpaSpecificationExecutor<TblStyleIcon> {
    public List<TblStyleIcon> findAllOrderByID();
}