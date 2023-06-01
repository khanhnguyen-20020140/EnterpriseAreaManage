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

import com.sgis.khukt.model.TblDmLoaiDoanhnghiep;
import com.sgis.khukt.repository.TblDmLoaiDoanhnghiepRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})


@RestController
@RequestMapping("/dmloaidoanhnghiep")
public class TblDmLoaiDoanhnghiepController {
    @Autowired
    TblDmLoaiDoanhnghiepRepository dmLoaiDoanhnghiepRepository;

    @GetMapping("/dmloaidoanhnghieps")
    public ResponseEntity<List<TblDmLoaiDoanhnghiep>> getAllTblDmLoaiDoanhnghieps(@RequestParam(required = false) String ten) {
        try {
            List<TblDmLoaiDoanhnghiep> dmloaidoanhnghiep = new ArrayList<TblDmLoaiDoanhnghiep>();

            if (ten == null) {
                dmLoaiDoanhnghiepRepository.findAll().forEach(dmloaidoanhnghiep::add);
            } else {
                dmLoaiDoanhnghiepRepository.findByLoaiDoanhnghiep(ten).forEach(dmloaidoanhnghiep::add);
            }

            if (dmloaidoanhnghiep.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dmloaidoanhnghiep, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dmloaidoanhnghiep/{id}")
    public ResponseEntity<TblDmLoaiDoanhnghiep> getTblDmLoaiDoanhnghiepById(@PathVariable("id") long id) {
        Optional<TblDmLoaiDoanhnghiep> dmloaidoanhnghiepData = dmLoaiDoanhnghiepRepository.findById(id);

        if (dmloaidoanhnghiepData.isPresent()) {
            return new ResponseEntity<>(dmloaidoanhnghiepData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dmloaidoanhnghiep")
    public ResponseEntity<TblDmLoaiDoanhnghiep> createTblDmLoaiDoanhnghiep(@RequestBody TblDmLoaiDoanhnghiep dmloaidoanhnghiep) {
        try {
            TblDmLoaiDoanhnghiep _dmloaidoanhnghiep = dmLoaiDoanhnghiepRepository
                    .save(new TblDmLoaiDoanhnghiep());
            return new ResponseEntity<>(_dmloaidoanhnghiep, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dmloaidoanhnghiep/{id}")
    public ResponseEntity<TblDmLoaiDoanhnghiep> updateTblDmLoaiDoanhnghiep(@PathVariable("id") long id, @RequestBody TblDmLoaiDoanhnghiep dmloaidoanhnghiep) {
        Optional<TblDmLoaiDoanhnghiep> dmloaidoanhnghiepData = dmLoaiDoanhnghiepRepository.findById(id);

        if (dmloaidoanhnghiepData.isPresent()) {
            TblDmLoaiDoanhnghiep _dmloaidoanhnghiep = dmloaidoanhnghiepData.get();
            _dmloaidoanhnghiep.setLoaiDoanhnghiep(_dmloaidoanhnghiep.getLoaiDoanhnghiep());
            
            return new ResponseEntity<>(dmLoaiDoanhnghiepRepository.save(_dmloaidoanhnghiep), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dmloaidoanhnghiep/{id}")
    public ResponseEntity<HttpStatus> deleteTblDmLoaiDoanhnghiep(@PathVariable("id") long id) {
        try {
            dmLoaiDoanhnghiepRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dmloaidoanhnghiep")
    public ResponseEntity<HttpStatus> deleteAllTblDmLoaiDoanhnghieps() {
        try {
            dmLoaiDoanhnghiepRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}