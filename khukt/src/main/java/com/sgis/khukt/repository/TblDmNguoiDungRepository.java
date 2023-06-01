package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblDmNguoiDung;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDmNguoiDungRepository extends JpaRepository<TblDmNguoiDung, Integer>, JpaSpecificationExecutor<TblDmNguoiDung> {
    List<TblDmNguoiDung> findByTendangnhap(String tennguoidung);
    
    //@Query(value="select st_astext(geom) as geom from tbl_quyhoach_line a where a.gid= ?1", nativeQuery = true)
   // @Query(value="SELECT t.idDuanSanxuatkinhdoanh.idKhuChuyennganh.id FROM TblKetquaSanxuatkinhdoanh t ", nativeQuery = true)
  @Query(value="SELECT dn.ten tendoanhnghiep, kkt.ten tenkhukinhte, da.ten tenduan, da.id FROM tbl_duan_dautusanxuat da INNER JOIN tbl_doanhnghiep dn ON (dn.id = da.id_doanhnghiep)  INNER JOIN tbl_khu_kinhte kkt ON (kkt.id = da.id_khu_chuyennganh)", nativeQuery = true)
       
        List<Integer> findquery();
    
    
    
}