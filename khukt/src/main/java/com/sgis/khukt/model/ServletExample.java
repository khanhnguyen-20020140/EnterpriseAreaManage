package com.sgis.khukt.model;

import com.sgis.khukt.repository.TblDoanhnghiepRepository;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;

public class ServletExample  extends HttpServlet {

    static final String URL = "jdbc:postgresql://localhost:5434/khukt_dongnam_20221223?useUnicode=yes&characterEncoding=UTF-8";

    static final String USER = "postgres";

    static final String PASS = "11";

    static final String QUERY = "SELECT id, ten, giamdoc, diachi,quocgia,ma_dangky,  ngay_thanhlap,masothue,  linhvuc_kinhdoanh,  \n" +
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

            System.out.println(rs.getString(2)
                    + "  " + rs.getString(3));
        }
        // close connection
        conn.close();
        return listDoanhnghiep;

    }
    protected void addTableHeader(String SoNN, String title,
                                  ReportDesignHandle designHandle,common v) {
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



            TextItemHandle text;
            text = designHandle.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("…………….., ngày …. tháng …. năm …………");
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
                    StyleHandle.MARGIN_TOP_PROP, "50px");
            label.setProperty(StyleHandle.FONT_WEIGHT_PROP,
                    DesignChoiceConstants.FONT_WEIGHT_BOLD);


            designHandle.getBody().add(label);


            label_content = "BẢNG THỐNG KÊ CÁC DOANH NGHIỆP KINH DOANH TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN";
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

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public GridHandle getTablePhanbo(List<List<String>> data,
                                     List<String> width, List<String> alignment, String clrHeader,
                                     String clrTextHeader, String clrEven, String clrOdd,
                                     ReportDesignHandle design) {
        GridHandle table = null;
        try {
            if (data.size() == 0) return table;
            table = design.getElementFactory().newGridItem("", 12,
                    data.size() + 2);
            table.setWidth("100%");

            // set width of column
            for (int c = 0; c < width.size(); c++) {
                ((ColumnHandle) table.getColumns().get(c)).getWidth().setValue(
                        width.get(c));
            }


            for (int i = 0; i < 1; i++) {
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
                    if (j == 9) {
                        ((CellHandle) ((RowHandle) table.getRows().get(i))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
                        ((CellHandle) ((RowHandle) table.getRows().get(i))
                                .getCells().get(j)).setProperty(
                                StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
                    }
                }
            }


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
            text.setContent("Gíam đốc");

            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(2));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Địa chỉ");

            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(3));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Quốc gia");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(4));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Mã đăng ký");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(5));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Ngày thành lập");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(6));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);

            text.setContent("Mã số thuế");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(7));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Lĩnh vực kinh doanh");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(8));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Lĩnh vực sản xuất");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(9));

            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Loại doanh nghiệp");
            cell1.getContent().add(text);



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

    public void buildReport(ReportDesignHandle designHandle) {
        Connection connection = null;
        try {
            int i, j, k, l;
//            connection = getConnection();
            common v = new common();
            v.setFontReport("Times New Roman", "13pt", designHandle);
//            v.setAlignReport("justify", designHandle);
            v.setMarginReport("A4", "landscape", "20px", "20px", "0px", "0px", designHandle);
            //***Trình tự1
            //addTableHeader
            addTableHeader("Hà Nội", "", designHandle,v);
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

            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
                    "dd/MM/yyyy");


            String clrHeader = "#E1FFFF";
            String clrTextHeader = "#000000";
            String clrEven = "#F5FFFA";
            String clrOdd = "#FFFFFF";
            List<String> width = new ArrayList<String>();
            List<String> align = new ArrayList<String>();

            width.add("12%");
            width.add("9%");
            width.add("13%");
            width.add("9%");
            width.add("7%");
            width.add("9%");
            width.add("7%");
            width.add("14%");
            width.add("10%");
            width.add("10%");


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


            List<List<String>> data_phanbo = getDataDoanhNghiep();

            //***Trình tự3
            getTablePhanbo(data_phanbo, width, align, clrHeader, clrTextHeader,
                    clrEven, clrOdd, designHandle);// .setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP,
            // "always");
            //***Trình tự4


        } catch (Exception e) {
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
            throws ServletException, IOException{
        System.out.println("print");
        res.setContentType("text/html; charset=UTF-8");
        res.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        IReportEngine engine = null;
        EngineConfig config = null;

        try {
            System.out.println("print");
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

            buildReport(report);

            IRunAndRenderTask task = engine.createRunAndRenderTask(design);

            IRenderOption options = new RenderOption();
            options.setOutputStream(new ByteArrayOutputStream());

            PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
            ServletContext servletContext = getServletContext();  ;

            String path = servletContext.getRealPath("/");
            options.setOutputFormat("pdf");
            path = path +"reports/doanhnghiep.pdf";
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


    public void Render(HttpServletResponse response,String format) throws IOException, ServletException, IOException{
        IReportEngine engine = null;
        EngineConfig config = null;
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



            buildReport(report);
            // create task to run and render report
            IRunAndRenderTask task = engine.createRunAndRenderTask(design);

            EXCELRenderOption options = new EXCELRenderOption();
            options.setOutputStream(new ByteArrayOutputStream());


            options.setOutputFormat(format);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            options.setOutputStream(baos);
            task.setRenderOption(options);
            // run report
            task.run();
            task.close();

            ServletOutputStream sos = response.getOutputStream();
            try {
                baos.writeTo(sos);
                baos.close();
                sos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }


            // Destroy the engine and shutdown the Platform
            // Note - If the program stays resident do not shutdown the Platform
            // or the Engine
            // engine.shutdown();
            // Platform.shutdown();
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
