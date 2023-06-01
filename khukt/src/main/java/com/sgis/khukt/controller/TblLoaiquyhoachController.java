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

import com.sgis.khukt.model.TblLoaiquyhoach;
import com.sgis.khukt.repository.TblLoaiquyhoachRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/loaiquyhoach")
public class TblLoaiquyhoachController {
    @Autowired
    TblLoaiquyhoachRepository loaiQuyhoachRepository;

    @GetMapping("/loaiquyhoachs")
    public ResponseEntity<List<TblLoaiquyhoach>> getAllTblLoaiquyhoachs(@RequestParam(required = false) String ten) {
        try {
            List<TblLoaiquyhoach> loaiquyhoach = new ArrayList<TblLoaiquyhoach>();

            if (ten == null) {
                loaiQuyhoachRepository.findAll().forEach(loaiquyhoach::add);
            } else {
                loaiQuyhoachRepository.findByTen(ten).forEach(loaiquyhoach::add);
            }

            if (loaiquyhoach.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(loaiquyhoach, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/loaiquyhoach/{id}")
    public ResponseEntity<TblLoaiquyhoach> getTblLoaiquyhoachById(@PathVariable("id") long id) {
        Optional<TblLoaiquyhoach> loaiquyhoachData = loaiQuyhoachRepository.findById(id);

        if (loaiquyhoachData.isPresent()) {
            return new ResponseEntity<>(loaiquyhoachData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/loaiquyhoach")
    public ResponseEntity<TblLoaiquyhoach> createTblLoaiquyhoach(@RequestBody TblLoaiquyhoach loaiquyhoach) {
        try {
            TblLoaiquyhoach _loaiquyhoach = loaiQuyhoachRepository
                    .save(new TblLoaiquyhoach());
            return new ResponseEntity<>(_loaiquyhoach, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/loaiquyhoach/{id}")
    public ResponseEntity<TblLoaiquyhoach> updateTblLoaiquyhoach(@PathVariable("id") long id, @RequestBody TblLoaiquyhoach loaiquyhoach) {
        Optional<TblLoaiquyhoach> loaiquyhoachData = loaiQuyhoachRepository.findById(id);

        if (loaiquyhoachData.isPresent()) {
            TblLoaiquyhoach _loaiquyhoach = loaiquyhoachData.get();
            _loaiquyhoach.setTen(_loaiquyhoach.getTen());
            _loaiquyhoach.setKieudulieu(_loaiquyhoach.getKieudulieu());
            
            return new ResponseEntity<>(loaiQuyhoachRepository.save(_loaiquyhoach), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/loaiquyhoach/{id}")
    public ResponseEntity<HttpStatus> deleteTblLoaiquyhoach(@PathVariable("id") long id) {
        try {
            loaiQuyhoachRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/loaiquyhoach")
    public ResponseEntity<HttpStatus> deleteAllTblLoaiquyhoachs() {
        try {
            loaiQuyhoachRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}