package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblLoaiquyhoach;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblLoaiquyhoachRepository extends JpaRepository<TblLoaiquyhoach, Long>, JpaSpecificationExecutor<TblLoaiquyhoach> {
    List<TblLoaiquyhoach> findByTen(String ten);
}