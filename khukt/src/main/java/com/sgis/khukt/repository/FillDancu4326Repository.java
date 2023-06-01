package com.sgis.khukt.repository;

import com.sgis.khukt.model.FillDancu4326;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FillDancu4326Repository extends JpaRepository<FillDancu4326, Long>, JpaSpecificationExecutor<FillDancu4326> {
    List<FillDancu4326> findByTen(String ten);
}