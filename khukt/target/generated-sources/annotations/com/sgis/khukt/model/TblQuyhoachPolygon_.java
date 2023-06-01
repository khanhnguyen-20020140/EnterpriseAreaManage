package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import com.sgis.khukt.model.TblKhuChuyennganh;
import com.sgis.khukt.model.TblKhuKinhte;
import com.sgis.khukt.model.TblLoaiquyhoach;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblQuyhoachPolygon.class)
public class TblQuyhoachPolygon_ { 

    public static volatile SingularAttribute<TblQuyhoachPolygon, TblKhuKinhte> idKhukt;
    public static volatile SingularAttribute<TblQuyhoachPolygon, Integer> gid;
    public static volatile SingularAttribute<TblQuyhoachPolygon, String> malo;
    public static volatile SingularAttribute<TblQuyhoachPolygon, String> ghichu;
    public static volatile SingularAttribute<TblQuyhoachPolygon, Float> latitude;
    public static volatile SingularAttribute<TblQuyhoachPolygon, TblLoaiquyhoach> idLoaiquyhoach;
    public static volatile SingularAttribute<TblQuyhoachPolygon, String> matdoxd;
    public static volatile SingularAttribute<TblQuyhoachPolygon, BigDecimal> dientich;
    public static volatile SingularAttribute<TblQuyhoachPolygon, TblKhuChuyennganh> idKhucn;
    public static volatile SingularAttribute<TblQuyhoachPolygon, String> khlodat;
    public static volatile SingularAttribute<TblQuyhoachPolygon, Float> hesosdd;
    public static volatile SingularAttribute<TblQuyhoachPolygon, TblDuanXaydunghatang> idDuanXdht;
    public static volatile SingularAttribute<TblQuyhoachPolygon, Integer> highlight;
    public static volatile SingularAttribute<TblQuyhoachPolygon, TblDuanDautusanxuat> idDuanDtsx;
    public static volatile SingularAttribute<TblQuyhoachPolygon, String> ten;
    public static volatile SingularAttribute<TblQuyhoachPolygon, Float> tangcao;
    public static volatile SingularAttribute<TblQuyhoachPolygon, BigDecimal> dtLo;
    public static volatile SingularAttribute<TblQuyhoachPolygon, String> mald;
    public static volatile SingularAttribute<TblQuyhoachPolygon, BigDecimal> dtKhu;
    public static volatile SingularAttribute<TblQuyhoachPolygon, Float> longitude;

}