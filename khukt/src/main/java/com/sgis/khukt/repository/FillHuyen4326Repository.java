package com.sgis.khukt.repository;

import com.sgis.khukt.model.FillHuyen4326;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FillHuyen4326Repository extends JpaRepository<FillHuyen4326, Long>, JpaSpecificationExecutor<FillHuyen4326> {
    List<FillHuyen4326> findByTen(String ten);
}