//package com.sgis.khukt.controller;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//import java.util.logging.Level;
//
//import com.sgis.khukt.model.TblDuanXaydunghatang;
//import com.sgis.khukt.model.TblDuanXaydunghatang;
//import com.sgis.khukt.model.common;
//import org.eclipse.birt.core.framework.Platform;
//import org.eclipse.birt.report.engine.api.*;
//import org.eclipse.birt.report.model.api.*;
//import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
//import org.eclipse.core.internal.registry.RegistryProviderFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Sort;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sgis.khukt.repository.TblDuanXaydunghatangRepository;
//
//import javax.servlet.ServletException;
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
//@RestController
//@RequestMapping("/duanXaydunghatang")
//public class TblDuanXaydunghatangController {
//    @Autowired
//    TblDuanXaydunghatangRepository duanXaydunghatangRepository;
//
//    @GetMapping("/preduanxaydunghatang/{id}")
//    public Integer preduanxaydunghatang(@PathVariable("id") Integer id) {
//        List<TblDuanXaydunghatang> TblDuanXaydunghatang = new ArrayList<TblDuanXaydunghatang>();
//
//        Integer preId = -1;
//        TblDuanXaydunghatang = duanXaydunghatangRepository.findAll(Sort.by("ten"));
//        for (int i = 0; i < TblDuanXaydunghatang.size(); i++) {
//            if (TblDuanXaydunghatang.get(i).getId().equals(id)) {
//                break;
//            } else {
//                preId = TblDuanXaydunghatang.get(i).getId();
//            }
//
//        }
//        return preId;
//    }
//
//    @GetMapping("/duanXaydunghatangs")
//    public ResponseEntity<List<TblDuanXaydunghatang>> getAllTblDuanXaydunghatangs(@RequestParam(required = false) String ten) {
//        try {
//            List<TblDuanXaydunghatang> duanXaydunghatang = new ArrayList<TblDuanXaydunghatang>();
//
//            if (ten == null) {
//                duanXaydunghatangRepository.findAll(Sort.by("ten")).forEach(duanXaydunghatang::add);
//            } else {
//                duanXaydunghatangRepository.findByTen(ten).forEach(duanXaydunghatang::add);
//            }
//
//            if (duanXaydunghatang.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(duanXaydunghatang, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/duanXaydunghatang/{id}")
//    public ResponseEntity<TblDuanXaydunghatang> getTblDuanXaydunghatangById(@PathVariable("id") Integer id) {
//        Optional<TblDuanXaydunghatang> duanXaydunghatangData = duanXaydunghatangRepository.findById(id);
//
//        if (duanXaydunghatangData.isPresent()) {
//            return new ResponseEntity<>(duanXaydunghatangData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping("/duanXaydunghatang")
//    public ResponseEntity<TblDuanXaydunghatang> createTblDuanXaydunghatang(@RequestBody TblDuanXaydunghatang duanXaydunghatang) {
//        try {
//            duanXaydunghatangRepository
//                    .save(duanXaydunghatang);
//            return new ResponseEntity<>(duanXaydunghatang, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/duanXaydunghatang/{id}")
//    public ResponseEntity<TblDuanXaydunghatang> updateTblDuanXaydunghatang(@PathVariable("id") Integer id, @RequestBody TblDuanXaydunghatang duanXaydunghatang) {
//        Optional<TblDuanXaydunghatang> duanXaydunghatangData = duanXaydunghatangRepository.findById(id);
//
//        if (duanXaydunghatangData.isPresent()) {
//            System.out.println(duanXaydunghatang.getIdKhuChuyennganh());
//            System.out.println(duanXaydunghatang.getIdNguonvon());
//
//            System.out.println(duanXaydunghatang.getIdDoanhnghiep());
//            TblDuanXaydunghatang _duanXaydunghatang = duanXaydunghatangData.get();
//
//            if(duanXaydunghatang.getTen() != null) _duanXaydunghatang.setTen(duanXaydunghatang.getTen());
//            else _duanXaydunghatang.setTen("");
//
//            _duanXaydunghatang.setIdDoanhnghiep(duanXaydunghatang.getIdDoanhnghiep());
//            if(duanXaydunghatang.getSoqdCapphep() != null) _duanXaydunghatang.setSoqdCapphep(duanXaydunghatang.getSoqdCapphep());
//            else _duanXaydunghatang.setSoqdCapphep("");
//            _duanXaydunghatang.setVondautuDangky(duanXaydunghatang.getVondautuDangky());
//
//            if(duanXaydunghatang.getIdNguonvon() == null || duanXaydunghatang.getIdNguonvon().getId() == -1)
//            {
//                _duanXaydunghatang.setIdNguonvon(null);
//            }
//            else{
//                _duanXaydunghatang.setIdNguonvon(duanXaydunghatang.getIdNguonvon());
//
//            }
//
//
//            if(duanXaydunghatang.getIdKhuChuyennganh() == null || duanXaydunghatang.getIdKhuChuyennganh().getId() == -1)
//                _duanXaydunghatang.setIdKhuChuyennganh(null);
//            else
//                _duanXaydunghatang.setIdKhuChuyennganh(duanXaydunghatang.getIdKhuChuyennganh());
//
//            if(duanXaydunghatang.getIdTrangthaiDuan() == null || duanXaydunghatang.getIdTrangthaiDuan().getId() == -1)
//                _duanXaydunghatang.setIdTrangthaiDuan(null);
//            else
//                _duanXaydunghatang.setIdTrangthaiDuan(duanXaydunghatang.getIdTrangthaiDuan());
//
//            if(duanXaydunghatang.getIdLoaihinhDuan() == null || duanXaydunghatang.getIdLoaihinhDuan().getId() == -1)
//                _duanXaydunghatang.setIdLoaihinhDuan(null);
//            else
//                _duanXaydunghatang.setIdLoaihinhDuan(duanXaydunghatang.getIdLoaihinhDuan());
//
//
//            _duanXaydunghatang.setMasoDuan(duanXaydunghatang.getMasoDuan());
//
//            if(duanXaydunghatang.getNgayQuyetdinhCapphep() != null) _duanXaydunghatang.setNgayQuyetdinhCapphep(duanXaydunghatang.getNgayQuyetdinhCapphep());
//
//            if(duanXaydunghatang.getVontuongduongUsd() != null) _duanXaydunghatang.setVontuongduongUsd(duanXaydunghatang.getVontuongduongUsd());
//
//            if(duanXaydunghatang.getQuymoCongsuat() != null) _duanXaydunghatang.setQuymoCongsuat(duanXaydunghatang.getQuymoCongsuat());
//            else _duanXaydunghatang.setQuymoCongsuat("");
//
//            if(duanXaydunghatang.getNgayHetphepHoatdong() != null) _duanXaydunghatang.setNgayHetphepHoatdong(duanXaydunghatang.getNgayHetphepHoatdong());
//
//
//            if(duanXaydunghatang.getTongmucDautu() != null) _duanXaydunghatang.setTongmucDautu(duanXaydunghatang.getTongmucDautu());
//
//
//            System.out.println(_duanXaydunghatang.getIdKhuChuyennganh());
//            System.out.println(_duanXaydunghatang.getIdNguonvon());
//
//            System.out.println(_duanXaydunghatang.getIdDoanhnghiep());
//
//            return new ResponseEntity<>(duanXaydunghatangRepository.save(_duanXaydunghatang), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/duanXaydunghatang/{id}")
//    public ResponseEntity<HttpStatus> deleteTblDuanXaydunghatang(@PathVariable("id") Integer id) {
//        try {
//            duanXaydunghatangRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/duanXaydunghatang")
//    public ResponseEntity<HttpStatus> deleteAllTblDuanXaydunghatangs() {
//        try {
//            duanXaydunghatangRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//
//    @GetMapping("/excel")
//    public void exportExcel(HttpServletRequest request,
//                            HttpServletResponse response) throws IOException, ServletException, IOException {
//        response.setContentType("application/octet-stream");
//        response.setCharacterEncoding("UTF-8");
//        HttpSession session = request.getSession();
//        String sessionID = "reports/" + session.getId() + new Date().getTime();
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//        String headerKey = "Content-Disposition";
//
//        //set format
//        String headerValue = "attachment; filename=duanxaydunghatang_" + currentDateTime + ".xls";
//        response.setHeader(headerKey, headerValue);
//        Render( response,"xls");
//
//    }
//
//
//    @GetMapping("/word")
//    public void exportWord(HttpServletRequest request,
//                           HttpServletResponse response) throws IOException, ServletException, IOException {
//        response.setContentType("application/octet-stream");
//        response.setCharacterEncoding("UTF-8");
//        HttpSession session = request.getSession();
//        String sessionID = "reports/" + session.getId() + new Date().getTime();
//        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//        String headerKey = "Content-Disposition";
//
//        //set format
//        String headerValue = "attachment; filename=duanxaydunghatang_" + currentDateTime + ".doc";
//        response.setHeader(headerKey, headerValue);
//        Render( response,"doc");
//
//    }
//
//
//    public List<List<String>> getDataDoanhNghiep() throws SQLException {
//        List<TblDuanXaydunghatang> duanxaydunghatang = new ArrayList<TblDuanXaydunghatang>();
//
//
//        duanxaydunghatang= duanXaydunghatangRepository.findAll(Sort.by("ten"));
//
//        List<List<String>> listduanxaydunghatang = new ArrayList<>();
//        for(int i=0;i<duanxaydunghatang.size();i++){
//            List<String> miniList = new ArrayList<>();
//
//
//            if(duanxaydunghatang.get(i).getTen()!=null){
//                miniList.add(duanxaydunghatang.get(i).getTen());
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getSoqdCapphep()!=null){
//                miniList.add(duanxaydunghatang.get(i).getSoqdCapphep());
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getVondautuDangky()!=null){
//
//                miniList.add(String.format ("%.3f", duanxaydunghatang.get(i).getVondautuDangky()));
//            }
//            else{
//                miniList.add("");
//            }
//
//
//
//
//            if(duanxaydunghatang.get(i).getMasoDuan()!=null){
//                miniList.add(duanxaydunghatang.get(i).getMasoDuan());
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getNgayQuyetdinhCapphep()!=null){
//                miniList.add(duanxaydunghatang.get(i).getNgayQuyetdinhCapphep().toString());
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getVontuongduongUsd()!=null){
//                miniList.add(String.format ("%.3f", duanxaydunghatang.get(i).getVontuongduongUsd()));
//
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getQuymoCongsuat()!=null){
//                miniList.add(duanxaydunghatang.get(i).getQuymoCongsuat());
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getNgayHetphepHoatdong()!=null){
//                miniList.add(duanxaydunghatang.get(i).getNgayHetphepHoatdong().toString());
//            }
//            else{
//                miniList.add("");
//            }
//            if(duanxaydunghatang.get(i).getIdLoaihinhDuan()!=null){
//                miniList.add(duanxaydunghatang.get(i).getIdLoaihinhDuan().getLoaihinhDuan());
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getIdDoanhnghiep()!=null){
//                miniList.add(duanxaydunghatang.get(i).getIdDoanhnghiep().getTen());
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getIdNguonvon()!=null){
//                miniList.add(duanxaydunghatang.get(i).getIdNguonvon().getNguonvon());
//            }
//            else{
//                miniList.add("");
//            }
//
//
//
//            if(duanxaydunghatang.get(i).getIdKhuChuyennganh()!=null){
//                miniList.add(duanxaydunghatang.get(i).getIdKhuChuyennganh().getTen());
//            }
//            else{
//                miniList.add("");
//            }
//
//
//
//            if(duanxaydunghatang.get(i).getIdTrangthaiDuan()!=null){
//                miniList.add(duanxaydunghatang.get(i).getIdTrangthaiDuan().getTrangthai());
//            }
//            else{
//                miniList.add("");
//            }
//
//            if(duanxaydunghatang.get(i).getTongmucDautu()!=null){
//                miniList.add(duanxaydunghatang.get(i).getTongmucDautu().toString());
//            }
//            else{
//                miniList.add("");
//            }
//
//
//            listduanxaydunghatang.add(miniList);
//        }
//        return listduanxaydunghatang;
//
//    }
//
//    protected void addTableHeader(String SoNN, String title,
//                                  ReportDesignHandle design) {
//        try {
//            GridHandle table = design.getElementFactory().newGridItem("Table",
//                    2, 3);
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
//                    "40%");
//            ((ColumnHandle) table.getColumns().get(1)).getWidth().setValue(
//                    "60%");
//
//
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
//            text.setContent("PHẦN MỀM SGIS");
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
//    public GridHandle getTablePhanbo(List<List<String>> data,
//                                     List<String> width, List<String> alignment, String clrHeader,
//                                     String clrTextHeader, String clrEven, String clrOdd,
//                                     ReportDesignHandle design) {
//        GridHandle table = null;
//        try {
//            if(data.size()==0) return table;
//            table = design.getElementFactory().newGridItem("", 14,
//                    data.size() + 2);
//            table.setWidth("100%");
//
//            // set width of column
//            for (int c = 0; c < width.size(); c++) {
////                System.out.println(width.get(c));
//                ((ColumnHandle) table.getColumns().get(c)).getWidth().setValue(
//                        width.get(c));
//            }
//
//
//
//            for (int i = 0; i <1; i++) {
//                for (int j = 0; j < 14; j++) {
//
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
//
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
//                    if(j==13){
//                        ((CellHandle) ((RowHandle) table.getRows().get(i))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
//                        ((CellHandle) ((RowHandle) table.getRows().get(i))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
//                    }
//                }
//            }
//
//
//            // //////Hòa các hàng của tiêu đề
//            TextItemHandle text;
//            CellHandle cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(0));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Tên dự án");
//            cell1.getContent().add(text);
//            //Độ sâu row
////            cell1.setRowSpan(2);
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(1));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Số QĐCP");
//
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(2));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Vốn đầu tư");
//
//            cell1.getContent().add(text);
//
//
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(3));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Mã số dự án");
//            cell1.getContent().add(text);
//
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(4));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Ngày cấp phép");
//            cell1.getContent().add(text);
//
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(5));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//
//            text.setContent("Vốn tương đương USD");
//            cell1.getContent().add(text);
//
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(6));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Quy mô");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(7));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Ngày hết phép");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(8));
////            cell1.setW
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Loại hình dự án");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(9));
////            cell1.setW
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Doanh nghiệp");
//            cell1.getContent().add(text);
//
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(10));
////            cell1.setW
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Nguồn vốn");
//            cell1.getContent().add(text);
//
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(11));
////            cell1.setW
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Khu chuyên ngành");
//            cell1.getContent().add(text);
//
//
//
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(12));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Trạng thái");
//            cell1.getContent().add(text);
//
//            cell1 = ((CellHandle) ((RowHandle) table.getRows()
//                    .get(0)).getCells().get(13));
//            text = design.getElementFactory().newTextItem("");
//            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//            text.setContent("Tổng đầu tư");
//            cell1.getContent().add(text);
//
//
//
//            for (int i = 0; i < data.size(); i++) {
//                for (int j = 0; j < data.get(i).size(); j++) {
//                    text = design.getElementFactory().newTextItem("");
//                    text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
//                    //ghi gia tri
//                    text.setContent(data.get(i).get(j));
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_TOP_STYLE_PROP, "solid");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_BOTTOM_STYLE_PROP, "solid");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_LEFT_STYLE_PROP, "solid");
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_BOTTOM_WIDTH_PROP, "1px");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_TOP_WIDTH_PROP, "1px");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.BORDER_LEFT_WIDTH_PROP, "1px");
//                    if (j == data.get(i).size() - 1) {
//                        ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BORDER_RIGHT_STYLE_PROP, "solid");
//                        ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BORDER_RIGHT_WIDTH_PROP, "1px");
//                    }
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.FONT_FAMILY_PROP, "Times New Roman");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.FONT_SIZE_PROP, "10pt");
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                            .getCells().get(j)).setProperty(
//                            StyleHandle.TEXT_ALIGN_PROP, alignment.get(j));
//
//                    if (i % 2 == 0) {
//                        ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BACKGROUND_COLOR_PROP, clrOdd);
//                    } else {
//                        ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
//                                .getCells().get(j)).setProperty(
//                                StyleHandle.BACKGROUND_COLOR_PROP, clrEven);
//                    }
//
//                    ((CellHandle) ((RowHandle) table.getRows().get(i + 1))
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
//
//    public void buildReport(ReportDesignHandle designHandle) {
//        Connection connection = null;
//        try {
//            int i, j, k, l;
////            connection = getConnection();
//            common v = new common();
////            v.setFontReport("Abadi", "13pt", designHandle);
////            v.setAlignReport("justify", designHandle);
//            v.setMarginReport("A4","landscape","0px","0px","0px","0px",designHandle);
//            //***Trình tự1
//            //addTableHeader
//            addTableHeader("Hà Nội", "", designHandle);
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
//            //***Trình tự2
//            v.addLabel("BÁO CÁO", "label1", designHandle).setProperty(
//                    StyleHandle.TEXT_ALIGN_PROP, "center");
//            v.addLabel("BẢNG THỐNG KÊ CÁC DỰ ÁN XÂY DỰNG HẠ TẦNG TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN", "label1",
//                    designHandle).setProperty(StyleHandle.TEXT_ALIGN_PROP,
//                    "center");
//            java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
//                    "dd/MM/yyyy");
//
//
//            String clrHeader = "#E1FFFF";
//            String clrTextHeader = "#000000";
//            String clrEven = "#F5FFFA";
//            String clrOdd = "#FFFFFF";
//            List<String> width = new ArrayList<String>();
//            List<String> align = new ArrayList<String>();
//
//
//
//
//
//            width.add("7%");
//            width.add("5%");
//            width.add("9%");
//            width.add("7%");
//            width.add("7%");
//            width.add("7%");
//            width.add("7%");
//            width.add("7%");
//            width.add("7%");
//            width.add("7%");
//            width.add("6%");
//            width.add("6%");
//            width.add("6%");//
//
//            width.add("6%");
//
//
//
//
//
//
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//            align.add("left");
//
//
//
//
//
//            List<List<String>> data_phanbo = getDataDoanhNghiep();
//
//            //***Trình tự3
//            getTablePhanbo(data_phanbo, width, align, clrHeader, clrTextHeader,
//                    clrEven, clrOdd, designHandle);// .setProperty(StyleHandle.PAGE_BREAK_AFTER_PROP,
//            // "always");
//            //***Trình tự4
//            addTableFooter(designHandle);
//
//        }catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (SQLException e) {
//                }
//            }
//        }
//
//
//    }
//
//    @GetMapping("/pdfrender")
//    public void PDFRender(){
//        IReportEngine engine = null;
//        EngineConfig config = null;
//
//        try {
//            config = new EngineConfig();
//            //doi duong dan
//            config.setEngineHome("C:\\Users\\Admin\\Downloads\\birt-runtime-4.8.0-20180626\\ReportEngine");
//            config.setLogConfig(null, Level.FINE);
//            Platform.startup(config);
//            final IReportEngineFactory FACTORY = (IReportEngineFactory) Platform
//                    .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
//            engine = FACTORY.createReportEngine(config);
//
//            // Open the report design
//            IReportRunnable design = null;
//            //doi duong dan
//            design = engine.openReportDesign("C:\\Users\\Admin\\Downloads\\birt-report-designer-all-in-one-4.8.0-20180522-win32.win32.x86_64\\eclipse\\workspace\\newKhanh\\new_report_3.rptdesign");
//
//
//            ReportDesignHandle report = (ReportDesignHandle) design
//                    .getDesignHandle();
//            MasterPageHandle pageHandle = report
//                    .findMasterPage("Simple MasterPage");
//            DimensionHandle leftMarginHandle = pageHandle.getLeftMargin();
//            DimensionHandle rightMarginHandle = pageHandle.getRightMargin();
//            pageHandle.setOrientation("portrait"); // trang doc
//            report.getElementFactory().newStyle("");
//            StyleHandle labelStyle = report.getElementFactory().newStyle( "Label" );
//            labelStyle.setProperty( StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_BOLD );
//            labelStyle.setProperty( StyleHandle.FONT_FAMILY_PROP, "Arial Black" );
//            labelStyle.setProperty( StyleHandle.COLOR_PROP, "#008000" );
//
//            StyleHandle dataStyle = report.getElementFactory().newStyle( "Data" );
//            dataStyle.setProperty( StyleHandle.FONT_WEIGHT_PROP,
//                    DesignChoiceConstants.FONT_WEIGHT_BOLD );
//            dataStyle.setProperty( StyleHandle.FONT_FAMILY_PROP, "Century"
//            );//$NON-NLS-1$
//            dataStyle.setProperty( StyleHandle.COLOR_PROP, "#009B9B" );
//
//            report.getStyles().add( labelStyle );
//            report.getStyles().add( dataStyle );
//
//            buildReport(report);
//
//            IRunAndRenderTask task = engine.createRunAndRenderTask(design);
//
//
//            Random generator = new Random();
//            Integer ab =generator.nextInt();
//
//
//            IRenderOption options = new RenderOption();
//            options.setOutputStream(new ByteArrayOutputStream());
//
//            PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
//            options.setOutputFormat("pdf");
//
//            String abc = "C:\\Users\\Admin\\Documents\\khukt_20221226\\khukt\\src\\main\\webapp\\duanxaydunghatangPDF.pdf";
//
//            options.setOutputFileName(abc);
//
//            task.setRenderOption(options);
//
//            task.run();
//            task.close();
//            engine.destroy();
//            RegistryProviderFactory.releaseDefault();
//            engine = null;
//            System.out.println("Finished");
//
//        } catch(final Exception EX) {
//            EX.printStackTrace();
//        } finally {
//            Platform.shutdown();
//        }
//    }
//
//
//
//    public void Render(HttpServletResponse response,String format) throws IOException, ServletException, IOException{
//        IReportEngine engine = null;
//        EngineConfig config = null;
//        try {
//
//            config = new EngineConfig();
//            //doi duong dan
//            config.setEngineHome("C:\\Users\\Admin\\Downloads\\birt-runtime-4.8.0-20180626\\ReportEngine");
//
//            config.setLogConfig(null, Level.FINE);
//
//
//            Platform.startup(config);
//            IReportEngineFactory factory = (IReportEngineFactory) Platform
//                    .createFactoryObject(IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
//            engine = factory.createReportEngine(config);
//            engine.changeLogLevel(Level.WARNING);
//
//            IReportRunnable design = null;
//
//
//            // Open a report design
//            //doi duong dan
//            design = engine.openReportDesign("C:\\Users\\Admin\\Downloads\\birt-report-designer-all-in-one-4.8.0-20180522-win32.win32.x86_64\\eclipse\\workspace\\newKhanh\\new_report_3.rptdesign");
//
//            ReportDesignHandle report = (ReportDesignHandle) design
//                    .getDesignHandle();
//            MasterPageHandle pageHandle = report
//                    .findMasterPage("Simple MasterPage");
//            DimensionHandle leftMarginHandle = pageHandle.getLeftMargin();
//            DimensionHandle rightMarginHandle = pageHandle.getRightMargin();
//            pageHandle.setOrientation("landscape");
//
//
//
//            buildReport(report);
//            // create task to run and render report
//            IRunAndRenderTask task = engine.createRunAndRenderTask(design);
//
//            EXCELRenderOption options = new EXCELRenderOption();
//            options.setOutputStream(new ByteArrayOutputStream());
//
//
//            options.setOutputFormat(format);
////            options.setOutputStream(stream );
////            String abc = "C:\\Users\\Admin\\Documents\\khukt_20221226\\khukt\\src\\main\\webapp\\khanh.xls";
////            options.setOutputFileName(abc);
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            options.setOutputStream(baos);
//            task.setRenderOption(options);
//            // run report
//            task.run();
//            task.close();
//
//            ServletOutputStream sos = response.getOutputStream();
//            try {
//                baos.writeTo(sos);
//                baos.close();
//                sos.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//
//            // Destroy the engine and shutdown the Platform
//            // Note - If the program stays resident do not shutdown the Platform
//            // or the Engine
//            // engine.shutdown();
//            // Platform.shutdown();
//            engine.destroy();
//            RegistryProviderFactory.releaseDefault();
//            engine = null;
//            System.out.println("Finished");
//
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
//
//}