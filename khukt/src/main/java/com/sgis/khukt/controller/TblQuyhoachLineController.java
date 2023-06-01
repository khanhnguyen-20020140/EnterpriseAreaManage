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

import com.sgis.khukt.model.TblQuyhoachLine;
import com.sgis.khukt.repository.TblQuyhoachLineRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/quyhoachline")
public class TblQuyhoachLineController {
    @Autowired
    TblQuyhoachLineRepository quyhoachlineRepository;

    @GetMapping("/quyhoachlines")
    public ResponseEntity<List<TblQuyhoachLine>> getAllTblQuyhoachLines() {
        try {
            List<TblQuyhoachLine> quyhoachline = new ArrayList<TblQuyhoachLine>();
            
            quyhoachlineRepository.findAll().forEach(quyhoachline::add);

            if (quyhoachline.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(quyhoachline, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quyhoachline/{id}")
    public ResponseEntity<TblQuyhoachLine> getTblQuyhoachLineById(@PathVariable("id") Integer id) {
        Optional<TblQuyhoachLine> quyhoachlineData = quyhoachlineRepository.findById(id);

        if (quyhoachlineData.isPresent()) {
            return new ResponseEntity<>(quyhoachlineData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/quyhoachline")
    public ResponseEntity<TblQuyhoachLine> createTblQuyhoachLine(@RequestBody TblQuyhoachLine quyhoachline) {
        try {
            TblQuyhoachLine _quyhoachline = quyhoachlineRepository
                    .save(new TblQuyhoachLine());
            return new ResponseEntity<>(_quyhoachline, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/quyhoachline/{id}")
    public ResponseEntity<TblQuyhoachLine> updateTblQuyhoachLine(@PathVariable("id") Integer id, @RequestBody TblQuyhoachLine quyhoachline) {
        Optional<TblQuyhoachLine> quyhoachlineData = quyhoachlineRepository.findById(id);

        if (quyhoachlineData.isPresent()) {
            TblQuyhoachLine _quyhoachline = quyhoachlineData.get();

             _quyhoachline.setIdDuanDtsx(quyhoachline.getIdDuanDtsx());
            return new ResponseEntity<>(quyhoachlineRepository.save(_quyhoachline), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quyhoachline/{id}")
    public ResponseEntity<HttpStatus> deleteTblQuyhoachLine(@PathVariable("id") Integer id) {
        try {
            quyhoachlineRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/quyhoachline")
    public ResponseEntity<HttpStatus> deleteAllTblQuyhoachLines() {
        try {
            quyhoachlineRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}