package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDuanXaydunghatang;
import com.sgis.khukt.model.TblNhomdmXdht;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblThanhphanXaydunghatang.class)
public class TblThanhphanXaydunghatang_ { 

    public static volatile SingularAttribute<TblThanhphanXaydunghatang, String> donvitinh;
    public static volatile SingularAttribute<TblThanhphanXaydunghatang, TblDuanXaydunghatang> idDuanXdht;
    public static volatile SingularAttribute<TblThanhphanXaydunghatang, BigDecimal> khoiluong;
    public static volatile SingularAttribute<TblThanhphanXaydunghatang, BigDecimal> mucdautu;
    public static volatile SingularAttribute<TblThanhphanXaydunghatang, Integer> id;
    public static volatile SingularAttribute<TblThanhphanXaydunghatang, String> ten;
    public static volatile SingularAttribute<TblThanhphanXaydunghatang, TblNhomdmXdht> nhomdmId;

}