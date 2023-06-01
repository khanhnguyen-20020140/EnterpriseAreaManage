package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblHatangkythuatPolygon;
import com.sgis.khukt.model.TblQuyhoachLine;
import com.sgis.khukt.model.TblQuyhoachLine;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblQuyhoachLineRepository extends JpaRepository<TblQuyhoachLine, Integer>, JpaSpecificationExecutor<TblQuyhoachLine> {
    List<TblQuyhoachLine> findByDuanXaydunghatang(@Param("idDuanXdht")Integer id);
    Optional<TblQuyhoachLine> findByGid(@Param("gid")Integer id);
    List<TblQuyhoachLine> findByDuandautusanxuat(@Param("idDuanDtsx")Integer id);
}