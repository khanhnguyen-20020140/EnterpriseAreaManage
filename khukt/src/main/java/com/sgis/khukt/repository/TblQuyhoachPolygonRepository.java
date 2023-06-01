package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblHatangkythuatPolygon;
import com.sgis.khukt.model.TblQuyhoachPolygon;
import com.sgis.khukt.model.TblQuyhoachPolygon;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblQuyhoachPolygonRepository extends JpaRepository<TblQuyhoachPolygon, Integer>, JpaSpecificationExecutor<TblQuyhoachPolygon> {
    List<TblQuyhoachPolygon> findByDuanXaydunghatang(@Param("idDuanXdht")Integer id);

    List<TblQuyhoachPolygon> findByDuandautusanxuat(@Param("idDuanDtsx")Integer id);

    Optional<TblQuyhoachPolygon> findByGid(@Param("gid")Integer id);
}