//package com.sgis.khukt.model;
//
//
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.logging.Level;
//
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import javax.sql.DataSource;
//
//import org.eclipse.birt.core.framework.Platform;
//import org.eclipse.birt.report.engine.api.EXCELRenderOption;
//import org.eclipse.birt.report.engine.api.EngineConfig;
//import org.eclipse.birt.report.engine.api.HTMLRenderOption;
//import org.eclipse.birt.report.engine.api.IPDFRenderOption;
//import org.eclipse.birt.report.engine.api.IRenderOption;
//import org.eclipse.birt.report.engine.api.IReportEngine;
//import org.eclipse.birt.report.engine.api.IReportEngineFactory;
//import org.eclipse.birt.report.engine.api.IReportRunnable;
//import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
//import org.eclipse.birt.report.engine.api.PDFRenderOption;
//import org.eclipse.birt.report.engine.api.RenderOption;
//import org.eclipse.birt.report.model.api.CellHandle;
//import org.eclipse.birt.report.model.api.ColumnHandle;
//import org.eclipse.birt.report.model.api.DimensionHandle;
//import org.eclipse.birt.report.model.api.GridHandle;
//import org.eclipse.birt.report.model.api.MasterPageHandle;
//import org.eclipse.birt.report.model.api.ReportDesignHandle;
//import org.eclipse.birt.report.model.api.RowHandle;
//import org.eclipse.birt.report.model.api.StyleHandle;
//import org.eclipse.birt.report.model.api.TextItemHandle;
//import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
//import org.eclipse.core.internal.registry.RegistryProviderFactory;
//
////import com.bvtv.bean.FunctionAll;
////import com.bvtv.dubao.DubaoBenhhaiCaylua;
////import com.bvtv.dubao.DubaoSauhaiCaylua;
////import com.bvtv.persistence.TblSinhtruongLua;
////import com.bvtv.persistence.TblTralua;
//import com.sgis.khukt.model.common;
//
///**
// * Servlet implementation class JQGridServlet
// */
//@WebServlet("/ReportChiCuc7ngayServlet")
//public class ReportChiCuc7ngayServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private DataSource datasource;
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public ReportChiCuc7ngayServlet() {
//        super();
//        // TODO Auto-generated constructor stub
//    }
//
//    private Connection getConnection() {
//        try {
//            // Look up the JNDI data source only once at init time
//            InitialContext ic = new InitialContext();
//            datasource = (DataSource) ic.lookup("java:comp/env/BvtvBase");
//            return datasource.getConnection();
//        } catch (NamingException e) {
//            e.printStackTrace();
//            return null;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public double round_intelligent(double value, int fractionDigits) {
//        double rc = 0;
//        for (int i = 1; i <= fractionDigits; i++) {
//            double d = Math.pow(10, i);
//            rc = Math.round(value * d) / d;
//            if (rc > 0)
//                break;
//            else
//                continue;
//        }
//        return rc;
//    }
//
//    @SuppressWarnings("deprecation")
//    protected void processRequest(HttpServletRequest request,
//                                  HttpServletResponse response) throws ServletException, IOException {
//        response.setContentType("text/html; charset=UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        request.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
////        FunctionAll func = new FunctionAll();
//        IReportEngine engine = null;
//        EngineConfig config = null;
//        HttpSession session = request.getSession();
//        String sessionID = "reports/" + session.getId() + new Date().getTime();
//        try {
//            System.out.println("1");
//            config = new EngineConfig();
//            config.setEngineHome("C:/birt-runtime/ReportEngine");
//            config.setLogConfig(null, Level.FINE);
//
//            System.out.println("2");
//            Platform.startup(config);
//            IReportEngineFactory factory = (IReportEngineFactory) Platform
//                    .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
//            engine = factory.createReportEngine(config);
//            engine.changeLogLevel(Level.WARNING);
//
//            IReportRunnable design = null;
//
//            System.out.println("3");
//            // Open a report design
//            design = engine
//                    .openReportDesign("C:/birt-runtime/sample/templateA4.rptdesign");
//            ReportDesignHandle report = (ReportDesignHandle) design
//                    .getDesignHandle();
//            MasterPageHandle pageHandle = report
//                    .findMasterPage("Simple MasterPage");
//            DimensionHandle leftMarginHandle = pageHandle.getLeftMargin();
//            DimensionHandle rightMarginHandle = pageHandle.getRightMargin();
//            pageHandle.setOrientation("portrait"); // trang doc
//
//            int ngaybd = func.stringDateToInt(request.getParameter("ngay"));
//            String tentinh = request.getParameter("tentinh");
//            String title = tentinh + ", "
//                    + func.stringDateToText(func.intToDate(ngaybd + 6));
//            tentinh = tentinh.toUpperCase();
//            if (tentinh.indexOf("THÀNH PHỐ") != -1) {
//                tentinh = tentinh.substring(10, tentinh.length());
//            }
//            tentinh = "SỞ NÔNG NGHIỆP & PTNT " + tentinh;
//            Integer matinh = Integer.valueOf(request.getParameter("idtinh"));
//            String kekeke = request.getParameter("format");
//            String formatReport = kekeke.split("#")[0];
//            if (formatReport.equalsIgnoreCase("html")) {
//                leftMarginHandle.setStringValue("2cm");
//                rightMarginHandle.setStringValue("1cm");
//            } else if (formatReport.equalsIgnoreCase("pdf")) {
//                leftMarginHandle.setStringValue("2cm");
//                rightMarginHandle.setStringValue("1cm");
//            }
//
//            String strTuychonSB = kekeke.split("#")[1];
//            String[] arrDubaoSaubenh = strTuychonSB.split(",");
//
//            String m_webname = request.getParameter("subwebname");
//            Integer m_capquanly = Integer.valueOf(request.getParameter("capquanly"));
//            buildReport(report, tentinh, title, matinh, ngaybd, formatReport, arrDubaoSaubenh, m_webname, m_capquanly);
//
//            // create task to run and render report
//            IRunAndRenderTask task = engine.createRunAndRenderTask(design);
//            if (formatReport.equalsIgnoreCase("xls")) {
//                EXCELRenderOption options = new EXCELRenderOption();
//                options.setOutputFormat("xls");
//                File destFile = new File(request.getRealPath("/") + sessionID + ".xls");
//                options.setOutputFileName(destFile.toString());
//                task.setRenderOption(options);
//            }else{
//                IRenderOption options = new RenderOption();
//                options.setOutputStream(new ByteArrayOutputStream());
//                if (formatReport.equalsIgnoreCase("html")) {
//                    File destFile = new File(request.getRealPath("/") + sessionID + ".html");
//                    options.setOutputFormat("html");
//                    options.setOutputFileName(destFile.toString());
//                    HTMLRenderOption htmlOptions = new HTMLRenderOption(options);
//                    // htmlOptions.setImageDirectory("output/image");
//                    htmlOptions.setHtmlPagination(false);
//                    // set this if you want your image source url to be altered
//                    // If using the setBaseImageURL, make sure to set image handler
//                    // to HTMLServerImageHandler
//                    // htmlOptions.setBaseImageURL("http://myhost/prependme?image=");
//
//                    htmlOptions.setHtmlRtLFlag(false);
//                    htmlOptions.setEmbeddable(false);
//                } else if (formatReport.equalsIgnoreCase("pdf")) {
//                    File destFile = new File(request.getRealPath("/") + sessionID + ".pdf");
//                    options.setOutputFormat("pdf");
//                    options.setOutputFileName(destFile.toString());
//                    PDFRenderOption pdfOptions = new PDFRenderOption(options);
//                    pdfOptions.setOption(IPDFRenderOption.PAGE_OVERFLOW, IPDFRenderOption.OUTPUT_TO_MULTIPLE_PAGES);
//                } else if (formatReport.equalsIgnoreCase("doc")) {
//                    File destFile = new File(request.getRealPath("/") + sessionID + ".doc");
//                    options.setOutputFormat("doc");
//                    options.setOutputFileName(destFile.toString());
//                }
//                task.setRenderOption(options);
//            }
//            // run report
//            task.run();
//            task.close();
//            // Destroy the engine and shutdown the Platform
//            // Note - If the program stays resident do not shutdown the Platform
//            // or the Engine
//            // engine.shutdown();
//            // Platform.shutdown();
//            engine.destroy();
//            RegistryProviderFactory.releaseDefault();
//            engine = null;
//            System.out.println("Finished");
//            out.print(sessionID + "#success");
//        } catch (Exception e) {
//            e.printStackTrace();
//            engine.destroy();
//            RegistryProviderFactory.releaseDefault();
//            engine = null;
//            throw new ServletException(e);
//        } finally {
//            Platform.shutdown();
//        }
//    }
//
//    public void buildReport(ReportDesignHandle designHandle, String tentinh,
//                            String title, Integer matinh, int ngaybd, String formatReport,
//                            String[] arrDubaoSaubenh, String m_webname, int m_capquanly) {
//        FunctionAll func = new FunctionAll();
//        Connection connection = null;
//        try {
//            int i, j, k, l;
//            connection = getConnection();
//            common v = new common();
//            Report_Functions_ChiCuc rf = new Report_Functions_ChiCuc();
//            v.setFontReport("Times New Roman", "13pt", designHandle);
//            v.setAlignReport("justify", designHandle);
//
//            addTableHeader(tentinh, title, designHandle);
//            v.addLabel("", designHandle);
//
//            StyleHandle labelStyle1 = v.createStyle("label1", "B", "", false,
//                    designHandle);
//            designHandle.getStyles().add(labelStyle1);
//            StyleHandle labelStyle2 = v.createStyle("label2", "I", "", false,
//                    designHandle);
//            designHandle.getStyles().add(labelStyle2);
//            StyleHandle labelStyle3 = v.createStyle("label3", "BI", "", false,
//                    designHandle);
//            designHandle.getStyles().add(labelStyle3);
//            StyleHandle textStyle1 = v.createStyle("text1", "N", "2.5em",
//                    false, designHandle);
//            designHandle.getStyles().add(textStyle1);
//
//            v.addLabel("THÔNG BÁO", "label1", designHandle).setProperty(
//                    StyleHandle.TEXT_ALIGN_PROP, "center");
//            v.addLabel("TÌNH HÌNH SINH VẬT GÂY HẠI 7 NGÀY", "label1",
//                    designHandle).setProperty(StyleHandle.TEXT_ALIGN_PROP,
//                    "center");
//            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
//                    "dd/MM/yyyy");
//            String intervalTime = "(Từ ngày "
//                    + sdf.format(func.intToDate(ngaybd)) + " đến ngày "
//                    + sdf.format(func.intToDate(ngaybd + 6)) + ")";
//            v.addLabel(intervalTime, "label2", designHandle).setProperty(
//                    StyleHandle.TEXT_ALIGN_PROP, "center");
//            v.addLabel("", designHandle);
//
//            v.addLabel("I. TÌNH HÌNH THỜI TIẾT VÀ SINH TRƯỞNG CỦA CÂY TRỒNG",
//                    "label1", designHandle);
//            // THOITIET
//            String[] data_tt = rf.Search_Thoitiet(connection, ngaybd,
//                    ngaybd + 6, matinh);
//
//            v.addLabel("1. Thời tiết", "label1", designHandle);
//            v.addLabel(data_tt[0], designHandle);
//            v.addLabel(data_tt[1], designHandle);
//            v.addLabel(data_tt[2], designHandle);
//            v.addLabel(data_tt[3], designHandle);
//            v.addLabel(" - Nhận xét khác:", designHandle);
//            v.addLabel("", designHandle);
//            v.addLabel("", designHandle);
//            // CAY TRONG
//            Class_Caylua data_caylua = rf.Search_Caylua(connection, ngaybd, ngaybd + 6, matinh);
//            List<Class_Raumau> data_raumau = null;
//            if(m_capquanly==0){
//                data_raumau = new ArrayList<Class_Raumau>();
//            }else if(m_capquanly==1){
//                data_raumau = rf.Search_Raumau(connection, ngaybd, ngaybd + 6, matinh);
//            }
//            v.addLabel("2. Cây trồng và giai đoạn sinh trưởng", "label1",
//                    designHandle);
//            v.addLabel("a) Cây lúa ("+data_caylua.name_vulua+")", "label3", designHandle);
//            List<Integer> listTralua = new ArrayList<Integer>();
//            if (data_caylua != null) {
//                for (i = 0; i < data_caylua.listGionglua.size(); i++) {
//                    if (listTralua.contains(data_caylua.listGionglua.get(i).id_tralua))
//                        continue;
//                    listTralua.add(data_caylua.listGionglua.get(i).id_tralua);
//                }
//                Collections.sort(listTralua);
//                String strDTGC = "";
//                for (i = 0; i < listTralua.size(); i++) {
//                    strDTGC += "<b><i>&nbsp;&nbsp; + "
//                            + new TblTralua().getTenTralua(listTralua.get(i))
//                            + ":</i></b><br>";
//                    strDTGC += " &nbsp;&nbsp;&nbsp;&nbsp; + Diện tích KH: ";
//                    double dtgc = 0;
//                    for (j = 0; j < data_caylua.listGionglua.size(); j++) {
//                        if (listTralua.get(i).intValue() == data_caylua.listGionglua.get(j).id_tralua
//                                && data_caylua.listGionglua.get(j).dtgieotrong > 0) {
//                            dtgc = dtgc + data_caylua.listGionglua.get(j).dtgieotrong;
//                        }
//                    }
//                    if (dtgc == 0) {
//                        strDTGC += "không có số liệu gieo cấy.";
//                    } else {
//                        strDTGC += func.format_numeric(dtgc) + " (ha).";
//                    }
//                    List<String> listGionglua = new ArrayList<String>();
//                    List<Integer> listGdst = new ArrayList<Integer>();
//                    int nIndex;
//                    for (j = 0; j < data_caylua.listGionglua.size(); j++) {
//                        if (data_caylua.listGionglua.get(j).id_tralua != listTralua.get(i).intValue())
//                            continue;
//                        if (listGdst.size() == 0) {
//                            listGionglua.add(data_caylua.listGionglua.get(j).name_gionglua);
//                            listGdst.add(data_caylua.listGionglua.get(j).id_gdst_gionglua);
//                        } else {
//                            nIndex = -1;
//                            for (k = 0; k < listGdst.size(); k++) {
//                                if (listGdst.get(k).intValue() == data_caylua.listGionglua.get(j).id_gdst_gionglua) {
//                                    nIndex = k;
//                                    break;
//                                }
//                            }
//                            if (nIndex == -1) {
//                                listGionglua.add(data_caylua.listGionglua.get(j).name_gionglua);
//                                listGdst.add(data_caylua.listGionglua.get(j).id_gdst_gionglua);
//                            } else {
//                                boolean bCheck=false;
//                                for (String c : listGionglua) {
//                                    if(c.contains(data_caylua.listGionglua.get(j).name_gionglua)){
//                                        bCheck=true;break;
//                                    }
//                                }
//                                if(bCheck==false)
//                                    listGionglua.set(nIndex,listGionglua.get(nIndex) + "," + data_caylua.listGionglua.get(j).name_gionglua);
//                            }
//                        }
//                    }
//                    strDTGC += "<br> &nbsp;&nbsp;&nbsp;&nbsp; + Giống lúa: ";
//                    for (j = 0; j < listGionglua.size(); j++) {
//                        if (j == 0) {
//                            if (listGdst.get(j) == -1)
//                                strDTGC += listGionglua.get(j) + " (GĐST không xác định)";
//                            else
//                                strDTGC += listGionglua.get(j)
//                                        + " ("
//                                        + new TblSinhtruongLua()
//                                        .getSinhtruongMota(listGdst
//                                                .get(j)) + ")";
//                        } else {
//                            if (listGdst.get(j) == -1)
//                                strDTGC += "; " + listGionglua.get(j) + " (GĐST không xác định)";
//                            else
//                                strDTGC += "; "
//                                        + listGionglua.get(j)
//                                        + " ("
//                                        + new TblSinhtruongLua()
//                                        .getSinhtruongMota(listGdst
//                                                .get(j)) + ")";
//                        }
//                    }
//                    strDTGC += ".<br>";
//                }
//                v.addTextHTML(strDTGC, designHandle);
//            }
//            v.addLabel("b) Rau màu", "label3", designHandle);
//            for (i = 0; i < data_raumau.size(); i++) {
//                Class_Raumau e = data_raumau.get(i);
//                if (e.dtgieotrong > 0) {
//                    String str = " - " + e.name_group + ": "
//                            + func.format_numeric(e.dtgieotrong) + " (ha). ";
//                    if (!e.gdst_group.isEmpty()) {
//                        str = str + "GĐST: " + e.gdst_group;
//                    }
//                    v.addLabel(str, designHandle);
//                }
//            }
//            v.addLabel("c) Cây trồng khác", "label3", designHandle);
//            v.addLabel("", designHandle);
//            // NHAN XET TINH HINH SVGH
//            v.addLabel("II. NHẬN XÉT TÌNH HÌNH SINH VẬT GÂY HẠI 7 NGÀY QUA",
//                    "label1", designHandle);
//            v.addLabel("1. Trên cây lúa", "label1", designHandle);
//            String[] arrKyhieu = { "a. ", "b. ", "c. ", "d. ", "e. ", "f. ", "g. ", "h. " };
//            if (data_caylua != null) {
//                String strDTGC = "";
//                for (i = 0; i < listTralua.size(); i++) {
//                    strDTGC += "<b><i>&nbsp;&nbsp; " + arrKyhieu[i] + data_caylua.name_vulua + " (" +
//                            new TblTralua().getTenTralua(listTralua.get(i)).toLowerCase() + "):</i></b><br>";
//                    for(j = 0; j < data_caylua.listGionglua.size(); j++){
//                        if(data_caylua.listGionglua.get(j).id_tralua != listTralua.get(i).intValue()) continue;
//                        if(data_caylua.listGionglua.get(j).listDichhai == null) continue;
//                        for(k = 0;k < data_caylua.listGionglua.get(j).listDichhai.size(); k++){
//                            Class_Dichhai_Caylua e = data_caylua.listGionglua.get(j).listDichhai.get(k);
//                            strDTGC += "&nbsp;&nbsp;&nbsp;&nbsp; - " + e.name_obj;
//                            if(e.name_gionglua.trim() != ""){
//                                strDTGC += " (trên giống "+e.name_gionglua+")";
//                            }
//                            if(e.tongdtnhiem>0){
//                                strDTGC += " gây hại " + func.format_numeric(e.tongdtnhiem) + " (ha)";
//                            }else{
//                                strDTGC += " không gây diện tích nhiễm";
//                            }
//                            if(e.tuoiPhobien.trim()!=""){
//                                strDTGC += ", tuổi phổ biến: " + e.tuoiPhobien;
//                            }
//                            if (e.listMatdo == null) {
//                                strDTGC += ", không có số liệu mật độ/tỷ lệ. ";
//                            } else {
//                                for (l = 0; l < e.listMatdo.size(); l++) {
//                                    Class_Matdo_Tylehai_Caylua pt = e.listMatdo.get(l);
//                                    if (pt.phobien1 == pt.phobien2) {
//                                        if (e.option == 0){ //là sâu hại
//                                            if(pt.dvt==0)
//                                                strDTGC += ", " + rf.getDienGiaiDVTCaylua(pt.dvt) + " phổ biến " + func.format_numeric(pt.phobien1) + " (" + rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                strDTGC += ", " + rf.getDienGiaiDVTCaylua(pt.dvt) + " phổ biến " + func.format_numeric(pt.phobien1) + "% (" + rf.getDVTCaylua(pt.dvt) + ")";
//                                        }else if (e.option == 1){ //là bệnh hại
//                                            strDTGC += ", " + rf.getDienGiaiDVTBenhCaylua(pt.dvt) + " phổ biến " + func.format_numeric(pt.phobien1) + "% (" + rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                        }
//                                    } else if (pt.phobien1 < pt.phobien2) {
//                                        if (e.option == 0){ //là sâu hại
//                                            if(pt.dvt==0)
//                                                strDTGC += ", "
//                                                        + rf.getDienGiaiDVTCaylua(pt.dvt)
//                                                        + " phổ biến " + func.format_numeric(pt.phobien1) + "-"
//                                                        + func.format_numeric(pt.phobien2) + " ("
//                                                        + rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                strDTGC += ", "
//                                                        + rf.getDienGiaiDVTCaylua(pt.dvt)
//                                                        + " phổ biến " + func.format_numeric(pt.phobien1) + "-"
//                                                        + func.format_numeric(pt.phobien2) + "% ("
//                                                        + rf.getDVTCaylua(pt.dvt) + ")";
//                                        }else if (e.option == 1){ //là bệnh hại
//                                            strDTGC += ", "
//                                                    + rf.getDienGiaiDVTBenhCaylua(pt.dvt)
//                                                    + " phổ biến " + func.format_numeric(pt.phobien1) + "-"
//                                                    + func.format_numeric(pt.phobien2) + "% ("
//                                                    + rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                        }
//                                    }
//                                    if (pt.cao > 0) {
//                                        if (e.option == 0){//là sâu hại
//                                            if(pt.dvt==0)
//                                                strDTGC += ", cao " + func.format_numeric(pt.cao) + " (" + rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                strDTGC += ", cao " + func.format_numeric(pt.cao) + "% (" + rf.getDVTCaylua(pt.dvt) + ")";
//                                        }else if (e.option == 1){ //là bệnh hại
//                                            strDTGC += ", cao " + func.format_numeric(pt.cao) + "% (" + rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                        }
//                                    }
//                                    if (pt.cucbo > 0) {
//                                        if (e.option == 0){//là sâu hại
//                                            if(pt.dvt==0)
//                                                strDTGC += ", cục bộ " + func.format_numeric(pt.cucbo) + " (" + rf.getDVTCaylua(pt.dvt) + ")" + "(" + pt.phanbo_cucbo + ")";
//                                            else
//                                                strDTGC += ", cục bộ " + func.format_numeric(pt.cucbo) + "% (" + rf.getDVTCaylua(pt.dvt) + ")" + "(" + pt.phanbo_cucbo + ")";
//                                        }else if (e.option == 1){ //là bệnh hại
//                                            strDTGC += ", cục bộ " + func.format_numeric(pt.cucbo) + "% (" + rf.getDVTBenhCaylua(pt.dvt) + ")" + "(" + pt.phanbo_cucbo + ")";
//                                        }
//                                    }
//                                }
//                                strDTGC += ". ";
//                            }
//                            strDTGC += "<br>";
//                        }
//                    }
//                    strDTGC += "<br>";
//                }
//                v.addTextHTML(strDTGC, designHandle);
//            }
//            v.addLabel("2. Trên cây rau", "label1", designHandle);
//            for (i = 0; i < data_raumau.size(); i++) {
//                Class_Raumau e = data_raumau.get(i);
//                if (e.listDichhai == null)
//                    continue;
//                String str = "   * " + e.name_group + ":";
//                for (j = 0; j < e.listDichhai.size(); j++) {
//                    Class_Dichhai_Raumau obj = e.listDichhai.get(j);
//                    if (obj.tongdtnhiem == 0) {
//                        str += obj.name_obj + " không gây diện tích nhiễm";
//                    } else {
//                        str += obj.name_obj + " gây hại " + func.format_numeric(obj.tongdtnhiem) + " (ha)";
//                    }
//                    if (!obj.tuoiPhobien.isEmpty()) {
//                        if (obj.option == 0)
//                            str += ", tuổi phổ biến: " + obj.tuoiPhobien;
//                        else
//                            str += ", cấp bệnh phổ biến: " + obj.tuoiPhobien;
//                    }
//                    if (obj.listMatdo == null) {
//                        str = str + ", không có số liệu mật độ/tỷ lệ. ";
//                    } else {
//                        for (k = 0; k < obj.listMatdo.size(); k++) {
//                            Class_Matdo_Tylehai_Raumau pt = obj.listMatdo.get(k);
//                            if (obj.option == 0){//là sâu hại
//                                if(pt.dvt==0)
//                                    str += ", " + rf.getDienGiaiDVTCayrau(pt.dvt)
//                                            + " phổ biến " + func.format_numeric(pt.phobien1) + " ("
//                                            + rf.getDVTCayrau(pt.dvt) + ")";
//                                else
//                                    str += ", " + rf.getDienGiaiDVTCayrau(pt.dvt)
//                                            + " phổ biến " + func.format_numeric(pt.phobien1) + "% ("
//                                            + rf.getDVTCayrau(pt.dvt) + ")";
//                            }else if (obj.option == 1){//là bệnh hại
//                                str += ", " + rf.getDienGiaiDVTBenhCayrau(pt.dvt)
//                                        + " phổ biến " + func.format_numeric(pt.phobien1) + "% ("
//                                        + rf.getDVTBenhCayrau(pt.dvt) + ")";
//                            }
//                            if (pt.cao > 0) {
//                                if (obj.option == 0){//là sâu hại
//                                    if(pt.dvt==0)
//                                        str += ", cao " + func.format_numeric(pt.cao) + " (" + rf.getDVTCayrau(pt.dvt) + ")";
//                                    else
//                                        str += ", cao " + func.format_numeric(pt.cao) + "% (" + rf.getDVTCayrau(pt.dvt) + ")";
//                                }else if (obj.option == 1){//là bệnh hại
//                                    str += ", cao " + func.format_numeric(pt.cao) + "% (" + rf.getDVTBenhCayrau(pt.dvt) + ")";
//                                }
//                            }
//                            if (pt.cucbo > 0) {
//                                if (obj.option == 0){//là sâu hại
//                                    if(pt.dvt==0)
//                                        str += ", cục bộ " + func.format_numeric(pt.cucbo) + " ("
//                                                + rf.getDVTCayrau(pt.dvt) + ")"
//                                                + "(" + pt.phanbo_cucbo + ")";
//                                    else
//                                        str += ", cục bộ " + func.format_numeric(pt.cucbo) + "% ("
//                                                + rf.getDVTCayrau(pt.dvt) + ")"
//                                                + "(" + pt.phanbo_cucbo + ")";
//                                }else if (obj.option == 1){//là bệnh hại
//                                    str += ", cục bộ " + func.format_numeric(pt.cucbo) + "% ("
//                                            + rf.getDVTBenhCayrau(pt.dvt) + ")"
//                                            + "(" + pt.phanbo_cucbo + ")";
//                                }
//                            }
//                        }
//                        str = str + ". ";
//                    }
//                }
//                v.addLabel(str, "text1", designHandle);
//            }
//            v.addLabel("", designHandle);
//            v.addLabel("3. Cây trồng khác", "label1", designHandle);
//            v.addLabel("", designHandle);
//            v.addLabel("", designHandle);
//            v.addLabel("", designHandle);
//            // DU KIEN DICH HAI
//            v.addLabel("III. DỰ KIẾN TÌNH HÌNH DỊCH HẠI TRONG THỜI GIAN TỚI",
//                    "label1", designHandle);
//            if (arrDubaoSaubenh.length > 0
//                    && !arrDubaoSaubenh[0].equalsIgnoreCase("null")) {
//                v.addLabel("1. Nhận xét của hệ thống", "label1", designHandle);
//                for (l = 0; l < arrDubaoSaubenh.length; l++) {
//                    int cs = Integer.parseInt(arrDubaoSaubenh[l]);
//                    if (cs == -1)
//                        continue;
//                    switch (cs) {
//                        case 0:// Rầy nâu
//                        {
//                            v.addLabel("*) Tình hình rầy nâu hại lúa", "label1", designHandle);
//                            DubaoSauhaiCaylua db = new DubaoSauhaiCaylua(ngaybd+6, String.valueOf(matinh), 1);
//                            db.dubao(connection);
//                            v.addTextHTML(db.getChanDoanHTML(), designHandle);
//                            break;
//                        }
//                        case 1:// Sâu cuốn lá nhỏ
//                        {
//                            v.addLabel("*) Tình hình sâu cuốn lá nhỏ hại lúa", "label1", designHandle);
//                            DubaoSauhaiCaylua db = new DubaoSauhaiCaylua(ngaybd+6, String.valueOf(matinh), 2);
//                            db.dubao(connection);
//                            v.addTextHTML(db.getChanDoanHTML(), designHandle);
//                            break;
//                        }
//                        case 2:// Sâu đục thân bướm hai chấm
//                        {
//                            v.addLabel("*) Tình hình sâu đục thân hai chấm hại lúa", "label1", designHandle);
//                            DubaoSauhaiCaylua db = new DubaoSauhaiCaylua(ngaybd+6, String.valueOf(matinh), 3);
//                            db.dubao(connection);
//                            v.addTextHTML(db.getChanDoanHTML(), designHandle);
//                            break;
//                        }
//                        case 3:// Bệnh đạo ôn lá
//                        {
//                            v.addLabel("*) Tình hình bệnh đạo ôn lá hại lúa", "label1", designHandle);
//                            DubaoBenhhaiCaylua db = new DubaoBenhhaiCaylua(ngaybd+6, String.valueOf(matinh), 1);
//                            db.dubao(connection);
//                            v.addTextHTML(db.getChanDoanHTML(), designHandle);
//                            break;
//                        }
//                        case 4:// Bệnh đạo ôn cổ bông
//                        {
//                            v.addLabel("*) Tình hình bệnh đạo ôn cổ bông hại lúa", "label1", designHandle);
//                            DubaoBenhhaiCaylua db = new DubaoBenhhaiCaylua(ngaybd+6, String.valueOf(matinh), 2);
//                            db.dubao(connection);
//                            v.addTextHTML(db.getChanDoanHTML(), designHandle);
//                            break;
//                        }
//                        case 5:// Bệnh khô vằn
//                        {
//                            v.addLabel("*) Tình hình bệnh khô vằn hại lúa", "label1", designHandle);
//                            DubaoBenhhaiCaylua db = new DubaoBenhhaiCaylua(ngaybd+6, String.valueOf(matinh), 3);
//                            db.dubao(connection);
//                            v.addTextHTML(db.getChanDoanHTML(), designHandle);
//                            break;
//                        }
//                        case 6:// Bệnh bạc lá
//                        {
//                            v.addLabel("*) Tình hình bệnh bạc lá hại lúa", "label1", designHandle);
//                            DubaoBenhhaiCaylua db = new DubaoBenhhaiCaylua(ngaybd+6, String.valueOf(matinh), 4);
//                            db.dubao(connection);
//                            v.addTextHTML(db.getChanDoanHTML(), designHandle);
//                            break;
//                        }
//                    }
//                }
//                v.addLabel("2. Nhận xét của chuyên gia BVTV", "label1",
//                        designHandle);
//            }
//            v.addLabel("", designHandle);
//            v.addLabel("", designHandle);
//            v.addLabel("", designHandle);
//            // DE NGHI BIEN PHAP XU LY
//            v.addLabel(
//                    "IV. ĐỀ NGHỊ BIỆN PHÁP XỬ LÝ HOẶC CÁC CHỦ TRƯƠNG CẦN TRIỂN KHAI THỰC HIỆN Ở ĐỊA PHƯƠNG",
//                    "label1", designHandle);
//            v.addLabel("", designHandle);
//            v.addLabel("", designHandle);
//            v.addLabel("", designHandle);
//            addTableFooter(designHandle);
//            // DIEN TÍCH, MAT DO
//            List<List<String>> data_phanbo = new ArrayList<List<String>>();
//            // Bổ sung số liệu điều tra cây lúa
//            if(data_caylua != null){
//                for (i = 0; i < data_caylua.listGionglua.size(); i++) {
//                    Class_Gionglua_Caylua e = data_caylua.listGionglua.get(i);
//                    if (e.listDichhai == null)
//                        continue;
//                    for (j = 0; j < e.listDichhai.size(); j++) {
//                        Class_Dichhai_Caylua obj = e.listDichhai.get(j);
//                        if (obj.tongdtnhiem == 0) {
//                            continue;
//                        }
//                        List<String> dtmdpb = new ArrayList<String>();
//                        dtmdpb.add(obj.name_obj);// tên sinh vật gây hại
//                        if(e.id_gdst_gionglua==-1)
//                            dtmdpb.add(data_caylua.name_vulua);// giai đoạn sinh trưởng cây trồng
//                        else
//                            dtmdpb.add(data_caylua.name_vulua + ", " + e.gdst_gionglua);// giai đoạn sinh trưởng cây trồng
//                        if (obj.listMatdo == null) {
//                            dtmdpb.add("");// mật độ/tỷ lệ phổ biến
//                            dtmdpb.add("");// mật độ/tỷ lệ cao
//                        } else {
//                            String str_phobien = "";
//                            String str_cao = "";
//                            for (k = 0; k < obj.listMatdo.size(); k++) {
//                                Class_Matdo_Tylehai_Caylua pt = obj.listMatdo.get(k);
//                                if (pt.phobien1 == pt.phobien2) {
//                                    if (k == 0) {
//                                        if (obj.option == 0){ //là sâu hại
//                                            if(pt.dvt==0)
//                                                str_phobien = func.format_numeric(pt.phobien1) + " (" + rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                str_phobien = func.format_numeric(pt.phobien1) + "% (" + rf.getDVTCaylua(pt.dvt) + ")";
//                                        }else if (obj.option == 1){ //là bệnh hại
//                                            str_phobien = func.format_numeric(pt.phobien1) + "% (" + rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                        }
//                                    } else {
//                                        if (obj.option == 0){//là sâu hại
//                                            if(pt.dvt==0)
//                                                str_phobien = str_phobien + ","
//                                                        + func.format_numeric(pt.phobien1) + " ("
//                                                        + rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                str_phobien = str_phobien + ","
//                                                        + func.format_numeric(pt.phobien1) + "% ("
//                                                        + rf.getDVTCaylua(pt.dvt) + ")";
//                                        }else if (obj.option == 1){ //là bệnh hại
//                                            str_phobien = str_phobien + ","
//                                                    + func.format_numeric(pt.phobien1) + "% ("
//                                                    + rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                        }
//                                    }
//                                } else if (pt.phobien1 < pt.phobien2) {
//                                    if (k == 0) {
//                                        if (obj.option == 0){//là sâu hại
//                                            if(pt.dvt==0)
//                                                str_phobien = func.format_numeric(pt.phobien1) + "-"
//                                                        + func.format_numeric(pt.phobien2) + " ("
//                                                        + rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                str_phobien = func.format_numeric(pt.phobien1) + "-"
//                                                        + func.format_numeric(pt.phobien2) + "% ("
//                                                        + rf.getDVTCaylua(pt.dvt) + ")";
//                                        }else if (obj.option == 1){ //là bệnh hại
//                                            str_phobien = func.format_numeric(pt.phobien1) + "-" + func.format_numeric(pt.phobien2) + "% ("
//                                                    + rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                        }
//                                    } else {
//                                        if (obj.option == 0){//là sâu hại
//                                            if(pt.dvt==0)
//                                                str_phobien = str_phobien + ","
//                                                        + func.format_numeric(pt.phobien1) + "-"
//                                                        + func.format_numeric(pt.phobien2) + " ("
//                                                        + rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                str_phobien = str_phobien + ","
//                                                        + func.format_numeric(pt.phobien1) + "-"
//                                                        + func.format_numeric(pt.phobien2) + "% ("
//                                                        + rf.getDVTCaylua(pt.dvt) + ")";
//                                        }else if (obj.option == 1){ //là bệnh hại
//                                            str_phobien = str_phobien + ","
//                                                    + func.format_numeric(pt.phobien1) + "-"
//                                                    + func.format_numeric(pt.phobien2) + "% ("
//                                                    + rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                        }
//                                    }
//                                }
//                                if (pt.cao > 0) {
//                                    if (obj.option == 0){//là sâu hại
//                                        if(pt.dvt==0){
//                                            if(str_cao.trim()=="")
//                                                str_cao = func.format_numeric(pt.cao) + " ("+ rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                str_cao = str_cao + "," + func.format_numeric(pt.cao) + " ("+ rf.getDVTCaylua(pt.dvt) + ")";
//                                        }else{
//                                            if(str_cao.trim()=="")
//                                                str_cao = func.format_numeric(pt.cao) + "% ("+ rf.getDVTCaylua(pt.dvt) + ")";
//                                            else
//                                                str_cao = str_cao + "," + func.format_numeric(pt.cao) + "% ("+ rf.getDVTCaylua(pt.dvt) + ")";
//                                        }
//                                    }else if (obj.option == 1){ //là bệnh hại
//                                        if(str_cao.trim()=="")
//                                            str_cao = func.format_numeric(pt.cao) + "% ("+ rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                        else
//                                            str_cao = str_cao + "," + func.format_numeric(pt.cao) + "% ("+ rf.getDVTBenhCaylua(pt.dvt) + ")";
//                                    }
//                                }
//                            }
//                            dtmdpb.add(str_phobien);// mật độ/tỷ lệ phổ biến
//                            dtmdpb.add(str_cao);// mật độ/tỷ lệ cao
//                        }
//                        // tổng diện tích nhiễm (ha)
//                        dtmdpb.add(func.format_numeric(obj.tongdtnhiem));
//                        // diện tích nhiễm nhẹ (ha)
//                        dtmdpb.add(func.format_numeric(obj.dtnhiemnhe));
//                        // diện tích nhiễm trung bình (ha)
//                        dtmdpb.add(func.format_numeric(obj.dtnhiemtb));
//                        // diện tích nhiễm nặng (ha)
//                        dtmdpb.add(func.format_numeric(obj.dtnhiemnang));
//                        // diện tích mất trắng (ha)
//                        dtmdpb.add(func.format_numeric(obj.dtmattrang));
//                        dtmdpb.add("");// diện tích nhiễm cùng kỳ năm trước
//                        // diện tích phòng trừ (ha)
//                        dtmdpb.add(func.format_numeric(obj.dtphongtru));
//                        dtmdpb.add(obj.phanbo_dientichnhiem);// phân bố theo diện tích nhiễm
//                        data_phanbo.add(dtmdpb);
//                    }
//                }
//            }
//            // Bổ sung số liệu của rau màu
//            for (i = 0; i < data_raumau.size(); i++) {
//                Class_Raumau e = data_raumau.get(i);
//                if (e.listDichhai == null)
//                    continue;
//                for (j = 0; j < e.listDichhai.size(); j++) {
//                    Class_Dichhai_Raumau obj = e.listDichhai.get(j);
//                    if (obj.tongdtnhiem == 0) {
//                        continue;
//                    }
//                    List<String> dtmdpb = new ArrayList<String>();
//                    dtmdpb.add(obj.name_obj);// tên sinh vật gây hại
//                    dtmdpb.add(e.gdst_group);// giai đoạn sinh trưởng cây trồng
//                    if (obj.listMatdo == null) {
//                        dtmdpb.add("");// mật độ/tỷ lệ phổ biến
//                        dtmdpb.add("");// mật độ/tỷ lệ cao
//                    } else {
//                        String str_phobien = "";
//                        String str_cao = "";
//                        for (k = 0; k < obj.listMatdo.size(); k++) {
//                            Class_Matdo_Tylehai_Raumau pt = obj.listMatdo.get(k);
//                            if (k == 0) {
//                                if (obj.option == 0){//là sâu hại
//                                    if(pt.dvt==0)
//                                        str_phobien = func.format_numeric(pt.phobien1) + " (" + rf.getDVTCayrau(pt.dvt) + ")";
//                                    else
//                                        str_phobien = func.format_numeric(pt.phobien1) + "% (" + rf.getDVTCayrau(pt.dvt) + ")";
//                                }else if (obj.option == 1){//là bệnh hại
//                                    str_phobien = func.format_numeric(pt.phobien1) + "% (" + rf.getDVTBenhCayrau(pt.dvt) + ")";
//                                }
//                            } else {
//                                if (obj.option == 0){//là sâu hại
//                                    if(pt.dvt==0)
//                                        str_phobien = str_phobien + "," + func.format_numeric(pt.phobien1) + " (" + rf.getDVTCayrau(pt.dvt) + ")";
//                                    else
//                                        str_phobien = str_phobien + "," + func.format_numeric(pt.phobien1) + "% (" + rf.getDVTCayrau(pt.dvt) + ")";
//                                }else if (obj.option == 1){//là bệnh hại
//                                    str_phobien = str_phobien + "," + func.format_numeric(pt.phobien1) + "% (" + rf.getDVTBenhCayrau(pt.dvt) + ")";
//                                }
//                            }
//                            if (pt.cao > 0) {
//                                if (obj.option == 0){//là sâu hại
//                                    if(pt.dvt==0){
//                                        if(str_cao.trim()=="")
//                                            str_cao = func.format_numeric(pt.cao) + " ("+ rf.getDVTCayrau(pt.dvt) + ")";
//                                        else
//                                            str_cao = str_cao + "," + func.format_numeric(pt.cao) + " ("+ rf.getDVTCayrau(pt.dvt) + ")";
//                                    }else{
//                                        if(str_cao.trim()=="")
//                                            str_cao = func.format_numeric(pt.cao) + "% ("+ rf.getDVTCayrau(pt.dvt) + ")";
//                                        else
//                                            str_cao = str_cao + "," + func.format_numeric(pt.cao) + "% ("+ rf.getDVTCayrau(pt.dvt) + ")";
//                                    }
//                                }else if (obj.option == 1){//là bệnh hại
//                                    if(str_cao.trim()=="")
//                                        str_cao = func.format_numeric(pt.cao) + "% ("+ rf.getDVTBenhCayrau(pt.dvt) + ")";
//                                    else
//                                        str_cao = str_cao + "," + func.format_numeric(pt.cao) + "% ("+ rf.getDVTBenhCayrau(pt.dvt) + ")";
//                                }
//                            }
//                        }
//                        dtmdpb.add(str_phobien);// mật độ/tỷ lệ phổ biến
//                        dtmdpb.add(str_cao);// mật độ/tỷ lệ cao
//                    }
//                    // tổng diện tích nhiễm (ha)
//                    dtmdpb.add(func.format_numeric(obj.tongdtnhiem));
//                    // diện tích nhiễm nhẹ (ha)
//                    dtmdpb.add(func.format_numeric(obj.dtnhiemnhe));
//                    // diện tích nhiễm trung bình (ha)
//                    dtmdpb.add(func.format_numeric(obj.dtnhiemtb));
//                    // diện tích nhiễm nặng (ha)
//                    dtmdpb.add(func.format_numeric(obj.dtnhiemnang));
//                    // diện tích mất trắng (ha)
//                    dtmdpb.add(func.format_numeric(obj.dtmattrang));
//                    dtmdpb.add("");// diện tích nhiễm cùng kỳ năm trước
//                    // diện tích phòng trừ (ha)
//                    dtmdpb.add(func.format_numeric(obj.dtphongtru));
//                    dtmdpb.add(obj.phanbo_dientichnhiem);// phân bố theo diện tích nhiễm
//                    data_phanbo.add(dtmdpb);
//                }
//            }
//            if(data_phanbo.size()>0){
//                v.addLabel("", designHandle).setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP, "always");
//                v.addLabel("DIỆN TÍCH, MẬT ĐỘ VÀ PHÂN BỐ", "label1", designHandle)
//                        .setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//                v.addLabel("MỘT SỐ ĐỐI TƯỢNG SINH VẬT GÂY HẠI", "label1",
//                        designHandle).setProperty(StyleHandle.TEXT_ALIGN_PROP,
//                        "center");
//                v.addLabel("", designHandle);
//            }
//            String clrHeader = "#E1FFFF";
//            String clrTextHeader = "#000000";
//            String clrEven = "#F5FFFA";
//            String clrOdd = "#FFFFFF";
//            List<String> width = new ArrayList<String>();
//            List<String> align = new ArrayList<String>();
//
//
//
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
//            align.add("left");
//            align.add("left");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("right");
//            align.add("left");
//            getTablePhanbo(data_phanbo, width, align, clrHeader, clrTextHeader,
//                    clrEven, clrOdd, designHandle, formatReport);// .setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP,
//            // "always");
//            v.addLabel("", designHandle);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//    }
//
//    /**
//     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//     *      response)
//     */
//    protected void doGet(HttpServletRequest request,
//                         HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        try {
//            processRequest(request, response);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//     *      response)
//     */
//    protected void doPost(HttpServletRequest request,
//                          HttpServletResponse response) throws ServletException, IOException {
//        // TODO Auto-generated method stub
//        try {
//            processRequest(request, response);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    protected void addTableHeader(String SoNN, String title,
//                                  ReportDesignHandle design) {
//        try {
//            GridHandle table = design.getElementFactory().newGridItem("Table",
//                    2, 3);
//            table.setWidth("105%");
//
//            table.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "none");
//            table.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP, "none");
//            table.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "none");
//            table.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP, "none");
//
//            table.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "0px");
//            table.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP, "0px");
//            table.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP, "0px");
//            table.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP, "0px");
//
//            // set width of column
//            ((ColumnHandle) table.getColumns().get(0)).getWidth().setValue(
//                    "45%");
//            ((ColumnHandle) table.getColumns().get(1)).getWidth().setValue(
//                    "60%");
//
//            TextItemHandle text;
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent(SoNN);
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "11pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("CỘNG HÒA XÃ HỘI CHỦ NGHĨA VIỆT NAM");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("CHI CỤC BẢO VỆ THỰC VẬT");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Độc lập - Tự do - Hạnh phúc");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Số:     /BVTV");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent(title);
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_STYLE_PROP,
//                    DesignChoiceConstants.FONT_STYLE_ITALIC);
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "14pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(1)).getContent().add(text);
//
//            design.getBody().add(table);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    // add footer
//    protected void addTableFooter(ReportDesignHandle design) {
//        try {
//            GridHandle table = design.getElementFactory().newGridItem("", 2, 4);
//            table.setWidth("100%");
//
//            table.setProperty(StyleHandle.BORDER_BOTTOM_STYLE_PROP, "none");
//            table.setProperty(StyleHandle.BORDER_TOP_STYLE_PROP, "none");
//            table.setProperty(StyleHandle.BORDER_LEFT_STYLE_PROP, "none");
//            table.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP, "none");
//
//            table.setProperty(StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "0px");
//            table.setProperty(StyleHandle.BORDER_TOP_WIDTH_PROP, "0px");
//            table.setProperty(StyleHandle.BORDER_LEFT_WIDTH_PROP, "0px");
//            table.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP, "0px");
//
//            // set width of column
//            ((ColumnHandle) table.getColumns().get(0)).getWidth().setValue(
//                    "50%");
//            ((ColumnHandle) table.getColumns().get(1)).getWidth().setValue(
//                    "50%");
//
//            TextItemHandle text;
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Nơi nhận:");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_STYLE_PROP,
//                    DesignChoiceConstants.FONT_STYLE_ITALIC);
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(0)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("- Như trên;");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(0)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("- Lưu VT.");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "left");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_NORMAL);
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(0)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("THỦ TRƯỞNG ĐƠN VỊ");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_BOLD);
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(0)).getCells()
//                    .get(1)).getContent().add(text);
//
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("(Ký tên, đóng dấu)");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_STYLE_PROP,
//                    DesignChoiceConstants.FONT_STYLE_ITALIC);
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
//                    "Times New Roman");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");
//            ((CellHandle) ((RowHandle) table.getRows().get(1)).getCells()
//                    .get(1)).getContent().add(text);
//
//            design.getBody().add(table);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /*
//     * dien tich, mat do va phan bo
//     */
//    public GridHandle getTablePhanbo(List<List<String>> data,
//                                     List<String> width, List<String> alignment, String clrHeader,
//                                     String clrTextHeader, String clrEven, String clrOdd,
//                                     ReportDesignHandle design, String formatReport) {
//        GridHandle table = null;
//        try {
//            if(data.size()==0) return table;
//            table = design.getElementFactory().newGridItem("", 12,
//                    data.size() + 2);
//            if (formatReport.equalsIgnoreCase("doc")) {
//                table.setWidth("104%");
//            } else {
//                table.setWidth("100%");
//            }
//
//            // set width of column
//            for (int c = 0; c < width.size(); c++) {
//                ((ColumnHandle) table.getColumns().get(c)).getWidth().setValue(
//                        width.get(c));
//            }
//
//            for (int i = 0; i <= 1; i++) {
//                for (int j = 0; j < 12; j++) {
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.TEXT_ALIGN_PROP, "center");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.FONT_WEIGHT_PROP,
//                            DesignChoiceConstants.FONT_WEIGHT_BOLD);
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.FONT_SIZE_PROP, "10pt");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BACKGROUND_COLOR_PROP, clrHeader);
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.COLOR_PROP, clrTextHeader);
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_TOP_STYLE_PROP, "solid");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "1px");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_TOP_WIDTH_PROP, "1px");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_LEFT_WIDTH_PROP, "1px");
//                }
//            }
//            // //////Hòa các hàng của tiêu đề
//            TextItemHandle text;
//            CellHandle cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(0));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Tên sinh vật gây hại");
//            cell1.getContent().add(text);
//            cell1.setRowSpan(2);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(0))
//                    .getCells().get(1));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("GĐST cây trồng");
//            cell1.getContent().add(text);
//            cell1.setRowSpan(2);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(0))
//                    .getCells().get(2));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_HTML);
//            text.setContent("Tỷ lệ (%, con/m<sup>2</sup>)");
//            cell1.getContent().add(text);
//            cell1.setColumnSpan(2);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(0))
//                    .getCells().get(3));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Diện tích nhiễm (ha)");
//            cell1.getContent().add(text);
//            cell1.setColumnSpan(5);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(0))
//                    .getCells().get(4));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("DTN cùng kỳ năm trước");
//            cell1.getContent().add(text);
//            cell1.setRowSpan(2);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(0))
//                    .getCells().get(5));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("DT phòng trừ(ha)");
//            cell1.getContent().add(text);
//            cell1.setRowSpan(2);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(0))
//                    .getCells().get(6));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Phân bố");
//            cell1.getContent().add(text);
//            cell1.setRowSpan(2);
//            cell1.setProperty(StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
//            cell1.setProperty(StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
//
//            // ///////////nội dung của các ô tiêu đề khác
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(1))
//                    .getCells().get(0));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Phổ biến");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(1))
//                    .getCells().get(1));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Cao");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(1))
//                    .getCells().get(2));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Tổng");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(1))
//                    .getCells().get(3));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Nhẹ");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(1))
//                    .getCells().get(4));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("TB");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(1))
//                    .getCells().get(5));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Nặng");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(1))
//                    .getCells().get(6));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Mất trắng");
//            cell1.getContent().add(text);
//
//            // xoa cac o
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
//
//            for (int i = 0; i < data.size(); i++) {
//                for (int j = 0; j < data.get(i).size(); j++) {
//                    text = design.getElementFactory().newTextItem("");
//                    text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//                    text.setContent(data.get(i).get(j));
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_TOP_STYLE_PROP, "solid");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "1px");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_TOP_WIDTH_PROP, "1px");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_LEFT_WIDTH_PROP, "1px");
//                    if (j == data.get(i).size() - 1) {
//                        ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
//                        ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
//                    }
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.FONT_SIZE_PROP, "10pt");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.TEXT_ALIGN_PROP, alignment.get(j));
//
//                    if (i % 2 == 0) {
//                        ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BACKGROUND_COLOR_PROP, clrOdd);
//                    } else {
//                        ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BACKGROUND_COLOR_PROP, clrEven);
//                    }
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 2))
//                            .getCells().get(j)).getContent().add(text);
//                }
//            }
//            design.getBody().add(table);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return table;
//    }
//}
