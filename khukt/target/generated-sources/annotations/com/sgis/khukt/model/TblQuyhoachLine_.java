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
@StaticMetamodel(TblQuyhoachLine.class)
public class TblQuyhoachLine_ { 

    public static volatile SingularAttribute<TblQuyhoachLine, TblDuanXaydunghatang> idDuanXdht;
    public static volatile SingularAttribute<TblQuyhoachLine, Integer> highlight;
    public static volatile SingularAttribute<TblQuyhoachLine, TblKhuKinhte> idKhukt;
    public static volatile SingularAttribute<TblQuyhoachLine, Integer> gid;
    public static volatile SingularAttribute<TblQuyhoachLine, String> ghichu;
    public static volatile SingularAttribute<TblQuyhoachLine, Float> chieurong;
    public static volatile SingularAttribute<TblQuyhoachLine, TblDuanDautusanxuat> idDuanDtsx;
    public static volatile SingularAttribute<TblQuyhoachLine, Float> latitude;
    public static volatile SingularAttribute<TblQuyhoachLine, TblLoaiquyhoach> idLoaiquyhoach;
    public static volatile SingularAttribute<TblQuyhoachLine, TblKhuChuyennganh> idKhucn;
    public static volatile SingularAttribute<TblQuyhoachLine, String> ten;
    public static volatile SingularAttribute<TblQuyhoachLine, Float> longitude;

}