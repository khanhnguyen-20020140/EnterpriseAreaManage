package com.sgis.khukt.servlet;


import com.sgis.khukt.model.TblTiendoXaydunghatangChitiet;
import com.sgis.khukt.model.common;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
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
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;
import java.util.logging.Level;

public class TienDoXaydunghatangcosoServlet extends HttpServlet {
    // SQL CONNECT


    DefinedValue definedValue = new DefinedValue();
    String enginehome = definedValue.getEnginehome();

    String rptdesignpath =definedValue.getRptdesignpath();

    String hostname=definedValue.getHostname();

    String port = definedValue.getPort();

    // TÊN CỦA TRƯỜNG MUỐN XUẤT BÁO CÁO
    static String selectedName =" ";

    //CÁC CỘT MUỐN RENDER
    List<String> selectedColumn = new ArrayList<>();

    //SỐ LƯỢNG CỘT MUỐN RENDER
    Integer selectedColumnInt = 0;

    String selectedID ="";



    //WIDTH VÀ ALIGN ĐỂ SET GIAO DIỆN CHO CÁC CỘT
    List<Integer> width = new ArrayList<Integer>();
    List<String> align = new ArrayList<String>();

    //CHECK XEM CÓ CỘT MỐC BÁO CÁO KHÔNG
    Integer checkExistMocbaocao = -1;

    //THỜI ĐIỂM BAN ĐẦU VÀ KẾT THÚC CỦA KHOẢNG THỜI GIAN CHỌN BÁO CÁO
    String beginDate = "";
    String endDate = "";
    String desFile ="";

    Map<Integer,Map<String,List<List<String>>>> maplistString = new HashMap<>();


    //LẤY DỮ LIỆU VÀ TÊN CỦA TRƯỜNG MUỐN XUẤT BÁO CÁO
    public Map<String,List<List<String>>> getDataTienDoXaydungHatang(String type_select, String id_select) throws SQLException {

        String duan = "duan";
        String khuchuyennganh = "khuchuyennganh";
        String khukinhte = "khukinhte";
        String toantinh ="toantinh";



        selectedColumnInt=0;


        if(selectedColumn.contains("tenduan")){
            selectedColumnInt++;
        }

        if(selectedColumn.contains("ngaybaocao")){
            selectedColumnInt++;
        }
        if(selectedColumn.contains("thanhphan")){
            selectedColumnInt++;
        }
        if(selectedColumn.contains("tiendocapvon")){
            selectedColumnInt++;
        }
        if(selectedColumn.contains("tiendogiaingan")){
            selectedColumnInt++;
        }
        if(selectedColumn.contains("khoiluonghoanthanh")){

            selectedColumnInt++;
        }


        if(selectedColumn.contains("trangthai")){
            selectedColumnInt++;
        }

        String pickedName = "";





        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/tiendoXDHTChitietbyIdduandtsx/"+id_select+"/"+beginDate+"/"+endDate;

        System.out.println(url);
        ResponseEntity<List<TblTiendoXaydunghatangChitiet>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<TblTiendoXaydunghatangChitiet>>() {});

        List<TblTiendoXaydunghatangChitiet> myListID = response.getBody();




//        ResultSet rs = stmt.executeQuery(SELECT_QUERY);
        Map<String,List<List<String>>> listThanhphan = new HashMap<>();
        List<List<String>> thanhphan = new ArrayList<>();
        String existDay = "1";

        System.out.println("1");
        System.out.println(myListID);

        for(int i=0;i<myListID.size();i++){
            List<String> miniList = new ArrayList<>();
            DateTimeFormatter f = DateTimeFormatter.ofPattern( "E MMM dd HH:mm:ss z uuuu" )
                    .withLocale( Locale.US );
            ZonedDateTime zdt = ZonedDateTime.parse( myListID.get(i).getIdTiendo().getNgaybaocao().toString() , f );
            LocalDate ld = zdt.toLocalDate();
            DateTimeFormatter fLocalDate = DateTimeFormatter.ofPattern( "dd/MM/uuuu" );
            String output = ld.format( fLocalDate) ;

            miniList.add(myListID.get(i).getThanhphan().toString());
            miniList.add(myListID.get(i).getTiendoCapvon().toString());
            miniList.add(myListID.get(i).getTiendoGiaingan().toString());
            miniList.add(myListID.get(i).getKhoiluongHoanthanh().toString());
            miniList.add(myListID.get(i).getIdTrangthai().getTrangthai().toString());
            if(!output.equals(existDay)){
                System.out.println("thanhphan :"+ existDay);
                System.out.println(thanhphan);
                listThanhphan.put(existDay,thanhphan);
                thanhphan = new ArrayList<>();
                thanhphan.add(miniList);
                existDay = output;
            }
            else{
                thanhphan.add(miniList);

            }

        }
        System.out.println("thanhphan :"+ existDay);
        System.out.println(thanhphan);
        listThanhphan.put(existDay,thanhphan);

        return listThanhphan;

    }


    protected void addTableHeader( String type_select, String selectedName, String title,
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

            String khukinhte ="khukinhte";
            String khuchuyennganh = "khuchuyennganh";
            String duan ="duan";
            String toantinh ="toantinh";


            if(type_select.equals(khukinhte)){
                label_content = "TIẾN ĐỘ XÂY DỰNG HẠ TẦNG KỸ THUẬT CƠ SỞ CỦA CÁC DỰ ÁN ĐẦU TƯ SẢN XUẤT , KINH DOANH TẠI " +selectedName.toUpperCase();

            }


            if(type_select.equals(khuchuyennganh)){
                label_content = "TIẾN ĐỘ XÂY DỰNG HẠ TẦNG KỸ THUẬT CƠ SỞ CỦA CÁC DỰ ÁN ĐẦU TƯ SẢN XUẤT , KINH DOANH TẠI " +selectedName.toUpperCase();
            }

            if(type_select.equals(duan)){
                label_content = "TIẾN ĐỘ XÂY DỰNG HẠ TẦNG KỸ THUẬT CƠ SỞ CỦA CÁC DỰ ÁN ĐẦU TƯ SẢN XUẤT , KINH DOANH CỦA " +selectedName.toUpperCase();
            }


            if(type_select.equals(toantinh)){
                label_content = "TIẾN ĐỘ XÂY DỰNG HẠ TẦNG KỸ THUẬT CƠ SỞ CỦA CÁC DỰ ÁN ĐẦU TƯ SẢN XUẤT , KINH DOANH TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN";
            }

            System.out.println("lable content"+ label_content);


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


            if(!beginDate.equals("")||!endDate.equals("")){
                String date = "Thời gian";
                if(!beginDate.equals("")){
                    String[] myArray = beginDate.split("-");
                    String newDate = "";
                    newDate = newDate.concat(myArray[2]);
                    newDate = newDate.concat("/");
                    newDate = newDate.concat(myArray[1]);
                    newDate = newDate.concat("/");

                    newDate = newDate.concat(myArray[0]);
                    date += " từ ngày " +newDate;
                }
                if(!endDate.equals("")){
                    String[] myArray = endDate.split("-");
                    String newDate = "";
                    newDate = newDate.concat(myArray[2]);
                    newDate = newDate.concat("/");
                    newDate = newDate.concat(myArray[1]);
                    newDate = newDate.concat("/");

                    newDate = newDate.concat(myArray[0]);

                    date += " tới ngày " +newDate;
                }

                date = "(" + date + ")";

                label_content = date;

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
                label.setProperty(StyleHandle.FONT_STYLE_PROP, DesignChoiceConstants.FONT_STYLE_ITALIC);

                label.setProperty(StyleHandle.FONT_WEIGHT_PROP,
                        DesignChoiceConstants.FONT_WEIGHT_NORMAL);

                designHandle.getBody().add(label);
            }

//            label_content=SoNN;
//            addTypeselect(label_content,label,v,designHandle);

        } catch (Exception e) {
            e.printStackTrace();
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
            checkExistMocbaocao = -1;
            if(selectedColumn.contains("tenduan")) {
                titles.add("Tên dự án");
                width.add(10);
                align.add("left");
                selectedColumnIntTitle++;
            }

            if(selectedColumn.contains("ngaybaocao")){
                titles.add("Ngày báo cáo");
                width.add(5);
                align.add("left");
                selectedColumnIntTitle++;
                checkExistMocbaocao = selectedColumnIntTitle;
            }
            if(selectedColumn.contains("thanhphan")){
                titles.add("Thành phần");
                width.add(8);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("tiendocapvon")){
                titles.add("Tiến độ cấp vốn");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("tiendogiaingan")){
                titles.add("Tiến độ giải ngân");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }
            if(selectedColumn.contains("khoiluonghoanthanh")){
                titles.add("Khối lượng hoàn thành");
                width.add(7);
                align.add("left");

                selectedColumnIntTitle++;
            }


            if(selectedColumn.contains("trangthai")){
                titles.add("Trạng thái");
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
                for (int j = 0; j < selectedColumnIntTitle+1; j++) {

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
                    if(j==selectedColumnIntTitle){
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

            for(int i=0;i<=selectedColumnIntTitle;i++){
                cell1 = ((CellHandle) ((RowHandle) table.getRows()
                        .get(0)).getCells().get(i));
                text = design.getElementFactory().newTextItem("");
                text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
                text.setContent(titles.get(i));
                cell1.getContent().add(text);
                //Độ sâu row

            }

            System.out.println(align);

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).size(); j++) {
                    System.out.println(i+"  "+j+"   "+ data.get(i).get(j));
                }
            }


            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).size(); j++) {
                    text = design.getElementFactory().newTextItem("");
                    text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);

                    System.out.println();

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
//            connection = getConnection();
            common v = new common();
//            v.setFontReport("Abadi", "13pt", designHandle);
//            v.setAlignReport("justify", designHandle);
            v.setMarginReport("A4","landscape","20px","20px","0px","0px",designHandle);

            //***Trình tự1

            LocalDate currentdate = LocalDate.now();
            String currentDate = currentdate.toString();
            System.out.println("Current date: "+currentDate);
            String[] parts = currentDate.split("-");
            String title = ".............,ngày "+ parts[2]+ " tháng " + parts[1]+" năm "+parts[0];

            String khukinhte ="khukinhte";
            String khuchuyennganh = "khuchuyennganh";
            String duan ="duan";
            String toantinh ="toantinh";

            String url ="";

            if(type_select.equals(khuchuyennganh)){
                url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/getTenKhuchuyennganh/"+selectedID;
            }

            if(type_select.equals(duan)){
                url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/getTenDuan/"+selectedID;
            }

            if(type_select.equals(khukinhte)){
                url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/getTenKhukinhte/"+selectedID;
            }



            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            HttpEntity<?> entity = new HttpEntity<>(headers);

            System.out.println(url);
            ResponseEntity<String> newresponse ;

            if(!type_select.equals(toantinh)){
                newresponse = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<String>() {});
                selectedName=newresponse.getBody();

            }



            if(type_select.equals(toantinh)){
                selectedName="";
            }

            System.out.println("selected NAME "+ selectedName);


            addTableHeader( type_select,selectedName ,title, designHandle,v);
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


            restTemplate = new RestTemplate();
            headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);

            entity = new HttpEntity<>(headers);



            if(type_select.equals(khuchuyennganh)){
                url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/getIdduanhatangcosoByKCN/"+id_select+"/"+beginDate+"/"+endDate;
            }

            if(type_select.equals(khukinhte)){
                url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/getIdduanhatangcosoByKKT/"+id_select+"/"+beginDate+"/"+endDate;
            }

            if(type_select.equals(duan)){
                url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/getIdduanhatangcosoByDuan/"+id_select+"/"+beginDate+"/"+endDate;
            }

            if(type_select.equals(toantinh)){
                url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/getIdduanhatangcosoAll/"+beginDate+"/"+endDate;
            }





            System.out.println(url);
            ResponseEntity<List<Integer>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Integer>>() {});

            //cac id cua du an ha tang
            List<Integer> myListID = response.getBody();

            for(int t=0;t<myListID.size();t++){

                Map<String,List<List<String>>> data_phanbo = getDataTienDoXaydungHatang( type_select, myListID.get(t).toString());

//                Map<Integer,List<>>
//                addTypeselect(,v,designHandle);

                url = "http://"+hostname+":"+port+ "/tiendoXDHTChitiet/tiendoXDHTChitietbyIdduandtsx/"+myListID.get(t);

                System.out.println(url);
                ResponseEntity<String> newresponseString = restTemplate.exchange(
                        url,
                        HttpMethod.GET,
                        entity,
                        new ParameterizedTypeReference<String>() {});

                String tenduan  = newresponseString.getBody();

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

                text.setContent("Tên dự án : "+tenduan);

                ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                        .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
                ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                        .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
                        DesignChoiceConstants.FONT_WEIGHT_BOLD);
                ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                        .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                        "Times New Roman");
                //font here
                ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                        .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
                ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
                        .get(0)).getContent().add(text);


                designHandle.getBody().add(table);

                Set<String> set = data_phanbo.keySet();
                for (String key : set) {

                    if(!key.equals("1")){
                        table = designHandle.getElementFactory().newGridItem("Table",
                                1, 1);
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


                        text = designHandle.getElementFactory().newTextItem("");
                        text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);

                        text.setContent("Ngày báo cáo : "+key);
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

                        System.out.println(key + " " + data_phanbo.get(key));
                        getTablePhanbo(data_phanbo.get(key),  clrHeader, clrTextHeader,
                                clrEven, clrOdd, designHandle);
                    }
                }



            }


            //***Trình tự3
            // .setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP,
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

        beginDate ="";
        endDate="";
        if(!selectedColumn.get(selectedColumn.size()-3).equals("none")){
            beginDate = selectedColumn.get(selectedColumn.size()-3);
        }
        if(!selectedColumn.get(selectedColumn.size()-2).equals("none")){
            endDate = selectedColumn.get(selectedColumn.size()-2);
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
                selectedID = id_select;

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
            String headerValue = "attachment; filename=tiendoxaydunghatang_" + currentDateTime + "."+kind_download;
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

