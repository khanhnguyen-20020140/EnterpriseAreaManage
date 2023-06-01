package com.sgis.khukt.controller;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sgis.khukt.model.TblHatangkythuatPolygon;
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

import com.sgis.khukt.model.TblHatangkythuatPoint;
import com.sgis.khukt.repository.TblHatangkythuatPointRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/hatangkythuatpoint")
public class TblHatangkythuatPointController {
    @Autowired
    TblHatangkythuatPointRepository hatangkythuatpointRepository;

    @GetMapping("/hatangkythuatpoints")
    public ResponseEntity<List<TblHatangkythuatPoint>> getAllTblHatangkythuatPoints() {
        try {
            List<TblHatangkythuatPoint> hatangkythuatpoint = new ArrayList<TblHatangkythuatPoint>();
            
            hatangkythuatpointRepository.findAll().forEach(hatangkythuatpoint::add);

            if (hatangkythuatpoint.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(hatangkythuatpoint, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/hatangkythuatpoint/{gid}")
    public ResponseEntity<TblHatangkythuatPoint> getTblHatangkythuatPointById(@PathVariable("gid") Integer id) {
        Optional<TblHatangkythuatPoint> hatangkythuatpointData = hatangkythuatpointRepository.findByGid(id);

        if (hatangkythuatpointData.isPresent()) {
            return new ResponseEntity<>(hatangkythuatpointData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/duanhatangkythuat/{idDuanXdht}")
    public ResponseEntity<List<TblHatangkythuatPoint>> getTblHatangkythuatPointByIdDuanHTKT(@PathVariable("idDuanXdht") Integer id) {
        List<TblHatangkythuatPoint> hatangkythuatpointData = hatangkythuatpointRepository.findByDuanXaydunghatang(id);

        if (hatangkythuatpointData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        
        return new ResponseEntity<>(hatangkythuatpointData, HttpStatus.OK);
    }

    @GetMapping("/duandautusanxuat/{idDuanDtsx}")
    public ResponseEntity<List<TblHatangkythuatPoint>> getTblDtsx(@PathVariable("idDuanDtsx") Integer id) {
        List<TblHatangkythuatPoint> hatangkythuatpointData = hatangkythuatpointRepository.findByDuandautusanxuat(id);

        if (hatangkythuatpointData.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(hatangkythuatpointData, HttpStatus.OK);
    }

    @PostMapping("/hatangkythuatpoint")
    public ResponseEntity<TblHatangkythuatPoint> createTblHatangkythuatPoint(@RequestBody TblHatangkythuatPoint hatangkythuatpoint) {
        try {
            TblHatangkythuatPoint _hatangkythuatpoint = hatangkythuatpointRepository
                    .save(new TblHatangkythuatPoint());
            return new ResponseEntity<>(_hatangkythuatpoint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/hatangkythuatpoint/{id}")
    public ResponseEntity<TblHatangkythuatPoint> updateTblHatangkythuatPoint(@PathVariable("id") Integer id, @RequestBody TblHatangkythuatPoint hatangkythuatpoint) {
        Optional<TblHatangkythuatPoint> hatangkythuatpointData = hatangkythuatpointRepository.findByGid(id);

        if (hatangkythuatpointData.isPresent()) {
            TblHatangkythuatPoint _hatangkythuatpoint = hatangkythuatpointData.get();
            _hatangkythuatpoint.setIdDuanXdht(_hatangkythuatpoint.getIdDuanXdht());
            
            return new ResponseEntity<>(hatangkythuatpointRepository.save(_hatangkythuatpoint), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping("/hatangkythuatpoints/{idDuan}")
    public ResponseEntity<TblHatangkythuatPoint> updateHangmucTblHatangkythuatPoint(@PathVariable("idDuan") Integer id, @RequestBody String strID_HangmucXDHT) {
        String[] arr = strID_HangmucXDHT.split(",");
        int i;
        for(i=0;i<arr.length;i++){
            Optional<TblHatangkythuatPoint> hatangkythuatpointData = hatangkythuatpointRepository.findByGid(Integer.valueOf(arr[i]));

            if (hatangkythuatpointData.isPresent()) {
                TblHatangkythuatPoint _hatangkythuatpoint = hatangkythuatpointData.get();
                if(id == -1) _hatangkythuatpoint.setIdDuanXdht(null);
                else _hatangkythuatpoint.setIdDuanXdht(new TblDuanXaydunghatang(id));
                hatangkythuatpointRepository.save(_hatangkythuatpoint);
            } 
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/hatangkythuatpointsDTSX/{idDuan}")
    public ResponseEntity<TblHatangkythuatPoint> updateHangmucTblHatangkythuatPoint_DTSX(@PathVariable("idDuan") Integer id, @RequestBody String strID_HangmucXDHT) {
        String[] arr = strID_HangmucXDHT.split(",");
        int i;
        for(i=0;i<arr.length;i++){
            Optional<TblHatangkythuatPoint> hatangkythuatpointData = hatangkythuatpointRepository.findByGid(Integer.valueOf(arr[i]));

            if (hatangkythuatpointData.isPresent()) {
                TblHatangkythuatPoint _hatangkythuatpoint = hatangkythuatpointData.get();
                if(id == -1) _hatangkythuatpoint.setIdDuanDtsx(null);
                else _hatangkythuatpoint.setIdDuanDtsx(new TblDuanDautusanxuat(id));
                hatangkythuatpointRepository.save(_hatangkythuatpoint);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @DeleteMapping("/hatangkythuatpoint/{id}")
    public ResponseEntity<HttpStatus> deleteTblHatangkythuatPoint(@PathVariable("id") Integer id) {
        try {
            hatangkythuatpointRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/hatangkythuatpoint")
    public ResponseEntity<HttpStatus> deleteAllTblHatangkythuatPoints() {
        try {
            hatangkythuatpointRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}