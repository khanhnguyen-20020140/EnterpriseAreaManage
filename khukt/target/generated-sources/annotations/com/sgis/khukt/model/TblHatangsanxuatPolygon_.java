package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDoanhnghiep;
import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblKhuChuyennganh;
import com.sgis.khukt.model.TblKhuKinhte;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblHatangsanxuatPolygon.class)
public class TblHatangsanxuatPolygon_ { 

    public static volatile SingularAttribute<TblHatangsanxuatPolygon, TblKhuKinhte> idKhukt;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, Integer> gid;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, String> ghichu;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, Integer> idLoaiquyhoach;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, Float> latitude;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, BigDecimal> dientich;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, TblKhuChuyennganh> idKhucn;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, String> kyhieu;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, Integer> highlight;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, TblDuanDautusanxuat> idDuanDtsx;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, TblDoanhnghiep> idDoanhnghiep;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, String> ten;
    public static volatile SingularAttribute<TblHatangsanxuatPolygon, Float> longitude;

}