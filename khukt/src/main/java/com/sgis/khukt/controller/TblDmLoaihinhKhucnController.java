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

import com.sgis.khukt.model.TblDmLoaihinhKhucn;
import com.sgis.khukt.repository.TblDmLoaihinhKhucnRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})


@RestController
@RequestMapping("/dmloaihinhkhucn")
public class TblDmLoaihinhKhucnController {
    @Autowired
    TblDmLoaihinhKhucnRepository dmLoaihinhKhucnRepository;

    @GetMapping("/dmloaihinhkhucns")
    public ResponseEntity<List<TblDmLoaihinhKhucn>> getAllTblDmLoaihinhKhucns(@RequestParam(required = false) String ten) {
        try {
            List<TblDmLoaihinhKhucn> dmloaihinhkhucn = new ArrayList<TblDmLoaihinhKhucn>();

            if (ten == null) {
                dmLoaihinhKhucnRepository.findAll().forEach(dmloaihinhkhucn::add);
            } else {
                dmLoaihinhKhucnRepository.findByTen(ten).forEach(dmloaihinhkhucn::add);
            }

            if (dmloaihinhkhucn.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dmloaihinhkhucn, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dmloaihinhkhucn/{id}")
    public ResponseEntity<TblDmLoaihinhKhucn> getTblDmLoaihinhKhucnById(@PathVariable("id") long id) {
        Optional<TblDmLoaihinhKhucn> dmloaihinhkhucnData = dmLoaihinhKhucnRepository.findById(id);

        if (dmloaihinhkhucnData.isPresent()) {
            return new ResponseEntity<>(dmloaihinhkhucnData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dmloaihinhkhucn")
    public ResponseEntity<TblDmLoaihinhKhucn> createTblDmLoaihinhKhucn(@RequestBody TblDmLoaihinhKhucn dmloaihinhkhucn) {
        try {
            TblDmLoaihinhKhucn _dmloaihinhkhucn = dmLoaihinhKhucnRepository
                    .save(new TblDmLoaihinhKhucn());
            return new ResponseEntity<>(_dmloaihinhkhucn, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dmloaihinhkhucn/{id}")
    public ResponseEntity<TblDmLoaihinhKhucn> updateTblDmLoaihinhKhucn(@PathVariable("id") long id, @RequestBody TblDmLoaihinhKhucn dmloaihinhkhucn) {
        Optional<TblDmLoaihinhKhucn> dmloaihinhkhucnData = dmLoaihinhKhucnRepository.findById(id);

        if (dmloaihinhkhucnData.isPresent()) {
            TblDmLoaihinhKhucn _dmloaihinhkhucn = dmloaihinhkhucnData.get();
            _dmloaihinhkhucn.setTen(_dmloaihinhkhucn.getTen());
            
            return new ResponseEntity<>(dmLoaihinhKhucnRepository.save(_dmloaihinhkhucn), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dmloaihinhkhucn/{id}")
    public ResponseEntity<HttpStatus> deleteTblDmLoaihinhKhucn(@PathVariable("id") long id) {
        try {
            dmLoaihinhKhucnRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dmloaihinhkhucn")
    public ResponseEntity<HttpStatus> deleteAllTblDmLoaihinhKhucns() {
        try {
            dmLoaihinhKhucnRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}