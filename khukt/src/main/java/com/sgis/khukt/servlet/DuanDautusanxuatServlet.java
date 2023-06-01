package com.sgis.khukt.servlet;

import com.sgis.khukt.model.common;
import com.sgis.khukt.servlet.MyBean;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;

public class DuanDautusanxuatServlet extends HttpServlet {

    DefinedValue definedValue = new DefinedValue();
    String enginehome = definedValue.getEnginehome();

    String rptdesignpath =definedValue.getRptdesignpath();

    String hostname=definedValue.getHostname();

    String port = definedValue.getPort();

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

    //CHECK XEM CÓ CỘT MỐC BÁO CÁO KHÔNG
    Integer  checkExistNgayquyetdinhcapphep = -1;
    Integer  checkExistNgayhetphephoatdong = -1;


    //THỜI ĐIỂM BAN ĐẦU VÀ KẾT THÚC CỦA KHOẢNG THƯỜI GIAN CHỌN BÁO CÁO
    String beginDate = "";
    String endDate = "";
    String desFile ="";

    Integer[] selected = {1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};


    //LẤY DỮ LIỆU VÀ TÊN CỦA TRƯỜNG MUỐN XUẤT BÁO CÁO
    public List<List<String>> getDataDuandautusanxuat(String type_select, String id_select) throws SQLException {

        // start loading driver class

        String khuchuyennganh = "khuchuyennganh";
        String duan ="duan";
        String doanhnghiep ="doanhnghiep";
        String toantinh = "toantinh";
        String khukinhte = "khukinhte";



        String SELECT_QUERY="SELECT dtsx.ten ";

        selectedColumnInt=0;

        if(selectedColumn.contains("doanhnghiep")){
            selected[1] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("soquyetdinhchophep")){
            selected[2] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("vondautu")){
            selected[3] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("nguonvon")){
            selected[4] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("khuchuyennganh")){
            selected[5] =1;
            selectedColumnInt++;
        }


        if(selectedColumn.contains("noidungsanxuat")){
            selected[6] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("linhvucsanxuat")){
            selected[7] =1;
            selectedColumnInt++;
        }

        if(selectedColumn.contains("masoduan")){
            selected[8] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("ngaycapphephoatdong")){
            selected[9] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("vontuongduongusd")){
            selected[10] =1;
            selectedColumnInt++;
        }

        if(selectedColumn.contains("quymo")){
            selected[11] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("ngayhetphephoatdong")){
            selected[12] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("trangthai")){
            selected[13] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("loaihinhduan")){
            selected[14] =1;
            selectedColumnInt++;
        }
        if(selectedColumn.contains("tongmucdautu")){
            selected[15] =1;
            selectedColumnInt++;
        }


        String pickedName = "";


        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String urlExactname="";

        if(type_select.equals(khuchuyennganh)){
            urlExactname="http://"+hostname+":"+port+ "/duandautusanxuat/duandautusanxuat/findByKCN/"+id_select;
        }



        if(type_select.equals(doanhnghiep)){
            urlExactname="http://"+hostname+":"+port+ "/duandautusanxuat/duandautusanxuat/findByDN/"+id_select;
        }

        if(type_select.equals(khukinhte)){
            urlExactname="http://"+hostname+":"+port+ "/duandautusanxuat/duandautusanxuat/findByKKT/"+id_select;
        }


        if(type_select.equals(toantinh)){
            urlExactname="http://"+hostname+":"+port+ "/duandautusanxuat/duandautusanxuat/findByAll";
        }

        ResponseEntity<List<Duandautusanxuat>> response = restTemplate.exchange(
                urlExactname,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Duandautusanxuat>>() {});

        List<Duandautusanxuat> myListDuan = response.getBody();


        List<List<String>> listDoanhnghiep = new ArrayList<>();

        // show data

        for(int i =0;i<myListDuan.size();i++){
            List<String> miniList = new ArrayList<>();
            for(int j=0;j<myListDuan.get(i).getDuandautusanxuat().size();j++){
                System.out.println(j+"   "+ myListDuan.get(i).getDuandautusanxuat().get(j));
                if(selected[j]==1){
                    if(j==9||j==12){
                        String[] myArray = myListDuan.get(i).getDuandautusanxuat().get(j).split("-");
                        String newDate = "";
                        newDate = newDate.concat(myArray[2]);
                        newDate = newDate.concat("/");
                        newDate = newDate.concat(myArray[1]);
                        newDate = newDate.concat("/");

                        newDate = newDate.concat(myArray[0]);
                        miniList.add(newDate);
                    }
                    else{
                        miniList.add(myListDuan.get(i).getDuandautusanxuat().get(j));
                    }
                }


            }
            listDoanhnghiep.add(miniList);
        }




        // close connection

        return listDoanhnghiep;

    }



    protected void addTableHeader(String SoNN, String type_select,String selectedName,String title,
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

            label_content = "Nghệ An, ngày …. tháng …. năm "+parts[0];
            label =  v.addLabel(label_content, "label1",
                    designHandle);
            label.setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
            label.setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
            label.setProperty(
                    StyleHandle.COLOR_PROP, "#010704");;
            label.setProperty(
                    StyleHandle.FONT_SIZE_PROP, "14pt");
            label.setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);


            designHandle.getBody().add(label);

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


            String khuchuyennganh = "khuchuyennganh";
            String duan ="duan";
            String doanhnghiep ="doanhnghiep";
            String toantinh ="toantinh";
            String khukinhte ="khukinhte";

            System.out.println("selectname  "+selectedName);


            if(type_select.equals(khuchuyennganh)){
                label_content = "DANH SÁCH CÁC DỰ ÁN ĐẦU TƯ TẠI " +selectedName.toUpperCase();

            }


            if(type_select.equals(doanhnghiep)){
                label_content = "DANH SÁCH CÁC DỰ ÁN ĐẦU TƯ CỦA " +selectedName.toUpperCase();
//                SELECT = "Doanh nghiệp : ";
            }

            if(type_select.equals(khukinhte)){
                label_content = "DANH SÁCH CÁC DỰ ÁN ĐẦU TƯ TẠI " +selectedName.toUpperCase();
//                SELECT = "Doanh nghiệp : ";
            }


            if(type_select.equals(toantinh)){
                label_content = "DANH SÁCH CÁC DỰ ÁN ĐẦU TƯ TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN";
            }


//            label_content = "TÌNH HÌNH SẢN XUẤT CỦA CÁC DOANH NGHIỆP ĐẦU TƯ TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN";
            label =  v.addLabel(label_content, "label1",
                    designHandle);
            label.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            label.setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
            label.setProperty(
                    StyleHandle.COLOR_PROP, "#010704");;
            label.setProperty(
                    StyleHandle.FONT_SIZE_PROP, "14pt");

            label.setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);

            designHandle.getBody().add(label);


//            addTypeselect(label_content,label,v,designHandle);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //HIỂN THỊ TRÊN TRƯỜNG VÀ MOOSC THỜI GIAN BÁO CÁO
    public void addTypeselect(String label_content, LabelHandle label, common v, ReportDesignHandle designHandle){
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

    // ĐẶT TIÊU ĐỀ CHO TUWFNG CỘT VÀ SET STYLE
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
            checkExistNgayquyetdinhcapphep =-1;
            checkExistNgayhetphephoatdong =-1;

            titles.add("Tên dự án");
            width.add(7);
            align.add("left");

            if(selectedColumn.contains("doanhnghiep")){
                titles.add("Tên doanh nghiệp");
                width.add(5);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("soquyetdinhchophep")){
                titles.add("Số quyết định cho phép");
                width.add(5);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("vondautu")){
                titles.add("Vốn đăng ký đầu tư");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("nguonvon")){
                titles.add("Nguồn vốn");
                width.add(5);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("khuchuyennganh")){
                titles.add("Khu chuyên ngành");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }


            if(selectedColumn.contains("noidungsanxuat")){
                titles.add("Nội dung sản xuất kinh doanh");
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
            if(selectedColumn.contains("masoduan")){
                titles.add("Mã số dự án");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("ngaycapphephoatdong")){
                titles.add("Ngày quyết định cấp phép");
                width.add(7);
                align.add("left");
                selectedColumnIntTitle++;
                checkExistNgayquyetdinhcapphep=selectedColumnIntTitle+1;


            }
            if(selectedColumn.contains("vontuongduongusd")){
                titles.add("Vốn tương đương USD");
                width.add(6);
                align.add("left");

                selectedColumnIntTitle++;
            }

            if(selectedColumn.contains("quymo")){
                titles.add("Quy mô công suất");
                width.add(6);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("ngayhetphephoatdong")){
                titles.add("Ngày hết phép hoạt động");
                width.add(6);
                align.add("left");
                selectedColumnIntTitle++;
                checkExistNgayhetphephoatdong = selectedColumnIntTitle+1;

            }
            if(selectedColumn.contains("trangthai")){
                titles.add("Trạng thái dự án");
                width.add(6);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("loaihinhduan")){
                titles.add("Loại hình dự án");
                width.add(6);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("tongmucdautu")){
                titles.add("Tổng mức đầu tư");
                width.add(6);
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
                System.out.println(i+"   "+titles.get(i));
                cell1.getContent().add(text);


            }



            System.out.println(data.get(1).size());

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < selectedColumnIntTitle+2; j++) {
                    text = design.getElementFactory().newTextItem("");
                    text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);

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
                    if (j == selectedColumnIntTitle+1) {
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

        } catch (Exception e) {
            e.printStackTrace();
        }

        return table;
    }


    public void buildReport(ReportDesignHandle designHandle,String type_select, String id_select) {
        Connection connection = null;
        try {
            int i, j, k, l;
            common v = new common();
            v.setMarginReport("A4","landscape","20px","20px","0px","0px",designHandle);
            //Dataa

            List<List<String>> data_phanbo = getDataDuandautusanxuat( type_select, id_select);
            //***Trình tự1

            LocalDate currentdate = LocalDate.now();
            String currentDate = currentdate.toString();
            System.out.println("Current date: "+currentDate);
            String[] parts = currentDate.split("-");
            String title = ".............,ngày "+ parts[2]+ " tháng " + parts[1]+" năm "+parts[0];


            String khuchuyennganh = "khuchuyennganh";
            String duan ="duan";
            String doanhnghiep ="doanhnghiep";
            String toantinh = "toantinh";
            String khukinhte ="khukinhte";
            String SELECT="";

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            String urlExactname="";


            if(type_select.equals(khuchuyennganh)){
                urlExactname ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/exactTen/kcn/"+id_select;
            }

            if(type_select.equals(duan)){
                urlExactname ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/exactTen/duan/"+id_select;

            }

            if(type_select.equals(doanhnghiep)){
                System.out.println("http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/exactTen/dn/"+id_select);
                urlExactname ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/exactTen/dn/"+id_select;
            }

            if(type_select.equals(khukinhte)){
                urlExactname ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/exactTen/kkt/"+id_select;
            }

            System.out.println(urlExactname);




            // TÊN CỦA VÙNG MUỐN TRUY VẤN
            selectedName="";
            if(!type_select.equals(toantinh)){
                ResponseEntity<String> responseExactName = restTemplate.exchange(
                        urlExactname,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<String>() {});

                selectedName  = responseExactName.getBody();
            }


            addTableHeader(SELECT,type_select,selectedName, title, designHandle,v);
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




            //Dataa
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

        //SET !=NULL
        String[] arrOfStr = id_request.split("/");
        String kind_select = arrOfStr[1];
        String kind_download = arrOfStr[2];
        String type_select = arrOfStr[3];
        String id_select = arrOfStr[4];
        //here
        selectedColumn.clear();

        for(int i=5;i<arrOfStr.length;i++){

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
                config.setEngineHome(enginehome);
                config.setLogConfig(null, Level.FINE);
                Platform.startup(config);
                final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
                        .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
                engine = FACTORY.createReportEngine(config);

                // Open the report design
                IReportRunnable design = null;
                //doi duong dan

                System.out.println("wtf here    "+ rptdesignpath);
                design = engine.openReportDesign(rptdesignpath);


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
                desFile = arrOfStr[arrOfStr.length-1];
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
            String headerValue = "attachment; filename=duandautusanxuat_" + currentDateTime + "."+kind_download;
            res.setHeader(headerKey, headerValue);
            try {

                config = new EngineConfig();
                //doi duong dan
                config.setEngineHome(enginehome);

                config.setLogConfig(null, Level.FINE);


                Platform.startup(config);
                IReportEngineFactory factory = (IReportEngineFactory) Platform
                        .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
                engine = factory.createReportEngine(config);
                engine.changeLogLevel(Level.WARNING);

                IReportRunnable design = null;


                // Open a report design
                //doi duong dan
                design = engine.openReportDesign(rptdesignpath);

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
