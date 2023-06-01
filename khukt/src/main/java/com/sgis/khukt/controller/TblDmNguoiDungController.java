package com.sgis.khukt.controller;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import com.sgis.khukt.model.Report;
import com.sgis.khukt.model.ServletExample;
import com.sgis.khukt.model.common;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

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

import org.eclipse.birt.core.framework.Platform;

import com.sgis.khukt.model.TblDmNguoiDung;
import com.sgis.khukt.repository.TblDmNguoiDungRepository;
import com.sgis.khukt.model.TblDoanhnghiep;
import com.sgis.khukt.repository.TblDoanhnghiepRepository;
import com.sgis.khukt.model.TblKhuKinhte;
import com.sgis.khukt.repository.TblKhuKinhteRepository;

import java.util.Date;
import java.util.logging.Level;
import javax.persistence.Entity;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.core.JdbcTemplate;

@CrossOrigin(origins = {"http://localhost:8080", "http://10.168.1.196:8080"})
@RestController
@RequestMapping("/dmnguoidung/")
public class TblDmNguoiDungController extends HttpServlet {
 @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    TblDmNguoiDungRepository dmnguoidungRepository;

    @GetMapping("/preDmNguoiDung/{id}")
    public Integer preDmNguoiDung(@PathVariable("id") Integer id) {
        List<TblDmNguoiDung> dmnguoidung = new ArrayList<TblDmNguoiDung>();
//        dmnguoidungRepository.findAll(Sort.by("ten")).forEach(dmnguoidung::add);
        Integer preId = -1;
        dmnguoidung = dmnguoidungRepository.findAll(Sort.by("tennguoidung"));
        for (int i = 0; i < dmnguoidung.size(); i++) {
            if (dmnguoidung.get(i).getId().equals(id)) {
                break;
            } else {
                preId = dmnguoidung.get(i).getId();
            }

        }
        return preId;

    }

    @GetMapping("/dmnguoidungs")
    public ResponseEntity<List<TblDmNguoiDung>> getAllTblDmNguoiDungs(@RequestParam(required = false) String ten) {
        try {
            List<TblDmNguoiDung> dmnguoidung = new ArrayList<TblDmNguoiDung>();

            if (ten == null) {
                dmnguoidungRepository.findAll(Sort.by("tennguoidung")).forEach(dmnguoidung::add);
            } else {
                dmnguoidungRepository.findByTendangnhap(ten).forEach(dmnguoidung::add);
            }

            if (dmnguoidung.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dmnguoidung, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    private EntityManager entityManager;

    @GetMapping("/my-api")
    public List<TblDmNguoiDung> getData() {
        String nativeQuery = "SELECT dn.ten AS tendoanhnghiep, kkt.ten AS tenkhukinhte, da.ten AS tenduan, da.id "
                + "FROM tbl_duan_dautusanxuat da "
                + "INNER JOIN tbl_doanhnghiep dn ON (dn.id = da.id_doanhnghiep) "
                + "INNER JOIN tbl_khu_kinhte kkt ON (kkt.id = da.id_khu_chuyennganh) ";
//                + "INNER JOIN tbl_dm_user us ON ((','||us.quyen_xem_thsx||',') ~ (','||da.id::character varying||','))";

        List<TblDmNguoiDung> result = entityManager.createNativeQuery(nativeQuery, "myMapping")
                .getResultList();

        return result;
    } 
    
    
    
    @GetMapping("/duans")
    public List<TblDmNguoiDung> getDuans() {
        String sql = "SELECT dn.ten tendoanhnghiep, kkt.ten tenkhukinhte, da.ten tenduan, da.id, us.quyen_xem_thsx, us.quyen_soanthao_thsx " +
                     "FROM tbl_duan_dautusanxuat da " +
                     "INNER JOIN tbl_doanhnghiep dn ON (dn.id = da.id_doanhnghiep) " +
                     "INNER JOIN tbl_khu_kinhte kkt ON (kkt.id = da.id_khu_chuyennganh) " +
                     "INNER JOIN tbl_dm_user us ON ((','||us.quyen_xem_thsx||',') ~ (','||da.id::character varying||','))";
        List<TblDmNguoiDung> results = jdbcTemplate.query(sql, (rs, rowNum) -> {
            TblDmNguoiDung duanDto = new TblDmNguoiDung();
//            duanDto.setTenDoanhNghiep(rs.getString("tendoanhnghiep"));
//            duanDto.setTenKhuKinhte(rs.getString("tenkhukinhte"));
//            duanDto.setTenDuan(rs.getString("tenduan"));
//            duanDto.setId(rs.getLong("id"));
//            duanDto.setQuyenXemThsx(rs.getString("quyen_xem_thsx"));
//            duanDto.setQuyenSoanthaoThsx(rs.getString("quyen_soanthao_thsx"));
            return duanDto;
        });
        return results;
    }   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @GetMapping("/dmnguoidung/{id}")
    public ResponseEntity<TblDmNguoiDung> getTblDmNguoiDungById(@PathVariable("id") Integer id) {
        Optional<TblDmNguoiDung> dmnguoidungData = dmnguoidungRepository.findById(id);

        if (dmnguoidungData.isPresent()) {
            return new ResponseEntity<>(dmnguoidungData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dmnguoidung")
    public ResponseEntity<TblDmNguoiDung> createTblDmNguoiDung(@RequestBody TblDmNguoiDung dmnguoidung) {
        try {

            TblDmNguoiDung _dmnguoidung = dmnguoidungRepository
                    .save(dmnguoidung);
            return new ResponseEntity<>(_dmnguoidung, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dmnguoidungquyen/{id}")
    public ResponseEntity<TblDmNguoiDung> updateTblDmNguoiDungQuyen(@PathVariable("id") Integer id, @RequestBody TblDmNguoiDung dmnguoidung) {
        Optional<TblDmNguoiDung> dmnguoidungData = dmnguoidungRepository.findById(id);
        if (dmnguoidungData.isPresent()){
            TblDmNguoiDung _dmnguoidung = dmnguoidungData.get();
            if (dmnguoidung.getQuyenxemthsx() != null) {
                _dmnguoidung.setQuyenxemthsx(dmnguoidung.getQuyenxemthsx());
            } else {
                _dmnguoidung.setQuyenxemthsx("");
            }
            if (dmnguoidung.getQuyensoanthaothsx() != null) {
                _dmnguoidung.setQuyensoanthaothsx(dmnguoidung.getQuyensoanthaothsx());
            } else {
                _dmnguoidung.setQuyensoanthaothsx("");
            }
            return new ResponseEntity<>(dmnguoidungRepository.save(_dmnguoidung), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/dmnguoidung/{id}")
    public ResponseEntity<TblDmNguoiDung> updateTblDmNguoiDung(@PathVariable("id") Integer id, @RequestBody TblDmNguoiDung dmnguoidung) {
        Optional<TblDmNguoiDung> dmnguoidungData = dmnguoidungRepository.findById(id);

        if (dmnguoidungData.isPresent()) {
            TblDmNguoiDung _dmnguoidung = dmnguoidungData.get();

            if (dmnguoidung.getTendangnhap() != null) {
                _dmnguoidung.setTendangnhap(dmnguoidung.getTendangnhap());
            } else {
                _dmnguoidung.setTendangnhap("");
            }
            if (dmnguoidung.getTennguoidung() != null) {
                _dmnguoidung.setTennguoidung(dmnguoidung.getTennguoidung());
            } else {
                _dmnguoidung.setTennguoidung("");
            }
            if (dmnguoidung.getMatkhau() != null) {
                _dmnguoidung.setMatkhau(dmnguoidung.getMatkhau());
            } else {
                _dmnguoidung.setMatkhau("");
            }
            if (dmnguoidung.getQuyenxemthsx() != null) {
                _dmnguoidung.setQuyenxemthsx(dmnguoidung.getQuyenxemthsx());
            } else {
                _dmnguoidung.setQuyenxemthsx("");
            }
            if (dmnguoidung.getQuyensoanthaothsx() != null) {
                _dmnguoidung.setQuyensoanthaothsx(dmnguoidung.getQuyensoanthaothsx());
            } else {
                _dmnguoidung.setQuyensoanthaothsx("");
            }
         
            return new ResponseEntity<>(dmnguoidungRepository.save(_dmnguoidung), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    
    @PutMapping("/dmnguoidungtest/{id}")
    public ResponseEntity<TblDmNguoiDung> updateTblDmNguoiDungTest(@PathVariable("id") Integer id, @RequestBody TblDmNguoiDung dmnguoidung) {
        Optional<TblDmNguoiDung> dmnguoidungData = dmnguoidungRepository.findById(id);

        if (dmnguoidungData.isPresent()) {
            TblDmNguoiDung _dmnguoidung = dmnguoidungData.get();

            if (dmnguoidung.getTendangnhap() != null) {
                _dmnguoidung.setTendangnhap(dmnguoidung.getTendangnhap());
            } else {
                _dmnguoidung.setTendangnhap("");
            }
            if (dmnguoidung.getTennguoidung() != null) {
                _dmnguoidung.setTennguoidung(dmnguoidung.getTennguoidung());
            } else {
                _dmnguoidung.setTennguoidung("");
            }
            if (dmnguoidung.getMatkhau() != null) {
                _dmnguoidung.setMatkhau(dmnguoidung.getMatkhau());
            } else {
                _dmnguoidung.setMatkhau("");
            }
            
            
            
            return new ResponseEntity<>(dmnguoidungRepository.save(_dmnguoidung), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    
    
    @DeleteMapping("/dmnguoidung/{id}")
    public ResponseEntity<HttpStatus> deleteTblDmNguoiDung(@PathVariable("id") Integer id) {
        try {
            dmnguoidungRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dmnguoidung")
    public ResponseEntity<HttpStatus> deleteAllTblDmNguoiDungs() {
        try {
            dmnguoidungRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



  

    public List<List<String>> getDataDoanhNghiep() throws SQLException{
        List<TblDmNguoiDung> dmnguoidung = new ArrayList<TblDmNguoiDung>();
        dmnguoidung = dmnguoidungRepository.findAll(Sort.by("tennguoidung"));
        List<List<String>> listDmNguoiDung = new ArrayList<>();
        for(int i=0;i<dmnguoidung.size();i++){
            List<String> miniList = new ArrayList<>();


            if(dmnguoidung.get(i).getTendangnhap()!=null){
                miniList.add(dmnguoidung.get(i).getTendangnhap());
            }
            else{
                miniList.add("");
            }

            if(dmnguoidung.get(i).getMatkhau()!=null){
                miniList.add(dmnguoidung.get(i).getMatkhau());
            }
            else{
                miniList.add("");
            }

            if(dmnguoidung.get(i).getTennguoidung()!=null){
                miniList.add(dmnguoidung.get(i).getTennguoidung());
            }
            else{
                miniList.add("");
            }

           


            if(dmnguoidung.get(i).getQuyensoanthaothsx()!=null){
                miniList.add(dmnguoidung.get(i).getQuyensoanthaothsx());
            }
            else{
                miniList.add("");
            }

          




            if(dmnguoidung.get(i).getQuyenxemthsx()!=null){
                miniList.add(dmnguoidung.get(i).getQuyenxemthsx());
            }
            else{
                miniList.add("");
            }

           
            listDmNguoiDung.add(miniList);
        }
        return listDmNguoiDung;

    }

  
 
   

}
