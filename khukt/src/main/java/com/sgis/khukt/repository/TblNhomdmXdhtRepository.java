package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblNhomdmXdht;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblNhomdmXdhtRepository extends JpaRepository<TblNhomdmXdht, Long>, JpaSpecificationExecutor<TblNhomdmXdht> {
    List<TblNhomdmXdht> findByTennhom(String tennhom);
}