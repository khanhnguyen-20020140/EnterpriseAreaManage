package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import com.sgis.khukt.model.TblKhuChuyennganh;
import com.sgis.khukt.model.TblKhuKinhte;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblHatangkythuatPoint.class)
public class TblHatangkythuatPoint_ { 

    public static volatile SingularAttribute<TblHatangkythuatPoint, TblDuanXaydunghatang> idDuanXdht;
    public static volatile SingularAttribute<TblHatangkythuatPoint, Integer> highlight;
    public static volatile SingularAttribute<TblHatangkythuatPoint, TblKhuKinhte> idKhukt;
    public static volatile SingularAttribute<TblHatangkythuatPoint, Integer> gid;
    public static volatile SingularAttribute<TblHatangkythuatPoint, TblDuanDautusanxuat> idDuanDtsx;
    public static volatile SingularAttribute<TblHatangkythuatPoint, Integer> idLoaiquyhoach;
    public static volatile SingularAttribute<TblHatangkythuatPoint, Float> latitude;
    public static volatile SingularAttribute<TblHatangkythuatPoint, String> thongso;
    public static volatile SingularAttribute<TblHatangkythuatPoint, TblKhuChuyennganh> idKhucn;
    public static volatile SingularAttribute<TblHatangkythuatPoint, String> ten;
    public static volatile SingularAttribute<TblHatangkythuatPoint, String> tenobj;
    public static volatile SingularAttribute<TblHatangkythuatPoint, Float> longitude;

}