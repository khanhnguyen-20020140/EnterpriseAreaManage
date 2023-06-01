package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDoanhnghiep;
import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblKhuKinhte;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblDmNguoiDung.class)
public class TblDmNguoiDung_ { 

    public static volatile SingularAttribute<TblDmNguoiDung, String> tennguoidung;
    public static volatile SingularAttribute<TblDmNguoiDung, String> matkhau;
    public static volatile SingularAttribute<TblDmNguoiDung, String> tendangnhap;
    public static volatile SingularAttribute<TblDmNguoiDung, String> quyen_soanthao_thsx;
    public static volatile SingularAttribute<TblDmNguoiDung, String> quyen_xem_thsx;
    public static volatile SingularAttribute<TblDmNguoiDung, TblDoanhnghiep> idDoanhnghiep;
    public static volatile SingularAttribute<TblDmNguoiDung, TblKhuKinhte> idKhukinhte;
    public static volatile SingularAttribute<TblDmNguoiDung, Integer> id;
    public static volatile SingularAttribute<TblDmNguoiDung, TblDuanDautusanxuat> idDuanSanxuatkinhdoanh;

}