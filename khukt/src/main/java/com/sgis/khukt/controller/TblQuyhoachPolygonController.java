package com.sgis.khukt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.sgis.khukt.model.TblDuanDautusanxuat;
import com.sgis.khukt.model.TblDuanXaydunghatang;
import com.sgis.khukt.model.TblQuyhoachPolygon;
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

import com.sgis.khukt.model.TblQuyhoachPolygon;
import com.sgis.khukt.repository.TblQuyhoachPolygonRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/quyhoachpolygon")
public class TblQuyhoachPolygonController {
    @Autowired
    TblQuyhoachPolygonRepository quyhoachpolygonRepository;

    @GetMapping("/quyhoachpolygons")
    public ResponseEntity<List<TblQuyhoachPolygon>> getAllTblQuyhoachPolygons() {
        try {
            List<TblQuyhoachPolygon> quyhoachpolygon = new ArrayList<TblQuyhoachPolygon>();
            
            quyhoachpolygonRepository.findAll().forEach(quyhoachpolygon::add);

            if (quyhoachpolygon.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(quyhoachpolygon, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quyhoachpolygon/{id}")
    public ResponseEntity<TblQuyhoachPolygon> getTblQuyhoachPolygonById(@PathVariable("id") Integer id) {
        Optional<TblQuyhoachPolygon> quyhoachpolygonData = quyhoachpolygonRepository.findById(id);

        if (quyhoachpolygonData.isPresent()) {
            return new ResponseEntity<>(quyhoachpolygonData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/duanhatangkythuat/{idDuanXdht}")
    public ResponseEntity<List<TblQuyhoachPolygon>> getquyhoachPolygonByIdDuanHTKT(@PathVariable("idDuanXdht") Integer id) {
        List<TblQuyhoachPolygon> quyhoachpolygon = quyhoachpolygonRepository.findByDuanXaydunghatang(id);
        if (quyhoachpolygon.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(quyhoachpolygon, HttpStatus.OK);

    }




    @GetMapping("/duandautusanxuat/{idDuanXdht}")
    public ResponseEntity<List<TblQuyhoachPolygon>> getTblQuyhoachPolygonByIdDuanHTKT(@PathVariable("idDuanXdht") Integer id) {
        List<TblQuyhoachPolygon> quyhoachpolygon = quyhoachpolygonRepository.findByDuandautusanxuat(id);

        if (quyhoachpolygon.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(quyhoachpolygon, HttpStatus.OK);
    }

    @PostMapping("/quyhoachpolygon")
    public ResponseEntity<TblQuyhoachPolygon> createTblQuyhoachPolygon(@RequestBody TblQuyhoachPolygon quyhoachpolygon) {
        try {
            TblQuyhoachPolygon _quyhoachpolygon = quyhoachpolygonRepository
                    .save(new TblQuyhoachPolygon());
            return new ResponseEntity<>(_quyhoachpolygon, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/quyhoachpolygonsXDHT/{idDuan}")
    public ResponseEntity<TblQuyhoachPolygon> updateHangmucTblquyhoachPoint(@PathVariable("idDuan") Integer id, @RequestBody String strID_HangmucXDHT) {
        String[] arr = strID_HangmucXDHT.split(",");
        int i;
        for(i=0;i<arr.length;i++){
            Optional<TblQuyhoachPolygon> quyhoachpolygonData = quyhoachpolygonRepository.findByGid(Integer.valueOf(arr[i]));

            if (quyhoachpolygonData.isPresent()) {
                TblQuyhoachPolygon _quyhoachpolygon = quyhoachpolygonData.get();
                if(id == -1) _quyhoachpolygon.setIdDuanXdht(null);
                else _quyhoachpolygon.setIdDuanXdht(new TblDuanXaydunghatang(id));
                quyhoachpolygonRepository.save(_quyhoachpolygon);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/quyhoachpolygonsDTSX/{idDuan}")
    public ResponseEntity<TblQuyhoachPolygon> updateHangmucTblQuyhoachPolygonDTSX(@PathVariable("idDuan") Integer id, @RequestBody String strID_HangmucXDHT) {
        String[] arr = strID_HangmucXDHT.split(",");
        int i;
        for(i=0;i<arr.length;i++){
            System.out.println(arr[i]);
            Optional<TblQuyhoachPolygon> quyhoachpolygonData = quyhoachpolygonRepository.findByGid(Integer.valueOf(arr[i]));

            if (quyhoachpolygonData.isPresent()) {
                TblQuyhoachPolygon _quyhoachpolygon = quyhoachpolygonData.get();
                if(id == -1) {
                    _quyhoachpolygon.setIdDuanDtsx(null);

                }

                else _quyhoachpolygon.setIdDuanDtsx(new TblDuanDautusanxuat(id));
                quyhoachpolygonRepository.save(_quyhoachpolygon);
            }
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/quyhoachpolygon/{id}")
    public ResponseEntity<TblQuyhoachPolygon> updateTblQuyhoachPolygon(@PathVariable("id") Integer id, @RequestBody TblQuyhoachPolygon quyhoachpolygon) {
        Optional<TblQuyhoachPolygon> quyhoachpolygonData = quyhoachpolygonRepository.findById(id);

        if (quyhoachpolygonData.isPresent()) {
            TblQuyhoachPolygon _quyhoachpolygon = quyhoachpolygonData.get();
//            _quyhoachpolygon.setDientich(_quyhoachpolygon.getDientich());
//            _quyhoachpolygon.setGhichu(_quyhoachpolygon.getGhichu());
//            _quyhoachpolygon.setGeom(_quyhoachpolygon.getGeom());
               _quyhoachpolygon.setIdDuanDtsx(quyhoachpolygon.getIdDuanDtsx());
            
            return new ResponseEntity<>(quyhoachpolygonRepository.save(_quyhoachpolygon), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quyhoachpolygon/{id}")
    public ResponseEntity<HttpStatus> deleteTblQuyhoachPolygon(@PathVariable("id") Integer id) {
        try {
            quyhoachpolygonRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/quyhoachpolygon")
    public ResponseEntity<HttpStatus> deleteAllTblQuyhoachPolygons() {
        try {
            quyhoachpolygonRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}