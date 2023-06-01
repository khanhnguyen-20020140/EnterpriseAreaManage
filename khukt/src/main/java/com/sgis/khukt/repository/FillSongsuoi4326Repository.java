package com.sgis.khukt.repository;

import com.sgis.khukt.model.FillSongsuoi4326;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FillSongsuoi4326Repository extends JpaRepository<FillSongsuoi4326, Long>, JpaSpecificationExecutor<FillSongsuoi4326> {
    List<FillSongsuoi4326> findByTen(String ten);
}