package com.sgis.khukt.model;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2023-04-15T16:09:45")
@StaticMetamodel(TblTiendoXaydunghatang.class)
public class TblTiendoXaydunghatang_ { 

    public static volatile SingularAttribute<TblTiendoXaydunghatang, TblDuanXaydunghatang> duanXdhtId;
    public static volatile SingularAttribute<TblTiendoXaydunghatang, Date> ngaybaocao;
    public static volatile SingularAttribute<TblTiendoXaydunghatang, TblDuanDautusanxuat> duanDtsxId;
    public static volatile SingularAttribute<TblTiendoXaydunghatang, Integer> id;

}