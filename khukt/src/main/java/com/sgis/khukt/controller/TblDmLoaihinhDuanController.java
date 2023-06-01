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

import com.sgis.khukt.model.TblDmLoaihinhDuan;
import com.sgis.khukt.repository.TblDmLoaihinhDuanRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/dmloaihinhduan")
public class TblDmLoaihinhDuanController {
    @Autowired
    TblDmLoaihinhDuanRepository dmLoaihinhDuanRepository;

    @GetMapping("/dmloaihinhduans")
    public ResponseEntity<List<TblDmLoaihinhDuan>> getAllTblDmLoaihinhDuans(@RequestParam(required = false) String ten) {
        try {
            List<TblDmLoaihinhDuan> dmloaihinhduan = new ArrayList<TblDmLoaihinhDuan>();

            if (ten == null) {
                dmLoaihinhDuanRepository.findAll().forEach(dmloaihinhduan::add);
            } else {
                dmLoaihinhDuanRepository.findByLoaihinhDuan(ten).forEach(dmloaihinhduan::add);
            }

            if (dmloaihinhduan.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dmloaihinhduan, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dmloaihinhduan/{id}")
    public ResponseEntity<TblDmLoaihinhDuan> getTblDmLoaihinhDuanById(@PathVariable("id") long id) {
        Optional<TblDmLoaihinhDuan> dmloaihinhduanData = dmLoaihinhDuanRepository.findById(id);

        if (dmloaihinhduanData.isPresent()) {
            return new ResponseEntity<>(dmloaihinhduanData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dmloaihinhduan")
    public ResponseEntity<TblDmLoaihinhDuan> createTblDmLoaihinhDuan(@RequestBody TblDmLoaihinhDuan dmloaihinhduan) {
        try {
            TblDmLoaihinhDuan _dmloaihinhduan = dmLoaihinhDuanRepository
                    .save(new TblDmLoaihinhDuan());
            return new ResponseEntity<>(_dmloaihinhduan, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dmloaihinhduan/{id}")
    public ResponseEntity<TblDmLoaihinhDuan> updateTblDmLoaihinhDuan(@PathVariable("id") long id, @RequestBody TblDmLoaihinhDuan dmloaihinhduan) {
        Optional<TblDmLoaihinhDuan> dmloaihinhduanData = dmLoaihinhDuanRepository.findById(id);

        if (dmloaihinhduanData.isPresent()) {
            TblDmLoaihinhDuan _dmloaihinhduan = dmloaihinhduanData.get();
            _dmloaihinhduan.setLoaihinhDuan(_dmloaihinhduan.getLoaihinhDuan());
            _dmloaihinhduan.setTenviettatLoaihinhDuan(_dmloaihinhduan.getTenviettatLoaihinhDuan());
            
            return new ResponseEntity<>(dmLoaihinhDuanRepository.save(_dmloaihinhduan), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dmloaihinhduan/{id}")
    public ResponseEntity<HttpStatus> deleteTblDmLoaihinhDuan(@PathVariable("id") long id) {
        try {
            dmLoaihinhDuanRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dmloaihinhduan")
    public ResponseEntity<HttpStatus> deleteAllTblDmLoaihinhDuans() {
        try {
            dmLoaihinhDuanRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}