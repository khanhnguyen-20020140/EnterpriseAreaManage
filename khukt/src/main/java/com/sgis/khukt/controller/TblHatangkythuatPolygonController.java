package com.sgis.khukt.controller;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.sgis.khukt.model.TblHatangkythuatPolygon;
import com.sgis.khukt.repository.TblHatangkythuatPolygonRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/hatangkythuatpolygon")
public class TblHatangkythuatPolygonController {
    @Autowired
    TblHatangkythuatPolygonRepository hatangkythuatpolygonRepository;

    @GetMapping("/hatangkythuatpolygons")
    public ResponseEntity<List<TblHatangkythuatPolygon>> getAllTblHatangkythuatPolygons() {
        try {
            List<TblHatangkythuatPolygon> hatangkythuatpolygon = new ArrayList<TblHatangkythuatPolygon>();
            
            hatangkythuatpolygonRepository.findAll().forEach(hatangkythuatpolygon::add);

            if (hatangkythuatpolygon.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hatangkythuatpolygon, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hatangkythuatpolygon/{gid}")
    public ResponseEntity<TblHatangkythuatPolygon> getTblHatangkythuatPolygonById(@PathVariable("gid") Integer id) {
        Optional<TblHatangkythuatPolygon> hatangkythuatpolygonData = hatangkythuatpolygonRepository.findByGid(id);

        if (hatangkythuatpolygonData.isPresent()) {
            return new ResponseEntity<>(hatangkythuatpolygonData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/duanhatangkythuat/tenthanhphan/{idDuanXdht}")
    public ResponseEntity<List<String>> getTenThanhphanByDuanxaydunghatang(@PathVariable("idDuanXdht") Integer id) {
        List<String> tenThanhphan = hatangkythuatpolygonRepository.getTenThanhphanByDuanxaydunghatang(id);

        if (tenThanhphan.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tenThanhphan, HttpStatus.OK);
    }

    @GetMapping("/duandautusanxuat/tenthanhphan/{idDuanDtsx}")
    public ResponseEntity<List<String>> getTenThanhphanByDuandautusanxuat(@PathVariable("idDuanDtsx") Integer id) {
        List<String> tenThanhphan = hatangkythuatpolygonRepository.getTenThanhphanByDuandautusanxuat(id);

        if (tenThanhphan.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tenThanhphan, HttpStatus.OK);
    }
    
    @GetMapping("/duanhatangkythuat/{idDuanXdht}")
    public ResponseEntity<List<TblHatangkythuatPolygon>> getTblHatangkythuatPolygonByIdDuanHTKT(@PathVariable("idDuanXdht") Integer id) {
        List<TblHatangkythuatPolygon> hatangkythuatpolygonData = hatangkythuatpolygonRepository.findByDuanXaydunghatang(id);

        if (hatangkythuatpolygonData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(hatangkythuatpolygonData, HttpStatus.OK);
    }

    @GetMapping("/duandautusanxuat/{idDuanDtsx}")
    public ResponseEntity<List<TblHatangkythuatPolygon>> getTblDtsx(@PathVariable("idDuanDtsx") Integer id) {
        List<TblHatangkythuatPolygon> hatangkythuatpolygonData = hatangkythuatpolygonRepository.findByDuandautusanxuat(id);

        if (hatangkythuatpolygonData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(hatangkythuatpolygonData, HttpStatus.OK);
    }

    @PostMapping("/hatangkythuatpolygon")
    public ResponseEntity<TblHatangkythuatPolygon> createTblHatangkythuatPolygon(@RequestBody TblHatangkythuatPolygon hatangkythuatpolygon) {
        try {
            TblHatangkythuatPolygon _hatangkythuatpolygon = hatangkythuatpolygonRepository
                    .save(new TblHatangkythuatPolygon());
            return new ResponseEntity<>(_hatangkythuatpolygon, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/hatangkythuatpolygon/{id}")
    public ResponseEntity<TblHatangkythuatPolygon> updateTblHatangkythuatPolygon(@PathVariable("id") Integer id, @RequestBody TblHatangkythuatPolygon hatangkythuatpolygon) {
        Optional<TblHatangkythuatPolygon> hatangkythuatpolygonData = hatangkythuatpolygonRepository.findByGid(id);

        if (hatangkythuatpolygonData.isPresent()) {
            TblHatangkythuatPolygon _hatangkythuatpolygon = hatangkythuatpolygonData.get();
            _hatangkythuatpolygon.setIdDuanXdht(hatangkythuatpolygon.getIdDuanXdht());
            
            return new ResponseEntity<>(hatangkythuatpolygonRepository.save(_hatangkythuatpolygon), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/hatangkythuatpolygons/{idDuan}")
    public ResponseEntity<TblHatangkythuatPolygon> updateHangmucTblHatangkythuatPoint(@PathVariable("idDuan") Integer id, @RequestBody String strID_HangmucXDHT) {
        String[] arr = strID_HangmucXDHT.split(",");
        int i;
        for(i=0;i<arr.length;i++){
            Optional<TblHatangkythuatPolygon> hatangkythuatpolygonData = hatangkythuatpolygonRepository.findByGid(Integer.valueOf(arr[i]));

            if (hatangkythuatpolygonData.isPresent()) {
                TblHatangkythuatPolygon _hatangkythuatpolygon = hatangkythuatpolygonData.get();
                if(id == -1) _hatangkythuatpolygon.setIdDuanXdht(null);
                else _hatangkythuatpolygon.setIdDuanXdht(new TblDuanXaydunghatang(id));
                hatangkythuatpolygonRepository.save(_hatangkythuatpolygon);
            } 
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/hatangkythuatpolygonsDTSX/{idDuan}")
    public ResponseEntity<TblHatangkythuatPolygon> updateHangmucTblHatangkythuatPolygonDTSX(@PathVariable("idDuan") Integer id, @RequestBody String strID_HangmucXDHT) {
        String[] arr = strID_HangmucXDHT.split(",");
        int i;
        for(i=0;i<arr.length;i++){
            System.out.println(arr[i]);
            Optional<TblHatangkythuatPolygon> hatangkythuatpolygonData = hatangkythuatpolygonRepository.findByGid(Integer.valueOf(arr[i]));

            if (hatangkythuatpolygonData.isPresent()) {
                TblHatangkythuatPolygon _hatangkythuatpolygon = hatangkythuatpolygonData.get();
                if(id == -1) {
                    _hatangkythuatpolygon.setIdDuanDtsx(null);

                }

                else _hatangkythuatpolygon.setIdDuanDtsx(new TblDuanDautusanxuat(id));
                hatangkythuatpolygonRepository.save(_hatangkythuatpolygon);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/hatangkythuatpolygon/{id}")
    public ResponseEntity<HttpStatus> deleteTblHatangkythuatPolygon(@PathVariable("id") Integer id) {
        try {
            hatangkythuatpolygonRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/hatangkythuatpolygon")
    public ResponseEntity<HttpStatus> deleteAllTblHatangkythuatPolygons() {
        try {
            hatangkythuatpolygonRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}