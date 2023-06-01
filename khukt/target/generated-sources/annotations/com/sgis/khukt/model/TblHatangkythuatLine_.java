package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import com.sgis.khukt.model.TblKhuChuyennganh;
import com.sgis.khukt.model.TblKhuKinhte;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblHatangkythuatLine.class)
public class TblHatangkythuatLine_ { 

    public static volatile SingularAttribute<TblHatangkythuatLine, TblKhuKinhte> idKhukt;
    public static volatile SingularAttribute<TblHatangkythuatLine, Integer> gid;
    public static volatile SingularAttribute<TblHatangkythuatLine, String> duongkinh;
    public static volatile SingularAttribute<TblHatangkythuatLine, Integer> idLoaiquyhoach;
    public static volatile SingularAttribute<TblHatangkythuatLine, Float> latitude;
    public static volatile SingularAttribute<TblHatangkythuatLine, String> thongso;
    public static volatile SingularAttribute<TblHatangkythuatLine, TblKhuChuyennganh> idKhucn;
    public static volatile SingularAttribute<TblHatangkythuatLine, String> tenduong;
    public static volatile SingularAttribute<TblHatangkythuatLine, TblDuanXaydunghatang> idDuanXdht;
    public static volatile SingularAttribute<TblHatangkythuatLine, Integer> highlight;
    public static volatile SingularAttribute<TblHatangkythuatLine, String> chatlieu;
    public static volatile SingularAttribute<TblHatangkythuatLine, BigDecimal> chieurong;
    public static volatile SingularAttribute<TblHatangkythuatLine, TblDuanDautusanxuat> idDuanDtsx;
    public static volatile SingularAttribute<TblHatangkythuatLine, BigDecimal> chieudai;
    public static volatile SingularAttribute<TblHatangkythuatLine, String> ten;
    public static volatile SingularAttribute<TblHatangkythuatLine, Float> longitude;

}