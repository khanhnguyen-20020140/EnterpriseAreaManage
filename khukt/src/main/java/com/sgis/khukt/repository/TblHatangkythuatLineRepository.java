package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblHatangkythuatLine;
import java.util.List;
import java.util.Optional;

import com.sgis.khukt.model.TblHatangkythuatPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblHatangkythuatLineRepository extends JpaRepository<TblHatangkythuatLine, Integer>, JpaSpecificationExecutor<TblHatangkythuatLine> {
    List<TblHatangkythuatLine> findByDuanXaydunghatang(@Param("idDuanXdht")Integer id);
    Optional<TblHatangkythuatLine> findByGid(@Param("gid")Integer id);
    List<TblHatangkythuatLine> findByDuandautusanxuat(@Param("idDuanDtsx")Integer id);
}