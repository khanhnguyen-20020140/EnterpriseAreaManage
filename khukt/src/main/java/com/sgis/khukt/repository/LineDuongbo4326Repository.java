package com.sgis.khukt.repository;

import com.sgis.khukt.model.LineDuongbo4326;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LineDuongbo4326Repository extends JpaRepository<LineDuongbo4326, Long>, JpaSpecificationExecutor<LineDuongbo4326> {
    List<LineDuongbo4326> findByTen(String ten);
}