package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblDmTrangthai;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDmTrangthaiRepository extends JpaRepository<TblDmTrangthai, Long>, JpaSpecificationExecutor<TblDmTrangthai> {
    List<TblDmTrangthai> findByTrangthai(String trangthai);
}