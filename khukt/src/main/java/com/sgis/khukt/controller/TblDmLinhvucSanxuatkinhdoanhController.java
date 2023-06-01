package com.sgis.khukt.controller;

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

import com.sgis.khukt.model.TblDmLinhvucSanxuatkinhdoanh;
import com.sgis.khukt.repository.TblDmLinhvucSanxuatkinhdoanhRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/dmlinhvucsanxuatkinhdoanh")
public class TblDmLinhvucSanxuatkinhdoanhController {
    @Autowired
    TblDmLinhvucSanxuatkinhdoanhRepository dmLinhvucSanxuatKinhdoanhRepository;

    @GetMapping("/dmlinhvucsanxuatkinhdoanhs")
    public ResponseEntity<List<TblDmLinhvucSanxuatkinhdoanh>> getAllTblDmLinhvucSanxuatkinhdoanhs(@RequestParam(required = false) String ten) {
        try {
            List<TblDmLinhvucSanxuatkinhdoanh> dmlinhvucsanxuatkinhdoanh = new ArrayList<TblDmLinhvucSanxuatkinhdoanh>();

            if (ten == null) {
                dmLinhvucSanxuatKinhdoanhRepository.findAll().forEach(dmlinhvucsanxuatkinhdoanh::add);
            } else {
                dmLinhvucSanxuatKinhdoanhRepository.findByLinhvucSanxuatkinhdoanh(ten).forEach(dmlinhvucsanxuatkinhdoanh::add);
            }

            if (dmlinhvucsanxuatkinhdoanh.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dmlinhvucsanxuatkinhdoanh, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dmlinhvucsanxuatkinhdoanh/{id}")
    public ResponseEntity<TblDmLinhvucSanxuatkinhdoanh> getTblDmLinhvucSanxuatkinhdoanhById(@PathVariable("id") long id) {
        Optional<TblDmLinhvucSanxuatkinhdoanh> dmlinhvucsanxuatkinhdoanhData = dmLinhvucSanxuatKinhdoanhRepository.findById(id);

        if (dmlinhvucsanxuatkinhdoanhData.isPresent()) {
            return new ResponseEntity<>(dmlinhvucsanxuatkinhdoanhData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dmlinhvucsanxuatkinhdoanh")
    public ResponseEntity<TblDmLinhvucSanxuatkinhdoanh> createTblDmLinhvucSanxuatkinhdoanh(@RequestBody TblDmLinhvucSanxuatkinhdoanh dmlinhvucsanxuatkinhdoanh) {
        try {
            TblDmLinhvucSanxuatkinhdoanh _dmlinhvucsanxuatkinhdoanh = dmLinhvucSanxuatKinhdoanhRepository
                    .save(new TblDmLinhvucSanxuatkinhdoanh());
            return new ResponseEntity<>(_dmlinhvucsanxuatkinhdoanh, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dmlinhvucsanxuatkinhdoanh/{id}")
    public ResponseEntity<TblDmLinhvucSanxuatkinhdoanh> updateTblDmLinhvucSanxuatkinhdoanh(@PathVariable("id") long id, @RequestBody TblDmLinhvucSanxuatkinhdoanh dmlinhvucsanxuatkinhdoanh) {
        Optional<TblDmLinhvucSanxuatkinhdoanh> dmlinhvucsanxuatkinhdoanhData = dmLinhvucSanxuatKinhdoanhRepository.findById(id);

        if (dmlinhvucsanxuatkinhdoanhData.isPresent()) {
            TblDmLinhvucSanxuatkinhdoanh _dmlinhvucsanxuatkinhdoanh = dmlinhvucsanxuatkinhdoanhData.get();
            _dmlinhvucsanxuatkinhdoanh.setLinhvucSanxuatkinhdoanh(_dmlinhvucsanxuatkinhdoanh.getLinhvucSanxuatkinhdoanh());
            
            return new ResponseEntity<>(dmLinhvucSanxuatKinhdoanhRepository.save(_dmlinhvucsanxuatkinhdoanh), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dmlinhvucsanxuatkinhdoanh/{id}")
    public ResponseEntity<HttpStatus> deleteTblDmLinhvucSanxuatkinhdoanh(@PathVariable("id") long id) {
        try {
            dmLinhvucSanxuatKinhdoanhRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dmlinhvucsanxuatkinhdoanh")
    public ResponseEntity<HttpStatus> deleteAllTblDmLinhvucSanxuatkinhdoanhs() {
        try {
            dmLinhvucSanxuatKinhdoanhRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}