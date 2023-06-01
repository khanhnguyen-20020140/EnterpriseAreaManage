package com.sgis.khukt.repository;

import com.sgis.khukt.model.FillTinh4326;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FillTinh4326Repository extends JpaRepository<FillTinh4326, Long>, JpaSpecificationExecutor<FillTinh4326> {
    List<FillTinh4326> findByTen(String ten);
}