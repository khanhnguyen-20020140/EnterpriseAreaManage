package com.sgis.khukt.repository;

import com.sgis.khukt.model.FillXa4326;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FillXa4326Repository extends JpaRepository<FillXa4326, Long>, JpaSpecificationExecutor<FillXa4326> {
    List<FillXa4326> findByTen(String ten);
}