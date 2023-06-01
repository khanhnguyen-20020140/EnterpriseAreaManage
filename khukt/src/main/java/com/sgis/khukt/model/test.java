package com.sgis.khukt.model;

import java.sql.*;
import java.util.*;

public class test {
    static final String URL = "jdbc:postgresql://localhost:5434/khukt_dongnam_20221223?useUnicode=yes&characterEncoding=UTF-8";

    static final String USER = "postgres";

    static final String PASS = "11";

    static final String SELECT_Ngansach = "SELECT duandtsx.id,duandtsx.ten,ketqua.moc_baocao,ketqua.nop_ngansach_tinh_tudaunam\n" +
            "\t\t\tFROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "\t\t\twhere kcn.id =2 and ketqua.moc_baocao > '2021-07-01' and ketqua.moc_baocao < '2023-03-15 '\n" +
            "\t\t\torder by duandtsx.id, ketqua.moc_baocao";

    static final  String allIdPrj ="SELECT DISTINCT duandtsx.id FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "\t\t\twhere kcn.id =2 \n" +
            "\t\t\torder by duandtsx.id";

    public static void main(String[] args) throws SQLException {

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {

            System.out.println("Error: unable to load driver class!");

            System.exit(1);

        }

        // finish loading driver class

        // start get connection

        Connection conn = null;

        conn = DriverManager.getConnection(URL, USER, PASS);

        // finish get connection

        // create stmt

        Statement stmt = conn.createStatement();
        System.out.println(SELECT_Ngansach);
        ResultSet rs ;

        //herre


        Map<Integer, List<String>> mapNgansach = new HashMap<Integer, List<String>>();
        List<String> listNgansach = new ArrayList<>();
        Map < Integer, Double > getNgansach = new HashMap < Integer, Double > ();
        boolean selectedNgansach = true;
        System.out.println("list ngan sach   ");

        if(selectedNgansach){
            rs = stmt.executeQuery(SELECT_Ngansach);
            String activeIdDuan = "-1";

            String existYear = "-1";

            boolean getNgansachSuccess = false;

            while (rs.next()) {
                getNgansachSuccess = true;



                String id = rs.getString(1);
                String tenda = rs.getString(2);
                String mocbaocao = rs.getString(3);
                String ngansach = rs.getString(4);

                //khoi tao
                if (activeIdDuan.equals("-1")) {
                    activeIdDuan = id;
                    listNgansach.add(id);

                    listNgansach.add(ngansach);
                    String[] arrOfStr = mocbaocao.split("-");
                    existYear = arrOfStr[0];
                } else {

                    //van du an do
                    if (activeIdDuan.equals(id)) {
                        String[] arrOfStr = mocbaocao.split("-");
                        String yearOfMocbaocao = arrOfStr[0];
                        if (existYear.equals(yearOfMocbaocao)) {
                            listNgansach.remove(listNgansach.size() - 1);
                            listNgansach.add(ngansach);
                        }
                        //doi nam moi
                        else {
                            listNgansach.add(ngansach);
                        }
                        existYear = yearOfMocbaocao;
                    }

                    //doi du an
                    else {
                        activeIdDuan = id;
                        Integer id_ketqua = Integer.parseInt(listNgansach.get(0));
                        listNgansach.remove(0);

                        mapNgansach.put(id_ketqua, listNgansach);
                        listNgansach = new ArrayList < > ();
                        listNgansach.add(id);
                        listNgansach.add(ngansach);
                        String[] arrOfStr = mocbaocao.split("-");
                        existYear = arrOfStr[0];

                    }
                }

            }

            if(getNgansachSuccess == true){
                Integer id_ketqua = Integer.parseInt(listNgansach.get(0));

                listNgansach.remove(0);

                mapNgansach.put(id_ketqua, listNgansach);
                listNgansach = new ArrayList < > ();
                for (Map.Entry < Integer, List < String >> entry: mapNgansach.entrySet()) {
                    System.out.println(entry.getKey() + " " + entry.getValue());
                }



                for (Map.Entry < Integer, List < String >> entry: mapNgansach.entrySet()) {

                    Double sumNgansachofPrj = 0.0;
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        if(entry.getValue().get(i)!=null){
                            sumNgansachofPrj += Double.parseDouble(entry.getValue().get(i));
                        }


                    }
                    getNgansach.put(entry.getKey(), sumNgansachofPrj);
                }
            }
        }


        rs = stmt.executeQuery(allIdPrj);
        System.out.println(allIdPrj);


        List<Integer> allId = new ArrayList<>();
        while (rs.next()) {
            allId.add(Integer.parseInt(rs.getString(1)));
        }

        for(int j =0;j<allId.size();j++){
            if(getNgansach.get(allId.get(j))==null){
                getNgansach.put(allId.get(j),0.0);
            }
        }

        for (Map.Entry<Integer, Double> entry : getNgansach.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }



        conn.close();


    }
}