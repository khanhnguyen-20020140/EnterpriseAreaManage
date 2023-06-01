package com.sgis.khukt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.sgis.khukt.model.TblKhuChuyennganh;
import com.sgis.khukt.repository.TblKhuChuyennganhRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})

@RestController
@RequestMapping("/khuchuyennganh")
public class TblKhuChuyennganhController {
    @Autowired
    TblKhuChuyennganhRepository khuChuyennganhRepository;

    @GetMapping("/prekhuchuyennganh/{id}")
    public Long prekhuchuyennganh(@PathVariable("id") Integer id) {
        List<TblKhuChuyennganh> TblKhuChuyennganh = new ArrayList<TblKhuChuyennganh>();

        Long preId = Long.valueOf(-1);
        TblKhuChuyennganh = khuChuyennganhRepository.findAll(Sort.by("ten"));
        for (int i = 0; i < TblKhuChuyennganh.size(); i++) {
            if ((long) TblKhuChuyennganh.get(i).getId() == (long) id) {
                break;
            } else {
                preId = TblKhuChuyennganh.get(i).getId();
            }

        }
        return preId;
    }

    @GetMapping("/khuchuyennganhs")
    public ResponseEntity<List<TblKhuChuyennganh>> getAllTblKhuChuyennganhs(@RequestParam(required = false) String ten) {
        try {
            List<TblKhuChuyennganh> khuchuyennganh = new ArrayList<TblKhuChuyennganh>();

            if (ten == null) {
                khuChuyennganhRepository.findAll().forEach(khuchuyennganh::add);
            } else {
                khuChuyennganhRepository.findByTen(ten).forEach(khuchuyennganh::add);
            }

            if (khuchuyennganh.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(khuchuyennganh, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/khuchuyennganh/{id}")
    public ResponseEntity<TblKhuChuyennganh> getTblKhuChuyennganhById(@PathVariable("id") long id) {
        Optional<TblKhuChuyennganh> khuchuyennganhData = khuChuyennganhRepository.findById(id);

        if (khuchuyennganhData.isPresent()) {
            return new ResponseEntity<>(khuchuyennganhData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/khuchuyennganh")
    public ResponseEntity<TblKhuChuyennganh> createTblKhuChuyennganh(@RequestBody TblKhuChuyennganh khuchuyennganh) {
        try {
            TblKhuChuyennganh _khuchuyennganh = khuChuyennganhRepository
                    .save(khuchuyennganh);
            return new ResponseEntity<>(_khuchuyennganh, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/khuchuyennganh/{id}")
    public ResponseEntity<TblKhuChuyennganh> updateTblKhuChuyennganh(@PathVariable("id") long id, @RequestBody TblKhuChuyennganh khuchuyennganh) {
        Optional<TblKhuChuyennganh> khuchuyennganhData = khuChuyennganhRepository.findById(id);

        if (khuchuyennganhData.isPresent()) {
            TblKhuChuyennganh _khuchuyennganh = khuchuyennganhData.get();
            
            if(khuchuyennganh.getDientich() != null) _khuchuyennganh.setDientich(khuchuyennganh.getDientich());
            
            if(khuchuyennganh.getIdKhuKinhte() == null || khuchuyennganh.getIdKhuKinhte().getId() == -1)
                _khuchuyennganh.setIdKhuKinhte(null);
            else
                _khuchuyennganh.setIdKhuKinhte(khuchuyennganh.getIdKhuKinhte());                       
            
            if(khuchuyennganh.getIdTinh() != null) _khuchuyennganh.setIdTinh(khuchuyennganh.getIdTinh());
            
            if(khuchuyennganh.getLoaiKhuChuyennganh() == null || khuchuyennganh.getLoaiKhuChuyennganh().getId() == -1)
                _khuchuyennganh.setLoaiKhuChuyennganh(null);
            else
                _khuchuyennganh.setLoaiKhuChuyennganh(khuchuyennganh.getLoaiKhuChuyennganh());           
            
            if(khuchuyennganh.getTen() != null) _khuchuyennganh.setTen(khuchuyennganh.getTen());
            else _khuchuyennganh.setTen("");
            if(khuchuyennganh.getTomtat() != null) _khuchuyennganh.setTomtat(khuchuyennganh.getTomtat());
            else _khuchuyennganh.setTomtat("");
            
            return new ResponseEntity<>(khuChuyennganhRepository.save(_khuchuyennganh), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/khuchuyennganh/{id}")
    public ResponseEntity<HttpStatus> deleteTblKhuChuyennganh(@PathVariable("id") long id) {
        try {
            khuChuyennganhRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/khuchuyennganh")
    public ResponseEntity<HttpStatus> deleteAllTblKhuChuyennganhs() {
        try {
            khuChuyennganhRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}