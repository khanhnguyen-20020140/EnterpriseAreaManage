package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblTiendoXaydunghatangChitiet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TblTiendoXaydunghatangChitietRepository extends JpaRepository<TblTiendoXaydunghatangChitiet, Integer>, JpaSpecificationExecutor<TblTiendoXaydunghatangChitiet> {
    @Query(value="SELECT id, id_tiendo, thanhphan, tiendo_capvon, tiendo_giaingan, khoiluong_hoanthanh, id_trangthai\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet where id_tiendo  = :id",nativeQuery = true)
    List<TblTiendoXaydunghatangChitiet> getTienDoChiTietByIdTiendo(@Param("id")Integer id);


    @Query(value="SELECT tdct.id, tdct.id_tiendo, tdct.thanhphan, tdct.tiendo_capvon, tdct.tiendo_giaingan, tdct.khoiluong_hoanthanh, tdct.id_trangthai  FROM public.tbl_tiendo_xaydunghatang_chitiet tdct \n" +
            "inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tdct.id_tiendo\n" +
            "\tinner join tbl_duan_xaydunghatang ON tbl_duan_xaydunghatang.id = tbl_tiendo_xaydunghatang.duan_xdht_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tdct.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_xaydunghatang.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte " +
            "\twhere tbl_duan_xaydunghatang.id =  :id " +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<TblTiendoXaydunghatangChitiet> getTienDoChiTietByIdduan(@Param("id")Integer id,@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);

    @Query( value="SELECT DISTINCT tbl_duan_xaydunghatang.id\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tbl_tiendo_xaydunghatang_chitiet.id_tiendo\n" +
            "\tinner join tbl_duan_xaydunghatang ON tbl_duan_xaydunghatang.id = tbl_tiendo_xaydunghatang.duan_xdht_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tbl_tiendo_xaydunghatang_chitiet.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_xaydunghatang.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte \n" +
            "\twhere tbl_khu_kinhte.id =  :id " +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<Integer> getIdduanhatangByKKT(@Param("id")Integer id,@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);


    @Query( value="SELECT DISTINCT tbl_duan_xaydunghatang.id\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tbl_tiendo_xaydunghatang_chitiet.id_tiendo\n" +
            "\tinner join tbl_duan_xaydunghatang ON tbl_duan_xaydunghatang.id = tbl_tiendo_xaydunghatang.duan_xdht_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tbl_tiendo_xaydunghatang_chitiet.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_xaydunghatang.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte \n" +
            "\twhere tbl_khu_chuyennganh.id =  :id " +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<Integer> getIdduanhatangByKCN(@Param("id")Integer id,@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);

    @Query( value="SELECT DISTINCT tbl_duan_xaydunghatang.id\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tbl_tiendo_xaydunghatang_chitiet.id_tiendo\n" +
            "\tinner join tbl_duan_xaydunghatang ON tbl_duan_xaydunghatang.id = tbl_tiendo_xaydunghatang.duan_xdht_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tbl_tiendo_xaydunghatang_chitiet.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_xaydunghatang.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte \n" +
            "\twhere tbl_duan_xaydunghatang.id =  :id " +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<Integer> getIdduanhatangByDuan(@Param("id")Integer id,@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);



    @Query(value = "SELECT  tbl_duan_xaydunghatang.ten from tbl_duan_xaydunghatang where  tbl_duan_xaydunghatang.id = :id",nativeQuery = true)
    String getTenDuanByIdduan(@Param("id")Integer id);

    @Query( value="SELECT DISTINCT tbl_duan_xaydunghatang.id\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tbl_tiendo_xaydunghatang_chitiet.id_tiendo\n" +
            "\tinner join tbl_duan_xaydunghatang ON tbl_duan_xaydunghatang.id = tbl_tiendo_xaydunghatang.duan_xdht_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tbl_tiendo_xaydunghatang_chitiet.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_xaydunghatang.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte \n" +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<Integer> getIdduanhatangAll(@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);

    @Query(value="SELECT ten FROM public.tbl_duan_xaydunghatang where tbl_duan_xaydunghatang.id = :id",nativeQuery = true)
    String getTenDuan(@Param("id")Integer id);
    @Query(value="SELECT kcn.ten from tbl_khu_chuyennganh kcn where kcn.id = :id",nativeQuery = true)
    String getTenKhuchuyennganh(@Param("id")Integer id);

    @Query(value="SELECT ten ten FROM public.tbl_khu_kinhte where id = :id",nativeQuery = true)
    String getTenKhukinhte(@Param("id")Integer id);





//new
@Query(value="SELECT tdct.id, tdct.id_tiendo, tdct.thanhphan, tdct.tiendo_capvon, tdct.tiendo_giaingan, tdct.khoiluong_hoanthanh, tdct.id_trangthai  FROM public.tbl_tiendo_xaydunghatang_chitiet tdct \n" +
        "inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tdct.id_tiendo\n" +
        "\tinner join tbl_duan_dautusanxuat ON tbl_duan_dautusanxuat.id = tbl_tiendo_xaydunghatang.duan_dtsx_id\n" +
        "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tdct.id_trangthai\n" +
        "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_dautusanxuat.id_khu_chuyennganh\n" +
        "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte " +
        "\twhere tbl_duan_dautusanxuat.id =  :id " +
        "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
List<TblTiendoXaydunghatangChitiet> getTienDoChiTietByIdduandtsx(@Param("id")Integer id,@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);

    @Query( value="SELECT DISTINCT tbl_duan_dautusanxuat.id\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tbl_tiendo_xaydunghatang_chitiet.id_tiendo\n" +
            "\tinner join tbl_duan_dautusanxuat ON tbl_duan_dautusanxuat.id = tbl_tiendo_xaydunghatang.duan_dtsx_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tbl_tiendo_xaydunghatang_chitiet.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_dautusanxuat.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte \n" +
            "\twhere tbl_khu_kinhte.id =  :id " +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<Integer> getIdduanhatangcosoByKKT(@Param("id")Integer id,@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);


    @Query( value="SELECT DISTINCT tbl_duan_dautusanxuat.id\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tbl_tiendo_xaydunghatang_chitiet.id_tiendo\n" +
            "\tinner join tbl_duan_dautusanxuat ON tbl_duan_dautusanxuat.id = tbl_tiendo_xaydunghatang.duan_dtsx_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tbl_tiendo_xaydunghatang_chitiet.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_dautusanxuat.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte \n" +
            "\twhere tbl_khu_chuyennganh.id =  :id " +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<Integer> getIdduanhatangcosoByKCN(@Param("id")Integer id,@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);

    @Query( value="SELECT DISTINCT tbl_duan_dautusanxuat.id\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tbl_tiendo_xaydunghatang_chitiet.id_tiendo\n" +
            "\tinner join tbl_duan_dautusanxuat ON tbl_duan_dautusanxuat.id = tbl_tiendo_xaydunghatang.duan_dtsx_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tbl_tiendo_xaydunghatang_chitiet.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_dautusanxuat.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte \n" +
            "\twhere tbl_duan_dautusanxuat.id =  :id " +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<Integer> getIdduanhatangcosoByDuan(@Param("id")Integer id,@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);



    @Query(value = "SELECT  tbl_duan_dautusanxuat.ten from tbl_duan_dautusanxuat where  tbl_duan_dautusanxuat.id = :id",nativeQuery = true)
    String getTenDuanByIdduandtsx(@Param("id")Integer id);

    @Query( value="SELECT DISTINCT tbl_duan_dautusanxuat.id\n" +
            "\tFROM public.tbl_tiendo_xaydunghatang_chitiet inner join tbl_tiendo_xaydunghatang on tbl_tiendo_xaydunghatang.id = tbl_tiendo_xaydunghatang_chitiet.id_tiendo\n" +
            "\tinner join tbl_duan_dautusanxuat ON tbl_duan_dautusanxuat.id = tbl_tiendo_xaydunghatang.duan_dtsx_id\n" +
            "\tinner join tbl_dm_trangthai ON tbl_dm_trangthai.id = tbl_tiendo_xaydunghatang_chitiet.id_trangthai\n" +
            "\tinner join tbl_khu_chuyennganh ON tbl_khu_chuyennganh.id = tbl_duan_dautusanxuat.id_khu_chuyennganh\n" +
            "\tinner join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte \n" +
            "and tbl_tiendo_xaydunghatang.ngaybaocao < :endday and tbl_tiendo_xaydunghatang.ngaybaocao > :firstday",nativeQuery = true)
    List<Integer> getIdduanhatangcosoAll(@Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);

    @Query(value="SELECT ten FROM public.tbl_duan_dautusanxuat where tbl_duan_dautusanxuat.id = :id",nativeQuery = true)
    String getTenDuandtsx(@Param("id")Integer id);



}