package com.sgis.khukt.model;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;

public class testMap {
    static final String URL = "jdbc:postgresql://localhost:5434/khukt_dongnam_20221223?useUnicode=yes&characterEncoding=UTF-8";

    static final String USER = "postgres";

    static final String PASS = "11";

    static final String QUERY = "SELECT duandtsx.id,duandtsx.ten,ketqua.moc_baocao,ketqua.nop_ngansach_tinh_tudaunam\n" +
            "\t\t\tFROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "\t\t\twhere kcn.id =1 and ketqua.moc_baocao > '2002-01-01' and ketqua.moc_baocao < '2222-03-03 '\n" +
            "\t\t\torder by duandtsx.id, ketqua.moc_baocao";


    public static void main(String[] args) {
        BigDecimal a = BigDecimal.valueOf(1.22);
        System.out.println(a);
    }
}
