package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblKhuKinhte;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblKhuKinhteRepository extends JpaRepository<TblKhuKinhte, Long>, JpaSpecificationExecutor<TblKhuKinhte> {
    List<TblKhuKinhte> findByTen(String ten);
}