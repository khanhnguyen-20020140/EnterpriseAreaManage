package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDmTrangthai;
import com.sgis.khukt.model.TblTiendoXaydunghatang;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblTiendoXaydunghatangChitiet.class)
public class TblTiendoXaydunghatangChitiet_ { 

    public static volatile SingularAttribute<TblTiendoXaydunghatangChitiet, String> thanhphan;
    public static volatile SingularAttribute<TblTiendoXaydunghatangChitiet, BigDecimal> khoiluongHoanthanh;
    public static volatile SingularAttribute<TblTiendoXaydunghatangChitiet, TblDmTrangthai> idTrangthai;
    public static volatile SingularAttribute<TblTiendoXaydunghatangChitiet, Integer> id;
    public static volatile SingularAttribute<TblTiendoXaydunghatangChitiet, Integer> tiendoCapvon;
    public static volatile SingularAttribute<TblTiendoXaydunghatangChitiet, TblTiendoXaydunghatang> idTiendo;
    public static volatile SingularAttribute<TblTiendoXaydunghatangChitiet, Integer> tiendoGiaingan;

}