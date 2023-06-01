package com.sgis.khukt.model;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class DoanhthuTest {

    static final String URL = "jdbc:postgresql://localhost:5434/khukt_dongnam_20221223?useUnicode=yes&characterEncoding=UTF-8";

    static final String USER = "postgres";

    static final String PASS = "11";

    static final String QUERY_DOANHTHU = "SELECT duandtsx.id,ketqua.moc_baocao,ketqua.doanhthu_tinh_tudaunam FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                        inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "            inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "            inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
//            "            where kcn.id = 2 \n" +
            "\t\t\torder by duandtsx.id,ketqua.moc_baocao";

    static  final String QUERY_IDDUANAN="SELECT duandtsx.id FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "            inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "\t\t\tinner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "\t\t\tinner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
//            "\t\t\twhere kcn.id = 2 \n" +
            "\t\t\tgroup by duandtsx.id";

    static String queryMaxday  = "2025-03-15";

    static String[] arrOfMaxYears = queryMaxday.split("-");
    static Integer queryMaxYear = Integer.parseInt(arrOfMaxYears[0]);

    static String queryMinday  = "2021-07-01";

    static String[] arrOfMinYears = queryMinday.split("-");
    static Integer queryMinYear = Integer.parseInt(arrOfMinYears[0]);

    static public Long  getDayDiff(String startDay, String endDay){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String firstInput = startDay;
        String secondInput = endDay;
        LocalDate firstDate = LocalDate.parse(firstInput, formatter);
        LocalDate secondDate = LocalDate.parse(secondInput, formatter);

        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }

    public static Map<Integer,List<Doanhthu>> getMapdoanhthu() throws SQLException, IOException{

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {

            System.out.println("Error: unable to load driver class!");

            System.exit(1);

        }

        Connection conn = null;

        conn = DriverManager.getConnection(URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY_DOANHTHU);

        System.out.println(QUERY_DOANHTHU);


        Map<Integer,List<Doanhthu>> mapDoanhthu = new HashMap<>();
        String activeIdDuan = "-1";
        Integer checkExistDuan = 0;
        String existYear = "-1";
        String existMocbaocao = "-1";
        String existDoanhthu ="-1";
        List<Doanhthu> doanhthuList = new ArrayList<>();
        while (rs.next()) {
//            System.out.println("querysuccess");

            String id = rs.getString(1);

            String mocbaocao = rs.getString(2);
            //            System.out.println(existMocbaocao+"  1   "+ mocbaocao);
            String doanhthu = rs.getString(3);

            if(mocbaocao!=null&&doanhthu!=null){
                //khoi tao du an
                if(activeIdDuan == "-1"){

                    String[] arrOfStr = mocbaocao.split("-");
                    String yearOfmocbaocao = arrOfStr[0];

                    Long day = getDayDiff(yearOfmocbaocao+"-01-01",mocbaocao);
                    System.out.println(day);
                    Double avgDoanhthu = Double.parseDouble(doanhthu)/day;

                    doanhthuList.add(
                            new Doanhthu(yearOfmocbaocao+"-01-01",mocbaocao,avgDoanhthu)
                    );
                }
                else{
                    String[] arrOfStr = mocbaocao.split("-");
                    String yearOfmocbaocao = arrOfStr[0];
                    //doi du an
                    if(!activeIdDuan.equals(id)){

                        Long day = getDayDiff(existYear+"-01-01",existMocbaocao);
                        System.out.println(day);
                        Double avgDoanhthu = Double.parseDouble(existDoanhthu)/day;

                        doanhthuList.add(
                                new Doanhthu(existMocbaocao,existYear+"-12-31",avgDoanhthu)
                        );
                        System.out.println();
                        mapDoanhthu.put(Integer.valueOf(activeIdDuan),doanhthuList);
                        activeIdDuan = id;
                        doanhthuList = new ArrayList<>();
                        existYear = "-1";
                        existMocbaocao = "-1";
                        existDoanhthu ="-1";

                        arrOfStr = mocbaocao.split("-");
                        yearOfmocbaocao = arrOfStr[0];

                        day = getDayDiff(yearOfmocbaocao+"-01-01",mocbaocao);

                        avgDoanhthu = Double.parseDouble(doanhthu)/day;

                        doanhthuList.add(
                                new Doanhthu(yearOfmocbaocao+"-01-01",mocbaocao,avgDoanhthu)
                        );
                    }
                    //van du an cu
                    else{
                        if(!existYear.equals(yearOfmocbaocao)){
                            // tu dau nam ExistYear toi ExistMaubaocao
                            Long day = getDayDiff(existYear+"-01-01",existMocbaocao);
                            System.out.println(day);
                            Double avgDoanhthu = Double.parseDouble(existDoanhthu)/day;

                            doanhthuList.add(
                                    new Doanhthu(existMocbaocao,existYear+"-12-31",avgDoanhthu)
                            );

                            day = getDayDiff(yearOfmocbaocao+"-01-01",mocbaocao);
                            System.out.println(day);
                            avgDoanhthu = Double.parseDouble(doanhthu)/day;
                            doanhthuList.add(
                                    new Doanhthu(yearOfmocbaocao+"-01-01",mocbaocao,avgDoanhthu)
                            );


                        }
                        else{
                            Long day = getDayDiff(existMocbaocao,mocbaocao);
                            System.out.println(day);
                            Double avgDoanhthu = (Double.parseDouble(doanhthu)-Double.parseDouble(existDoanhthu))/day;

                            doanhthuList.add(
                                    new Doanhthu(existMocbaocao,mocbaocao,avgDoanhthu)
                            );
                        }
                    }

                }


                existDoanhthu = doanhthu;
                activeIdDuan = id;
                existMocbaocao = mocbaocao;
                String[] arrOfStr = existMocbaocao.split("-");
                existYear = arrOfStr[0];
            }




        }
        if(!activeIdDuan.equals("-1")){
            System.out.println(activeIdDuan);
            System.out.println(doanhthuList);
            Long day = getDayDiff(existYear+"-01-01",existMocbaocao);
            System.out.println(day);
            Double avgDoanhthu = Double.parseDouble(existDoanhthu)/day;

            doanhthuList.add(
                    new Doanhthu(existMocbaocao,existYear+"-12-31",avgDoanhthu)
            );

            mapDoanhthu.put(Integer.valueOf(activeIdDuan),doanhthuList);
        }
//        System.out.println(doanhthuList);
        System.out.println(mapDoanhthu);
        System.out.println();
        Set<Integer> set = mapDoanhthu.keySet();
        for (Integer key : set) {
            System.out.println(key);
//            System.out.println(mapDoanhthu.get(key));
            for(int i=0;i<mapDoanhthu.get(key).size();i++){

                System.out.println(mapDoanhthu.get(key).get(i).startDate+"  "
                        +mapDoanhthu.get(key).get(i).endDate+"  "+
                        getDayDiff(mapDoanhthu.get(key).get(i).startDate,mapDoanhthu.get(key).get(i).endDate)
                         +"    "+mapDoanhthu.get(key).get(i).avgDoanhthu

                );
            }
        }
        return mapDoanhthu;
    }


    public static void main(String[] args) throws SQLException, IOException {

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException ex) {

            System.out.println("Error: unable to load driver class!");

            System.exit(1);

        }

        Connection conn = null;

        conn = DriverManager.getConnection(URL, USER, PASS);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY_IDDUANAN);

        System.out.println(QUERY_IDDUANAN);

        Map<Integer,Double> sumDoanhthu = new HashMap<>();
        Map<Integer,List<Doanhthu>> doanhthu=  getMapdoanhthu();


        System.out.println("xett");
        while (rs.next()) {
            Integer prjId = Integer.parseInt(rs.getString(1));
            System.out.println(prjId);
            Double doanhthuOfPrj =0.0;
            if(doanhthu.get(prjId)!=null){
                for(int i=0;i<doanhthu.get(prjId).size();i++){

//                System.out.println(doanhthu.get(prjId).get(i).startDate+"  "
//                        +doanhthu.get(prjId).get(i).endDate+"  "+
//                        doanhthu.get(prjId).get(i).avgDoanhthu
//                );

                    if(getDayDiff(doanhthu.get(prjId).get(i).startDate,queryMinday)>0){
                        //khoi tao
                        doanhthuOfPrj =0.0;
                        // query Maxday <
                        if(getDayDiff(queryMaxday,doanhthu.get(prjId).get(i).endDate)>0){
                            doanhthuOfPrj+=doanhthu.get(prjId).get(i).avgDoanhthu*getDayDiff(queryMinday,queryMaxday);
                            System.out.println(doanhthu.get(prjId).get(i).avgDoanhthu
                                    +"    "+queryMinday+   "    "+ queryMaxday
                                    +"   "+getDayDiff(queryMinday,queryMaxday) );
                            System.out.println("break");
                            break;
                        }
                        else{
                            doanhthuOfPrj+=doanhthu.get(prjId).get(i).avgDoanhthu*getDayDiff(queryMinday,doanhthu.get(prjId).get(i).getEndDate());
                            System.out.println(doanhthu.get(prjId).get(i).avgDoanhthu
                                    +"    "+queryMinday+   "    "+ doanhthu.get(prjId).get(i).getEndDate()
                                    +"    "+ getDayDiff(queryMinday,doanhthu.get(prjId).get(i).getEndDate()));
                        }

                    }
                    else{
                        //queryMaxday < startDate
                        if(getDayDiff(doanhthu.get(prjId).get(i).startDate,queryMaxday)<0){
                            System.out.println(queryMaxday+   "    "+ doanhthu.get(prjId).get(i).startDate);
                            System.out.println("end prj");
                            break;
                        }
                        //queryMaxday > startDate
                        else{
                            if(getDayDiff(doanhthu.get(prjId).get(i).endDate,queryMaxday)<0){
                                doanhthuOfPrj+= doanhthu.get(prjId).get(i).avgDoanhthu*getDayDiff(doanhthu.get(prjId).get(i).startDate,queryMaxday);
                                System.out.println(doanhthu.get(prjId).get(i).avgDoanhthu+
                                        "     "+doanhthu.get(prjId).get(i).startDate+   "    "+ queryMaxday
                                        +"    "+getDayDiff(doanhthu.get(prjId).get(i).startDate,queryMaxday));
                            }
                            else{
                                doanhthuOfPrj+= doanhthu.get(prjId).get(i).avgDoanhthu*getDayDiff(doanhthu.get(prjId).get(i).startDate,doanhthu.get(prjId).get(i).endDate);
                                System.out.println(doanhthu.get(prjId).get(i).avgDoanhthu+
                                        "     "+doanhthu.get(prjId).get(i).startDate+   "    "+ doanhthu.get(prjId).get(i).endDate
                                        +"    "+getDayDiff(doanhthu.get(prjId).get(i).startDate,doanhthu.get(prjId).get(i).endDate));
                            }
                        }
                    }

                }
            }



//            doanhthuOfPrj = (double) Math.round(doanhthuOfPrj * 100) / 100;
            sumDoanhthu.put(prjId,doanhthuOfPrj);
        }

        System.out.println(sumDoanhthu);

        for (Map.Entry<Integer, Double> entry : sumDoanhthu.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }


    }
}
