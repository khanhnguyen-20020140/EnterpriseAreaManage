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

import com.sgis.khukt.model.TblDmTrangthai;
import com.sgis.khukt.repository.TblDmTrangthaiRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/dmtrangthai")
public class TblDmTrangthaiController {
    @Autowired
    TblDmTrangthaiRepository dmTrangthaiRepository;

    @GetMapping("/dmtrangthais")
    public ResponseEntity<List<TblDmTrangthai>> getAllTblDmTrangthais(@RequestParam(required = false) String ten) {
        try {
            List<TblDmTrangthai> dmtrangthai = new ArrayList<TblDmTrangthai>();

            if (ten == null) {
                dmTrangthaiRepository.findAll().forEach(dmtrangthai::add);
            } else {
                dmTrangthaiRepository.findByTrangthai(ten).forEach(dmtrangthai::add);
            }

            if (dmtrangthai.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dmtrangthai, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dmtrangthai/{id}")
    public ResponseEntity<TblDmTrangthai> getTblDmTrangthaiById(@PathVariable("id") long id) {
        Optional<TblDmTrangthai> dmtrangthaiData = dmTrangthaiRepository.findById(id);

        if (dmtrangthaiData.isPresent()) {
            return new ResponseEntity<>(dmtrangthaiData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dmtrangthai")
    public ResponseEntity<TblDmTrangthai> createTblDmTrangthai(@RequestBody TblDmTrangthai dmtrangthai) {
        try {
            TblDmTrangthai _dmtrangthai = dmTrangthaiRepository
                    .save(new TblDmTrangthai());
            return new ResponseEntity<>(_dmtrangthai, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dmtrangthai/{id}")
    public ResponseEntity<TblDmTrangthai> updateTblDmTrangthai(@PathVariable("id") long id, @RequestBody TblDmTrangthai dmtrangthai) {
        Optional<TblDmTrangthai> dmtrangthaiData = dmTrangthaiRepository.findById(id);

        if (dmtrangthaiData.isPresent()) {
            TblDmTrangthai _dmtrangthai = dmtrangthaiData.get();
            _dmtrangthai.setTrangthai(_dmtrangthai.getTrangthai());
            
            return new ResponseEntity<>(dmTrangthaiRepository.save(_dmtrangthai), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dmtrangthai/{id}")
    public ResponseEntity<HttpStatus> deleteTblDmTrangthai(@PathVariable("id") long id) {
        try {
            dmTrangthaiRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dmtrangthai")
    public ResponseEntity<HttpStatus> deleteAllTblDmTrangthais() {
        try {
            dmTrangthaiRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}