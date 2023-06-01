package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblThanhphanXaydunghatang;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblThanhphanXaydunghatangRepository extends JpaRepository<TblThanhphanXaydunghatang, Integer>, JpaSpecificationExecutor<TblThanhphanXaydunghatang> {
    List<TblThanhphanXaydunghatang> findByTen(String ten);

//    List<TblThanhphanXaydunghatang> findByDua(String id);

    @Query(value="SELECT id, nhomdm_id, ten, khoiluong, donvitinh, mucdautu, id_duan_xdht\n" +
            "\tFROM public.tbl_thanhphan_xaydunghatang where id_duan_xdht = :id",nativeQuery = true)
    List<TblThanhphanXaydunghatang> findByIdDuan(@Param("id")Integer id);
}