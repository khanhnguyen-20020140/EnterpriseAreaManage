package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblHatangkythuatPolygon;
import com.sgis.khukt.model.TblQuyhoachPoint;
import com.sgis.khukt.model.TblQuyhoachPoint;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblQuyhoachPointRepository extends JpaRepository<TblQuyhoachPoint, Integer>, JpaSpecificationExecutor<TblQuyhoachPoint> {
    List<TblQuyhoachPoint> findByDuanXaydunghatang(@Param("idDuanXdht")Integer id);
    Optional<TblQuyhoachPoint> findByGid(@Param("gid")Integer id);
    List<TblQuyhoachPoint> findByDuandautusanxuat(@Param("idDuanDtsx")Integer id);
}
