package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblNguonvonDautu;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblNguonvonDautuRepository extends JpaRepository<TblNguonvonDautu, Integer>, JpaSpecificationExecutor<TblNguonvonDautu> {
    List<TblNguonvonDautu> findByNguonvon(String nguonvon);
}