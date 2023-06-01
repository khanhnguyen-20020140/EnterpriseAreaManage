package com.sgis.khukt.servlet;


//import com.sgis.khukt.controller.TblKetquaSanxuatkinhdoanhController;
import com.sgis.khukt.model.*;
import com.sgis.khukt.servlet.KetquadautusanxuatDoanhthu;
import com.sgis.khukt.repository.TblKetquaSanxuatkinhdoanhRepository;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.activity.SemanticException;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
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
import java.util.*;
import java.util.Date;
import java.util.logging.Level;

public class KetquadautusanxuatServlet extends HttpServlet {

    // SQL CONNECT


    // TÊN CỦA TRƯỜNG MUỐN XUẤT BÁO CÁO
    static String selectedName =" ";

    //CÁC CỘT MUỐN RENDER
    List<String> selectedColumn = new ArrayList<>();

    //SỐ LƯỢNG CỘT MUỐN RENDER
    Integer selectedColumnInt = 0;



    //WIDTH VÀ ALIGN ĐỂ SET GIAO DIỆN CHO CÁC CỘT
    List<Integer> width = new ArrayList<Integer>();
    List<String> align = new ArrayList<String>();

    //CHECK XEM CÓ CỘT MỐC BÁO CÁO KHÔNG
    Integer checkExistMocbaocao = -1;

    DefinedValue definedValue = new DefinedValue();
    String enginehome = definedValue.getEnginehome();

    String rptdesignpath =definedValue.getRptdesignpath();

    String hostname=definedValue.getHostname();

    String port = definedValue.getPort();

//THỜI ĐIỂM BAN ĐẦU VÀ KẾT THÚC CỦA KHOẢNG THỜI GIAN CHỌN BÁO CÁO
    String beginDate = "";
    String endDate = "";
    String desFile ="";


    //LẤY DỮ LIỆU VÀ TÊN CỦA TRƯỜNG MUỐN XUẤT BÁO CÁO
    public List<List<String>> getDataKetquadautusanxuat(String type_select, String id_select) throws SQLException {



        String khuchuyennganh = "khuchuyennganh";
        String duan ="duan";
        String doanhnghiep ="doanhnghiep";
        String toantinh = "toantinh";
        //CÂU LẤY ID, TÊN DỰ ÁN , MỐC BÁO CÁO CỦA CÁC DỰ ÁN CÓ TRONG VÙNG TRUY VẤN


        selectedColumnInt=0;
        boolean selectedTenduan =false;
        boolean selectedDoanhthu =false;
        boolean selectedNgansach = false;

//        CHECK XEM TRONG CÁC CỘT MUỐN RENDER CÓ NHỮNG CỘT NÀO
        if(selectedColumn.contains("tenduan")){
            selectedColumnInt++;
            selectedTenduan = true;

        }
        if(selectedColumn.contains("mocbaocao")){
            selectedColumnInt++;
        }

        if(selectedColumn.contains("doanhthu")){
            selectedColumnInt++;
            selectedDoanhthu =true;

        }
        if(selectedColumn.contains("ngansach")){
            selectedColumnInt++;
            selectedNgansach = true;

        }

        int[] selectedNhancongColumnInt =  {0, 0,0, 0, 0,0, 0, 0, 0};
        if(selectedColumn.contains("tongso")){
            selectedColumnInt++;
            selectedNhancongColumnInt[0]=1;

        }
        if(selectedColumn.contains("nam")){
            selectedColumnInt++;
            selectedNhancongColumnInt[1]=1;
        }
        if(selectedColumn.contains("nu")){
            selectedColumnInt++;
            selectedNhancongColumnInt[2]=1;
        }
        if(selectedColumn.contains("tuoitrungbinh")){
            selectedColumnInt++;
            selectedNhancongColumnInt[3]=1;
        }

        if(selectedColumn.contains("trungcap")){
            selectedColumnInt++;
            selectedNhancongColumnInt[4]=1;
        }
        if(selectedColumn.contains("caodang")){
            selectedColumnInt++;
            selectedNhancongColumnInt[5]=1;
        }
        if(selectedColumn.contains("daihoc")){
            selectedColumnInt++;
            selectedNhancongColumnInt[6]=1;
        }
        if(selectedColumn.contains("trendaihoc")){
            selectedColumnInt++;
            selectedNhancongColumnInt[7]=1;
        }
        if(selectedColumn.contains("luongtrungbinh")){
            selectedColumnInt++;
            selectedNhancongColumnInt[8]=1;
        }

//      XEM NGƯỜI DÙNG MUỐN XUẤT BÁO CÁO THEO KHU CHUYÊN NGÀNH , THEO DỰ ÁN HAY THEO DOANH NGHIỆP
        String detailQuery =type_select;


//      BÁO CO TỪ NGÀY ... TỚI NGÀY ...
        beginDate ="";
        endDate="";

        if(!selectedColumn.get(13).equals("none")){
            beginDate = selectedColumn.get(13);
        }

        if(!selectedColumn.get(14).equals("none")){
            endDate = selectedColumn.get(14);
        }

         // CÂU TRUY VẤN TÊN CỦA VÙNG MUỐN TRUY VẤN
        String pickedName = "";
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
            urlExactname ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/exactTen/dn/"+id_select;

        }


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



        Map<Integer, List<Object>> nhancong = new HashMap<Integer, List<Object>>();

        List<Integer> allId = new ArrayList<>();

        if(type_select.equals(khuchuyennganh)){
            String url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/kcnIn/"+id_select+"/"+beginDate+"/"+endDate;

            ResponseEntity<List<NhanCong>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<NhanCong>>() {});

            List<NhanCong> myList = response.getBody();
            for(int i =0;i<myList.size();i++){
                System.out.println(myList.get(i));
                nhancong.put(myList.get(i).getId(),myList.get(i).getNhancong());
            }



            url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/kcnID/"+id_select+"/";
            ResponseEntity<List<Integer>> responseID = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Integer>>() {});

            allId = responseID.getBody();


            for(int j =0;j<allId.size();j++){
                //nếu cho vùng đang xét k trả ra danh sách nhân công
                if(nhancong.get(allId.get(j))==null){

                    url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/notIn/"+allId.get(j)+"/"+beginDate+"/"+endDate;

                    response = restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<NhanCong>>() {});


                    if(response!=null){
                        myList = response.getBody();

                        nhancong.put(allId.get(j),myList.get(0).getNhancong());
                    }

                }


            }
        }


        if(type_select.equals(duan)){
            String url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/duanIn/"+id_select+"/"+beginDate+"/"+endDate;

            ResponseEntity<List<NhanCong>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<NhanCong>>() {});

            List<NhanCong> myList = response.getBody();
            for(int i =0;i<myList.size();i++){
                System.out.println(myList.get(i));
                nhancong.put(myList.get(i).getId(),myList.get(i).getNhancong());
            }



            url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/duanID/"+id_select+"/";
            ResponseEntity<List<Integer>> responseID = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Integer>>() {});

            allId = responseID.getBody();


            for(int j =0;j<allId.size();j++){
                //nếu cho vùng đang xét k trả ra danh sách nhân công
                if(nhancong.get(allId.get(j))==null){

                    url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/notIn/"+allId.get(j)+"/"+beginDate+"/"+endDate;

                    response = restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<NhanCong>>() {});

                    if(response!=null){
                        myList = response.getBody();

                        nhancong.put(allId.get(j),myList.get(0).getNhancong());
                    }
                }


            }
        }


        if(type_select.equals(doanhnghiep)){
            String url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/dnIn/"+id_select+"/"+beginDate+"/"+endDate;

            ResponseEntity<List<NhanCong>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<NhanCong>>() {});

            List<NhanCong> myList = response.getBody();
            for(int i =0;i<myList.size();i++){
                System.out.println(myList.get(i));
                nhancong.put(myList.get(i).getId(),myList.get(i).getNhancong());
            }



            url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/dnID/"+id_select+"/";
            ResponseEntity<List<Integer>> responseID = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Integer>>() {});

            allId = responseID.getBody();


            for(int j =0;j<allId.size();j++){
                //nếu cho vùng đang xét k trả ra danh sách nhân công
                if(nhancong.get(allId.get(j))==null){

                    url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/notIn/"+allId.get(j)+"/"+beginDate+"/"+endDate;

                    response = restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<NhanCong>>() {});

                    if(response!=null){
                        myList = response.getBody();

                        nhancong.put(allId.get(j),myList.get(0).getNhancong());
                    }
                }


            }
        }

        if(type_select.equals(toantinh)){
            String url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/toantinhIn/"+beginDate+"/"+endDate;

            ResponseEntity<List<NhanCong>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<NhanCong>>() {});

            List<NhanCong> myList = response.getBody();
            for(int i =0;i<myList.size();i++){
                System.out.println(myList.get(i));
                nhancong.put(myList.get(i).getId(),myList.get(i).getNhancong());
            }



            url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/toantinhID/";
            ResponseEntity<List<Integer>> responseID = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Integer>>() {});

            allId = responseID.getBody();


            for(int j =0;j<allId.size();j++){
                //nếu cho vùng đang xét k trả ra danh sách nhân công
                if(nhancong.get(allId.get(j))==null){

                    url="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/notIn/"+allId.get(j)+"/"+beginDate+"/"+endDate;

                    response = restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            new ParameterizedTypeReference<List<NhanCong>>() {});

                    if(response!=null){
                        myList = response.getBody();

                        nhancong.put(allId.get(j),myList.get(0).getNhancong());
                    }
                }


            }
        }



        // show data
        Map<Integer,Double> getDoanhthu = new HashMap<>();
        if(selectedDoanhthu){
            KetquadautusanxuatDoanhthu ketquadautusanxuatDoanhthu = new KetquadautusanxuatDoanhthu();

            try {
                getDoanhthu=ketquadautusanxuatDoanhthu.getDoanhthu(detailQuery,id_select,beginDate,endDate);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

//      Map để tính toán Ngân sách của mỗi dự án
        Map<Integer, List<String>> mapNgansach = new HashMap<Integer, List<String>>();
        List<String> listNgansach = new ArrayList<>();
        Map < Integer, Double > getNgansach = new HashMap < Integer, Double > ();

        if(selectedNgansach){

            String activeIdDuan = "-1";
            String existYear = "-1";
            boolean getNgansachSuccess = false;

            String url ="";

            if(type_select.equals(khuchuyennganh)){
                url ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/ngansach/kcn/"+id_select+"/"+beginDate+"/"+endDate;
            }
            if(type_select.equals(duan)){
                url ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/ngansach/duan/"+id_select+"/"+beginDate+"/"+endDate;
            }
            if(type_select.equals(doanhnghiep)){
                url ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/ngansach/dn/"+id_select+"/"+beginDate+"/"+endDate;
            }
            if(type_select.equals(toantinh)){
                url ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/ngansach/toantinh/"+beginDate+"/"+endDate;
            }

            ResponseEntity<List<Ngansach>> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    new ParameterizedTypeReference<List<Ngansach>>() {});

            List<Ngansach> myListNgansach = response.getBody();

            for(int i=0;i<myListNgansach.size();i++) {
                getNgansachSuccess = true;
                String id = myListNgansach.get(i).getNgansach().get(0);
                String tenda = myListNgansach.get(i).getNgansach().get(1);
                String mocbaocao =myListNgansach.get(i).getNgansach().get(2);
                String ngansach ="0.0";
                if(myListNgansach.get(i).getNgansach().get(3)!=null){
                    ngansach = myListNgansach.get(i).getNgansach().get(3);
                }



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

                        //nam cu
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

        for(int j =0;j<allId.size();j++){
            if(getNgansach.get(allId.get(j))==null){
                getNgansach.put(allId.get(j),0.0);
            }
        }

        String url ="";

        if(type_select.equals(khuchuyennganh)){
            url ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/kcnTen/"+id_select+"/";
        }
        if(type_select.equals(duan)){
            url ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/duanTen/"+id_select+"/";
        }
        if(type_select.equals(doanhnghiep)){
            url ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/dnTen/"+id_select+"/";
        }
        if(type_select.equals(toantinh)){
            url ="http://"+hostname+":"+port+"/ketquaSanxuatkinhdoanh/ketquaSanxuatkinhdoanhsreport/toantinhTen/";
        }

        ResponseEntity<List<TenDuAn>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<TenDuAn>>() {});

        List<TenDuAn> myListTenDuAn = response.getBody();


        List<List<String>> listKetquasanxuat = new ArrayList<>();

        for(int i = 0;i<myListTenDuAn.size();i++){
            System.out.println(myListTenDuAn.get(i).getIdName());
        }

        for(int i = 0;i<myListTenDuAn.size();i++){
            List<String> miniList = new ArrayList<>();

            if(selectedTenduan){
                miniList.add(myListTenDuAn.get(i).getIdName().get(1));

            }

            if(selectedDoanhthu){
                miniList.add(String.valueOf(getDoanhthu.get(Integer.parseInt(myListTenDuAn.get(i).getIdName().get(0)))));
            }

            //nop ngan sach

            if(selectedNgansach){
                miniList.add(String.valueOf(getNgansach.get(Integer.parseInt(myListTenDuAn.get(i).getIdName().get(0)))));
            }

            for(int j=0;j<selectedNhancongColumnInt.length;j++){
                if(selectedNhancongColumnInt[j]!=0){
                    System.out.println(j);

                    miniList.add(String.valueOf(nhancong.get(Integer.parseInt(myListTenDuAn.get(i).getIdName().get(0))).get(j)));

                }
            }

            listKetquasanxuat.add(miniList);
        }



        if(listKetquasanxuat.size()==0){
            return new ArrayList<>();
        }


        System.out.println(listKetquasanxuat);
        return listKetquasanxuat;

    }

    public static String getExactValue(String string){

        Integer toInt =null;
        if(string!=null){
            Double b =  Double.parseDouble(string);
            int value = b.intValue();
            if(b==value){
                toInt=value;
            }
            else{
                if (value %2 ==0){
                    toInt= value;
                }
                else {
                    toInt = value +1;
                }

            }
        }
        String s =null;
        if(toInt!=null){
            s=Integer.toString(toInt);
        }
        return s;
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



            if(type_select.equals(khuchuyennganh)){
                label_content = "TÌNH HÌNH SẢN XUẤT CỦA CÁC DỰ ÁN ĐẦU TƯ TẠI " +selectedName.toUpperCase();

            }

            if(type_select.equals(duan)){
                label_content = "TÌNH HÌNH SẢN XUẤT CỦA " +selectedName.toUpperCase();
//                SELECT = "Dự án đầu tư sản xuất : ";
            }

            if(type_select.equals(doanhnghiep)){
                label_content = "TÌNH HÌNH SẢN XUẤT CỦA " +selectedName.toUpperCase();
//                SELECT = "Doanh nghiệp : ";
            }
            if(type_select.equals(toantinh)){
                label_content = "TÌNH HÌNH SẢN XUẤT CỦA CÁC DỰ ÁN ĐẦU TƯ TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN";
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

    //HIỂN THỊ TRÊN TRƯỜNG VÀ MOOSC THỜI GIAN BÁO CÁO
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

                text.setContent(date);
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
            }


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
            table = design.getElementFactory().newGridItem("", 12,
                    data.size() + 2);
            table.setWidth("100%");
//            table.setProperty(StyleHandle.VERTICAL_ALIGN_PROP, "CENTER");

            width.clear();
            align.clear();
            Integer selectedColumnIntTitle=-1;
            List<String> titles = new ArrayList<>();

            if(selectedColumn.contains("tenduan")){
                titles.add("Tên dự án");
                width.add(20);
                align.add("left");
                selectedColumnIntTitle++;
            }
            checkExistMocbaocao =-1;
            if(selectedColumn.contains("mocbaocao")){
                titles.add("Mốc báo cáo");
                align.add("right");
                width.add(8);
                selectedColumnIntTitle++;
                checkExistMocbaocao =selectedColumnIntTitle;
            }


            if(selectedColumn.contains("doanhthu")){
                titles.add("Doanh thu");
                align.add("right");
                width.add(8);
                selectedColumnIntTitle++;

            }
            if(selectedColumn.contains("ngansach")){
                titles.add("Ngân sách");
                align.add("right");
                width.add(8);
                selectedColumnIntTitle++;
            }

            Integer selectedColumnIntTitleSpan=-1;
            List<String> titlespans = new ArrayList<>();

            boolean checkNhancong = false;
            if(selectedColumn.contains("tongso")){
                titlespans.add("Tổng số");
                checkNhancong = true;
                align.add("right");
                width.add(8);
                selectedColumnIntTitleSpan++;
            }
            if(selectedColumn.contains("nam")){
                titlespans.add("Nam");
                checkNhancong = true;
                align.add("right");
                width.add(8);
                selectedColumnIntTitleSpan++;
            }
            if(selectedColumn.contains("nu")){
                titlespans.add("Nữ");
                checkNhancong = true;
                align.add("right");
                width.add(8);
                selectedColumnIntTitleSpan++;
            }
            if(selectedColumn.contains("tuoitrungbinh")){
                titlespans.add("Tuổi trung bình");
                checkNhancong = true;
                align.add("right");
                width.add(8);
                selectedColumnIntTitleSpan++;
            }

            if(selectedColumn.contains("trungcap")){
                titlespans.add("Trung cấp");
                checkNhancong = true;
                align.add("right");
                width.add(6);
                selectedColumnIntTitleSpan++;
            }
            if(selectedColumn.contains("caodang")){
                titlespans.add("Cao đẳng");
                checkNhancong = true;
                align.add("right");
                width.add(6);
                selectedColumnIntTitleSpan++;
            }
            if(selectedColumn.contains("daihoc")){
                titlespans.add("Đại học");
                checkNhancong = true;
                align.add("right");
                width.add(6);
                selectedColumnIntTitleSpan++;
            }
            if(selectedColumn.contains("trendaihoc")){
                titlespans.add("Trên đại học");
                checkNhancong = true;
                align.add("right");
                width.add(6);
                selectedColumnIntTitleSpan++;
            }
            if(selectedColumn.contains("luongtrungbinh")){
                titlespans.add("Lương trung bình");
                checkNhancong = true;
                align.add("right");
                width.add(8);
                selectedColumnIntTitleSpan++;
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

            // //////Hòa các hàng của tiêu đề
            TextItemHandle text;
            CellHandle cell1;

            System.out.println("selectedCoumnSpan "+selectedColumnIntTitleSpan);
            System.out.println("selectedColum" +selectedColumnIntTitle);


            for (int i = 0; i <=1; i++) {
                for (int j = 0; j < selectedColumnIntTitleSpan+1; j++) {
                    if((i==0&&j<selectedColumnIntTitle+2)||(i==1&&j<selectedColumnIntTitleSpan+1)){
                        ((CellHandle) ((RowHandle) table.getRows().get(i))
                                .getCells().get(j)).setProperty(
                                StyleHandle.TEXT_ALIGN_PROP, DesignChoiceConstants.TEXT_ALIGN_CENTER);
                        ((CellHandle) ((RowHandle) table.getRows().get(i))
                                .getCells().get(j)).setProperty(
                                StyleHandle.VERTICAL_ALIGN_PROP, "middle");

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
                        if((i==0&&j==selectedColumnIntTitle+1)||(i==1&&j==selectedColumnIntTitleSpan)){
                            ((CellHandle) ((RowHandle) table.getRows().get(i))
                                    .getCells().get(j)).setProperty(
                                    StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
                            ((CellHandle) ((RowHandle) table.getRows().get(i))
                                    .getCells().get(j)).setProperty(
                                    StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
                        }
                    }


                }
            }

            for(int i=0;i<=selectedColumnIntTitle;i++){
                cell1 = ((CellHandle) ((RowHandle) table.getRows()
                        .get(0)).getCells().get(i));
                text = design.getElementFactory().newTextItem("");
                text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
                text.setContent(titles.get(i));
                cell1.getContent().add(text);
                //Độ sâu row
                cell1.setRowSpan(2);
            }

            if(checkNhancong){
                cell1 = ((CellHandle) ((RowHandle) table.getRows().get(0))
                        .getCells().get(selectedColumnIntTitle+1));
                text = design.getElementFactory().newTextItem("");
                text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
                text.setContent("Nhân công");
                cell1.getContent().add(text);
                cell1.setColumnSpan(selectedColumnIntTitleSpan+1);
            }

            for(int i=0;i<=selectedColumnIntTitleSpan;i++){
                cell1 = ((CellHandle) ((RowHandle) table.getRows()
                        .get(1)).getCells().get(i));
                text = design.getElementFactory().newTextItem("");
                text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
                text.setContent(titlespans.get(i));
                cell1.getContent().add(text);

            }

            for (int i = 0; i < data.size(); i++) {
                for (int j = 0; j < data.get(i).size(); j++) {
                    text = design.getElementFactory().newTextItem("");
                    text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);

                    if(j == checkExistMocbaocao&&data.get(i).get(j)!=null){

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


                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_TOP_STYLE_PROP, "solid");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");

                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "1px");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_TOP_WIDTH_PROP, "1px");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.BORDER_LEFT_WIDTH_PROP, "1px");
                    if (j == data.get(i).size() - 1) {
                        ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
                        ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
                    }

                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.FONT_SIZE_PROP, "10pt");
                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                            .getCells().get(j)).setProperty(
                            StyleHandle.TEXT_ALIGN_PROP, align.get(j));

                    if (i % 2 == 0) {
                        ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BACKGROUND_COLOR_PROP, clrOdd);
                    } else {
                        ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BACKGROUND_COLOR_PROP, clrEven);
                    }

                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
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

        try {
            int i, j, k, l;
//            connection = getConnection();
            common v = new common();
//            v.setFontReport("Abadi", "13pt", designHandle);
//            v.setAlignReport("justify", designHandle);
            v.setMarginReport("A4","landscape","20px","20px","0px","0px",designHandle);
            List<List<String>> data_phanbo = getDataKetquadautusanxuat( type_select, id_select);
            //***Trình tự1
            //addTableHeader
            LocalDate currentdate = LocalDate.now();
            String currentDate = currentdate.toString();
            System.out.println("Current date: "+currentDate);
            String[] parts = currentDate.split("-");
            String title = ".............,ngày "+ parts[2]+ " tháng " + parts[1]+" năm "+parts[0];


            System.out.println("yess");
//            System.out.println(tblKetquaSanxuatkinhdoanhRepository.findAll());


            String khuchuyennganh = "khuchuyennganh";
            String duan ="duan";
            String doanhnghiep ="doanhnghiep";
            String toantinh ="toantinh";
            String SELECT ="";
            if(type_select.equals(khuchuyennganh)){
                SELECT = "Khu chuyên ngành : ";
            }

            if(type_select.equals(duan)){
                SELECT = "Dự án đầu tư sản xuất : ";
            }

            if(type_select.equals(doanhnghiep)){
                SELECT = "Doanh nghiệp : ";
            }
            if(!type_select.equals(toantinh)){
                SELECT += selectedName;
            }

            addTableHeader(SELECT, type_select,selectedName ,title, designHandle,v);
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

        for(int i=5;i<20;i++){

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

                System.out.println(rptdesignpath);
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
                System.out.println(ReportKQDTSXData.data);
                IRunAndRenderTask task = engine.createRunAndRenderTask(design);

                IRenderOption options = new RenderOption();
                options.setOutputStream(new ByteArrayOutputStream());

                PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
                ServletContext servletContext = getServletContext();  ;

                String path = servletContext.getRealPath("/");


                options.setOutputFormat("pdf");
                desFile = arrOfStr[20];
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
            String headerValue = "attachment; filename=ketquaSanxuatkinhdoanh_" + currentDateTime + "."+kind_download;
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

