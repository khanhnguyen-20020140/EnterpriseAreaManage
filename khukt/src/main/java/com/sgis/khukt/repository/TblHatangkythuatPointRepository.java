package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblHatangkythuatPoint;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblHatangkythuatPointRepository extends JpaRepository<TblHatangkythuatPoint, Integer>, JpaSpecificationExecutor<TblHatangkythuatPoint> {
    List<TblHatangkythuatPoint> findByDuanXaydunghatang(@Param("idDuanXdht")Integer id);
    Optional<TblHatangkythuatPoint> findByGid(@Param("gid")Integer id);
    List<TblHatangkythuatPoint> findByDuandautusanxuat(@Param("idDuanDtsx")Integer id);

}