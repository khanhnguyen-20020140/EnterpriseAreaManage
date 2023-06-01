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

import com.sgis.khukt.model.TblNguonvonDautu;
import com.sgis.khukt.repository.TblNguonvonDautuRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/nguonvondautu")
public class TblNguonvonDautuController {
    @Autowired
    TblNguonvonDautuRepository nguonvonDautuRepository;

    @GetMapping("/nguonvondautus")
    public ResponseEntity<List<TblNguonvonDautu>> getAllTblNguonvonDautus(@RequestParam(required = false) String nguonvon) {
        try {
            List<TblNguonvonDautu> nguonvondautu = new ArrayList<TblNguonvonDautu>();

            if (nguonvon == null) {
                nguonvonDautuRepository.findAll(Sort.by("nguonvon")).forEach(nguonvondautu::add);
            } else {
                nguonvonDautuRepository.findByNguonvon(nguonvon).forEach(nguonvondautu::add);
            }

            if (nguonvondautu.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(nguonvondautu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nguonvondautu/{id}")
    public ResponseEntity<TblNguonvonDautu> getTblNguonvonDautuById(@PathVariable("id") Integer id) {
        Optional<TblNguonvonDautu> nguonvondautuData = nguonvonDautuRepository.findById(id);

        if (nguonvondautuData.isPresent()) {
            return new ResponseEntity<>(nguonvondautuData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/nguonvondautu")
    public ResponseEntity<TblNguonvonDautu> createTblNguonvonDautu(@RequestBody TblNguonvonDautu nguonvondautu) {
        try {
            TblNguonvonDautu _nguonvondautu = nguonvonDautuRepository
                    .save(nguonvondautu);
            return new ResponseEntity<>(_nguonvondautu, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/nguonvondautu/{id}")
    public ResponseEntity<TblNguonvonDautu> updateTblNguonvonDautu(@PathVariable("id") Integer id, @RequestBody TblNguonvonDautu nguonvondautu) {
        Optional<TblNguonvonDautu> nguonvondautuData = nguonvonDautuRepository.findById(id);

        if (nguonvondautuData.isPresent()) {
            TblNguonvonDautu _nguonvondautu = nguonvondautuData.get();
            _nguonvondautu.setNguonvon(nguonvondautu.getNguonvon());
            
            return new ResponseEntity<>(nguonvonDautuRepository.save(_nguonvondautu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/nguonvondautu/{id}")
    public ResponseEntity<HttpStatus> deleteTblNguonvonDautu(@PathVariable("id") Integer id) {
        try {
            nguonvonDautuRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/nguonvondautu")
    public ResponseEntity<HttpStatus> deleteAllTblNguonvonDautus() {
        try {
            nguonvonDautuRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}