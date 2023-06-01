package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblHatangkythuatPolygon;
import java.util.List;
import java.util.Optional;

import com.sgis.khukt.model.TblTiendoXaydunghatang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblHatangkythuatPolygonRepository extends JpaRepository<TblHatangkythuatPolygon, Integer>, JpaSpecificationExecutor<TblHatangkythuatPolygon> {
    List<TblHatangkythuatPolygon> findByDuanXaydunghatang(@Param("idDuanXdht")Integer id);
    List<TblHatangkythuatPolygon> findByDuandautusanxuat(@Param("idDuanDtsx")Integer id);
    Optional<TblHatangkythuatPolygon> findByGid(@Param("gid")Integer id);




    @Query(value = "SELECT  ten FROM public.tbl_hatangkythuat_line where id_duan_xdht = :idDuanXdht\n" +
            "UNION SELECT  ten FROM public.tbl_hatangkythuat_point where id_duan_xdht = :idDuanXdht\n" +
            "UNION SELECT  ten FROM public.tbl_hatangkythuat_polygon where id_duan_xdht = :idDuanXdht\n"
            ,nativeQuery = true)
    List<String> getTenThanhphanByDuanxaydunghatang(@Param("idDuanXdht")Integer id);

    @Query(value = "SELECT  ten FROM public.tbl_hatangsanxuat_polygon where id_duan_dtsx = :idDuanDtsx\n"
            ,nativeQuery = true)
    List<String> getTenThanhphanByDuandautusanxuat(@Param("idDuanDtsx")Integer id);



}