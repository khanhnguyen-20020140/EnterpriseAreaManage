package com.sgis.khukt.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.logging.Level;

import com.sgis.khukt.model.ReportKQDTSXData;
import com.sgis.khukt.model.common;
import com.sgis.khukt.servlet.Doanhthu;
import com.sgis.khukt.servlet.Ngansach;
import com.sgis.khukt.servlet.NhanCong;
import com.sgis.khukt.servlet.TenDuAn;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;
import org.eclipse.birt.report.model.api.*;
import org.eclipse.birt.report.model.api.elements.DesignChoiceConstants;
import org.eclipse.core.internal.registry.RegistryProviderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
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
import org.springframework.web.bind.annotation.RestController;

import com.sgis.khukt.model.TblKetquaSanxuatkinhdoanh;
import com.sgis.khukt.repository.TblKetquaSanxuatkinhdoanhRepository;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/ketquaSanxuatkinhdoanh")
public class TblKetquaSanxuatkinhdoanhController {
    @Autowired
    TblKetquaSanxuatkinhdoanhRepository ketquaSanxuatkinhdoanhRepository;

    @GetMapping("/preketquaSanxuatkinhdoanh/{id}")
    public Integer preketquaSanxuatkinhdoanh(@PathVariable("id") Integer id) {

        List<TblKetquaSanxuatkinhdoanh> ketquaSanxuatkinhdoanh = new ArrayList<TblKetquaSanxuatkinhdoanh>();

        Integer preId =-1;
        ketquaSanxuatkinhdoanh = ketquaSanxuatkinhdoanhRepository.findAll(Sort.by("idDuanSanxuatkinhdoanh"));
        for (int i =0;i<ketquaSanxuatkinhdoanh.size();i++) {
            if(ketquaSanxuatkinhdoanh.get(i).getIdKetqua().equals(id)){
                break;
            }
            else {
                preId = ketquaSanxuatkinhdoanh.get(i).getIdKetqua();
            }

        }
        return preId;

    }
    public static Integer getExactValue(Double b){
        Integer toInt =null;
        if(b!=null){
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

        return toInt;
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/kcnID/{id}")
    public ResponseEntity<List<Integer>> getAllIdInKCN(@PathVariable("id")Integer id){
        return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.getAllIdInKCN(id), HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/toantinhID")
    public ResponseEntity<List<Integer>> getAllIdInToantinh(){
        return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.getAllIdInToantinh(), HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/dnID/{id}")
    public ResponseEntity<List<Integer>> getAllIdInDN(@PathVariable("id")Integer id){
        return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.getAllIdInDN(id), HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/duanID/{id}")
    public ResponseEntity<List<Integer>> getAllIdInDuan(@PathVariable("id")Integer id){
        return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.getAllIdInDuan(id), HttpStatus.OK);
    }

    //ten va id
    @GetMapping("/ketquaSanxuatkinhdoanhsreport/kcnTen/{id}")
    public ResponseEntity<List<TenDuAn>> getAllTenInKCN(@PathVariable("id")Integer id){
        List<List<String>> getTenId = ketquaSanxuatkinhdoanhRepository.getAllTenInKCN(id);
        List<TenDuAn> tenDuAnList = new ArrayList<>();
        for (int i =0;i<getTenId.size();i++){

            List<String> tenduanString = new ArrayList<>();
            for (int j=0;j<getTenId.get(i).size();j++){
                tenduanString.add(getTenId.get(i).get(j));
            }

            tenDuAnList.add(new TenDuAn(tenduanString));
        }
        return new ResponseEntity<>(tenDuAnList, HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/toantinhTen")
    public ResponseEntity<List<TenDuAn>> getAllTenInToantinh(){
        List<List<String>> getTenId = ketquaSanxuatkinhdoanhRepository.getAllTenInToantinh();
        List<TenDuAn> tenDuAnList = new ArrayList<>();
        for (int i =0;i<getTenId.size();i++){

            List<String> tenduanString = new ArrayList<>();
            for (int j=0;j<getTenId.get(i).size();j++){
                tenduanString.add(getTenId.get(i).get(j));
            }

            tenDuAnList.add(new TenDuAn(tenduanString));
        }
        return new ResponseEntity<>(tenDuAnList, HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/dnTen/{id}")
    public ResponseEntity<List<TenDuAn>> getAllTenInDN(@PathVariable("id")Integer id){
        List<List<String>> getTenId = ketquaSanxuatkinhdoanhRepository.getAllTenInDN(id);
        List<TenDuAn> tenDuAnList = new ArrayList<>();
        for (int i =0;i<getTenId.size();i++){

            List<String> tenduanString = new ArrayList<>();
            for (int j=0;j<getTenId.get(i).size();j++){
                tenduanString.add(getTenId.get(i).get(j));
            }

            tenDuAnList.add(new TenDuAn(tenduanString));
        }
        return new ResponseEntity<>(tenDuAnList, HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/duanTen/{id}")
    public ResponseEntity<List<TenDuAn>> getAllTenInDuan(@PathVariable("id")Integer id){
        List<List<String>> getTenId = ketquaSanxuatkinhdoanhRepository.getAllTenInDuan(id);
        List<TenDuAn> tenDuAnList = new ArrayList<>();
        for (int i =0;i<getTenId.size();i++){

            List<String> tenduanString = new ArrayList<>();
            for (int j=0;j<getTenId.get(i).size();j++){
                tenduanString.add(getTenId.get(i).get(j));
            }

            tenDuAnList.add(new TenDuAn(tenduanString));
        }
        return new ResponseEntity<>(tenDuAnList, HttpStatus.OK);
    }

    // ten cua noi truy van
    @GetMapping("/ketquaSanxuatkinhdoanhsreport/exactTen/kcn/{id}")
    public ResponseEntity<String> getExactTenInKCN(@PathVariable("id")Integer id){
        return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.getExactTenKCN(id), HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/exactTen/duan/{id}")
    public ResponseEntity<String> getExactTenInduan(@PathVariable("id")Integer id){
        return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.getExactTenDuan(id), HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/exactTen/dn/{id}")
    public ResponseEntity<String> getExactTenInDN(@PathVariable("id")Integer id){
        return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.getExactTenDn(id), HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/exactTen/kkt/{id}")
    public ResponseEntity<String> getExactTenKkt(@PathVariable("id")Integer id){
        return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.getExactTenKkt(id), HttpStatus.OK);
    }


    @GetMapping("/ketquaSanxuatkinhdoanhsreport/notIn/{id_duan}/{firstday}/{endday}")
    public ResponseEntity<List<NhanCong>> ketquareportNotInKCN(@PathVariable("id_duan")Integer id, @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){
        System.out.println(id+"    "+java.sql.Date.valueOf(firstday));
        List<List<Double>> avgNhancong=ketquaSanxuatkinhdoanhRepository.avgNhancongBeforeKCN(id,java.sql.Date.valueOf(firstday));


        NhanCong nhanCongsMin = new NhanCong();
        for(int i = 0;i<avgNhancong.size();i++){
//            System.out.println(avgNhancong.get(i));
            List<Object> nhancongdetail = new ArrayList<>();
            for(int j=1;j<avgNhancong.get(i).size();j++){


                if(j==4||j==9){
                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add((double) Math.round(avgNhancong.get(i).get(j) * 100) / 100);
                    }
                    else{
                        nhancongdetail.add(0.0);
                    }
                }
                else if(j==8){
                    if(avgNhancong.get(i).get(j)!=null){
                        Integer trendaihoc =0;
                        if(avgNhancong.get(i).get(1)!=null){
                            trendaihoc+=getExactValue(avgNhancong.get(i).get(1));
                        }
                        if(avgNhancong.get(i).get(7)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(7));
                        }
                        if(avgNhancong.get(i).get(6)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(6));
                        }
                        if(avgNhancong.get(i).get(5)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(5));
                        }

                        nhancongdetail.add(trendaihoc);
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }
                else {

                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add(getExactValue(avgNhancong.get(i).get(j)));
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }

            }

            nhanCongsMin  = new NhanCong( avgNhancong.get(i).get(0).intValue(),nhancongdetail);

        }

        if(avgNhancong.size()==0){
            List<Object> avgMinNhancong = new ArrayList<>();
            avgMinNhancong.add(0);
            avgMinNhancong.add(0);
            avgMinNhancong.add(0);
            avgMinNhancong.add(0.0);
            avgMinNhancong.add(0);
            avgMinNhancong.add(0);
            avgMinNhancong.add(0);
            avgMinNhancong.add(0);
            avgMinNhancong.add(0.0);
            nhanCongsMin = new NhanCong( id,avgMinNhancong);

        }


        avgNhancong=ketquaSanxuatkinhdoanhRepository.avgNhancongAfterKCN(id,java.sql.Date.valueOf(endday));
        NhanCong nhanCongsMax = new NhanCong();
        for(int i = 0;i<avgNhancong.size();i++){
//            System.out.println(avgNhancong.get(i));
            List<Object> nhancongdetail = new ArrayList<>();
            for(int j=1;j<avgNhancong.get(i).size();j++){


                if(j==4||j==9){
                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add((double) Math.round(avgNhancong.get(i).get(j) * 100) / 100);
                    }
                    else{
                        nhancongdetail.add(0.0);
                    }
                }
                else if(j==8){
                    if(avgNhancong.get(i).get(j)!=null){
                        Integer trendaihoc =0;
                        if(avgNhancong.get(i).get(1)!=null){
                            trendaihoc+=getExactValue(avgNhancong.get(i).get(1));
                        }
                        if(avgNhancong.get(i).get(7)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(7));
                        }
                        if(avgNhancong.get(i).get(6)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(6));
                        }
                        if(avgNhancong.get(i).get(5)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(5));
                        }

                        nhancongdetail.add(trendaihoc);
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }
                else {

                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add(getExactValue(avgNhancong.get(i).get(j)));
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }

            }

            nhanCongsMax = new NhanCong( avgNhancong.get(i).get(0).intValue(),nhancongdetail);

        }

        if(avgNhancong.size()==0){
            List<Object> avgMaxNhancong = new ArrayList<>();
            avgMaxNhancong.add(0);
            avgMaxNhancong.add(0);
            avgMaxNhancong.add(0);
            avgMaxNhancong.add(0.0);
            avgMaxNhancong.add(0);
            avgMaxNhancong.add(0);
            avgMaxNhancong.add(0);
            avgMaxNhancong.add(0);
            avgMaxNhancong.add(0.0);
            nhanCongsMax = new NhanCong( id,avgMaxNhancong);

        }

        List<NhanCong> nhanCongsAvg = new ArrayList<>();

        Integer trendaihoc =0 ;
        Integer nam =0;
        List<Object> nhancongdetail = new ArrayList<>();

        System.out.println(nhanCongsMin.getNhancong());
        System.out.println(nhanCongsMax.getNhancong());
        for(int z=0;z<9;z++){

            if(z==3||z==8){

                nhancongdetail.add((((Number) nhanCongsMin.getNhancong().get(z)).doubleValue()+
                        ((Number) nhanCongsMax.getNhancong().get(z)).doubleValue())/2);
            }
            else if(z==0){

                nhancongdetail.add(getExactValue(
                                        (((Number) nhanCongsMin.getNhancong().get(z)).doubleValue()+
                                                ((Number) nhanCongsMax.getNhancong().get(z)).doubleValue())/2
                                )
                );



                trendaihoc += getExactValue(
                        (((Number) nhanCongsMin.getNhancong().get(z)).doubleValue()+
                                ((Number) nhanCongsMax.getNhancong().get(z)).doubleValue())/2
                );
                System.out.println("trendaihoc + " +trendaihoc);
            }
            else if(z==1){
                nhancongdetail.add(
                        getExactValue(
                                (((Number) nhanCongsMin.getNhancong().get(z)).doubleValue()+
                                        ((Number) nhanCongsMax.getNhancong().get(z)).doubleValue())/2
                        )
                );
                nam = getExactValue(
                        (((Number) nhanCongsMin.getNhancong().get(z)).doubleValue()+
                                ((Number) nhanCongsMax.getNhancong().get(z)).doubleValue())/2
                );
            }
            else if(z==2){
                nhancongdetail.add(trendaihoc-nam);
            }

            else if(z==6||z==4||z==5){
                nhancongdetail.add(
                        getExactValue(
                                (((Number) nhanCongsMin.getNhancong().get(z)).doubleValue()+
                                        ((Number) nhanCongsMax.getNhancong().get(z)).doubleValue())/2
                        )
                );
                trendaihoc -= getExactValue(
                        (((Number) nhanCongsMin.getNhancong().get(z)).doubleValue()+
                                ((Number) nhanCongsMax.getNhancong().get(z)).doubleValue())/2
                );

                System.out.println("trendaihoc - " +trendaihoc);
            }
            else{
                System.out.println("add tren dai hoc" + trendaihoc);
                nhancongdetail.add(trendaihoc);
            }


        }

        nhanCongsAvg.add(new NhanCong(id,nhancongdetail));


        return new ResponseEntity<>(nhanCongsAvg, HttpStatus.OK);
    }



    @GetMapping("/ketquaSanxuatkinhdoanhsreport/kcnIn/{id_kcn}/{firstday}/{endday}")
    public ResponseEntity<List<NhanCong>> ketquareportByKCN(@PathVariable("id_kcn")Integer id, @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){

        System.out.println(id+"    "+java.sql.Date.valueOf(firstday));
        List<List<Double>> avgNhancong=ketquaSanxuatkinhdoanhRepository.avgNhancongInKCN(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));
        System.out.println(avgNhancong.size());

        List<NhanCong> nhanCongs = new ArrayList<>();
        for(int i = 0;i<avgNhancong.size();i++){
//            System.out.println(avgNhancong.get(i));
            List<Object> nhancongdetail = new ArrayList<>();
            for(int j=1;j<avgNhancong.get(i).size();j++){


                if(j==4||j==9){
                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add((double) Math.round(avgNhancong.get(i).get(j) * 100) / 100);
                    }
                    else{
                        nhancongdetail.add(0.0);
                    }
                }
                else if(j==8){
                    if(avgNhancong.get(i).get(j)!=null){
                        Integer trendaihoc =0;
                        if(avgNhancong.get(i).get(1)!=null){
                            trendaihoc+=getExactValue(avgNhancong.get(i).get(1));
                        }
                        if(avgNhancong.get(i).get(7)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(7));
                        }
                        if(avgNhancong.get(i).get(6)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(6));
                        }
                        if(avgNhancong.get(i).get(5)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(5));
                        }

                        nhancongdetail.add(trendaihoc);
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }
                else {

                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add(getExactValue(avgNhancong.get(i).get(j)));
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }

            }
            System.out.println("???   "+nhancongdetail );
            NhanCong nhanCong = new NhanCong( avgNhancong.get(i).get(0).intValue(),nhancongdetail);
            nhanCongs.add(nhanCong);
        }

        for(int i=0;i<nhanCongs.size();i++){
            System.out.println("here wwe go");
            System.out.println(nhanCongs.get(i).getNhancong());
        }



        System.out.println(nhanCongs);

        return new ResponseEntity<>(nhanCongs, HttpStatus.OK);
    }


    @GetMapping("/ketquaSanxuatkinhdoanhsreport/dnIn/{id_dn}/{firstday}/{endday}")
    public ResponseEntity<List<NhanCong>> ketquareportByDN(@PathVariable("id_dn")Integer id, @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){

        System.out.println(id+"    "+java.sql.Date.valueOf(firstday));
        List<List<Double>> avgNhancong=ketquaSanxuatkinhdoanhRepository.avgNhancongInDN(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));
        System.out.println(avgNhancong.size());

        List<NhanCong> nhanCongs = new ArrayList<>();
        for(int i = 0;i<avgNhancong.size();i++){
//            System.out.println(avgNhancong.get(i));
            List<Object> nhancongdetail = new ArrayList<>();
            for(int j=1;j<avgNhancong.get(i).size();j++){


                if(j==4||j==9){
                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add((double) Math.round(avgNhancong.get(i).get(j) * 100) / 100);
                    }
                    else{
                        nhancongdetail.add(0.0);
                    }
                }
                else if(j==8){
                    if(avgNhancong.get(i).get(j)!=null){
                        Integer trendaihoc =0;
                        if(avgNhancong.get(i).get(1)!=null){
                            trendaihoc+=getExactValue(avgNhancong.get(i).get(1));
                        }
                        if(avgNhancong.get(i).get(7)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(7));
                        }
                        if(avgNhancong.get(i).get(6)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(6));
                        }
                        if(avgNhancong.get(i).get(5)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(5));
                        }

                        nhancongdetail.add(trendaihoc);
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }
                else {

                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add(getExactValue(avgNhancong.get(i).get(j)));
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }

            }
            System.out.println("???   "+nhancongdetail );
            NhanCong nhanCong = new NhanCong( avgNhancong.get(i).get(0).intValue(),nhancongdetail);
            nhanCongs.add(nhanCong);
        }

        for(int i=0;i<nhanCongs.size();i++){
            System.out.println("here wwe go");
            System.out.println(nhanCongs.get(i).getNhancong());
        }



        System.out.println(nhanCongs);

        return new ResponseEntity<>(nhanCongs, HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/duanIn/{id_duan}/{firstday}/{endday}")
    public ResponseEntity<List<NhanCong>> ketquareportByDuan(@PathVariable("id_duan")Integer id, @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){

        System.out.println(id+"    "+java.sql.Date.valueOf(firstday));
        List<List<Double>> avgNhancong=ketquaSanxuatkinhdoanhRepository.avgNhancongInDuan(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));
        System.out.println(avgNhancong.size());

        List<NhanCong> nhanCongs = new ArrayList<>();
        for(int i = 0;i<avgNhancong.size();i++){
//            System.out.println(avgNhancong.get(i));
            List<Object> nhancongdetail = new ArrayList<>();
            for(int j=1;j<avgNhancong.get(i).size();j++){


                if(j==4||j==9){
                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add((double) Math.round(avgNhancong.get(i).get(j) * 100) / 100);
                    }
                    else{
                        nhancongdetail.add(0.0);
                    }
                }
                else if(j==8){
                    if(avgNhancong.get(i).get(j)!=null){
                        Integer trendaihoc =0;
                        if(avgNhancong.get(i).get(1)!=null){
                            trendaihoc+=getExactValue(avgNhancong.get(i).get(1));
                        }
                        if(avgNhancong.get(i).get(7)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(7));
                        }
                        if(avgNhancong.get(i).get(6)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(6));
                        }
                        if(avgNhancong.get(i).get(5)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(5));
                        }

                        nhancongdetail.add(trendaihoc);
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }
                else {

                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add(getExactValue(avgNhancong.get(i).get(j)));
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }

            }
            System.out.println("???   "+nhancongdetail );
            NhanCong nhanCong = new NhanCong( avgNhancong.get(i).get(0).intValue(),nhancongdetail);
            nhanCongs.add(nhanCong);
        }

        for(int i=0;i<nhanCongs.size();i++){
            System.out.println("here wwe go");
            System.out.println(nhanCongs.get(i).getNhancong());
        }



        System.out.println(nhanCongs);

        return new ResponseEntity<>(nhanCongs, HttpStatus.OK);
    }


    @GetMapping("/ketquaSanxuatkinhdoanhsreport/toantinhIn/{firstday}/{endday}")
    public ResponseEntity<List<NhanCong>> ketquareportByToantinh( @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){

//        System.out.println(id+"    "+java.sql.Date.valueOf(firstday));
        List<List<Double>> avgNhancong=ketquaSanxuatkinhdoanhRepository.avgNhancongInToantinh(java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));
        System.out.println(avgNhancong.size());

        List<NhanCong> nhanCongs = new ArrayList<>();
        for(int i = 0;i<avgNhancong.size();i++){
//            System.out.println(avgNhancong.get(i));
            List<Object> nhancongdetail = new ArrayList<>();
            for(int j=1;j<avgNhancong.get(i).size();j++){


                if(j==4||j==9){
                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add((double) Math.round(avgNhancong.get(i).get(j) * 100) / 100);
                    }
                    else{
                        nhancongdetail.add(0.0);
                    }
                }
                else if(j==8){
                    if(avgNhancong.get(i).get(j)!=null){
                        Integer trendaihoc =0;
                        if(avgNhancong.get(i).get(1)!=null){
                            trendaihoc+=getExactValue(avgNhancong.get(i).get(1));
                        }
                        if(avgNhancong.get(i).get(7)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(7));
                        }
                        if(avgNhancong.get(i).get(6)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(6));
                        }
                        if(avgNhancong.get(i).get(5)!=null){
                            trendaihoc-=getExactValue(avgNhancong.get(i).get(5));
                        }

                        nhancongdetail.add(trendaihoc);
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }
                else {

                    if(avgNhancong.get(i).get(j)!=null){
                        nhancongdetail.add(getExactValue(avgNhancong.get(i).get(j)));
                    }
                    else{
                        nhancongdetail.add(0);
                    }
                }

            }
            System.out.println("???   "+nhancongdetail );
            NhanCong nhanCong = new NhanCong( avgNhancong.get(i).get(0).intValue(),nhancongdetail);
            nhanCongs.add(nhanCong);
        }

        for(int i=0;i<nhanCongs.size();i++){
            System.out.println("here wwe go");
            System.out.println(nhanCongs.get(i).getNhancong());
        }



        System.out.println(nhanCongs);

        return new ResponseEntity<>(nhanCongs, HttpStatus.OK);
    }


    // ngansach

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/ngansach/kcn/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Ngansach>> getNgansachByKCN(@PathVariable("id")Integer id, @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){
        List<List<String>> ngansach = ketquaSanxuatkinhdoanhRepository.getNgansachByKCN(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));
        List<Ngansach> ngansachList = new ArrayList<>();

        System.out.println(firstday+"   "+endday);

        System.out.println(ngansach.size());
        for (int i=0;i<ngansach.size();i++){

            List<String> ngansachString = new ArrayList<>();
            for (int j=0;j<ngansach.get(i).size();j++){
                ngansachString.add(ngansach.get(i).get(j));
            }
            ngansachList.add(new Ngansach(ngansachString));
        }
        return new ResponseEntity<>(ngansachList, HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/ngansach/duan/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Ngansach>> getNgansachByDuan(@PathVariable("id")Integer id, @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){
        List<List<String>> ngansach = ketquaSanxuatkinhdoanhRepository.getNgansachByDuan(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));
        List<Ngansach> ngansachList = new ArrayList<>();

        System.out.println(firstday+"   "+endday);

        System.out.println(ngansach.size());
        for (int i=0;i<ngansach.size();i++){

            List<String> ngansachString = new ArrayList<>();
            for (int j=0;j<ngansach.get(i).size();j++){
                ngansachString.add(ngansach.get(i).get(j));
            }
            ngansachList.add(new Ngansach(ngansachString));
        }
        return new ResponseEntity<>(ngansachList, HttpStatus.OK);
    }


    @GetMapping("/ketquaSanxuatkinhdoanhsreport/ngansach/dn/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Ngansach>> getNgansachByDn(@PathVariable("id")Integer id, @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){
        List<List<String>> ngansach = ketquaSanxuatkinhdoanhRepository.getNgansachByDn(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));
        List<Ngansach> ngansachList = new ArrayList<>();

        System.out.println(firstday+"   "+endday);

        System.out.println(ngansach.size());
        for (int i=0;i<ngansach.size();i++){

            List<String> ngansachString = new ArrayList<>();
            for (int j=0;j<ngansach.get(i).size();j++){
                ngansachString.add(ngansach.get(i).get(j));
            }
            ngansachList.add(new Ngansach(ngansachString));
        }
        return new ResponseEntity<>(ngansachList, HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/ngansach/toantinh/{firstday}/{endday}")
    public ResponseEntity<List<Ngansach>> getNgansachByToantinh( @PathVariable("firstday")String firstday, @PathVariable("endday")String endday){
        List<List<String>> ngansach = ketquaSanxuatkinhdoanhRepository.getNgansachByToantinh(java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));
        List<Ngansach> ngansachList = new ArrayList<>();

        System.out.println(firstday+"   "+endday);

        System.out.println(ngansach.size());
        for (int i=0;i<ngansach.size();i++){

            List<String> ngansachString = new ArrayList<>();
            for (int j=0;j<ngansach.get(i).size();j++){
                ngansachString.add(ngansach.get(i).get(j));
            }
            ngansachList.add(new Ngansach(ngansachString));
        }
        return new ResponseEntity<>(ngansachList, HttpStatus.OK);
    }

    //doanhthu
    @GetMapping("/ketquaSanxuatkinhdoanhsreport/doanhthu/kcn/{id}")
    public ResponseEntity<List<Doanhthu>> getDoanhthuByKCN(@PathVariable("id")Integer id){
        List<List<String>> doanhthu = ketquaSanxuatkinhdoanhRepository.getDoanhthuByKCN(id);
        List<Doanhthu> doanhthuList = new ArrayList<>();
        System.out.println(doanhthu.size());
        for (int i=0;i<doanhthu.size();i++){

            List<String> ngansachString = new ArrayList<>();
            for (int j=0;j<doanhthu.get(i).size();j++){
                ngansachString.add(doanhthu.get(i).get(j));
            }
            doanhthuList.add(new Doanhthu(ngansachString));
        }
        return new ResponseEntity<>(doanhthuList, HttpStatus.OK);
    }


    @GetMapping("/ketquaSanxuatkinhdoanhsreport/doanhthu/duan/{id}")
    public ResponseEntity<List<Doanhthu>> getDoanhthuByDuan(@PathVariable("id")Integer id){
        List<List<String>> doanhthu = ketquaSanxuatkinhdoanhRepository.getDoanhthuByDuan(id);
        List<Doanhthu> doanhthuList = new ArrayList<>();
        System.out.println(doanhthu.size());
        for (int i=0;i<doanhthu.size();i++){

            List<String> ngansachString = new ArrayList<>();
            for (int j=0;j<doanhthu.get(i).size();j++){
                ngansachString.add(doanhthu.get(i).get(j));
            }
            doanhthuList.add(new Doanhthu(ngansachString));
        }
        return new ResponseEntity<>(doanhthuList, HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/doanhthu/dn/{id}")
    public ResponseEntity<List<Doanhthu>> getDoanhthuByDN(@PathVariable("id")Integer id){
        List<List<String>> doanhthu = ketquaSanxuatkinhdoanhRepository.getDoanhthuByDN(id);
        List<Doanhthu> doanhthuList = new ArrayList<>();
        System.out.println(doanhthu.size());
        for (int i=0;i<doanhthu.size();i++){

            List<String> ngansachString = new ArrayList<>();
            for (int j=0;j<doanhthu.get(i).size();j++){
                ngansachString.add(doanhthu.get(i).get(j));
            }
            doanhthuList.add(new Doanhthu(ngansachString));
        }
        return new ResponseEntity<>(doanhthuList, HttpStatus.OK);
    }

    @GetMapping("/ketquaSanxuatkinhdoanhsreport/doanhthu/toantinh/")
    public ResponseEntity<List<Doanhthu>> getDoanhthuByToantinh(){
        List<List<String>> doanhthu = ketquaSanxuatkinhdoanhRepository.getDoanhthuByToantinh();
        List<Doanhthu> doanhthuList = new ArrayList<>();
        System.out.println(doanhthu.size());
        for (int i=0;i<doanhthu.size();i++){

            List<String> ngansachString = new ArrayList<>();
            for (int j=0;j<doanhthu.get(i).size();j++){
                ngansachString.add(doanhthu.get(i).get(j));
            }
            doanhthuList.add(new Doanhthu(ngansachString));
        }
        return new ResponseEntity<>(doanhthuList, HttpStatus.OK);
    }






    @GetMapping("/ketquaSanxuatkinhdoanhs")
    public ResponseEntity<List<TblKetquaSanxuatkinhdoanh>> getAllTblKetquaSanxuatkinhdoanhs() {
        try {
            List<TblKetquaSanxuatkinhdoanh> ketquaSanxuatkinhdoanh = new ArrayList<TblKetquaSanxuatkinhdoanh>();

            ketquaSanxuatkinhdoanhRepository.findAll(Sort.by(Sort.Order.by("idDuanSanxuatkinhdoanh"),
                    Sort.Order.by("mocBaocao")
                    )).forEach(ketquaSanxuatkinhdoanh::add);


            if (ketquaSanxuatkinhdoanh.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(ketquaSanxuatkinhdoanh, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/duandautusanxuat/{id}")
    public ResponseEntity<List<TblKetquaSanxuatkinhdoanh>> getTblKetquaSanxuatkinhdoanhByDTSX(@PathVariable("id") Integer id) {
        try {
            List<TblKetquaSanxuatkinhdoanh> ketquaSanxuatkinhdoanh = new ArrayList<TblKetquaSanxuatkinhdoanh>();


            ketquaSanxuatkinhdoanhRepository.findByIdDuanSanxuatkinhdoanh(id).forEach(ketquaSanxuatkinhdoanh::add);
            System.out.println(ketquaSanxuatkinhdoanh.size());
            for(int i=0;i<ketquaSanxuatkinhdoanh.size();i++){
                System.out.println(ketquaSanxuatkinhdoanh.get(i));
            }


//            List<Integer> a = ketquaSanxuatkinhdoanhRepository.finddd();
//            System.out.println(a.size());
//            System.out.println();
//            for(int i=0;i<a.size();i++){
//                System.out.println(a.get(i));
//            }
//            System.out.println(ketquaSanxuatkinhdoanh.get(0));
            if (ketquaSanxuatkinhdoanh.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(ketquaSanxuatkinhdoanh, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/ketquaSanxuatkinhdoanh/{id}")
    public ResponseEntity<TblKetquaSanxuatkinhdoanh> getTblKetquaSanxuatkinhdoanhById(@PathVariable("id") Integer id) {
        Optional<TblKetquaSanxuatkinhdoanh> ketquaSanxuatkinhdoanhData = ketquaSanxuatkinhdoanhRepository.findById(id);

        if (ketquaSanxuatkinhdoanhData.isPresent()) {
            return new ResponseEntity<>(ketquaSanxuatkinhdoanhData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/ketquaSanxuatkinhdoanh")
    public ResponseEntity<TblKetquaSanxuatkinhdoanh> createTblKetquaSanxuatkinhdoanh(@RequestBody TblKetquaSanxuatkinhdoanh ketquaSanxuatkinhdoanh) {
        try {
            TblKetquaSanxuatkinhdoanh _ketquaSanxuatkinhdoanh = ketquaSanxuatkinhdoanhRepository
                    .save(ketquaSanxuatkinhdoanh);
            return new ResponseEntity<>(_ketquaSanxuatkinhdoanh, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/ketquaSanxuatkinhdoanh/{id}")
    public ResponseEntity<TblKetquaSanxuatkinhdoanh> updateTblKetquaSanxuatkinhdoanh(@PathVariable("id") Integer id, @RequestBody TblKetquaSanxuatkinhdoanh ketquaSanxuatkinhdoanh) {
        Optional<TblKetquaSanxuatkinhdoanh> ketquaSanxuatkinhdoanhData = ketquaSanxuatkinhdoanhRepository.findById(id);

        System.out.println(ketquaSanxuatkinhdoanh.getLuongTrungbinh());

        if (ketquaSanxuatkinhdoanhData.isPresent()) {
            TblKetquaSanxuatkinhdoanh _ketquaSanxuatkinhdoanh = ketquaSanxuatkinhdoanhData.get();
            _ketquaSanxuatkinhdoanh.setDoanhthuTinhTudaunam(ketquaSanxuatkinhdoanh.getDoanhthuTinhTudaunam());
            _ketquaSanxuatkinhdoanh.setIdDuanSanxuatkinhdoanh(ketquaSanxuatkinhdoanh.getIdDuanSanxuatkinhdoanh());

            _ketquaSanxuatkinhdoanh.setLuongTrungbinh(ketquaSanxuatkinhdoanh.getLuongTrungbinh());
            _ketquaSanxuatkinhdoanh.setNhancongCaodang(ketquaSanxuatkinhdoanh.getNhancongCaodang());
            _ketquaSanxuatkinhdoanh.setNhancongDaihoc(ketquaSanxuatkinhdoanh.getNhancongDaihoc());
            _ketquaSanxuatkinhdoanh.setNhancongTrendaihoc(ketquaSanxuatkinhdoanh.getNhancongTrendaihoc());
            _ketquaSanxuatkinhdoanh.setNhancongTrungcap(ketquaSanxuatkinhdoanh.getNhancongTrungcap());
            _ketquaSanxuatkinhdoanh.setNopNgansachTinhTudaunam(ketquaSanxuatkinhdoanh.getNopNgansachTinhTudaunam());
            _ketquaSanxuatkinhdoanh.setTongsoNhancong(ketquaSanxuatkinhdoanh.getTongsoNhancong());
            _ketquaSanxuatkinhdoanh.setTongsoNhancongNam(ketquaSanxuatkinhdoanh.getTongsoNhancongNam());
            _ketquaSanxuatkinhdoanh.setTongsoNhancongNu(ketquaSanxuatkinhdoanh.getTongsoNhancongNu());
            _ketquaSanxuatkinhdoanh.setTuoiTrungbinhNhancong(ketquaSanxuatkinhdoanh.getTuoiTrungbinhNhancong());
            _ketquaSanxuatkinhdoanh.setMocBaocao(ketquaSanxuatkinhdoanh.getMocBaocao());

            return new ResponseEntity<>(ketquaSanxuatkinhdoanhRepository.save(_ketquaSanxuatkinhdoanh), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ketquaSanxuatkinhdoanh/{id}")
    public ResponseEntity<HttpStatus> deleteTblKetquaSanxuatkinhdoanh(@PathVariable("id") Integer id) {
        try {
            ketquaSanxuatkinhdoanhRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/ketquaSanxuatkinhdoanh")
    public ResponseEntity<HttpStatus> deleteAllTblKetquaSanxuatkinhdoanhs() {
        try {
            ketquaSanxuatkinhdoanhRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

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
        String headerValue = "attachment; filename=ketquaSanxuatkinhdoanh_" + currentDateTime + ".xls";
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
        String headerValue = "attachment; filename=ketquaSanxuatkinhdoanh_" + currentDateTime + ".doc";
        response.setHeader(headerKey, headerValue);
        Render( response,"doc");

    }


    public List<List<String>> getDataKetquaSanxuat() throws SQLException {
        List<TblKetquaSanxuatkinhdoanh> ketquaSanxuatkinhdoanh = new ArrayList<TblKetquaSanxuatkinhdoanh>();


        ketquaSanxuatkinhdoanh = ketquaSanxuatkinhdoanhRepository.findAll(Sort.by("idKetqua"));
        List<List<String>> listKetQuaSanxuatkinhdoanh = new ArrayList<>();
        for(int i=0;i<ketquaSanxuatkinhdoanh.size();i++){
            List<String> miniList = new ArrayList<>();


            if(ketquaSanxuatkinhdoanh.get(i).getIdDuanSanxuatkinhdoanh()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getIdDuanSanxuatkinhdoanh().getTen());
            }
            else{
                miniList.add("");
            }

            if(ketquaSanxuatkinhdoanh.get(i).getDoanhthuTinhTudaunam()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getDoanhthuTinhTudaunam().toString());
            }
            else{
                miniList.add("");
            }

            if(ketquaSanxuatkinhdoanh.get(i).getNopNgansachTinhTudaunam()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getNopNgansachTinhTudaunam().toString());
            }
            else{
                miniList.add("");
            }

            if(ketquaSanxuatkinhdoanh.get(i).getTongsoNhancong()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getTongsoNhancong().toString());
            }
            else{
                miniList.add("");
            }


            if(ketquaSanxuatkinhdoanh.get(i).getTongsoNhancongNu()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getTongsoNhancongNu().toString());
            }
            else{
                miniList.add("");
            }

            if(ketquaSanxuatkinhdoanh.get(i).getTuoiTrungbinhNhancong()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getTuoiTrungbinhNhancong().toString());
            }
            else{
                miniList.add("");
            }
            if(ketquaSanxuatkinhdoanh.get(i).getNhancongTrungcap()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getNhancongTrungcap().toString());
            }
            else{
                miniList.add("");
            }




            if(ketquaSanxuatkinhdoanh.get(i).getNhancongCaodang()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getNhancongCaodang().toString());
            }
            else{
                miniList.add("");
            }

            if(ketquaSanxuatkinhdoanh.get(i).getNhancongDaihoc()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getNhancongDaihoc().toString());
            }
            else{
                miniList.add("");
            }
            if(ketquaSanxuatkinhdoanh.get(i).getNhancongTrendaihoc()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getNhancongTrendaihoc().toString());
            }
            else{
                miniList.add("");
            }

            if(ketquaSanxuatkinhdoanh.get(i).getLuongTrungbinh()!=null){
                miniList.add(ketquaSanxuatkinhdoanh.get(i).getLuongTrungbinh().toString());
            }
            else{
                miniList.add("");
            }


            listKetQuaSanxuatkinhdoanh.add(miniList);
        }
        return listKetQuaSanxuatkinhdoanh;

    }

    protected void addTableHeader(String SoNN, String title,
                                  ReportDesignHandle design) {
        try {
            GridHandle table = design.getElementFactory().newGridItem("Table",
                    3, 3);
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
                    "50%");
            ((ColumnHandle) table.getColumns().get(2)).getWidth().setValue(
                    "10%");





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
            text.setContent("");
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
            text.setContent("");
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
                    .get(1)).setProperty(StyleHandle.TEXT_ALIGN_PROP, "right");
//            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
//                    .get(1)).setProperty(StyleHandle.FONT_STYLE_PROP,
//                    DesignChoiceConstants.FONT_STYLE_ITALIC);
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_FAMILY_PROP,
                    "Times New Roman");
            ((CellHandle) ((RowHandle) table.getRows().get(2)).getCells()
                    .get(1)).setProperty(StyleHandle.FONT_SIZE_PROP, "12pt");

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
            table = design.getElementFactory().newGridItem("", 11,
                    data.size() + 2);
            table.setWidth("100%");

            // set width of column
            for (int c = 0; c < width.size(); c++) {
                ((ColumnHandle) table.getColumns().get(c)).getWidth().setValue(
                        width.get(c));
            }



            for (int i = 0; i <=1; i++) {
                for (int j = 0; j < 8; j++) {
                    if((i==0&&j<4)||(i==1&&j<8)){
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
                        if((i==0&&j==3)||(i==1&&j==7)){
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


            // //////Hòa các hàng của tiêu đề
            TextItemHandle text;
            CellHandle cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(0));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Tên dự án");
            cell1.getContent().add(text);
            //Độ sâu row
            cell1.setRowSpan(2);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(1));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Doanh thu ");

            cell1.getContent().add(text);
            cell1.setRowSpan(2);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(0)).getCells().get(2));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Ngân sách");

            cell1.getContent().add(text);
            cell1.setRowSpan(2);

            cell1 = ((CellHandle) ((RowHandle) table.getRows().get(0))
                    .getCells().get(3));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Nhân công");
            cell1.getContent().add(text);
            cell1.setColumnSpan(8);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(1)).getCells().get(0));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Tổng số");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(1)).getCells().get(1));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Nữ");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(1)).getCells().get(2));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);

            text.setContent("Tuổi trung bình");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(1)).getCells().get(3));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Trung cấp");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(1)).getCells().get(4));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Cao đẳng");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(1)).getCells().get(5));
//            cell1.setW
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Đại học ");
            cell1.getContent().add(text);


            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(1)).getCells().get(6));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Trên đại học");
            cell1.getContent().add(text);

            cell1 = ((CellHandle) ((RowHandle) table.getRows()
                    .get(1)).getCells().get(7));
            text = design.getElementFactory().newTextItem("");
            text.setContentType(DesignChoiceConstants.TEXT_DATA_CONTENT_TYPE_PLAIN);
            text.setContent("Lương trung bình");
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
                    text.setContent(data.get(i).get(j));

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
                            StyleHandle.TEXT_ALIGN_PROP, alignment.get(j));

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

    public void buildReport(ReportDesignHandle designHandle) {
        Connection connection = null;
        try {
            int i, j, k, l;
//            connection = getConnection();
            common v = new common();
//            v.setFontReport("Abadi", "13pt", designHandle);
//            v.setAlignReport("justify", designHandle);
            v.setMarginReport("A4","landscape","20px","20px","0px","0px",designHandle);
            //***Trình tự1
            //addTableHeader
            LocalDate currentdate = LocalDate.now();
            String currentDate = currentdate.toString();
            System.out.println("Current date: "+currentDate);
            String[] parts = currentDate.split("-");
            String title = ".............,ngày "+ parts[2]+ " tháng " + parts[1]+" năm "+parts[0];

            addTableHeader("", title, designHandle);
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
            String label_content ="BÁO CÁO";
            LabelHandle label= v.addLabel(label_content, "label1", designHandle);
            label =  v.addLabel(label_content, "label1",
                    designHandle);
            label.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            label.setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");

            designHandle.getBody().add(label);



            label_content = "BẢNG THỐNG KÊ TÌNH HÌNH CÁC DỰ ÁN ĐẦU TƯ SẢN XUẤT KINH DOANH TẠI CÁC KHU KINH TẾ TỈNH NGHỆ AN NGÀY "+parts[2]+" THÁNG "+parts[1]+" NĂM "+parts[0];
            label =  v.addLabel(label_content, "label1",
                    designHandle);


            label.setProperty(StyleHandle.TEXT_ALIGN_PROP, "center");
            label.setProperty(StyleHandle.FONT_FAMILY_PROP, "Times New Roman");


            designHandle.getBody().add(label);




            String clrHeader = "#E1FFFF";
            String clrTextHeader = "#000000";
            String clrEven = "#F5FFFA";
            String clrOdd = "#FFFFFF";
            List<String> width = new ArrayList<String>();
            List<String> align = new ArrayList<String>();





            width.add("20%");
            width.add("8%");
            width.add("8%");
            width.add("8%");
            width.add("8%");
            width.add("8%");
            width.add("8%");
            width.add("8%");
            width.add("8%");
            width.add("8%");
            width.add("8%");

            align.add("left");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");
            align.add("right");





            List<List<String>> data_phanbo = getDataKetquaSanxuat();

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
    public void PDFRender(HttpServletRequest request){
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

//            String abc = "C:\\Users\\Admin\\Documents\\khukt_20221226\\khukt\\src\\main\\webapp\\ketquaSanxuatkinhdoanhPDF.pdf";
//
//            options.setOutputFileName(abc);

            File destFile = new File(request.getRealPath("/")  + "test.pdf");
            System.out.println(destFile.toString());
            options.setOutputFileName(destFile.toString());

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
            design = engine.openReportDesign("C:\\Users\\Admin\\Downloads\\birt-report-designer-all-in-one-4.8.0-20180522-win32.win32.x86_64\\eclipse\\workspace\\newKhanh\\new_report_4.rptdesign");

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