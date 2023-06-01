package com.sgis.khukt.repository;

import com.sgis.khukt.model.TblKetquaSanxuatkinhdoanh;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface TblKetquaSanxuatkinhdoanhRepository extends JpaRepository<TblKetquaSanxuatkinhdoanh, Integer>, JpaSpecificationExecutor<TblKetquaSanxuatkinhdoanh> {
    @Query("SELECT t FROM TblKetquaSanxuatkinhdoanh t WHERE t.idDuanSanxuatkinhdoanh.idKhuChuyennganh.id = :idDuanSanxuatkinhdoanh")
    List<TblKetquaSanxuatkinhdoanh> findByIdDuanSanxuatkinhdoanh(@Param("idDuanSanxuatkinhdoanh")Integer id);

    @Query("SELECT t.idDuanSanxuatkinhdoanh.idKhuChuyennganh.id FROM TblKetquaSanxuatkinhdoanh t ")
    List<Integer> finddd();

    //kcn
    @Query(value="SELECT duandtsx.id, avg(ketqua.tongso_nhancong) as  tongso_nhancong, avg(ketqua.tongso_nhancong_nam) as tongso_nhancong_nam, " +
            "avg(ketqua.tongso_nhancong_nu) as tongso_nhancong_nu, avg(ketqua.tuoi_trungbinh_nhancong) as tuoi_trungbinh_nhancong, avg(ketqua.nhancong_trungcap) as nhancong_trungcap, " +
            "avg(ketqua.nhancong_caodang) as nhancong_caodang, avg(ketqua.nhancong_daihoc) as  nhancong_daihoc, avg(ketqua.nhancong_trendaihoc) as nhancong_trendaihoc, avg(ketqua.luong_trungbinh)  as luong_trungbinh" +
            " FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id " +
            "inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh " +
            "inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id_khu_chuyennganh = :id_kcn and " +
            "ketqua.moc_baocao >= :firstday and " +
            "ketqua.moc_baocao <= :endday group by duandtsx.id", nativeQuery = true)
    List<List<Double>> avgNhancongInKCN(@Param("id_kcn")Integer id, @Param("firstday") java.sql.Date firstday,@Param("endday")java.sql.Date endday);

    @Query(value="SELECT DISTINCT duandtsx.id FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id_khu_chuyennganh =:id_kcn order by duandtsx.id\n", nativeQuery = true)
    List<Integer> getAllIdInKCN (@Param("id_kcn")Integer id);




    //dn
    @Query(value="SELECT DISTINCT duandtsx.id FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id_doanhnghiep = :id_dn order by duandtsx.id\n", nativeQuery = true)
    List<Integer> getAllIdInDN (@Param("id_dn")Integer id);




    @Query(value="SELECT duandtsx.id, avg(ketqua.tongso_nhancong) as  tongso_nhancong, avg(ketqua.tongso_nhancong_nam) as tongso_nhancong_nam, " +
            "avg(ketqua.tongso_nhancong_nu) as tongso_nhancong_nu, avg(ketqua.tuoi_trungbinh_nhancong) as tuoi_trungbinh_nhancong, avg(ketqua.nhancong_trungcap) as nhancong_trungcap, " +
            "avg(ketqua.nhancong_caodang) as nhancong_caodang, avg(ketqua.nhancong_daihoc) as  nhancong_daihoc, avg(ketqua.nhancong_trendaihoc) as nhancong_trendaihoc, avg(ketqua.luong_trungbinh)  as luong_trungbinh" +
            " FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id " +
            "inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh " +
            "inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id_doanhnghiep = :id_dn and " +
            "ketqua.moc_baocao >= :firstday and " +
            "ketqua.moc_baocao <= :endday group by duandtsx.id", nativeQuery = true)
    List<List<Double>> avgNhancongInDN(@Param("id_dn")Integer id, @Param("firstday") java.sql.Date firstday,@Param("endday")java.sql.Date endday);

    //duan
    @Query(value="SELECT DISTINCT duandtsx.id FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id = :id_duan order by duandtsx.id\n", nativeQuery = true)
    List<Integer> getAllIdInDuan (@Param("id_duan")Integer id);



    @Query(value="SELECT duandtsx.id, avg(ketqua.tongso_nhancong) as  tongso_nhancong, avg(ketqua.tongso_nhancong_nam) as tongso_nhancong_nam, " +
            "avg(ketqua.tongso_nhancong_nu) as tongso_nhancong_nu, avg(ketqua.tuoi_trungbinh_nhancong) as tuoi_trungbinh_nhancong, avg(ketqua.nhancong_trungcap) as nhancong_trungcap, " +
            "avg(ketqua.nhancong_caodang) as nhancong_caodang, avg(ketqua.nhancong_daihoc) as  nhancong_daihoc, avg(ketqua.nhancong_trendaihoc) as nhancong_trendaihoc, avg(ketqua.luong_trungbinh)  as luong_trungbinh" +
            " FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id " +
            "inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh " +
            "inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id = :id_duan and " +
            "ketqua.moc_baocao >= :firstday and " +
            "ketqua.moc_baocao <= :endday group by duandtsx.id", nativeQuery = true)
    List<List<Double>> avgNhancongInDuan(@Param("id_duan")Integer id, @Param("firstday") java.sql.Date firstday,@Param("endday")java.sql.Date endday);


    //toan tinh

    @Query(value="SELECT DISTINCT duandtsx.id FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            " order by duandtsx.id\n", nativeQuery = true)
    List<Integer> getAllIdInToantinh ();





    @Query(value="SELECT duandtsx.id, avg(ketqua.tongso_nhancong) as  tongso_nhancong, avg(ketqua.tongso_nhancong_nam) as tongso_nhancong_nam, " +
            "avg(ketqua.tongso_nhancong_nu) as tongso_nhancong_nu, avg(ketqua.tuoi_trungbinh_nhancong) as tuoi_trungbinh_nhancong, avg(ketqua.nhancong_trungcap) as nhancong_trungcap, " +
            "avg(ketqua.nhancong_caodang) as nhancong_caodang, avg(ketqua.nhancong_daihoc) as  nhancong_daihoc, avg(ketqua.nhancong_trendaihoc) as nhancong_trendaihoc, avg(ketqua.luong_trungbinh)  as luong_trungbinh" +
            " FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id " +
            "inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh " +
            "inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where  " +
            "ketqua.moc_baocao >= :firstday and " +
            "ketqua.moc_baocao <= :endday group by duandtsx.id", nativeQuery = true)
    List<List<Double>> avgNhancongInToantinh( @Param("firstday") java.sql.Date firstday,@Param("endday")java.sql.Date endday);







    @Query(value="SELECT duandtsx.id,\n" +
            "\t\t\t(ketqua.tongso_nhancong) as  tongso_nhancong,\n" +
            "\t\t\t(ketqua.tongso_nhancong_nam) as tongso_nhancong_nam,\n" +
            "\t\t\t(ketqua.tongso_nhancong_nu) as tongso_nhancong_nu,\n" +
            "\t\t\t(ketqua.tuoi_trungbinh_nhancong) as tuoi_trungbinh_nhancong,\n" +
            "\t\t\t\n" +
            "\t\t\t(ketqua.nhancong_trungcap) as nhancong_trungcap,\n" +
            "\t\t\t(ketqua.nhancong_caodang) as nhancong_caodang,\n" +
            "\t\t\t(ketqua.nhancong_daihoc) as  nhancong_daihoc,\n" +
            "\t\t\t(ketqua.nhancong_trendaihoc) as nhancong_trendaihoc,\n" +
            "\t\t\t(ketqua.luong_trungbinh)  as luong_trungbinh\n" +
            "\t\t\tFROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "\t\t\twhere duandtsx.id=:id and ketqua.moc_baocao = (SELECT \n" +
            "  max(moc_baocao)\n" +
            "\tFROM public.tbl_ketqua_sanxuatkinhdoanh\n" +
            "\twhere id_duan_sanxuatkinhdoanh =:id and moc_baocao <:firstday)", nativeQuery = true)
    List<List<Double>> avgNhancongBeforeKCN(@Param("id")Integer id, @Param("firstday") java.sql.Date firstday);




    @Query(value="SELECT duandtsx.id,\n" +
            "\t\t\t(ketqua.tongso_nhancong) as  tongso_nhancong,\n" +
            "\t\t\t(ketqua.tongso_nhancong_nam) as tongso_nhancong_nam,\n" +
            "\t\t\t(ketqua.tongso_nhancong_nu) as tongso_nhancong_nu,\n" +
            "\t\t\t(ketqua.tuoi_trungbinh_nhancong) as tuoi_trungbinh_nhancong,\n" +
            "\t\t\t\n" +
            "\t\t\t(ketqua.nhancong_trungcap) as nhancong_trungcap,\n" +
            "\t\t\t(ketqua.nhancong_caodang) as nhancong_caodang,\n" +
            "\t\t\t(ketqua.nhancong_daihoc) as  nhancong_daihoc,\n" +
            "\t\t\t(ketqua.nhancong_trendaihoc) as nhancong_trendaihoc,\n" +
            "\t\t\t(ketqua.luong_trungbinh)  as luong_trungbinh\n" +
            "\t\t\tFROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "\t\t\twhere duandtsx.id=:id and ketqua.moc_baocao = (SELECT \n" +
            "  min(moc_baocao)\n" +
            "\tFROM public.tbl_ketqua_sanxuatkinhdoanh\n" +
            "\twhere id_duan_sanxuatkinhdoanh =:id and moc_baocao >:endday)", nativeQuery = true)
    List<List<Double>> avgNhancongAfterKCN(@Param("id")Integer id, @Param("endday") java.sql.Date endday);


    @Query(value = "SELECT duandtsx.id,duandtsx.ten,ketqua.moc_baocao,ketqua.nop_ngansach_tinh_tudaunam\n" +
            "\t\t\tFROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep  \n" +
            " where duandtsx.id_khu_chuyennganh = :id and ketqua.moc_baocao >= :firstday and ketqua.moc_baocao <= :endday order by duandtsx.id, ketqua.moc_baocao",nativeQuery = true)
        List<List<String>> getNgansachByKCN(@Param("id")Integer id, @Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);

    @Query(value = "SELECT duandtsx.id,duandtsx.ten,ketqua.moc_baocao,ketqua.nop_ngansach_tinh_tudaunam\n" +
            "\t\t\tFROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep  \n" +
            " where duandtsx.id = :id and ketqua.moc_baocao >= :firstday and ketqua.moc_baocao <= :endday order by duandtsx.id, ketqua.moc_baocao",nativeQuery = true)
    List<List<String>> getNgansachByDuan(@Param("id")Integer id, @Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);


    @Query(value = "SELECT duandtsx.id,duandtsx.ten,ketqua.moc_baocao,ketqua.nop_ngansach_tinh_tudaunam\n" +
            "\t\t\tFROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep  \n" +
            " where duandtsx.id_doanhnghiep = :id and ketqua.moc_baocao >= :firstday and ketqua.moc_baocao <= :endday order by duandtsx.id, ketqua.moc_baocao",nativeQuery = true)
    List<List<String>> getNgansachByDn(@Param("id")Integer id, @Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);


    @Query(value = "SELECT duandtsx.id,duandtsx.ten,ketqua.moc_baocao,ketqua.nop_ngansach_tinh_tudaunam\n" +
            "\t\t\tFROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep  \n" +
            " where   ketqua.moc_baocao >= :firstday and ketqua.moc_baocao <= :endday order by duandtsx.id, ketqua.moc_baocao",nativeQuery = true)
    List<List<String>> getNgansachByToantinh( @Param("firstday") java.sql.Date firstday, @Param("endday") java.sql.Date endday);



    @Query(value="SELECT DISTINCT duandtsx.id, duandtsx.ten FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id_khu_chuyennganh =:id_kcn order by duandtsx.id\n", nativeQuery = true)
    List<List<String>> getAllTenInKCN (@Param("id_kcn")Integer id);

    @Query(value="SELECT DISTINCT duandtsx.id, duandtsx.ten FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            " order by duandtsx.id\n", nativeQuery = true)
    List<List<String>> getAllTenInToantinh ();

    @Query(value="SELECT DISTINCT duandtsx.id, duandtsx.ten FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id_doanhnghiep = :id_dn order by duandtsx.id\n", nativeQuery = true)
    List<List<String>> getAllTenInDN (@Param("id_dn")Integer id);

    @Query(value="SELECT DISTINCT duandtsx.id, duandtsx.ten FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep " +
            "where duandtsx.id = :id_duan order by duandtsx.id\n", nativeQuery = true)
    List<List<String>> getAllTenInDuan (@Param("id_duan")Integer id);



    //doanhthu

    @Query(value = "SELECT duandtsx.id,ketqua.moc_baocao,ketqua.doanhthu_tinh_tudaunam FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                        inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "            inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "            inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "            where  duandtsx.id_khu_chuyennganh = :id"+
            "\t\t\torder by duandtsx.id,ketqua.moc_baocao",nativeQuery = true)
    List<List<String>> getDoanhthuByKCN(@Param("id")Integer id);

    @Query(value = "SELECT duandtsx.id,ketqua.moc_baocao,ketqua.doanhthu_tinh_tudaunam FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                        inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "            inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "            inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "            where  duandtsx.id_doanhnghiep = :id"+
            "\t\t\torder by duandtsx.id,ketqua.moc_baocao",nativeQuery = true)
    List<List<String>> getDoanhthuByDN(@Param("id")Integer id);

    @Query(value = "SELECT duandtsx.id,ketqua.moc_baocao,ketqua.doanhthu_tinh_tudaunam FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                        inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "            inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "            inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "            where  duandtsx.id = :id"+
            "\t\t\torder by duandtsx.id,ketqua.moc_baocao",nativeQuery = true)
    List<List<String>> getDoanhthuByDuan(@Param("id")Integer id);

    @Query(value = "SELECT duandtsx.id,ketqua.moc_baocao,ketqua.doanhthu_tinh_tudaunam FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                        inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "            inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "            inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +

            "\t\t\torder by duandtsx.id,ketqua.moc_baocao",nativeQuery = true)
    List<List<String>> getDoanhthuByToantinh();


    @Query(value="SELECT kcn.ten from tbl_khu_chuyennganh kcn where kcn.id = :id",nativeQuery = true)
    String getExactTenKCN(@Param("id")Integer id);

    @Query(value="SELECT duandtsx.ten from public.tbl_duan_dautusanxuat duandtsx where duandtsx.id = :id",nativeQuery = true)
    String getExactTenDuan(@Param("id")Integer id);


    @Query(value="SELECT dn.ten from tbl_doanhnghiep dn where dn.id = :id",nativeQuery = true)
    String getExactTenDn(@Param("id")Integer id);

    @Query(value="SELECT ten FROM public.tbl_khu_kinhte where public.tbl_khu_kinhte.id= :id",nativeQuery = true)
    String getExactTenKkt(@Param("id")Integer id);


}