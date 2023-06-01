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

import com.sgis.khukt.model.TblNhomdmXdht;
import com.sgis.khukt.repository.TblNhomdmXdhtRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/nhomdmXdht")
public class TblNhomdmXdhtController {
    @Autowired
    TblNhomdmXdhtRepository nhomdmXdhtRepository;

    @GetMapping("/nhomdmXdhts")
    public ResponseEntity<List<TblNhomdmXdht>> getAllTblNhomdmXdhts(@RequestParam(required = false) String tennhom) {
        try {
            List<TblNhomdmXdht> nhomdmXdht = new ArrayList<TblNhomdmXdht>();

            if (tennhom == null) {
                nhomdmXdhtRepository.findAll().forEach(nhomdmXdht::add);
            } else {
                nhomdmXdhtRepository.findByTennhom(tennhom).forEach(nhomdmXdht::add);
            }

            if (nhomdmXdht.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(nhomdmXdht, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/nhomdmXdht/{id}")
    public ResponseEntity<TblNhomdmXdht> getTblNhomdmXdhtById(@PathVariable("id") long id) {
        Optional<TblNhomdmXdht> nhomdmXdhtData = nhomdmXdhtRepository.findById(id);

        if (nhomdmXdhtData.isPresent()) {
            return new ResponseEntity<>(nhomdmXdhtData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/nhomdmXdht")
    public ResponseEntity<TblNhomdmXdht> createTblNhomdmXdht(@RequestBody TblNhomdmXdht nhomdmXdht) {
        try {
            TblNhomdmXdht _nhomdmXdht = nhomdmXdhtRepository
                    .save(new TblNhomdmXdht());
            return new ResponseEntity<>(_nhomdmXdht, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/nhomdmXdht/{id}")
    public ResponseEntity<TblNhomdmXdht> updateTblNhomdmXdht(@PathVariable("id") long id, @RequestBody TblNhomdmXdht nhomdmXdht) {
        Optional<TblNhomdmXdht> nhomdmXdhtData = nhomdmXdhtRepository.findById(id);

        if (nhomdmXdhtData.isPresent()) {
            TblNhomdmXdht _nhomdmXdht = nhomdmXdhtData.get();
            _nhomdmXdht.setTennhom(_nhomdmXdht.getTennhom());
            
            return new ResponseEntity<>(nhomdmXdhtRepository.save(_nhomdmXdht), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/nhomdmXdht/{id}")
    public ResponseEntity<HttpStatus> deleteTblNhomdmXdht(@PathVariable("id") long id) {
        try {
            nhomdmXdhtRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/nhomdmXdht")
    public ResponseEntity<HttpStatus> deleteAllTblNhomdmXdhts() {
        try {
            nhomdmXdhtRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}