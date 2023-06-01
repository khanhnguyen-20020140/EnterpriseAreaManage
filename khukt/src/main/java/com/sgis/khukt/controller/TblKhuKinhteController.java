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

import com.sgis.khukt.model.TblKhuKinhte;
import com.sgis.khukt.repository.TblKhuKinhteRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/khukinhte")
public class TblKhuKinhteController {
    @Autowired
    TblKhuKinhteRepository khuKinhteRepository;

    @GetMapping("/khukinhtes")
    public ResponseEntity<List<TblKhuKinhte>> getAllTblKhuKinhtes(@RequestParam(required = false) String ten) {
        try {
            List<TblKhuKinhte> khukinhte = new ArrayList<TblKhuKinhte>();

            if (ten == null) {
                khuKinhteRepository.findAll().forEach(khukinhte::add);
            } else {
                khuKinhteRepository.findByTen(ten).forEach(khukinhte::add);
            }

            if (khukinhte.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(khukinhte, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/khukinhte/{id}")
    public ResponseEntity<TblKhuKinhte> getTblKhuKinhteById(@PathVariable("id") long id) {
        Optional<TblKhuKinhte> khukinhteData = khuKinhteRepository.findById(id);

        if (khukinhteData.isPresent()) {
            return new ResponseEntity<>(khukinhteData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/khukinhte")
    public ResponseEntity<TblKhuKinhte> createTblKhuKinhte(@RequestBody TblKhuKinhte khukinhte) {
        try {
            TblKhuKinhte _khukinhte = khuKinhteRepository
                    .save(new TblKhuKinhte());
            return new ResponseEntity<>(_khukinhte, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/khukinhte/{id}")
    public ResponseEntity<TblKhuKinhte> updateTblKhuKinhte(@PathVariable("id") long id, @RequestBody TblKhuKinhte khukinhte) {
        Optional<TblKhuKinhte> khukinhteData = khuKinhteRepository.findById(id);

        if (khukinhteData.isPresent()) {
            TblKhuKinhte _khukinhte = khukinhteData.get();
            _khukinhte.setTen(_khukinhte.getTen());
            _khukinhte.setIdTinh(_khukinhte.getIdTinh());
            _khukinhte.setSoqdThanhlap(_khukinhte.getSoqdThanhlap());
            _khukinhte.setTomtat(_khukinhte.getTomtat());
            
            return new ResponseEntity<>(khuKinhteRepository.save(_khukinhte), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/khukinhte/{id}")
    public ResponseEntity<HttpStatus> deleteTblKhuKinhte(@PathVariable("id") long id) {
        try {
            khuKinhteRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/khukinhte")
    public ResponseEntity<HttpStatus> deleteAllTblKhuKinhtes() {
        try {
            khuKinhteRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}