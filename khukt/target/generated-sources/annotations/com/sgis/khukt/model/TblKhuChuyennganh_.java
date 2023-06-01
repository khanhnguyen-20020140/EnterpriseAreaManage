package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDmLoaihinhKhucn;
import com.sgis.khukt.model.TblKhuKinhte;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblKhuChuyennganh.class)
public class TblKhuChuyennganh_ { 

    public static volatile SingularAttribute<TblKhuChuyennganh, TblKhuKinhte> idKhuKinhte;
    public static volatile SingularAttribute<TblKhuChuyennganh, String> tomtat;
    public static volatile SingularAttribute<TblKhuChuyennganh, BigDecimal> dientich;
    public static volatile SingularAttribute<TblKhuChuyennganh, TblDmLoaihinhKhucn> loaiKhuChuyennganh;
    public static volatile SingularAttribute<TblKhuChuyennganh, Float> lon;
    public static volatile SingularAttribute<TblKhuChuyennganh, Long> id;
    public static volatile SingularAttribute<TblKhuChuyennganh, String> ten;
    public static volatile SingularAttribute<TblKhuChuyennganh, Float> lat;
    public static volatile SingularAttribute<TblKhuChuyennganh, Integer> idTinh;

}