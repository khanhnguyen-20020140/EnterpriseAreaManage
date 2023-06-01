package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblTiendoXaydunghatang;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TblTiendoXaydunghatangRepository extends JpaRepository<TblTiendoXaydunghatang, Integer>, JpaSpecificationExecutor<TblTiendoXaydunghatang> {

    @Query(value="SELECT id, duan_xdht_id, duan_dtsx_id, ngaybaocao FROM tbl_tiendo_xaydunghatang t where duan_xdht_id is not null order by duan_xdht_id,ngaybaocao",nativeQuery = true)
    List<TblTiendoXaydunghatang> findAllXDHT();


    @Query(value="SELECT id, duan_xdht_id, duan_dtsx_id, ngaybaocao FROM tbl_tiendo_xaydunghatang t where duan_dtsx_id is not null order by duan_dtsx_id,ngaybaocao",nativeQuery = true)
    List<TblTiendoXaydunghatang> findAllDTSX();
    @Query(value="SELECT ngaybaocao FROM public.tbl_tiendo_xaydunghatang where  duan_xdht_id = :id order by ngaybaocao desc",nativeQuery = true)
    List<String> getNgaybaocaobyIdduanxdht(@Param("id") Integer id);

    @Query(value="SELECT id FROM public.tbl_tiendo_xaydunghatang  where  duan_xdht_id =:id and ngaybaocao=:date",nativeQuery = true)
    Integer getIdTiendoxdht(@Param("id") Integer id,@Param("date") java.sql.Date date);


    @Query(value="SELECT ngaybaocao FROM public.tbl_tiendo_xaydunghatang where  duan_dtsx_id = :id order by ngaybaocao desc",nativeQuery = true)
    List<String> getNgaybaocaobyIdduandtsx(@Param("id") Integer id);

    @Query(value="SELECT id FROM public.tbl_tiendo_xaydunghatang  where  duan_dtsx_id =:id and ngaybaocao=:date",nativeQuery = true)
    Integer getIdTiendodtsx(@Param("id") Integer id,@Param("date") java.sql.Date date);


}