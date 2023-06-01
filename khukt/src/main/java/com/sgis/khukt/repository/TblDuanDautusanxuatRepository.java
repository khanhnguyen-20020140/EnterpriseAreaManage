package com.sgis.khukt.repository;

import java.util.List;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TblDuanDautusanxuatRepository extends JpaRepository<TblDuanDautusanxuat, Integer>, JpaSpecificationExecutor<TblDuanDautusanxuat> {
    List<TblDuanDautusanxuat> findByTen(String ten);

    @Query(value="SELECT dtsx.ten as duanten , tbl_doanhnghiep.ten as doanhnghiepten, " +
            "soquyetdinh_capphep, vondautu_dangky, tbl_nguonvon_dautu.nguonvon, " +
            "tbl_khu_chuyennganh.ten as khuchuyennganhten , noidung_sanxuat_kinhdoanh,tbl_dm_linhvuc_sanxuatkinhdoanh.linhvuc_sanxuatkinhdoanh, " +
            "maso_duan, ngay_quyetdinh_capphep , vontuongduong_usd ,quymo_congsuat, ngay_hetphep_hoatdong, " +
            "tbl_dm_trangthai.trangthai, tbl_dm_loaihinh_duan.loaihinh_duan, tongmuc_dautu FROM public.tbl_duan_dautusanxuat dtsx\n" +
            "\tleft join tbl_doanhnghiep  on dtsx.id_doanhnghiep = tbl_doanhnghiep.id \n" +
            "\tleft join tbl_nguonvon_dautu on tbl_nguonvon_dautu.id = dtsx.id_nguonvon\n" +
            "\tleft join tbl_khu_chuyennganh on tbl_khu_chuyennganh.id = dtsx.id_khu_chuyennganh\n" +
            "\tleft join tbl_dm_linhvuc_sanxuatkinhdoanh ON tbl_dm_linhvuc_sanxuatkinhdoanh.id_linhvuc_sanxuatkinhdoanh = dtsx.id_linhvuc_sanxuatkinhdoanh\n" +
            "\tleft join tbl_dm_trangthai ON tbl_dm_trangthai.id = dtsx.id_trangthai_duan\n" +
            "\tleft join tbl_dm_loaihinh_duan ON tbl_dm_loaihinh_duan.id = dtsx.id_loaihinh_duan  where dtsx.id_khu_chuyennganh = :id",nativeQuery = true)
    List<List<String>> findByKCN(@Param("id")Integer id);


    @Query(value="SELECT dtsx.ten as duanten , tbl_doanhnghiep.ten as doanhnghiepten, " +
            "soquyetdinh_capphep, vondautu_dangky, tbl_nguonvon_dautu.nguonvon, " +
            "tbl_khu_chuyennganh.ten as khuchuyennganhten , noidung_sanxuat_kinhdoanh,tbl_dm_linhvuc_sanxuatkinhdoanh.linhvuc_sanxuatkinhdoanh, " +
            "maso_duan, ngay_quyetdinh_capphep , vontuongduong_usd ,quymo_congsuat, ngay_hetphep_hoatdong, " +
            "tbl_dm_trangthai.trangthai, tbl_dm_loaihinh_duan.loaihinh_duan, tongmuc_dautu FROM public.tbl_duan_dautusanxuat dtsx\n" +
            "\tleft join tbl_doanhnghiep  on dtsx.id_doanhnghiep = tbl_doanhnghiep.id \n" +
            "\tleft join tbl_nguonvon_dautu on tbl_nguonvon_dautu.id = dtsx.id_nguonvon\n" +
            "\tleft join tbl_khu_chuyennganh on tbl_khu_chuyennganh.id = dtsx.id_khu_chuyennganh\n" +
            "\tleft join tbl_dm_linhvuc_sanxuatkinhdoanh ON tbl_dm_linhvuc_sanxuatkinhdoanh.id_linhvuc_sanxuatkinhdoanh = dtsx.id_linhvuc_sanxuatkinhdoanh\n" +
            "\tleft join tbl_dm_trangthai ON tbl_dm_trangthai.id = dtsx.id_trangthai_duan\n" +
            "\tleft join tbl_dm_loaihinh_duan ON tbl_dm_loaihinh_duan.id = dtsx.id_loaihinh_duan  where dtsx.id_doanhnghiep = :id",nativeQuery = true)
    List<List<String>> findByDN(@Param("id")Integer id);


    @Query(value="SELECT dtsx.ten as duanten , tbl_doanhnghiep.ten as doanhnghiepten, soquyetdinh_capphep, vondautu_dangky, tbl_nguonvon_dautu.nguonvon, tbl_khu_chuyennganh.ten as khuchuyennganhten, noidung_sanxuat_kinhdoanh,tbl_dm_linhvuc_sanxuatkinhdoanh.linhvuc_sanxuatkinhdoanh, maso_duan, ngay_quyetdinh_capphep , vontuongduong_usd ,quymo_congsuat, ngay_hetphep_hoatdong, tbl_dm_trangthai.trangthai, tbl_dm_loaihinh_duan.loaihinh_duan, tongmuc_dautu \n" +
            "FROM public.tbl_duan_dautusanxuat dtsx\n" +
            "\tleft join tbl_doanhnghiep  on dtsx.id_doanhnghiep = tbl_doanhnghiep.id \n" +
            "\tleft join tbl_nguonvon_dautu on tbl_nguonvon_dautu.id = dtsx.id_nguonvon\n" +
            "\tleft join tbl_khu_chuyennganh on tbl_khu_chuyennganh.id = dtsx.id_khu_chuyennganh\n" +
            "\tleft join tbl_dm_linhvuc_sanxuatkinhdoanh ON tbl_dm_linhvuc_sanxuatkinhdoanh.id_linhvuc_sanxuatkinhdoanh = dtsx.id_linhvuc_sanxuatkinhdoanh\n" +
            "\tleft join tbl_dm_trangthai ON tbl_dm_trangthai.id = dtsx.id_trangthai_duan\n" +
            "\tleft join tbl_dm_loaihinh_duan ON tbl_dm_loaihinh_duan.id = dtsx.id_loaihinh_duan  \n" +
            "\tleft join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte\n" +
            "\twhere tbl_khu_kinhte.id = :id",nativeQuery = true)
    List<List<String>> findByKKT(@Param("id")Integer id);

    @Query(value="SELECT dtsx.ten as duanten , tbl_doanhnghiep.ten as doanhnghiepten, soquyetdinh_capphep, vondautu_dangky, tbl_nguonvon_dautu.nguonvon, tbl_khu_chuyennganh.ten as khuchuyennganhten, noidung_sanxuat_kinhdoanh,tbl_dm_linhvuc_sanxuatkinhdoanh.linhvuc_sanxuatkinhdoanh, maso_duan, ngay_quyetdinh_capphep , vontuongduong_usd ,quymo_congsuat, ngay_hetphep_hoatdong, tbl_dm_trangthai.trangthai, tbl_dm_loaihinh_duan.loaihinh_duan, tongmuc_dautu \n" +
            "FROM public.tbl_duan_dautusanxuat dtsx\n" +
            "\tleft join tbl_doanhnghiep  on dtsx.id_doanhnghiep = tbl_doanhnghiep.id \n" +
            "\tleft join tbl_nguonvon_dautu on tbl_nguonvon_dautu.id = dtsx.id_nguonvon\n" +
            "\tleft join tbl_khu_chuyennganh on tbl_khu_chuyennganh.id = dtsx.id_khu_chuyennganh\n" +
            "\tleft join tbl_dm_linhvuc_sanxuatkinhdoanh ON tbl_dm_linhvuc_sanxuatkinhdoanh.id_linhvuc_sanxuatkinhdoanh = dtsx.id_linhvuc_sanxuatkinhdoanh\n" +
            "\tleft join tbl_dm_trangthai ON tbl_dm_trangthai.id = dtsx.id_trangthai_duan\n" +
            "\tleft join tbl_dm_loaihinh_duan ON tbl_dm_loaihinh_duan.id = dtsx.id_loaihinh_duan  \n" +
            "\tleft join tbl_khu_kinhte ON tbl_khu_kinhte.id = tbl_khu_chuyennganh.id_khu_kinhte\n" +
            "\t",nativeQuery = true)
    List<List<String>> findByAll();




}