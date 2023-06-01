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

import com.sgis.khukt.model.TblDmVonDautu;
import com.sgis.khukt.repository.TblDmVonDautuRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/dmvondautu")
public class TblDmVonDautuController {
    @Autowired
    TblDmVonDautuRepository dmVonDautuRepository;

    @GetMapping("/dmvondautus")
    public ResponseEntity<List<TblDmVonDautu>> getAllTblDmVonDautus(@RequestParam(required = false) String vondautu) {
        try {
            List<TblDmVonDautu> dmvondautu = new ArrayList<TblDmVonDautu>();

            if (vondautu == null) {
                dmVonDautuRepository.findAll().forEach(dmvondautu::add);
            } else {
                dmVonDautuRepository.findByVonDautu(vondautu).forEach(dmvondautu::add);
            }

            if (dmvondautu.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dmvondautu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dmvondautu/{id}")
    public ResponseEntity<TblDmVonDautu> getTblDmVonDautuById(@PathVariable("id") long id) {
        Optional<TblDmVonDautu> dmvondautuData = dmVonDautuRepository.findById(id);

        if (dmvondautuData.isPresent()) {
            return new ResponseEntity<>(dmvondautuData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dmvondautu")
    public ResponseEntity<TblDmVonDautu> createTblDmVonDautu(@RequestBody TblDmVonDautu dmvondautu) {
        try {
            TblDmVonDautu _dmvondautu = dmVonDautuRepository
                    .save(new TblDmVonDautu());
            return new ResponseEntity<>(_dmvondautu, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dmvondautu/{id}")
    public ResponseEntity<TblDmVonDautu> updateTblDmVonDautu(@PathVariable("id") long id, @RequestBody TblDmVonDautu dmvondautu) {
        Optional<TblDmVonDautu> dmvondautuData = dmVonDautuRepository.findById(id);

        if (dmvondautuData.isPresent()) {
            TblDmVonDautu _dmvondautu = dmvondautuData.get();
            _dmvondautu.setVonDautu(_dmvondautu.getVonDautu());
            
            return new ResponseEntity<>(dmVonDautuRepository.save(_dmvondautu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dmvondautu/{id}")
    public ResponseEntity<HttpStatus> deleteTblDmVonDautu(@PathVariable("id") long id) {
        try {
            dmVonDautuRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dmvondautu")
    public ResponseEntity<HttpStatus> deleteAllTblDmVonDautus() {
        try {
            dmVonDautuRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}