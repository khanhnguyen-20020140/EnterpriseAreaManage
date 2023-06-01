package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import com.sgis.khukt.model.TblKhuChuyennganh;
import com.sgis.khukt.model.TblKhuKinhte;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblHatangkythuatPolygon.class)
public class TblHatangkythuatPolygon_ { 

    public static volatile SingularAttribute<TblHatangkythuatPolygon, TblDuanXaydunghatang> idDuanXdht;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, Integer> highlight;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, TblKhuKinhte> idKhukt;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, Integer> gid;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, String> tendnghiep;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, TblDuanDautusanxuat> idDuanDtsx;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, Integer> idLoaiquyhoach;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, Float> latitude;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, String> ten;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, TblKhuChuyennganh> idKhucn;
    public static volatile SingularAttribute<TblHatangkythuatPolygon, Float> longitude;

}