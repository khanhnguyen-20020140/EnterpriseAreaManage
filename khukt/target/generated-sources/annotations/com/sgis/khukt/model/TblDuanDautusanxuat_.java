package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDmLinhvucSanxuatkinhdoanh;
import com.sgis.khukt.model.TblDmLoaihinhDuan;
import com.sgis.khukt.model.TblDmTrangthai;
import com.sgis.khukt.model.TblDoanhnghiep;
import com.sgis.khukt.model.TblKhuChuyennganh;
import com.sgis.khukt.model.TblNguonvonDautu;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblDuanDautusanxuat.class)
public class TblDuanDautusanxuat_ { 

    public static volatile SingularAttribute<TblDuanDautusanxuat, TblDmTrangthai> idTrangthaiDuan;
    public static volatile SingularAttribute<TblDuanDautusanxuat, BigDecimal> tongmucDautu;
    public static volatile SingularAttribute<TblDuanDautusanxuat, TblDmLinhvucSanxuatkinhdoanh> idLinhvucSanxuatkinhdoanh;
    public static volatile SingularAttribute<TblDuanDautusanxuat, Double> vondautuDangky;
    public static volatile SingularAttribute<TblDuanDautusanxuat, TblKhuChuyennganh> idKhuChuyennganh;
    public static volatile SingularAttribute<TblDuanDautusanxuat, Date> ngayHetphepHoatdong;
    public static volatile SingularAttribute<TblDuanDautusanxuat, String> soquyetdinhCapphep;
    public static volatile SingularAttribute<TblDuanDautusanxuat, Double> vontuongduongUSD;
    public static volatile SingularAttribute<TblDuanDautusanxuat, String> noidungSanxuatKinhdoanh;
    public static volatile SingularAttribute<TblDuanDautusanxuat, TblDmLoaihinhDuan> idLoaihinhDuan;
    public static volatile SingularAttribute<TblDuanDautusanxuat, TblDoanhnghiep> idDoanhnghiep;
    public static volatile SingularAttribute<TblDuanDautusanxuat, String> masoDuan;
    public static volatile SingularAttribute<TblDuanDautusanxuat, String> quymoCongsuat;
    public static volatile SingularAttribute<TblDuanDautusanxuat, TblNguonvonDautu> idNguonvon;
    public static volatile SingularAttribute<TblDuanDautusanxuat, Date> ngayQuyetdinhCapphep;
    public static volatile SingularAttribute<TblDuanDautusanxuat, Integer> id;
    public static volatile SingularAttribute<TblDuanDautusanxuat, String> ten;

}