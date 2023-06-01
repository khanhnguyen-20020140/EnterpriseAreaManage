package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblKhuChuyennganh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblKhuChuyennganhRepository extends JpaRepository<TblKhuChuyennganh, Long>, JpaSpecificationExecutor<TblKhuChuyennganh> {
    List<TblKhuChuyennganh> findByTen(String ten);
}