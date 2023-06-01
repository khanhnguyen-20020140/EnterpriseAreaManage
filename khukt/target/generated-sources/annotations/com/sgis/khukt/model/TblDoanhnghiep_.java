package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDmLinhvucSanxuatkinhdoanh;
import com.sgis.khukt.model.TblDmLoaiDoanhnghiep;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblDoanhnghiep.class)
public class TblDoanhnghiep_ { 

    public static volatile SingularAttribute<TblDoanhnghiep, Date> ngayThanhlap;
    public static volatile SingularAttribute<TblDoanhnghiep, String> diachi;
    public static volatile SingularAttribute<TblDoanhnghiep, String> giamdoc;
    public static volatile SingularAttribute<TblDoanhnghiep, String> quocgia;
    public static volatile SingularAttribute<TblDoanhnghiep, String> maDangky;
    public static volatile SingularAttribute<TblDoanhnghiep, String> masothue;
    public static volatile SingularAttribute<TblDoanhnghiep, TblDmLoaiDoanhnghiep> idLoaiDoanhnghiep;
    public static volatile SingularAttribute<TblDoanhnghiep, TblDmLinhvucSanxuatkinhdoanh> idLinhvucSanxuatkinhdoanh;
    public static volatile SingularAttribute<TblDoanhnghiep, Integer> id;
    public static volatile SingularAttribute<TblDoanhnghiep, String> linhvucKinhdoanh;
    public static volatile SingularAttribute<TblDoanhnghiep, String> ten;

}