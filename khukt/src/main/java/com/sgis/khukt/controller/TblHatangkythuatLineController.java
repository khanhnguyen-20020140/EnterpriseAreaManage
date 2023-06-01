package com.sgis.khukt.controller;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sgis.khukt.model.TblHatangkythuatPoint;
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

import com.sgis.khukt.model.TblHatangkythuatLine;
import com.sgis.khukt.repository.TblHatangkythuatLineRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/hatangkythuatline")
public class TblHatangkythuatLineController {
    @Autowired
    TblHatangkythuatLineRepository hatangkythuatlineRepository;

    @GetMapping("/hatangkythuatlines")
    public ResponseEntity<List<TblHatangkythuatLine>> getAllTblHatangkythuatLines() {
        try {
            List<TblHatangkythuatLine> hatangkythuatline = new ArrayList<TblHatangkythuatLine>();
            
            hatangkythuatlineRepository.findAll().forEach(hatangkythuatline::add);

            if (hatangkythuatline.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hatangkythuatline, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hatangkythuatline/{gid}")
    public ResponseEntity<TblHatangkythuatLine> getTblHatangkythuatLineById(@PathVariable("gid") Integer id) {
        Optional<TblHatangkythuatLine> hatangkythuatlineData = hatangkythuatlineRepository.findByGid(id);

        if (hatangkythuatlineData.isPresent()) {
            return new ResponseEntity<>(hatangkythuatlineData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/duanhatangkythuat/{idDuanXdht}")
    public ResponseEntity<List<TblHatangkythuatLine>> getTblHatangkythuatLineByIdDuanHTKT(@PathVariable("idDuanXdht") Integer id) {
        List<TblHatangkythuatLine> hatangkythuatlineData = hatangkythuatlineRepository.findByDuanXaydunghatang(id);

        if (hatangkythuatlineData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(hatangkythuatlineData, HttpStatus.OK);
    }


    @GetMapping("/duandautusanxuat/{idDuanDtsx}")
    public ResponseEntity<List<TblHatangkythuatLine>> getTblDtsx(@PathVariable("idDuanDtsx") Integer id) {
        List<TblHatangkythuatLine> hatangkythuatpointData = hatangkythuatlineRepository.findByDuandautusanxuat(id);

        if (hatangkythuatpointData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(hatangkythuatpointData, HttpStatus.OK);
    }

    @PostMapping("/hatangkythuatline")
    public ResponseEntity<TblHatangkythuatLine> createTblHatangkythuatLine(@RequestBody TblHatangkythuatLine hatangkythuatline) {
        try {
            TblHatangkythuatLine _hatangkythuatline = hatangkythuatlineRepository
                    .save(new TblHatangkythuatLine());
            return new ResponseEntity<>(_hatangkythuatline, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/hatangkythuatline/{id}")
    public ResponseEntity<TblHatangkythuatLine> updateTblHatangkythuatLine(@PathVariable("id") Integer id, @RequestBody TblHatangkythuatLine hatangkythuatline) {
        Optional<TblHatangkythuatLine> hatangkythuatlineData = hatangkythuatlineRepository.findByGid(id);

        if (hatangkythuatlineData.isPresent()) {
            TblHatangkythuatLine _hatangkythuatline = hatangkythuatlineData.get();
             _hatangkythuatline.setIdDuanXdht(_hatangkythuatline.getIdDuanXdht());
            
            return new ResponseEntity<>(hatangkythuatlineRepository.save(_hatangkythuatline), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/hatangkythuatlines/{idDuan}")
    public ResponseEntity<TblHatangkythuatLine> updateHangmucTblHatangkythuatPoint(@PathVariable("idDuan") Integer id, @RequestBody String strID_HangmucXDHT) {
        String[] arr = strID_HangmucXDHT.split(",");
        int i;
        for(i=0;i<arr.length;i++){
            Optional<TblHatangkythuatLine> hatangkythuatlineData = hatangkythuatlineRepository.findByGid(Integer.valueOf(arr[i]));

            if (hatangkythuatlineData.isPresent()) {
                TblHatangkythuatLine _hatangkythuatline = hatangkythuatlineData.get();
                if(id == -1) _hatangkythuatline.setIdDuanXdht(null);
                else _hatangkythuatline.setIdDuanXdht(new TblDuanXaydunghatang(id));
                hatangkythuatlineRepository.save(_hatangkythuatline);
            } 
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/hatangkythuatlinesDTSX/{idDuan}")
    public ResponseEntity<TblHatangkythuatLine> updateHangmucTblHatangkythuatLine_DADTSX(@PathVariable("idDuan") Integer id, @RequestBody String strID_HangmucXDHT) {
        String[] arr = strID_HangmucXDHT.split(",");
        int i;
        for(i=0;i<arr.length;i++){
            Optional<TblHatangkythuatLine> hatangkythuatlineData = hatangkythuatlineRepository.findByGid(Integer.valueOf(arr[i]));

            if (hatangkythuatlineData.isPresent()) {
                TblHatangkythuatLine _hatangkythuatline = hatangkythuatlineData.get();
                if(id == -1) _hatangkythuatline.setIdDuanDtsx(null);
                else _hatangkythuatline.setIdDuanDtsx(new TblDuanDautusanxuat(id));
                hatangkythuatlineRepository.save(_hatangkythuatline);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/hatangkythuatline/{id}")
    public ResponseEntity<HttpStatus> deleteTblHatangkythuatLine(@PathVariable("id") Integer id) {
        try {
            hatangkythuatlineRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/hatangkythuatline")
    public ResponseEntity<HttpStatus> deleteAllTblHatangkythuatLines() {
        try {
            hatangkythuatlineRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}