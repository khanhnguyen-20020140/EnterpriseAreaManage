package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblDmLinhvucSanxuatkinhdoanh;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDmLinhvucSanxuatkinhdoanhRepository extends JpaRepository<TblDmLinhvucSanxuatkinhdoanh, Long>, JpaSpecificationExecutor<TblDmLinhvucSanxuatkinhdoanh> {
    List<TblDmLinhvucSanxuatkinhdoanh> findByLinhvucSanxuatkinhdoanh(String linhvucSanxuatkinhdoanh);
}