package com.sgis.khukt.controller;

import com.sgis.khukt.model.TblDoanhnghiep;
import com.sgis.khukt.model.TblDuanDautusanxuat;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.common;
import com.sgis.khukt.servlet.Duandautusanxuat;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sgis.khukt.repository.TblDuanDautusanxuatRepository;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;

@CrossOrigin(origins = {"http://localhost:8080", "http://10.168.1.196:8080"})
@RestController
@RequestMapping("/duandautusanxuat")
public class TblDuanDautusanxuatController {

    @Autowired
    TblDuanDautusanxuatRepository duanDautusanxuatRepository;

    @GetMapping("/preduandautusanxuat/{id}")
    public Integer preduandautusanxuat(@PathVariable("id") Integer id) {
        List<TblDuanDautusanxuat> TblDuanDautusanxuat = new ArrayList<TblDuanDautusanxuat>();

        Integer preId = -1;
        TblDuanDautusanxuat = duanDautusanxuatRepository.findAll(Sort.by("ten"));
        for (int i = 0; i < TblDuanDautusanxuat.size(); i++) {
            if (TblDuanDautusanxuat.get(i).getId().equals(id)) {
                break;
            } else {
                preId = TblDuanDautusanxuat.get(i).getId();
            }

        }
        return preId;
    }

    @GetMapping("/duandautusanxuats")
    public ResponseEntity<List<TblDuanDautusanxuat>> getAllTblDuanDautusanxuats(@RequestParam(required = false) String vondautu) {
        try {
            List<TblDuanDautusanxuat> duandautusanxuat = new ArrayList<TblDuanDautusanxuat>();

            if (vondautu == null) {
                duanDautusanxuatRepository.findAll(Sort.by("ten")).forEach(duandautusanxuat::add);
            } else {
                duanDautusanxuatRepository.findByTen(vondautu).forEach(duandautusanxuat::add);
            }

            if (duandautusanxuat.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(duandautusanxuat, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/duandautusanxuat/{id}")
    public ResponseEntity<TblDuanDautusanxuat> getTblDuanDautusanxuatById(@PathVariable("id") Integer id) {
        Optional<TblDuanDautusanxuat> duandautusanxuatData = duanDautusanxuatRepository.findById(id);

        if (duandautusanxuatData.isPresent()) {
            return new ResponseEntity<>(duandautusanxuatData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/duandautusanxuat")
    public ResponseEntity<TblDuanDautusanxuat> createTblDuanDautusanxuat(@RequestBody TblDuanDautusanxuat duandautusanxuat) {
        try {
            TblDuanDautusanxuat _duandautusanxuat = duanDautusanxuatRepository
                    .save(duandautusanxuat);

            return new ResponseEntity<>(_duandautusanxuat, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/duandautusanxuat/{id}")
    public ResponseEntity<TblDuanDautusanxuat> updateTblDuanDautusanxuat(@PathVariable("id") Integer id, @RequestBody TblDuanDautusanxuat duandautusanxuat) {
        Optional<TblDuanDautusanxuat> duandautusanxuatData = duanDautusanxuatRepository.findById(id);

        if (duandautusanxuatData.isPresent()) {
            TblDuanDautusanxuat _duandautusanxuat = duandautusanxuatData.get();
            
            if(duandautusanxuat.getTen() != null) _duandautusanxuat.setTen(duandautusanxuat.getTen());
            else _duandautusanxuat.setTen("");                
            _duandautusanxuat.setIdDoanhnghiep(duandautusanxuat.getIdDoanhnghiep());

            if(duandautusanxuat.getTongmucDautu() != null) _duandautusanxuat.setTongmucDautu(duandautusanxuat.getTongmucDautu());


            if(duandautusanxuat.getSoquyetdinhCapphep() != null) _duandautusanxuat.setSoquyetdinhCapphep(duandautusanxuat.getSoquyetdinhCapphep());
            else _duandautusanxuat.setSoquyetdinhCapphep("");                                           
            
            if(duandautusanxuat.getIdNguonvon() == null || duandautusanxuat.getIdNguonvon().getId() == -1)
                _duandautusanxuat.setIdNguonvon(null);
            else
                _duandautusanxuat.setIdNguonvon(duandautusanxuat.getIdNguonvon());
            
            if(duandautusanxuat.getIdKhuChuyennganh() == null || duandautusanxuat.getIdKhuChuyennganh().getId() == -1)
                _duandautusanxuat.setIdKhuChuyennganh(null);
            else
                _duandautusanxuat.setIdKhuChuyennganh(duandautusanxuat.getIdKhuChuyennganh());
                        
            if(duandautusanxuat.getNoidungSanxuatKinhdoanh() != null) _duandautusanxuat.setNoidungSanxuatKinhdoanh(duandautusanxuat.getNoidungSanxuatKinhdoanh());
            else _duandautusanxuat.setNoidungSanxuatKinhdoanh("");                                           
            
            if(duandautusanxuat.getIdLinhvucSanxuatkinhdoanh() == null || duandautusanxuat.getIdLinhvucSanxuatkinhdoanh().getIdLinhvucSanxuatkinhdoanh() == -1)
                _duandautusanxuat.setIdLinhvucSanxuatkinhdoanh(null);
            else
                _duandautusanxuat.setIdLinhvucSanxuatkinhdoanh(duandautusanxuat.getIdLinhvucSanxuatkinhdoanh());

            if(duandautusanxuat.getMasoDuan() != null) _duandautusanxuat.setMasoDuan(duandautusanxuat.getMasoDuan());
            else _duandautusanxuat.setMasoDuan(""); 
            
            if(duandautusanxuat.getNgayQuyetdinhCapphep() != null) _duandautusanxuat.setNgayQuyetdinhCapphep(duandautusanxuat.getNgayQuyetdinhCapphep());
            
            if(duandautusanxuat.getVontuongduongUSD() != null) _duandautusanxuat.setVontuongduongUSD(duandautusanxuat.getVontuongduongUSD());

            if(duandautusanxuat.getVondautuDangky() != null) _duandautusanxuat.setVondautuDangky(duandautusanxuat.getVondautuDangky());


            if(duandautusanxuat.getQuymoCongsuat() != null)  _duandautusanxuat.setQuymoCongsuat(duandautusanxuat.getQuymoCongsuat());
            else _duandautusanxuat.setQuymoCongsuat("");
            
            if(duandautusanxuat.getNgayHetphepHoatdong() != null) _duandautusanxuat.setNgayHetphepHoatdong(duandautusanxuat.getNgayHetphepHoatdong());
            
            if(duandautusanxuat.getIdTrangthaiDuan() == null || duandautusanxuat.getIdTrangthaiDuan().getId() == -1)
                _duandautusanxuat.setIdTrangthaiDuan(null);
            else
                _duandautusanxuat.setIdTrangthaiDuan(duandautusanxuat.getIdTrangthaiDuan());
            
            if(duandautusanxuat.getIdLoaihinhDuan() == null || duandautusanxuat.getIdLoaihinhDuan().getId() == -1)
                _duandautusanxuat.setIdLoaihinhDuan(null);
            else
                _duandautusanxuat.setIdLoaihinhDuan(duandautusanxuat.getIdLoaihinhDuan());
                      

            return new ResponseEntity<>(duanDautusanxuatRepository.save(_duandautusanxuat), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/duandautusanxuat/{id}")
    public ResponseEntity<HttpStatus> deleteTblDuanDautusanxuat(@PathVariable("id") Integer id) {
        try {
            duanDautusanxuatRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/duandautusanxuat")
    public ResponseEntity<HttpStatus> deleteAllTblDuanDautusanxuats() {
        try {
            duanDautusanxuatRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @GetMapping("/duandautusanxuat/findByKCN/{id}")
    public ResponseEntity<List<Duandautusanxuat>> getByKCN(@PathVariable("id") Integer id){
        List<List<String>> listduan = duanDautusanxuatRepository.findByKCN(id);
        List<Duandautusanxuat> duandautusanxuatList = new ArrayList<>();
        for(int i =0;i<listduan.size();i++){
            List<String> miniDuan = new ArrayList<>();
            for(int j=0;j<listduan.get(i).size();j++){
                miniDuan.add(listduan.get(i).get(j));
            }
            duandautusanxuatList.add(new Duandautusanxuat(miniDuan));
        }
        return new ResponseEntity<>(duandautusanxuatList, HttpStatus.OK);
    }

    @GetMapping("/duandautusanxuat/findByDN/{id}")
    public ResponseEntity<List<Duandautusanxuat>> findByDN(@PathVariable("id") Integer id){
        List<List<String>> listduan = duanDautusanxuatRepository.findByDN(id);
        List<Duandautusanxuat> duandautusanxuatList = new ArrayList<>();
        for(int i =0;i<listduan.size();i++){
            List<String> miniDuan = new ArrayList<>();
            for(int j=0;j<listduan.get(i).size();j++){
                miniDuan.add(listduan.get(i).get(j));
            }
            duandautusanxuatList.add(new Duandautusanxuat(miniDuan));
        }
        return new ResponseEntity<>(duandautusanxuatList, HttpStatus.OK);
    }


    @GetMapping("/duandautusanxuat/findByKKT/{id}")
    public ResponseEntity<List<Duandautusanxuat>> findByKKT(@PathVariable("id") Integer id){
        List<List<String>> listduan = duanDautusanxuatRepository.findByKKT(id);
        List<Duandautusanxuat> duandautusanxuatList = new ArrayList<>();
        for(int i =0;i<listduan.size();i++){
            List<String> miniDuan = new ArrayList<>();
            for(int j=0;j<listduan.get(i).size();j++){
                miniDuan.add(listduan.get(i).get(j));
            }
            duandautusanxuatList.add(new Duandautusanxuat(miniDuan));
        }
        return new ResponseEntity<>(duandautusanxuatList, HttpStatus.OK);
    }

    @GetMapping("/duandautusanxuat/findByAll")
    public ResponseEntity<List<Duandautusanxuat>> findByAll(){
        List<List<String>> listduan = duanDautusanxuatRepository.findByAll();
        List<Duandautusanxuat> duandautusanxuatList = new ArrayList<>();
        for(int i =0;i<listduan.size();i++){
            List<String> miniDuan = new ArrayList<>();
            for(int j=0;j<listduan.get(i).size();j++){
                miniDuan.add(listduan.get(i).get(j));
            }
            duandautusanxuatList.add(new Duandautusanxuat(miniDuan));
        }
        return new ResponseEntity<>(duandautusanxuatList, HttpStatus.OK);
    }


    @GetMapping("/excel")
    public void exportExcel(HttpServletRequest request,
                            HttpServletResponse response) throws IOException, ServletException, IOException {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String sessionID = "reports/" + session.getId() + new Date().getTime();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";

        //set format
        String headerValue = "attachment; filename=duandautusanxuat_" + currentDateTime + ".xls";
        response.setHeader(headerKey, headerValue);
        Render( response,"xls");

    }


    @GetMapping("/word")
    public void exportWord(HttpServletRequest request,
                           HttpServletResponse response) throws IOException, ServletException, IOException {
        response.setContentType("application/octet-stream");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String sessionID = "reports/" + session.getId() + new Date().getTime();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";

        //set format
        String headerValue = "attachment; filename=duandautusanxuat_" + currentDateTime + ".doc";
        response.setHeader(headerKey, headerValue);
        Render( response,"doc");

    }


    public List<List<String>> getDataDoanhNghiep() throws SQLException {
        List<TblDuanDautusanxuat> duandautusanxuat = new ArrayList<TblDuanDautusanxuat>();


        duandautusanxuat= duanDautusanxuatRepository.findAll(Sort.by("ten"));

        List<List<String>> listDuandautusanxuat = new ArrayList<>();
        for(int i=0;i<duandautusanxuat.size();i++){
            List<String> miniList = new ArrayList<>();


            if(duandautusanxuat.get(i).getTen()!=null){
                miniList.add(duandautusanxuat.get(i).getTen());
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getSoquyetdinhCapphep()!=null){
                miniList.add(duandautusanxuat.get(i).getSoquyetdinhCapphep());
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getVondautuDangky()!=null){

                miniList.add(String.format ("%.3f", duandautusanxuat.get(i).getVondautuDangky()));
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getNoidungSanxuatKinhdoanh()!=null){
                miniList.add(duandautusanxuat.get(i).getNoidungSanxuatKinhdoanh());
            }
            else{
                miniList.add("");
            }


            if(duandautusanxuat.get(i).getMasoDuan()!=null){
                miniList.add(duandautusanxuat.get(i).getMasoDuan());
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getNgayQuyetdinhCapphep()!=null){
                miniList.add(duandautusanxuat.get(i).getNgayQuyetdinhCapphep().toString());
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getVontuongduongUSD()!=null){
                miniList.add(String.format ("%.3f", duandautusanxuat.get(i).getVontuongduongUSD()));

            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getQuymoCongsuat()!=null){
                miniList.add(duandautusanxuat.get(i).getQuymoCongsuat());
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getNgayHetphepHoatdong()!=null){
                miniList.add(duandautusanxuat.get(i).getNgayHetphepHoatdong().toString());
            }
            else{
                miniList.add("");
            }
            if(duandautusanxuat.get(i).getIdLoaihinhDuan()!=null){
                miniList.add(duandautusanxuat.get(i).getIdLoaihinhDuan().getLoaihinhDuan());
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getIdDoanhnghiep()!=null){
                miniList.add(duandautusanxuat.get(i).getIdDoanhnghiep().getTen());
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getIdNguonvon()!=null){
                miniList.add(duandautusanxuat.get(i).getIdNguonvon().getNguonvon());
            }
            else{
                miniList.add("");
            }



            if(duandautusanxuat.get(i).getIdKhuChuyennganh()!=null){
                miniList.add(duandautusanxuat.get(i).getIdKhuChuyennganh().getTen());
            }
            else{
                miniList.add("");
            }


            if(duandautusanxuat.get(i).getIdLinhvucSanxuatkinhdoanh()!=null){
                miniList.add(duandautusanxuat.get(i).getIdLinhvucSanxuatkinhdoanh().getLinhvucSanxuatkinhdoanh());
            }
            else{
                miniList.add("");
            }
            if(duandautusanxuat.get(i).getIdTrangthaiDuan()!=null){
                miniList.add(duandautusanxuat.get(i).getIdTrangthaiDuan().getTrangthai());
            }
            else{
                miniList.add("");
            }

            if(duandautusanxuat.get(i).getTongmucDautu()!=null){
                miniList.add(duandautusanxuat.get(i).getTongmucDautu().toString());
            }
            else{
                miniList.add("");
            }


            listDuandautusanxuat.add(miniList);
        }
        return listDuandautusanxuat;

    }

    protected void addTableHeader(String SoNN, String title,
                                  ReportDesignHandle design) {
        try {
            GridHandle table = design.getElementFactory().newGridItem("Table",
                    2, 3);
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
                    "40%");
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
            text.setContent("PHẦN MỀM SGIS");
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
                                     ReportDesignHandle design) {
        GridHandle table = null;
        try {
            if(data.size()==0) return table;
            table = design.getElementFactory().newGridItem("", 16,
                    data.size() + 2);
            table.setWidth("100%");

            // set width of column
            for (int c = 0; c < width.size(); c++) {
//                System.out.println(width.get(c));
                ((ColumnHandle) table.getColumns().get(c)).getWidth().setValue(
                        width.get(c));
            }



            for (int i = 0; i <1; i++) {
                for (int j = 0; j < 16; j++) {

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
                    if(j==15){
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
            text.setContent("Tên dự án");
            cell1.getContent().add(text);
            //Độ sâu row
//            cell1.setRowSpan(2);
            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(1));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Số QĐCP");

            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(2));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Vốn đầu tư");

            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(3));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Nội dung sản xuất");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(4));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Mã số dự án");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(5));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Ngày cấp phép");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(6));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);

            text.setContent("Vốn tương đương USD");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(7));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Quy mô");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(8));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Ngày hết phép");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(9));
//            cell1.setW
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Loại hình dự án");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(10));
//            cell1.setW
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Doanh nghiệp");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(11));
//            cell1.setW
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Nguồn vốn");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(12));
//            cell1.setW
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Khu chuyên ngành");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(13));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Lĩnh vực sản xuất");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(14));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Trạng thái");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(15));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Tổng đầu tư");
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
//            v.setFontReport("Abadi", "13pt", designHandle);
//            v.setAlignReport("justify", designHandle);
            v.setMarginReport("A4","landscape","0px","0px","0px","0px",designHandle);
            //***Trình tự1
            //addTableHeader
            addTableHeader("Hà Nội", "", designHandle);
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
            v.addLabel("BÁO CÁO", "label1", designHandle).setProperty(
                    StyleHandle.TEXT_ALIGN_PROP, "center");
            v.addLabel("BẢNG THỐNG KÊ CÁC DỰ ÁN ĐẦU TƯ SẢN XUẤT TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN", "label1",
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





            width.add("7%");
            width.add("5%");
            width.add("5%");
            width.add("7%");
            width.add("5%");//msda

            width.add("7%");
            width.add("7%");
            width.add("7%");
            width.add("7%");//nhphd
            width.add("7%");//lhda//

            width.add("6%");
            width.add("6%");
            width.add("6%");//

            width.add("6%");
            width.add("6%");
            width.add("6%");





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

    @GetMapping("/pdfrender")
    public void PDFRender(){
        IReportEngine engine = null;
        EngineConfig config = null;

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

            buildReport(report);

            IRunAndRenderTask task = engine.createRunAndRenderTask(design);


            Random generator = new Random();
            Integer ab =generator.nextInt();


            IRenderOption options = new RenderOption();
            options.setOutputStream(new ByteArrayOutputStream());

            PDFRenderOption PDF_OPTIONS = new PDFRenderOption();
            options.setOutputFormat("pdf");

            String abc = "C:\\Users\\Admin\\Documents\\khukt_20221226\\khukt\\src\\main\\webapp\\duandautusanxuatPDF.pdf";

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
//            options.setOutputStream(stream );
//            String abc = "C:\\Users\\Admin\\Documents\\khukt_20221226\\khukt\\src\\main\\webapp\\khanh.xls";
//            options.setOutputFileName(abc);

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
