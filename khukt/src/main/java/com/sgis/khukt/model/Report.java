package com.sgis.khukt.model;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class Report extends HttpServlet {
    public Report(){}
    private DataSource datasource;
//
    public void buildReport(ReportDesignHandle designHandle) {
        Connection connection = null;
        try {
            int i, j, k, l;
//            connection = getConnection();
            common v = new common();
//            v.setFontReport("Abadi", "13pt", designHandle);
//            v.setAlignReport("justify", designHandle);
            v.setMarginReport("A4","landscape","0px","0px","0px","0px",designHandle);
            //***Trình tự1
            //addTableHeader
            addTableHeader("Hà Nộii", "TITLEE", designHandle);
            v.addLabel("", designHandle);

            StyleHandle labelStyle1 = v.createStyle("label1", "B", "", false,
                    designHandle);
            designHandle.getStyles().add(labelStyle1);
            StyleHandle labelStyle2 = v.createStyle("label2", "I", "", false,
                    designHandle);
            designHandle.getStyles().add(labelStyle2);
            StyleHandle labelStyle3 = v.createStyle("label3", "BI", "", false,
                    designHandle);
            designHandle.getStyles().add(labelStyle3);
            StyleHandle textStyle1 = v.createStyle("text1", "N", "2.5em",
                    false, designHandle);
            designHandle.getStyles().add(textStyle1);
            //***Trình tự2
            v.addLabel("THÔNG BÁO", "label1", designHandle).setProperty(
                    StyleHandle.TEXT_ALIGN_PROP, "center");
            v.addLabel("TÌNH HÌNH SINH VẬT GÂY HẠI 7 NGÀY", "label1",
                    designHandle).setProperty(StyleHandle.TEXT_ALIGN_PROP,
                    "center");
            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "dd/MM/yyyy");


            String clrHeader = "#E1FFFF";
            String clrTextHeader = "#000000";
            String clrEven = "#F5FFFA";
            String clrOdd = "#FFFFFF";
            List<String> width = new ArrayList<String>();
            List<String> align = new ArrayList<String>();


            String formatReport = "doc";
//            if (formatReport.equalsIgnoreCase("doc")) {
//                width.add("12%");
//                width.add("12%");
//                width.add("8%");
//                width.add("8%");
//                width.add("7.5%");
//                width.add("7.5%");
//                width.add("7%");
//                width.add("7.5%");
//                width.add("8%");
//                width.add("9.5%");
//                width.add("9.5%");
//                width.add("7.5%");
//            } else {
//                width.add("11%");
//                width.add("11%");
//                width.add("8%");
//                width.add("7.5%");
//                width.add("7.5%");
//                width.add("7.5%");
//                width.add("7%");
//                width.add("7.5%");
//                width.add("7.5%");
//                width.add("9%");
//                width.add("9%");
//                width.add("7.5%");
//            }
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
            align.add("left");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
            align.add("left");
//            List<String> a = new ArrayList<>();
//            a.add("1");
//            a.add("2");
//            a.add("3");
//            a.add("3");
//            a.add("3");
//            a.add("3");
//            List<String> b = new ArrayList<>();
//            b.add("11");
//            b.add("21");
//            b.add("31");
//            b.add("11");
//            b.add("21");
//            b.add("31");


            List<List<String>> data_phanbo = getDataDoanhNghiep();

            //***Trình tự3
            getTablePhanbo(data_phanbo, width, align, clrHeader, clrTextHeader,
                    clrEven, clrOdd, designHandle, formatReport);// .setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP,
            // "always");
            //***Trình tự4
            addTableFooter(designHandle);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }


    }

//    private static String DB_URL = "jdbc:postgresql://localhost:5434/khukt_dongnam_20221223";

    static final String URL = "jdbc:postgresql://localhost:5434/khukt_dongnam_20221223?useUnicode=yes&characterEncoding=UTF-8";

    static final String USER = "postgres";

    static final String PASS = "11";

    static final String QUERY = "SELECT id, ten, ma_dangky, masothue, diachi, giamdoc,quocgia, linhvuc_kinhdoanh, ngay_thanhlap, \n" +
            "tbl_dm_loai_doanhnghiep.loai_doanhnghiep, \n" +
            "tbl_dm_linhvuc_sanxuatkinhdoanh.linhvuc_sanxuatkinhdoanh \n" +
            "\tFROM tbl_doanhnghiep\n" +
            "\tleft join tbl_dm_loai_doanhnghiep ON tbl_dm_loai_doanhnghiep.id_loai_doanhnghiep = tbl_doanhnghiep.id_loai_doanhnghiep\n" +
            "\tleft join tbl_dm_linhvuc_sanxuatkinhdoanh ON tbl_dm_linhvuc_sanxuatkinhdoanh.id_linhvuc_sanxuatkinhdoanh = tbl_doanhnghiep.id_linhvuc_sanxuatkinhdoanh\n" +
            "\t order by tbl_doanhnghiep.ten";



    public List<List<String>> getDataDoanhNghiep() throws SQLException {

        // start loading driver class

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

        ResultSet rs = stmt.executeQuery(QUERY);
        List<List<String>> listDoanhnghiep = new ArrayList<>();
        int a= 0;
            // show data
            while (rs.next()) {
                List<String> miniList = new ArrayList<>();
                miniList.add(rs.getString(2));
                miniList.add(rs.getString(3));
                miniList.add(rs.getString(4));
                miniList.add(rs.getString(5));
                miniList.add(rs.getString(6));
                miniList.add(rs.getString(7));
                miniList.add(rs.getString(8));
                miniList.add(rs.getString(9));
                miniList.add(rs.getString(10));
                miniList.add(rs.getString(11));
                listDoanhnghiep.add(miniList);
                if(a==0){
                    System.out.println(miniList);
                    a++;
                }
//                System.out.println(rs.getString(2)
//                        + "  " + rs.getString(3));
            }
            // close connection
            conn.close();
            return listDoanhnghiep;

    }





    protected void addTableHeader(String SoNN, String title,
                                  ReportDesignHandle design) {
        try {
            GridHandle table = design.getElementFactory().newGridItem("Table",
                    2, 3);
            table.setWidth("105%");

            table.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "none");
            table.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP, "none");
            table.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "none");
            table.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP, "none");

            table.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "0px");
            table.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP, "0px");
            table.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP, "0px");
            table.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP, "0px");

            // set width of column
            ((ColumnHandle) table.getColumns().get(0)).getWidth().setValue(
                    "45%");
            ((ColumnHandle) table.getColumns().get(1)).getWidth().setValue(
                    "60%");



            TextItemHandle text;
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent(SoNN);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "11pt");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("CHI CỤC BẢO VỆ THỰC VẬT");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Độc lập - Tự do - Hạnh phúc");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Số:     /BVTV");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent(title);
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_STYLE_PROP,
                    DesignChoiceConstants.FONT_STYLE_ITALIC);
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(1)).getContent().add(text);

            design.getBody().add(table);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // add footer
    protected void addTableFooter(ReportDesignHandle design) {
        try {
            GridHandle table = design.getElementFactory().newGridItem("", 2, 4);
            table.setWidth("100%");

            table.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "none");
            table.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP, "none");
            table.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "none");
            table.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP, "none");

            table.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "0px");
            table.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP, "0px");
            table.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP, "0px");
            table.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP, "0px");

            // set width of column
            ((ColumnHandle) table.getColumns().get(0)).getWidth().setValue(
                    "50%");
            ((ColumnHandle) table.getColumns().get(1)).getWidth().setValue(
                    "50%");

            TextItemHandle text;
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Nơi nhận:");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_STYLE_PROP,
                    DesignChoiceConstants.FONT_STYLE_ITALIC);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("- Như trên;");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(0)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("- Lưu VT.");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(0)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("THỦ TRƯỞNG ĐƠN VỊ");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).getContent().add(text);

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("(Ký tên, đóng dấu)");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_STYLE_PROP,
                    DesignChoiceConstants.FONT_STYLE_ITALIC);
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
                    .get(1)).getContent().add(text);

            design.getBody().add(table);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public GridHandle getTablePhanbo(List<List<String>> data,
                                     List<String> width, List<String> alignment, String clrHeader,
                                     String clrTextHeader, String clrEven, String clrOdd,
                                     ReportDesignHandle design, String formatReport) {
        GridHandle table = null;
        try {
            if(data.size()==0) return table;
            table = design.getElementFactory().newGridItem("", 12,
                    data.size() + 2);
            if (formatReport.equalsIgnoreCase("doc")) {
                table.setWidth("104%");
            } else {
                table.setWidth("100%");
            }

            // set width of column
            for (int c = 0; c < width.size(); c++) {
                ((ColumnHandle) table.getColumns().get(c)).getWidth().setValue(
                        width.get(c));
            }



            for (int i = 0; i <1; i++) {
                for (int j = 0; j < 10; j++) {

                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.TEXT_ALIGN_PROP, "center");
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.FONT_WEIGHT_PROP,
                            DesignChoiceConstants.FONT_WEIGHT_BOLD);
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.FONT_SIZE_PROP, "10pt");
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BACKGROUND_COLOR_PROP, clrHeader);
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.COLOR_PROP, clrTextHeader);

                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_TOP_STYLE_PROP, "solid");
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");

                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "1px");
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_TOP_WIDTH_PROP, "1px");
                    ((CellHandle) ((RowHandle) table.getRows().get(i))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_LEFT_WIDTH_PROP, "1px");
                }
            }

            ((ColumnHandle) table.getColumns().get(0)).getWidth().setValue(
                    "12%");
            ((ColumnHandle) table.getColumns().get(1)).getWidth().setValue(
                    "7%");
            ((ColumnHandle) table.getColumns().get(2)).getWidth().setValue(
                    "7%");
            ((ColumnHandle) table.getColumns().get(3)).getWidth().setValue(
                    "9%");
            ((ColumnHandle) table.getColumns().get(4)).getWidth().setValue(
                    "9%");
            ((ColumnHandle) table.getColumns().get(5)).getWidth().setValue(
                    "9%");
            ((ColumnHandle) table.getColumns().get(6)).getWidth().setValue(
                    "14%");
            ((ColumnHandle) table.getColumns().get(7)).getWidth().setValue(
                    "9%");
            ((ColumnHandle) table.getColumns().get(8)).getWidth().setValue(
                    "10%");
            ((ColumnHandle) table.getColumns().get(9)).getWidth().setValue(
                    "10%");
            // //////Hòa các hàng của tiêu đề
            TextItemHandle text;
            CellHandle cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(0));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Tên");
            cell1.getContent().add(text);
            //Độ sâu row
//            cell1.setRowSpan(2);
            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(1));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Mã đăng ký");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(2));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Mã số thuế");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(3));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Địa chỉ");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(4));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Gíam đốc");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(5));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Quốc gia");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(6));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Lĩnh vực kinh doanh");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(7));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Ngày thành lập");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(8));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Lĩnh vực sản xuất");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(9));
//            cell1.setW
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Loại doanh nghiệp");
            cell1.getContent().add(text);





            // xoa cac o
//            for (int i = 0; i < 5; i++) {
//                CellHandle cell2 = ((CellHandle) ((RowHandle) table.getRows()
//                        .get(0)).getCells().get(7));
//                cell2.drop();
//            }
//            for (int i = 0; i < 5; i++) {
//                CellHandle cell2 = ((CellHandle) ((RowHandle) table.getRows()
//                        .get(1)).getCells().get(7));
//                cell2.drop();
//            }

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).size(); j++) {
                    text = design.getElementFactory().newTextItem("");
                    text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
                    //ghi gia tri
                    text.setContent(data.get(i).get(j));

                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_TOP_STYLE_PROP, "solid");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");

                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "1px");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_TOP_WIDTH_PROP, "1px");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_LEFT_WIDTH_PROP, "1px");
                    if (j == data.get(i).size() - 1) {
                        ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
                        ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
                    }

                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.FONT_SIZE_PROP, "10pt");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).setProperty(
                            StyleHandle.TEXT_ALIGN_PROP, alignment.get(j));

                    if (i % 2 == 0) {
                        ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BACKGROUND_COLOR_PROP, clrOdd);
                    } else {
                        ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BACKGROUND_COLOR_PROP, clrEven);
                    }

                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
                            .getCells().get(j)).getContent().add(text);
                }
            }
            design.getBody().add(table);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return table;
    }

    public void executeReportPDF() throws EngineException {

        IReportEngine engine = null;
        EngineConfig config = null;

        try {
            config = new EngineConfig();
            config.setEngineHome("C:\\Users\\Admin\\Downloads\\birt-runtime-4.8.0-20180626\\ReportEngine");
            config.setLogConfig(null, Level.FINE);
            Platform.startup(config);
            final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
                    .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            engine = FACTORY.createReportEngine(config);

            // Open the report design
            IReportRunnable design = null;
            design = engine.openReportDesign("C:\\Users\\Admin\\Downloads\\birt-report-designer-all-in-one-4.8.0-20180522-win32.win32.x86_64\\eclipse\\workspace\\newKhanh\\new_report_3.rptdesign");


            ReportDesignHandle report = (ReportDesignHandle) design
                    .getDesignHandle();
            MasterPageHandle pageHandle = report
                    .findMasterPage("Simple MasterPage");
            DimensionHandle leftMarginHandle = pageHandle.getLeftMargin();
            DimensionHandle rightMarginHandle = pageHandle.getRightMargin();
            pageHandle.setOrientation("portrait"); // trang doc
            report.getElementFactory().newStyle("");
            StyleHandle labelStyle = report.getElementFactory().newStyle( "Label" );
            labelStyle.setProperty( StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD );
            labelStyle.setProperty( StyleHandle.FONT_FAMILY_PROP, "Arial Black" );
            labelStyle.setProperty( StyleHandle.COLOR_PROP, "#008000" );

            StyleHandle dataStyle = report.getElementFactory().newStyle( "Data" );
            dataStyle.setProperty( StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD );
            dataStyle.setProperty( StyleHandle.FONT_FAMILY_PROP, "Century"
            );//$NON-NLS-1$
            dataStyle.setProperty( StyleHandle.COLOR_PROP, "#009B9B" );

            report.getStyles().add( labelStyle );
            report.getStyles().add( dataStyle );

            buildReport(report);

            IRunAndRenderTask task = engine.createRunAndRenderTask(design);


            Random generator = new Random();
            Integer ab =generator.nextInt();


            IRenderOption options = new RenderOption();
            options.setOutputStream(new ByteArrayOutputStream());

            PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
            options.setOutputFormat("pdf");

            String abc = "C:\\Users\\Admin\\Documents\\khukt_20221226\\khukt\\src\\main\\webapp\\test.pdf";

        options.setOutputFileName(abc);

            task.setRenderOption(options);

            task.run();
            task.close();
            engine.destroy();
            RegistryProviderFactory.releaseDefault();
            engine = null;
            System.out.println("Finished");

        } catch(final Exception EX) {
            EX.printStackTrace();
        } finally {
            Platform.shutdown();
        }
    }

//    /HttpServletRequest request, HttpServletResponse response
    public void executeReportEXCEL() throws EngineException {

        IReportEngine engine = null;
        EngineConfig config = null;

        try {
            config = new EngineConfig();
            config.setEngineHome("C:\\Users\\Admin\\Downloads\\birt-runtime-4.8.0-20180626\\ReportEngine");
            config.setLogConfig(null, Level.FINE);
            Platform.startup(config);
            final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
                    .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            engine = FACTORY.createReportEngine(config);

            // Open the report design
            IReportRunnable design = null;
            design = engine.openReportDesign("C:\\Users\\Admin\\Downloads\\birt-report-designer-all-in-one-4.8.0-20180522-win32.win32.x86_64\\eclipse\\workspace\\newKhanh\\new_report_3.rptdesign");
//        IRunAndRenderTask task = engine.createRunAndRenderTask(design);
            // task.setParameterValue("Top Count", (new Integer(5)));
            // task.validateParameters();
            ReportDesignHandle report = (ReportDesignHandle) design
                    .getDesignHandle();
            MasterPageHandle pageHandle = report
                    .findMasterPage("Simple MasterPage");
            DimensionHandle leftMarginHandle = pageHandle.getLeftMargin();
            DimensionHandle rightMarginHandle = pageHandle.getRightMargin();
            pageHandle.setOrientation("portrait"); // trang doc
            report.getElementFactory().newStyle("");
            StyleHandle labelStyle = report.getElementFactory().newStyle( "Label" );
            labelStyle.setProperty( StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD );
            labelStyle.setProperty( StyleHandle.FONT_FAMILY_PROP, "Arial Black" );
            labelStyle.setProperty( StyleHandle.COLOR_PROP, "#008000" );

            StyleHandle dataStyle = report.getElementFactory().newStyle( "Data" );
            dataStyle.setProperty( StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD );
            dataStyle.setProperty( StyleHandle.FONT_FAMILY_PROP, "Century"
            );//$NON-NLS-1$
            dataStyle.setProperty( StyleHandle.COLOR_PROP, "#009B9B" );

            report.getStyles().add( labelStyle );
            report.getStyles().add( dataStyle );

            buildReport(report);

            IRunAndRenderTask task = engine.createRunAndRenderTask(design);


            Random generator = new Random();
            Integer ab =generator.nextInt();


//
//            task.setRenderOption(PDF_OPTIONS);

            IRenderOption options = new RenderOption();
            options.setOutputStream(new ByteArrayOutputStream());

            EXCELRenderOption EXCEL_OPTIONS = new EXCELRenderOption();
            options.setOutputFormat("xls");
            String abc = "C:\\Users\\Admin\\Documents\\khukt_20221226\\khukt\\src\\main\\webapp\\test.xls";
            options.setOutputFileName(abc);
            task.setRenderOption(options);

            task.run();
            task.close();
            engine.destroy();
            RegistryProviderFactory.releaseDefault();
            engine = null;
            System.out.println("Finished");

        } catch(final Exception EX) {
            EX.printStackTrace();
        } finally {
            Platform.shutdown();
        }
    }
}
