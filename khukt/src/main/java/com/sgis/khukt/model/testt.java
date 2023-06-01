package com.sgis.khukt.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class testt {
    static final String URL = "jdbc:postgresql://localhost:5434/khukt_dongnam_20221223?useUnicode=yes&characterEncoding=UTF-8";

    static final String USER = "postgres";

    static final String PASS = "11";

    // ngay bat dau 1111-01-01 ngay ket thuc 2023-06-16

    static final String QUERY_DOANHTHU = "SELECT duandtsx.id,duandtsx.ten,ketqua.moc_baocao,ketqua.doanhthu_tinh_tudaunam FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                        inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "            inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "            inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "            where kcn.id = 1 and ketqua.moc_baocao > '1111-01-01' and " +
            "ketqua.moc_baocao < '2023-06-16' \n" +
            "\t\t\torder by duandtsx.id,ketqua.moc_baocao";

//    static String
    static String queryMaxday  = "2023-06-16";

    static String[] arrOfMaxYears = queryMaxday.split("-");
    static Integer queryMaxYear = Integer.parseInt(arrOfMaxYears[0]);

    static String queryMinday  = "1111-01-01";

    static String[] arrOfMinYears = queryMinday.split("-");
    static Integer queryMinYear = Integer.parseInt(arrOfMinYears[0]);



    static final String QUERY_MAXDAY = "SELECT duandtsx.id,MAX(ketqua.moc_baocao)  FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                        inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "            inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "            inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep \n" +
            "            where kcn.id = 1 and ketqua.moc_baocao > '1111-01-01' and ketqua.moc_baocao < '2023-06-16' \n" +
            "\t\t\tgroup by  duandtsx.id\n" +
            "\t\t\torder by duandtsx.id ";

    static final String QUERY_DAYAFTERREPORT = "SELECT duandtsx.id,ketqua.moc_baocao,ketqua.doanhthu_tinh_tudaunam  FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                                                inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "                                  inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "                                    inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep\n" +
            "                                    where kcn.id = 1 and ketqua.moc_baocao > '2023-06-16' \n" +
            "                       order by duandtsx.id,ketqua.moc_baocao desc";


    static final String QUERY_DAYBEFOREREPORT = "SELECT duandtsx.id,ketqua.moc_baocao,ketqua.doanhthu_tinh_tudaunam  FROM public.tbl_ketqua_sanxuatkinhdoanh ketqua \n" +
            "                                                inner join public.tbl_duan_dautusanxuat duandtsx on ketqua.id_duan_sanxuatkinhdoanh = duandtsx.id \n" +
            "                                  inner join public.tbl_khu_chuyennganh kcn ON kcn.id = duandtsx.id_khu_chuyennganh\n" +
            "                                    inner join public.tbl_doanhnghiep dn ON dn.id = duandtsx.id_doanhnghiep\n" +
            "                                    where kcn.id = 1 and ketqua.moc_baocao < '1111-01-01' \n" +
            "                       order by duandtsx.id,ketqua.doanhthu_tinh_tudaunam";

    static public Long  getDayDiff(String startDay, String endDay){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String firstInput = startDay;
        String secondInput = endDay;
        LocalDate firstDate = LocalDate.parse(firstInput, formatter);
        LocalDate secondDate = LocalDate.parse(secondInput, formatter);
        return ChronoUnit.DAYS.between(firstDate, secondDate);
    }



    public static void main(String[] args) throws SQLException, IOException {

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
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(QUERY_MAXDAY);
        Map < Integer, String > maxDayofPrj = new HashMap< Integer, String >();
        Map <Integer,Double> doanhthuOfAllduan = new HashMap<>();
        while (rs.next()) {
            maxDayofPrj.put(Integer.parseInt(rs.getString(1)), rs.getString(2));
        }

        rs = stmt.executeQuery(QUERY_DAYAFTERREPORT);
        Map < Integer, List<String>> dayAfterReport = new HashMap < Integer, List<String> > ();
        while (rs.next()) {
            List<String> baocaovadoanhthu = new ArrayList<>();
            baocaovadoanhthu.add(rs.getString(2));
            baocaovadoanhthu.add(rs.getString(3));
            if(dayAfterReport.get(Integer.parseInt(rs.getString(1)))!=null){
                dayAfterReport.remove(Integer.parseInt(rs.getString(1)));
            }
            dayAfterReport.put(Integer.parseInt(rs.getString(1)), baocaovadoanhthu);
        }


        rs = stmt.executeQuery(QUERY_DAYBEFOREREPORT);
        Map < Integer, List<String> > dayBeforeReport = new HashMap < Integer, List<String> > ();
        while (rs.next()) {
            List<String> baocaovadoanhthu = new ArrayList<>();
            baocaovadoanhthu.add(rs.getString(2));
            baocaovadoanhthu.add(rs.getString(3));

            if(dayBeforeReport.get(Integer.parseInt(rs.getString(1)))!=null){
                dayBeforeReport.remove(Integer.parseInt(rs.getString(1)));
            }
            dayBeforeReport.put(Integer.parseInt(rs.getString(1)), baocaovadoanhthu);
        }

        String activeIdDuan = "-1";
        Integer checkExistDuan = 0;
        String existYear = "-1";
        String existMocbaocao = "-1";
        String existDoanhthu = "-1";
        Double sumDoanhthu = 0.0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(QUERY_DOANHTHU);
        rs = stmt.executeQuery(QUERY_DOANHTHU);



        Integer activeIdDuanInteger;

        while (rs.next()) {

            String id = rs.getString(1);
            String tenda = rs.getString(2);
            String mocbaocao = rs.getString(3);
            //            System.out.println(existMocbaocao+"  1   "+ mocbaocao);
            String doanhthu = rs.getString(4);

            String[] arrOfStr = mocbaocao.split("-");
            String yearOfMocbaocao = arrOfStr[0];





            //khoi tao & bat dau du an moi
            if (activeIdDuan.equals("-1")) {
                System.out.println("khoi tao du an moi");
                //id cua du an dang xet
                activeIdDuan = id;

                checkExistDuan = 0;
                existYear = "-1";
                existMocbaocao = "-1";
                existDoanhthu = "-1";

                activeIdDuanInteger = Integer.parseInt(activeIdDuan);
                existDoanhthu = doanhthu;

                // lay thoi diem moc bao cao gan nhat truoc giai doan bao cao

                System.out.println(activeIdDuanInteger);



                String dayBeforeReportofPrj  ="0";
                String[] arrOfYearsdayBeforeReportofPrj;
                String YearBeforeReportofPrj ="0";
                System.out.println("ccc");

                if(dayBeforeReport.get(activeIdDuanInteger)!=null&&(dayBeforeReport.get(activeIdDuanInteger).get(1)!=null)){
                    dayBeforeReportofPrj = dayBeforeReport.get(activeIdDuanInteger).get(0);
                    arrOfYearsdayBeforeReportofPrj= dayBeforeReportofPrj.split("-");
                    YearBeforeReportofPrj= arrOfYearsdayBeforeReportofPrj[0];
                }





                //lay thoi diem moc bao cao gan nhat sau giai doan bao cao
                String dayAfterReportofPrj   ="0";
                String[] arrOfYearsdayAfterReportofPrj;
                String YearAfterReportofPrj ="0";

                System.out.println("herer we go   "+ dayAfterReport.get(activeIdDuanInteger));
                if(dayAfterReport.get(activeIdDuanInteger)!=null){
                    dayAfterReportofPrj  = dayAfterReport.get(activeIdDuanInteger).get(0);
                    arrOfYearsdayAfterReportofPrj = dayAfterReportofPrj.split("-");
                    YearAfterReportofPrj = arrOfYearsdayAfterReportofPrj[0];
                }



                // sau khi khoi tao thi do la moc bao cao cuoi -> doi du an
                if(maxDayofPrj.get(activeIdDuanInteger).equals(mocbaocao)){

                    System.out.println("sau khi khoi tao thi day la moc bao cao cuoi");


                    //neu nam dang xet < queryMaxYear
                    //*luu y
                    if (Integer.parseInt(yearOfMocbaocao) < queryMaxYear) {
                        //xu ly tu thoi gian truoc day toi truoc no
                        //check xem tu thoi diem do toi dau nam co moc bao cao khac khong


                        if(yearOfMocbaocao.equals(YearBeforeReportofPrj)){

                            Long day = getDayDiff(dayBeforeReportofPrj,mocbaocao);

                            Double avgDoanhthu  = 0.0;

                            Double _doanhthu =0.0;
                            Double _doanhthucu =0.0;

                            if(doanhthu!=null){
                                _doanhthu = Double.parseDouble(doanhthu);
                            }
                            if(dayBeforeReport.get(activeIdDuanInteger)!=null&&(dayBeforeReport.get(activeIdDuanInteger).get(1)!=null)){
                                _doanhthucu=Double.parseDouble(dayBeforeReport.get(activeIdDuanInteger).get(1));
                            }

                            avgDoanhthu=(_doanhthu-_doanhthucu)/day;


                            day = getDayDiff(queryMinday,mocbaocao);


                            // tu thoi diem queryMinday toi moc bao cao dau tien
                            sumDoanhthu +=avgDoanhthu*day;
                            System.out.println(sumDoanhthu);


                            String firstInput = yearOfMocbaocao + "-01-01";
                            String secondInput = mocbaocao;


                            day = getDayDiff(firstInput, secondInput);


                            _doanhthu =0.0;


                            if(doanhthu!=null){
                                _doanhthu = Double.parseDouble(doanhthu);
                            }


                            avgDoanhthu = _doanhthu/day;

                            firstInput = mocbaocao;
                            secondInput = yearOfMocbaocao+"-12-31";
                            day = getDayDiff(firstInput, secondInput);
                            //tu moc bao cao toi cuoi nam do
                            sumDoanhthu += avgDoanhthu * day;

                            System.out.println(sumDoanhthu);
//                             ((2))
//                          done
                        }
                        //neu khong YearBeforeReportofPrj< yearOfMocbaocao
                        else {
//                             ((1))
                            String firstInput = yearOfMocbaocao + "-01-01";
                            String secondInput = mocbaocao;
                            Long day = getDayDiff(firstInput, secondInput);

                            Double IntDoanhthu =0.0;
                            if(doanhthu!=null){
                                IntDoanhthu= Double.parseDouble(doanhthu);
                            }
                            Double avgDoanhthu = IntDoanhthu/day;

                            // xet xem nam dau tien truy van < yearOfMocbaocao hay =
                            if(queryMinYear<Integer.parseInt(yearOfMocbaocao)){
                                if(Integer.parseInt(YearBeforeReportofPrj)==queryMinYear){
                                    firstInput = YearBeforeReportofPrj+"-01-01";
                                    secondInput = dayBeforeReport.get(0).get(0);
                                    day = getDayDiff(firstInput, secondInput);


                                    Double _doanhthucu =0.0;


                                    if(dayBeforeReport.get(activeIdDuanInteger)!=null&&(dayBeforeReport.get(activeIdDuanInteger).get(1)!=null)){
                                        _doanhthucu=Double.parseDouble(dayBeforeReport.get(activeIdDuanInteger).get(1));
                                    }



                                    avgDoanhthu = _doanhthucu/day;

                                    firstInput = queryMinday;
                                    secondInput = YearBeforeReportofPrj+"-12-31";
                                    day = getDayDiff(firstInput, secondInput);
                                    sumDoanhthu += avgDoanhthu * day;
                                    System.out.println(sumDoanhthu);



                                    firstInput = yearOfMocbaocao+"-01-01";
                                    secondInput = mocbaocao;
                                    day = getDayDiff(firstInput, secondInput);

                                    Double _doanhthu =0.0;

                                    if(doanhthu!=null){
                                        _doanhthu = Double.parseDouble(doanhthu);
                                    }


                                    avgDoanhthu = Double.parseDouble(doanhthu)/day;


                                    sumDoanhthu += avgDoanhthu*365;
                                    System.out.println(sumDoanhthu);
                                }
                            }
                            else if(queryMinYear==Integer.parseInt(yearOfMocbaocao)){
                                firstInput = queryMinday;
                                secondInput = yearOfMocbaocao+"-12-31";
                                day = getDayDiff(firstInput, secondInput);
                                sumDoanhthu += avgDoanhthu * day;
                                System.out.println(sumDoanhthu);
                            }


                        }

                        // da xet het trong yearofmocbaocao


                        // xu ly tu dau nam trong maxYear toi thoi diem do

                        System.out.println("maxx   "+ queryMaxYear +"    "+YearAfterReportofPrj);
                        if(queryMaxYear==(Integer.parseInt(YearAfterReportofPrj))){
                            System.out.println("vo max");
                            String firstInput = YearAfterReportofPrj+"-01-01";
                            String secondInput = dayAfterReport.get(activeIdDuanInteger).get(0);
                            Long day = getDayDiff(firstInput, secondInput);

                            Double avgDoanhthu =  0.0;


                            Double _doanhthucu =0.0;


                            if(dayBeforeReport.get(activeIdDuanInteger)!=null&&(dayBeforeReport.get(activeIdDuanInteger).get(1)!=null)){
                                _doanhthucu=Double.parseDouble(dayBeforeReport.get(activeIdDuanInteger).get(1));
                            }

                            avgDoanhthu=(_doanhthucu)
                                    /day;

                            System.out.println("avgdoanhthu   "+avgDoanhthu);

                            firstInput = YearAfterReportofPrj+"-01-01";
                            secondInput = queryMaxday;
                            day = getDayDiff(firstInput, secondInput);



                            sumDoanhthu += avgDoanhthu*day;
                            System.out.println(sumDoanhthu);
                        }

                    }



                    //neu nam dang xet = nam queryMax
                    else {
                        //queryMaxday


                        //check xem moc bao cao trc do nam o cung nam hay khong
                        // khac nhau o cach tinh trung binh cong
                        if(yearOfMocbaocao.equals(YearBeforeReportofPrj)){
                            String firstInput = dayBeforeReportofPrj;
                            String secondInput = mocbaocao;

                            Long day = getDayDiff(firstInput, secondInput);

                            //trung binh cong = khoang cach giua 2 moc bao cao
                            Double avgDoanhthu  = 0.0;

                            Double _doanhthu =0.0;
                            Double _doanhthucu =0.0;

                            if(doanhthu!=null){
                                _doanhthu = Double.parseDouble(doanhthu);
                            }
                            if(dayBeforeReport.get(activeIdDuanInteger)!=null&&(dayBeforeReport.get(activeIdDuanInteger).get(1)!=null)){
                                _doanhthucu=Double.parseDouble(dayBeforeReport.get(activeIdDuanInteger).get(1));
                            }

                            avgDoanhthu=(_doanhthu-_doanhthucu)/day;


                            firstInput = queryMinday;
                            secondInput = mocbaocao;
                            day = getDayDiff(firstInput, secondInput);


                            // tu thoi diem queryMinday toi moc bao cao dau tien
                            sumDoanhthu +=avgDoanhthu*day;
                            System.out.println(sumDoanhthu);


                            firstInput = yearOfMocbaocao + "-01-01";
                            secondInput = mocbaocao;
                            day = getDayDiff(firstInput, secondInput);


                            avgDoanhthu = _doanhthu/day;

                            firstInput = mocbaocao;
                            secondInput = queryMaxday;
                            day = getDayDiff(firstInput, secondInput);
                            //tu thoi diem moc bao cao dau tien toi queryMax
                            sumDoanhthu += avgDoanhthu * day;
                            System.out.println(sumDoanhthu);
//
//                          done
                        }
//                         }


                        else{
//                             ((1))
                            String firstInput = yearOfMocbaocao + "-01-01";
                            String secondInput = mocbaocao;

                            Long day = getDayDiff(firstInput, secondInput);

                            Double IntDoanhthu =0.0;
                            if(doanhthu!=null){
                                IntDoanhthu=Double.parseDouble(doanhthu);
                            }
                            //Trung binh cong = tu moc bao cao toi dau nam
                            Double avgDoanhthu = IntDoanhthu/day;

                            // xet xem nam dau tien truy van < yearOfMocbaocao hay =
                            if(queryMinYear<Integer.parseInt(yearOfMocbaocao)){
                                if(Integer.parseInt(YearBeforeReportofPrj)==queryMinYear){
                                    firstInput = YearBeforeReportofPrj+"-01-01";
                                    secondInput = dayBeforeReport.get(0).get(0);
                                    day = getDayDiff(firstInput, secondInput);
                                    avgDoanhthu = 0.0;
                                    if(dayBeforeReport.get(0).get(1)!=null){
                                        avgDoanhthu=Double.parseDouble(dayBeforeReport.get(0).get(1))/day;
                                    }


                                    firstInput = queryMinday;
                                    secondInput = YearBeforeReportofPrj+"-12-31";
                                    day = getDayDiff(firstInput, secondInput);
                                    //xet tu queryMinday toi het nam trong queryMinYear
                                    sumDoanhthu += avgDoanhthu * day;
                                    System.out.println(sumDoanhthu);



                                    if(YearAfterReportofPrj==yearOfMocbaocao){
                                        firstInput = yearOfMocbaocao+"-01-01";
                                        secondInput = dayAfterReport.get(activeIdDuanInteger).get(0);
                                        day = getDayDiff(firstInput, secondInput);
                                        //xet tu queryMinday toi het nam trong queryMinYear
                                        avgDoanhthu = 0.0;
                                        if(dayBeforeReport.get(0).get(1)!=null){
                                            avgDoanhthu=Double.parseDouble(dayBeforeReport.get(0).get(1))/day;
                                        }


                                        firstInput = yearOfMocbaocao+"-01-01";
                                        secondInput = queryMaxday;
                                        day = getDayDiff(firstInput, secondInput);
                                        //xet tu dau nam toi maxday
                                        sumDoanhthu += avgDoanhthu * day;
                                        System.out.println(sumDoanhthu);


                                    }
                                    else{
                                        firstInput = yearOfMocbaocao+"-01-01";
                                        secondInput = mocbaocao;
                                        day = getDayDiff(firstInput, secondInput);
                                        //xet tu queryMinday toi het nam trong queryMinYear


                                        avgDoanhthu = 0.0;
                                        if(doanhthu!=null){
                                            avgDoanhthu=Double.parseDouble(doanhthu)/day;
                                        }


                                        firstInput = yearOfMocbaocao+"-01-01";
                                        secondInput = queryMaxday;
                                        day = getDayDiff(firstInput, secondInput);
                                        //xet tu dau nam toi maxday
                                        sumDoanhthu += avgDoanhthu * day;
                                        System.out.println(sumDoanhthu);
                                    }



                                }


                            }
                            else if(queryMinYear==Integer.parseInt(yearOfMocbaocao)){
                                if(YearAfterReportofPrj==yearOfMocbaocao){
                                    firstInput = yearOfMocbaocao+"-01-01";
                                    secondInput = dayAfterReport.get(activeIdDuanInteger).get(0);
                                    day = getDayDiff(firstInput, secondInput);
                                    //xet tu queryMinday toi het nam trong queryMinYear

                                    avgDoanhthu = 0.0;
                                    if(dayBeforeReport.get(0).get(1)!=null){
                                        avgDoanhthu=Double.parseDouble(dayBeforeReport.get(0).get(1))/day;
                                    }


                                    firstInput = queryMinday;
                                    secondInput = queryMaxday;
                                    day = getDayDiff(firstInput, secondInput);
                                    //xet tu dau nam toi maxday
                                    sumDoanhthu += avgDoanhthu * day;
                                    System.out.println(sumDoanhthu);


                                }
                                else{
                                    firstInput = yearOfMocbaocao+"-01-01";
                                    secondInput = mocbaocao;
                                    day = getDayDiff(firstInput, secondInput);
                                    //xet tu queryMinday toi het nam trong queryMinYear
                                    avgDoanhthu = 0.0;
                                    if(doanhthu!=null){
                                        avgDoanhthu=Double.parseDouble(doanhthu)/day;
                                    }


                                    firstInput = queryMinday;
                                    secondInput = queryMaxday;
                                    day = getDayDiff(firstInput, secondInput);
                                    //xet tu dau nam toi maxday
                                    sumDoanhthu += avgDoanhthu * day;
                                    System.out.println(sumDoanhthu);
                                }
                            }


                        }

                    }

                    System.out.println("day la moc bao cao cuoi " +sumDoanhthu);
                    doanhthuOfAllduan.put(activeIdDuanInteger,sumDoanhthu);
                    activeIdDuan = "-1";

                    sumDoanhthu = 0.0;

                }






                //neu khong thi xet
                else{

                    System.out.println("sau khi khoi tao thi day khong phai moc bao cao cuoi");

                    //check xem tu thoi diem do toi dau nam co moc bao cao khac khong
                    //neu co
                    //minyear ==yearofmocbaocao
                    if(yearOfMocbaocao.equals(YearBeforeReportofPrj)){
                        String firstInput = dayBeforeReportofPrj;
                        String secondInput = mocbaocao;
                        LocalDate firstDate = LocalDate.parse(firstInput, formatter);
                        LocalDate secondDate = LocalDate.parse(secondInput, formatter);
                        Long day = ChronoUnit.DAYS.between(firstDate, secondDate);



                        Double avgDoanhthu = (Double.parseDouble(doanhthu)-Double.parseDouble(dayBeforeReport.get(activeIdDuanInteger).get(1)))/day;


                        firstInput = queryMinday;
                        secondInput = mocbaocao;
                        firstDate = LocalDate.parse(firstInput, formatter);
                        secondDate = LocalDate.parse(secondInput, formatter);
                        day = ChronoUnit.DAYS.between(firstDate, secondDate);
                        System.out.println(firstDate+"    "+secondDate);

//                          ((3))
                        sumDoanhthu += avgDoanhthu * day;
                        System.out.println(sumDoanhthu);


                    }

                    //neu tu do toi dau nam khong co moc bao cao khac
                    else{
                        String firstInput = yearOfMocbaocao + "-01-01";
                        String secondInput = mocbaocao;

                        Long day = getDayDiff(firstInput, secondInput);

                        Double IntDoanhthu =0.0;
                        if(doanhthu!=null){
                            IntDoanhthu=Double.parseDouble(doanhthu);
                        }
                        //Trung binh cong = tu moc bao cao toi dau nam
                        Double avgDoanhthu = IntDoanhthu/day;

                        System.out.println("???? "+queryMinYear+ "    "+ yearOfMocbaocao);

                        // xet xem nam dau tien truy van < yearOfMocbaocao hay =
                        if(queryMinYear<Integer.parseInt(yearOfMocbaocao)){
                            if(Integer.parseInt(YearBeforeReportofPrj)==queryMinYear){
                                firstInput = YearBeforeReportofPrj+"-01-01";
                                secondInput = dayBeforeReport.get(0).get(0);
                                day = getDayDiff(firstInput, secondInput);
                                avgDoanhthu = 0.0;
                                if(dayBeforeReport.get(0).get(1)!=null){
                                    avgDoanhthu=Double.parseDouble(dayBeforeReport.get(0).get(1))/day;
                                }


                                firstInput = queryMinday;
                                secondInput = YearBeforeReportofPrj+"-12-31";
                                day = getDayDiff(firstInput, secondInput);
                                //xet tu queryMinday toi het nam trong queryMinYear
                                sumDoanhthu += avgDoanhthu * day;
                                System.out.println(sumDoanhthu);



                                sumDoanhthu += getDayDiff(yearOfMocbaocao+"-01-01",mocbaocao)*avgDoanhthu;
                                System.out.println("doanh thu thoi diem nay "+sumDoanhthu);


                            }

                            else{
                                if(doanhthu!=null){
                                    sumDoanhthu +=Double.parseDouble(doanhthu);
                                }

                            }

                        }


                        else if(queryMinYear==Integer.parseInt(yearOfMocbaocao)){

                            System.out.println("queryMinYear==Integer.parseInt(yearOfMocbaocao)");
                            sumDoanhthu += getDayDiff(queryMinday,mocbaocao)*avgDoanhthu;
                            System.out.println("doanh thu thoi diem nay "+sumDoanhthu);
                        }

                    }


                    //xet het tu queryMinday toi moc bao cao dau tien
                }




            }

            //tiep tuc du an cu
            else{

                System.out.println("tiep tuc du an cu");


                activeIdDuanInteger = Integer.parseInt(activeIdDuan);




                String dayBeforeReportofPrj  ="0";
                String[] arrOfYearsdayBeforeReportofPrj;
                String YearBeforeReportofPrj ="0";
                if(dayBeforeReport.get(activeIdDuanInteger)!=null&&(dayBeforeReport.get(activeIdDuanInteger).get(1)!=null)){
                    dayBeforeReportofPrj = dayBeforeReport.get(activeIdDuanInteger).get(0);
                    arrOfYearsdayBeforeReportofPrj= dayBeforeReportofPrj.split("-");
                    YearBeforeReportofPrj= arrOfYearsdayBeforeReportofPrj[0];
                }


                //lay thoi diem moc bao cao gan nhat sau giai doan bao cao


                String dayAfterReportofPrj   ="0";
                String[] arrOfYearsdayAfterReportofPrj;
                String YearAfterReportofPrj ="0";
                if(dayAfterReport.get(activeIdDuanInteger)!=null){
                    dayAfterReportofPrj  = dayAfterReport.get(activeIdDuanInteger).get(0);
                    arrOfYearsdayAfterReportofPrj = dayAfterReportofPrj.split("-");
                    YearAfterReportofPrj = arrOfYearsdayAfterReportofPrj[0];
                }


                //day la su kien cuoi cung khong
                //neu co

                if(maxDayofPrj.get(activeIdDuanInteger).equals(mocbaocao)){
                    System.out.println("tiep tuc thi day la bao cao cuoi");


                    //* check xem 2 moc bao cao cu va moi co cung nam hay khong

                    if(yearOfMocbaocao.equals(existYear)){

                        Double _doanhthu =0.0;
                        Double _doanhthucu =0.0;

                        if(doanhthu!=null){
                            _doanhthu = Double.parseDouble(doanhthu);
                        }

                        if(existDoanhthu!=null){
                            _doanhthucu=Double.parseDouble(existDoanhthu);
                        }
                        System.out.println("trc khi moc bao cao "+ sumDoanhthu);

                        sumDoanhthu +=_doanhthu-_doanhthucu;

                        System.out.println("day la moc bao cao cuoi va "+ sumDoanhthu);
                    }
                    //2 bao cao cu va moi khac nam
                    else{
                        String firstInput = existYear + "-01-01";
                        String secondInput = existMocbaocao;
                        Long day = getDayDiff(firstInput, secondInput);
                        //heree
                        Double avgDoanhthu = 0.0;

                        Double _doanhthucu =0.0;



                        if(existDoanhthu!=null){
                            avgDoanhthu=_doanhthucu/day;
                        }





                        firstInput = existMocbaocao;
                        secondInput = existYear + "-12-31";
                        day = getDayDiff(firstInput, secondInput);

                        sumDoanhthu += avgDoanhthu *day;

                        //tu dau nam toi moc bao cao do

                        Double _doanhthu =0.0;


                        if(doanhthu!=null){
                            _doanhthu = Double.parseDouble(doanhthu);
                        }
                        sumDoanhthu +=_doanhthu;
                        System.out.println("day la moc bao cao cuoi va "+ sumDoanhthu);

                    }

                    // xong het toi dayOfmocbaocao



                    if(queryMaxYear.equals(Integer.parseInt(yearOfMocbaocao))){

                        if(queryMaxYear.equals(Integer.parseInt(YearAfterReportofPrj))){

                            String firstInput = mocbaocao;
                            String secondInput = dayAfterReportofPrj;

                            Long day = getDayDiff(firstInput, secondInput);

                            Double avgDoanhthu  = 0.0;
                            Double _doanhthu =0.0;
                            Double _doanhthucu =0.0;

                            if(doanhthu!=null){
                                _doanhthu = Double.parseDouble(doanhthu);
                            }
                            System.out.println("??" +dayBeforeReport.get(activeIdDuanInteger));
                            if(dayBeforeReport.get(activeIdDuanInteger)!=null&&(dayBeforeReport.get(activeIdDuanInteger).get(1)!=null)){
                                _doanhthucu=Double.parseDouble(dayBeforeReport.get(activeIdDuanInteger).get(1));
                            }

                            avgDoanhthu=(_doanhthu-_doanhthucu)/day;

                            firstInput = mocbaocao;
                            secondInput = queryMaxday;
                            day = getDayDiff(firstInput, secondInput);

                            System.out.println(avgDoanhthu);



                            System.out.println("doanh thu thoi diem nay "+sumDoanhthu);

                            sumDoanhthu += avgDoanhthu*day;
                            System.out.println(sumDoanhthu);
                        }
                        else{
                            String firstInput = yearOfMocbaocao+"-01-01";
                            String secondInput = mocbaocao;

                            Long day = getDayDiff(firstInput, secondInput);

                            Double avgDoanhthu = 0.0;
                            if(doanhthu!=null){
                                avgDoanhthu=Double.parseDouble(doanhthu)/day;
                            }

                            firstInput = mocbaocao;
                            secondInput = queryMaxday;
                            day = getDayDiff(firstInput, secondInput);

                            sumDoanhthu += avgDoanhthu*day;
                            System.out.println(sumDoanhthu);
                        }
                        //done
                    }

                    else{

                        String firstInput = yearOfMocbaocao+"-01-01";
                        String secondInput = mocbaocao;

                        Long day = day = getDayDiff(firstInput, secondInput);

                        Double avgDoanhthu = 0.0;
                        if(doanhthu!=null){
                            avgDoanhthu=Double.parseDouble(doanhthu)/day;
                        }

                        firstInput = mocbaocao;
                        secondInput = yearOfMocbaocao+"-12-31";
                        day = getDayDiff(firstInput, secondInput);

                        sumDoanhthu += avgDoanhthu*day;
                        System.out.println(sumDoanhthu);
                        if(YearAfterReportofPrj.equals(queryMaxday)){
                            firstInput = queryMaxYear+"-01-01";
                            secondInput = dayAfterReport.get(activeIdDuanInteger).get(0);
                            day = getDayDiff(firstInput, secondInput);
                            avgDoanhthu = 0.0;
                            if(dayBeforeReport.get(0).get(1)!=null){
                                avgDoanhthu=Double.parseDouble(dayBeforeReport.get(0).get(1))/day;
                            }

                            firstInput = queryMaxYear+"-01-01";
                            secondInput = queryMaxday;
                            day = getDayDiff(firstInput, secondInput);
                            sumDoanhthu += avgDoanhthu*day;
                            System.out.println(sumDoanhthu);

                        }
                        //done
                    }


                    doanhthuOfAllduan.put(activeIdDuanInteger,sumDoanhthu);
                    System.out.println( "ket qua cuoi cung "+ sumDoanhthu);
                    activeIdDuan = "-1";
                    checkExistDuan = 0;
                    existYear = "-1";
                    existMocbaocao = "-1";
                    existDoanhthu = "-1";
                    sumDoanhthu = 0.0;


                }






                // khong phai su kien cuoi
                else{

                    System.out.println("tiep tuc thi day khong phai bao cao cuoi");
                    // check xem sang nam moi hay chua
                    // truong hop chua sang

                    if(yearOfMocbaocao.equals(existYear)){
                        System.out.println("yearOfMocbaocao.equals(existYear)");
                        System.out.println(existMocbaocao+"    "+mocbaocao);
                        Double _doanhthu =0.0;
                        Double _doanhthucu =0.0;

                        if(doanhthu!=null){
                            _doanhthu = Double.parseDouble(doanhthu);
                        }

                        if(existDoanhthu!=null){
                            _doanhthucu=Double.parseDouble(existDoanhthu);
                        }

                        sumDoanhthu +=_doanhthu-_doanhthucu;
                        System.out.println(sumDoanhthu);

                    }
                    // truong hop sang nam moi
                    else{
                        System.out.println("sang nam moi "+ yearOfMocbaocao +"   "+ existYear);
                        System.out.println("!yearOfMocbaocao.equals(existYear)");
                        String firstInput = existYear + "-01-01";
                        String secondInput = existMocbaocao;

                        Long day = getDayDiff(firstInput, secondInput);
                        // doanh thu cap nhat sau 1 nam
                        Double avgDoanhthu = 0.0;
                        if(doanhthu!=null){
                            avgDoanhthu=Double.parseDouble(doanhthu)/day;
                        }

                        firstInput = existMocbaocao;
                        secondInput = existYear + "-12-31";
                        day = getDayDiff(firstInput, secondInput);
                        //het nam cu
                        sumDoanhthu += avgDoanhthu*day;

                        //cong tu cuoi nam cu toi thoi diem moc bao cao

                        Double IntDoanhthu =0.0;
                        if(doanhthu!=null){
                            IntDoanhthu=Double.parseDouble(doanhthu);
                        }
                        sumDoanhthu+=IntDoanhthu;

                        System.out.println(sumDoanhthu);

                    }

                }
            }


            System.out.println();
            existMocbaocao = mocbaocao;
            existDoanhthu = doanhthu;
            existYear = yearOfMocbaocao;
        }

        System.out.println();


        Map <Integer,Double> finaldoanhthuOfAllduan = new HashMap<>();

        for (Map.Entry<Integer, Double> entry : doanhthuOfAllduan.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
            Double doanhthuOfDuan = (double) Math.ceil(entry.getValue() * 100) / 100;
            finaldoanhthuOfAllduan.put(entry.getKey(),doanhthuOfDuan);
        }

        System.out.println(finaldoanhthuOfAllduan);



        conn.close();
    }
}