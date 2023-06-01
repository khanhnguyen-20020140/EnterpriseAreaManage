package com.sgis.khukt.model;

import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.core.internal.registry.RegistryProviderFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class DoanhNghiepServlet extends HttpServlet {
    static final String URL = "jdbc:postgresql://localhost:5434/khukt_dongnam_20221223?useUnicode=yes&characterEncoding=UTF-8";

    static final String USER = "postgres";

    static final String PASS = "11";


    //QUERY
    static  String QUERY = "" ;


    // TÊN CỦA TRƯỜNG MUOOSN XUẤT BÁO CÁO
    static String selectedName =" ";

    //CÁC CỘT MUỐN RENDER
    List<String> selectedColumn = new ArrayList<>();

    //SỐ LƯỢNG CỘT MUỐN RENDER
    Integer selectedColumnInt = 0;

    //WIDTH VÀ ALIGN ĐỂ SET GIAO DIỆN CHO CÁC CỘT
    List<Integer> width = new ArrayList<Integer>();
    List<String> align = new ArrayList<String>();

    Integer  checkExistNgaythanhlap = -1;

    String desFile ="";

    public List<List<String>> getDataDoanhnghiep(String type_select, String id_select) throws SQLException {

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
        String loaidoanhnghiep = "loaidoanhnghiep";

        String linhvucsanxuat ="linhvucsanxuat";

        String SELECT_QUERY="SELECT ten ";





        selectedColumnInt=0;



        if(selectedColumn.contains("giamdoc")){
            SELECT_QUERY = SELECT_QUERY.concat(", giamdoc");
            selectedColumnInt++;
        }
        if(selectedColumn.contains("diachi")){
            SELECT_QUERY = SELECT_QUERY.concat(",diachi");
            selectedColumnInt++;
        }

        if(selectedColumn.contains("linhvuc")){
            SELECT_QUERY = SELECT_QUERY.concat(", linhvuc_kinhdoanh");
            selectedColumnInt++;
        }
        if(selectedColumn.contains("madangky")){
            SELECT_QUERY = SELECT_QUERY.concat(", ma_dangky");
            selectedColumnInt++;
        }
        if(selectedColumn.contains("masothue")){
            SELECT_QUERY = SELECT_QUERY.concat(", masothue");
            selectedColumnInt++;
        }
        if(selectedColumn.contains("ngaythanhlap")){
            SELECT_QUERY = SELECT_QUERY.concat(", ngay_thanhlap");
            selectedColumnInt++;
        }


        if(selectedColumn.contains("loaidoanhnghiep")){
            SELECT_QUERY = SELECT_QUERY.concat(", tbl_dm_loai_doanhnghiep.loai_doanhnghiep");
            selectedColumnInt++;
        }
        if(selectedColumn.contains("linhvucsanxuat")){
            SELECT_QUERY = SELECT_QUERY.concat(", tbl_dm_linhvuc_sanxuatkinhdoanh.linhvuc_sanxuatkinhdoanh ");
            selectedColumnInt++;
        }
        //
        //
        if(selectedColumn.contains("quocgia")){
            SELECT_QUERY = SELECT_QUERY.concat(", quocgia");
            selectedColumnInt++;
        }

        SELECT_QUERY = SELECT_QUERY.concat(" FROM tbl_doanhnghiep  left join tbl_dm_loai_doanhnghiep ON tbl_dm_loai_doanhnghiep.id_loai_doanhnghiep = tbl_doanhnghiep.id_loai_doanhnghiep\n" +
                "            left join tbl_dm_linhvuc_sanxuatkinhdoanh ON tbl_dm_linhvuc_sanxuatkinhdoanh.id_linhvuc_sanxuatkinhdoanh = tbl_doanhnghiep.id_linhvuc_sanxuatkinhdoanh  \n" +
                "");

        if(type_select.equals(loaidoanhnghiep)){
            SELECT_QUERY = SELECT_QUERY.concat(" where tbl_doanhnghiep.id_loai_doanhnghiep =  ");
        }



        if(type_select.equals(linhvucsanxuat)){
            SELECT_QUERY = SELECT_QUERY.concat("where tbl_doanhnghiep.linhvuc_kinhdoanh = ");
        }
        SELECT_QUERY = SELECT_QUERY.concat(id_select);

        SELECT_QUERY = SELECT_QUERY.concat("  order by tbl_doanhnghiep.ten  ");

        System.out.println(SELECT_QUERY);

        String pickedName = "";


        if(type_select.equals(loaidoanhnghiep)){
            pickedName ="SELECT  loai_doanhnghiep FROM public.tbl_dm_loai_doanhnghiep where id_loai_doanhnghiep = ";
        }



        if(type_select.equals(linhvucsanxuat)){
            pickedName ="SELECT  linhvuc_sanxuatkinhdoanh FROM public.tbl_dm_linhvuc_sanxuatkinhdoanh where id_linhvuc_sanxuatkinhdoanh =  ";
        }

        pickedName = pickedName.concat(id_select);

        ResultSet pn = stmt.executeQuery(pickedName);
        selectedName="";
        while (pn.next()) {
            selectedName = pn.getString(1);
        }




        ResultSet rs = stmt.executeQuery(SELECT_QUERY);
        List<List<String>> listDoanhnghiep = new ArrayList<>();

        // show data

        while (rs.next()) {


            List<String> miniList = new ArrayList<>();

            for(int i=0;i<=selectedColumnInt;i++){
                miniList.add(rs.getString(1+i));
            }




            listDoanhnghiep.add(miniList);


        }
        // close connection
        conn.close();
        return listDoanhnghiep;

    }

    protected void addTableHeader(String SoNN, String title,
                                  ReportDesignHandle designHandle, common v) {
        try {
            String label_content ="CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM";
            LabelHandle label= v.addLabel(label_content, "label1", designHandle);
            label =  v.addLabel(label_content, "label1",
                    designHandle);
            label.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            label.setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
            label.setProperty(
                    StyleHandle.COLOR_PROP, "#010704");;
            label.setProperty(
                    StyleHandle.FONT_SIZE_PROP, "13pt");
            label.setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);

            designHandle.getBody().add(label);



            label_content = "Độc lập - Tự do - Hạnh phúc";
            label =  v.addLabel(label_content, "label1",
                    designHandle);
            label.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            label.setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
            label.setProperty(
                    StyleHandle.COLOR_PROP, "#010704");;
            label.setProperty(
                    StyleHandle.FONT_SIZE_PROP, "14pt");
            label.setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);


            designHandle.getBody().add(label);

            GridHandle table = designHandle.getElementFactory().newGridItem("Table",
                    2, 1);
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


            LocalDate currentdate = LocalDate.now();
            String currentDate = currentdate.toString();
            System.out.println("Current date: "+currentDate);
            String[] parts = currentDate.split("-");

            TextItemHandle text;
            text = designHandle.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Nghệ An, ngày …. tháng …. năm "+parts[0]);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(1)).getContent().add(text);

            designHandle.getBody().add(table);

            label_content = "BÁO CÁO THỐNG KÊ";
            label =  v.addLabel(label_content, "label1",
                    designHandle);
            label.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            label.setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
            label.setProperty(
                    StyleHandle.COLOR_PROP, "#010704");;
            label.setProperty(
                    StyleHandle.FONT_SIZE_PROP, "14pt");
            label.setProperty(
                    StyleHandle.MARGIN_TOP_PROP, "30px");
            label.setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);


            designHandle.getBody().add(label);


            label_content = "DANH SÁCH CÁC DOANH NGHIỆP TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN";
            label =  v.addLabel(label_content, "label1",
                    designHandle);
            label.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            label.setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
            label.setProperty(
                    StyleHandle.COLOR_PROP, "#010704");;
            label.setProperty(
                    StyleHandle.FONT_SIZE_PROP, "14pt");
            label.setProperty(
                    StyleHandle.MARGIN_BOTTOM_PROP, "10px");
            label.setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);

            designHandle.getBody().add(label);

            label_content=SoNN;
            addTypeselect(label_content,label,v,designHandle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTypeselect(String label_content,LabelHandle label,common v,ReportDesignHandle designHandle){
        try {

            GridHandle table = designHandle.getElementFactory().newGridItem("Table",
                    1, 2);
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
                    "100%");




            TextItemHandle text;
            text = designHandle.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent(label_content);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                    .get(0)).getContent().add(text);

            text = designHandle.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);



            designHandle.getBody().add(table);
        } catch (SemanticException e) {
            throw new RuntimeException(e);
        }


    }


    public GridHandle getTablePhanbo(List<List<String>> data,
                                     String clrHeader,
                                     String clrTextHeader, String clrEven, String clrOdd,
                                     ReportDesignHandle design) {
        GridHandle table = null;
        try {
            if(data.size()==0) return table;
            table = design.getElementFactory().newGridItem("", 99,
                    data.size() + 2);
            table.setWidth("100%");

            width.clear();
            align.clear();



            Integer selectedColumnIntTitle=-1;
            List<String> titles = new ArrayList<>();
            checkExistNgaythanhlap =-1;
            titles.add("Tên doanh nghiệp");
            width.add(7);
            align.add("left");


            if(selectedColumn.contains("giamdoc")){
                titles.add("Tên giám đốc");
                width.add(5);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("diachi")){
                titles.add("Địa chỉ");
                width.add(5);
                align.add("left");

                selectedColumnIntTitle++;
            }

            if(selectedColumn.contains("linhvuc")){
                titles.add("Lĩnh vực ");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("madangky")){
                titles.add("Mã đăng ký");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("masothue")){
                titles.add("Mã số thuế");
                width.add(5);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("ngaythanhlap")){
                titles.add("Ngày thành lập");
                width.add(7);
                align.add("left");
                selectedColumnIntTitle++;
                checkExistNgaythanhlap=selectedColumnIntTitle+1;
            }


            if(selectedColumn.contains("loaidoanhnghiep")){
                titles.add("Loại doanh nghiệp");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("linhvucsanxuat")){
                titles.add("Lĩnh vực sản xuất kinh doanh");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }
            //
            //
            if(selectedColumn.contains("quocgia")){
                titles.add("Quốc gia");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }

            Integer totalWidth = 0;
            for (int c = 0; c < width.size(); c++) {
                totalWidth+=width.get(c);
            }


            // set width of column
            for (int c = 0; c < width.size(); c++) {

                Double widthColumn = (double) width.get(c)/totalWidth*100;

                String getwidthColumn = Double.toString(widthColumn);
                getwidthColumn = getwidthColumn.concat("%");

                ((ColumnHandle) table.getColumns().get(c)).getWidth().setValue(getwidthColumn);

            }




            System.out.println("selectedColum" +selectedColumnIntTitle);


            for (int i = 0; i <1; i++) {
                for (int j = 0; j < selectedColumnIntTitle+2; j++) {

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
                    if(j==selectedColumnIntTitle+1){
                        ((CellHandle) ((RowHandle) table.getRows().get(i))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
                        ((CellHandle) ((RowHandle) table.getRows().get(i))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
                    }
                }
            }

            TextItemHandle text;
            CellHandle cell1 ;

            for(int i=0;i<=selectedColumnIntTitle+1;i++){
                cell1 = ((CellHandle) ((RowHandle) table.getRows()
                        .get(0)).getCells().get(i));
                text = design.getElementFactory().newTextItem("");
                text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
                text.setContent(titles.get(i));
                cell1.getContent().add(text);
                //Độ sâu row

            }











            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).size(); j++) {
                    text = design.getElementFactory().newTextItem("");
                    text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);

                    if((j == checkExistNgaythanhlap)&&data.get(i).get(j)!=null){

                        String[] myArray = data.get(i).get(j).split("-");
                        String newDate = "";
                        newDate = newDate.concat(myArray[2]);
                        newDate = newDate.concat("/");
                        newDate = newDate.concat(myArray[1]);
                        newDate = newDate.concat("/");

                        newDate = newDate.concat(myArray[0]);
                        text.setContent(newDate);
                    }
                    else{
                        text.setContent(data.get(i).get(j));
                    }


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
                            StyleHandle.TEXT_ALIGN_PROP, align.get(j));

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




        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return table;
    }

    public void buildReport(ReportDesignHandle designHandle,String type_select, String id_select) {
        Connection connection = null;
        try {
            int i, j, k, l;
//            connection = getConnection();
            common v = new common();
//            v.setFontReport("Abadi", "13pt", designHandle);
//            v.setAlignReport("justify", designHandle);
            v.setMarginReport("A4","landscape","20px","20px","0px","0px",designHandle);
            List<List<String>> data_phanbo = getDataDoanhnghiep( type_select, id_select);
            //***Trình tự1
            //addTableHeader
            LocalDate currentdate = LocalDate.now();
            String currentDate = currentdate.toString();
            System.out.println("Current date: "+currentDate);
            String[] parts = currentDate.split("-");
            String title = ".............,ngày "+ parts[2]+ " tháng " + parts[1]+" năm "+parts[0];


            String loaidoanhnghiep = "loaidoanhnghiep";

            String linhvucsanxuat ="linhvucsanxuat";
            String SELECT ="";
            if(type_select.equals(loaidoanhnghiep)){
                SELECT = "Loại doanh nghiệp : ";
            }

            if(type_select.equals(linhvucsanxuat)){
                SELECT = "Lĩnh vực sản xuất : ";
            }



            SELECT += selectedName;



            addTableHeader(SELECT, title, designHandle,v);
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


            String clrHeader = "#E1FFFF";
            String clrTextHeader = "#000000";
            String clrEven = "#F5FFFA";
            String clrOdd = "#FFFFFF";


            //***Trình tự3
            getTablePhanbo(data_phanbo,  clrHeader, clrTextHeader,
                    clrEven, clrOdd, designHandle);// .setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP,
            // "always");
            //***Trình tự4
//            addTableFooter(designHandle);

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

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        String id_request = req.getPathInfo();
        System.out.println("idrequest  "+id_request);

        //SET !=NULL
        String[] arrOfStr = id_request.split("/");
        String kind_select = arrOfStr[1];
        String kind_download = arrOfStr[2];
        String type_select = arrOfStr[3];
        String id_select = arrOfStr[4];
        //here
        selectedColumn.clear();

        for(int i=5;i<14;i++){

            selectedColumn.add(arrOfStr[i]);
        }
        //here
        desFile ="";
        System.out.println(type_select);

        IReportEngine engine = null;
        EngineConfig config = null;

        if(!kind_select.equals("download")){
            res.setContentType("text/html; charset=UTF-8");
            res.setCharacterEncoding("UTF-8");
            req.setCharacterEncoding("UTF-8");
            try {

                config = new EngineConfig();
                //doi duong dan
                config.setEngineHome("C:\\Users\\Admin\\Downloads\\birt-runtime-4.8.0-20180626\\ReportEngine");
                config.setLogConfig(null, Level.FINE);
                Platform.startup(config);
                final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
                        .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
                engine = FACTORY.createReportEngine(config);

                // Open the report design
                IReportRunnable design = null;
                //doi duong dan
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

                buildReport(report,type_select,id_select);

                IRunAndRenderTask task = engine.createRunAndRenderTask(design);

                IRenderOption options = new RenderOption();
                options.setOutputStream(new ByteArrayOutputStream());

                PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
                ServletContext servletContext = getServletContext();  ;

                String path = servletContext.getRealPath("/");


                options.setOutputFormat("pdf");
                desFile = arrOfStr[14];
                path = path +"reports/"+desFile+".pdf";

                options.setOutputFileName(path);
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
        else{

            res.setContentType("application/octet-stream");
            res.setCharacterEncoding("UTF-8");
            HttpSession session = req.getSession();
            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());
            String headerKey = "Content-Disposition";

            //set format
            String headerValue = "attachment; filename=doanhnghiep_" + currentDateTime + "."+kind_download;
            res.setHeader(headerKey, headerValue);
            try {

                config = new EngineConfig();
                //doi duong dan
                config.setEngineHome("C:\\Users\\Admin\\Downloads\\birt-runtime-4.8.0-20180626\\ReportEngine");

                config.setLogConfig(null, Level.FINE);


                Platform.startup(config);
                IReportEngineFactory factory = (IReportEngineFactory) Platform
                        .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
                engine = factory.createReportEngine(config);
                engine.changeLogLevel(Level.WARNING);

                IReportRunnable design = null;


                // Open a report design
                //doi duong dan
                design = engine.openReportDesign("C:\\Users\\Admin\\Downloads\\birt-report-designer-all-in-one-4.8.0-20180522-win32.win32.x86_64\\eclipse\\workspace\\newKhanh\\new_report_3.rptdesign");

                ReportDesignHandle report = (ReportDesignHandle) design
                        .getDesignHandle();
                MasterPageHandle pageHandle = report
                        .findMasterPage("Simple MasterPage");
                DimensionHandle leftMarginHandle = pageHandle.getLeftMargin();
                DimensionHandle rightMarginHandle = pageHandle.getRightMargin();
                pageHandle.setOrientation("landscape");



                buildReport(report,type_select,id_select);
                // create task to run and render report
                IRunAndRenderTask task = engine.createRunAndRenderTask(design);

                EXCELRenderOption options = new EXCELRenderOption();
                options.setOutputStream(new ByteArrayOutputStream());
                options.setOutputFormat(kind_download);

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                options.setOutputStream(baos);
                task.setRenderOption(options);
                // run report
                task.run();
                task.close();

                ServletOutputStream sos = res.getOutputStream();
                try {
                    baos.writeTo(sos);
                    baos.close();
                    sos.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                engine.destroy();
                RegistryProviderFactory.releaseDefault();
                engine = null;
                System.out.println("Finished");

            } catch (Exception e) {
                e.printStackTrace();
                engine.destroy();
                RegistryProviderFactory.releaseDefault();
                engine = null;
                throw new ServletException(e);
            } finally {
                Platform.shutdown();
            }
        }


    }




}
