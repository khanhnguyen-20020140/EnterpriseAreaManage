package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblDmVonDautu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDmVonDautuRepository extends JpaRepository<TblDmVonDautu, Long>, JpaSpecificationExecutor<TblDmVonDautu> {
    List<TblDmVonDautu> findByVonDautu(String vonDautu);
}