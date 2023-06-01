package com.sgis.khukt.model;

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
@StaticMetamodel(TblDuanXaydunghatang.class)
public class TblDuanXaydunghatang_ { 

    public static volatile SingularAttribute<TblDuanXaydunghatang, String> soqdCapphep;
    public static volatile SingularAttribute<TblDuanXaydunghatang, TblDmTrangthai> idTrangthaiDuan;
    public static volatile SingularAttribute<TblDuanXaydunghatang, BigDecimal> tongmucDautu;
    public static volatile SingularAttribute<TblDuanXaydunghatang, BigDecimal> vondautuDangky;
    public static volatile SingularAttribute<TblDuanXaydunghatang, TblKhuChuyennganh> idKhuChuyennganh;
    public static volatile SingularAttribute<TblDuanXaydunghatang, Date> ngayHetphepHoatdong;
    public static volatile SingularAttribute<TblDuanXaydunghatang, BigDecimal> vontuongduongUsd;
    public static volatile SingularAttribute<TblDuanXaydunghatang, TblDmLoaihinhDuan> idLoaihinhDuan;
    public static volatile SingularAttribute<TblDuanXaydunghatang, String> masoDuan;
    public static volatile SingularAttribute<TblDuanXaydunghatang, String> quymoCongsuat;
    public static volatile SingularAttribute<TblDuanXaydunghatang, TblDoanhnghiep> idDoanhnghiep;
    public static volatile SingularAttribute<TblDuanXaydunghatang, Date> ngayQuyetdinhCapphep;
    public static volatile SingularAttribute<TblDuanXaydunghatang, TblNguonvonDautu> idNguonvon;
    public static volatile SingularAttribute<TblDuanXaydunghatang, Integer> id;
    public static volatile SingularAttribute<TblDuanXaydunghatang, String> ten;

}