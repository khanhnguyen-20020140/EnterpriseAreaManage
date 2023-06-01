package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import com.sgis.khukt.model.TblKhuChuyennganh;
import com.sgis.khukt.model.TblKhuKinhte;
import com.sgis.khukt.model.TblLoaiquyhoach;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblQuyhoachPoint.class)
public class TblQuyhoachPoint_ { 

    public static volatile SingularAttribute<TblQuyhoachPoint, TblKhuKinhte> idKhukt;
    public static volatile SingularAttribute<TblQuyhoachPoint, Integer> gid;
    public static volatile SingularAttribute<TblQuyhoachPoint, String> ghichu;
    public static volatile SingularAttribute<TblQuyhoachPoint, Float> latitude;
    public static volatile SingularAttribute<TblQuyhoachPoint, TblLoaiquyhoach> idLoaiquyhoach;
    public static volatile SingularAttribute<TblQuyhoachPoint, Float> caodoTn;
    public static volatile SingularAttribute<TblQuyhoachPoint, String> duongdan;
    public static volatile SingularAttribute<TblQuyhoachPoint, TblKhuChuyennganh> idKhucn;
    public static volatile SingularAttribute<TblQuyhoachPoint, TblDuanXaydunghatang> idDuanXdht;
    public static volatile SingularAttribute<TblQuyhoachPoint, Integer> highlight;
    public static volatile SingularAttribute<TblQuyhoachPoint, TblDuanDautusanxuat> idDuanDtsx;
    public static volatile SingularAttribute<TblQuyhoachPoint, String> ten;
    public static volatile SingularAttribute<TblQuyhoachPoint, Float> caodoTke;
    public static volatile SingularAttribute<TblQuyhoachPoint, Float> longitude;

}