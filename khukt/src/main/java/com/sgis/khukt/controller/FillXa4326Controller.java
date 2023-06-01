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

import com.sgis.khukt.model.FillXa4326;
import com.sgis.khukt.repository.FillXa4326Repository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/xa")
public class FillXa4326Controller {
    @Autowired
    FillXa4326Repository fillXaRepository;

    @GetMapping("/xas")
    public ResponseEntity<List<FillXa4326>> getAllFillXa4326s(@RequestParam(required = false) String ten) {
        try {
            List<FillXa4326> xa = new ArrayList<FillXa4326>();

            if (ten == null) {
                fillXaRepository.findAll().forEach(xa::add);
            } else {
                fillXaRepository.findByTen(ten).forEach(xa::add);
            }

            if (xa.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(xa, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/xa/{id}")
    public ResponseEntity<FillXa4326> getFillXa4326ById(@PathVariable("id") long id) {
        Optional<FillXa4326> xaData = fillXaRepository.findById(id);

        if (xaData.isPresent()) {
            return new ResponseEntity<>(xaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/xa")
    public ResponseEntity<FillXa4326> createFillXa4326(@RequestBody FillXa4326 xa) {
        try {
            FillXa4326 _xa = fillXaRepository
                    .save(new FillXa4326());
            return new ResponseEntity<>(_xa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/xa/{id}")
    public ResponseEntity<FillXa4326> updateFillXa4326(@PathVariable("id") long id, @RequestBody FillXa4326 xa) {
        Optional<FillXa4326> xaData = fillXaRepository.findById(id);

        if (xaData.isPresent()) {
            FillXa4326 _xa = xaData.get();
            _xa.setTen(xa.getTen());
            _xa.setDientich(xa.getDientich());
            _xa.setMadoituong(xa.getMadoituong());
            _xa.setManhandang(xa.getManhandang());
            _xa.setNgaycapnha(xa.getNgaycapnha());
            _xa.setNgaythunha(xa.getNgaythunha());
            _xa.setShapeArea(xa.getShapeArea());
            return new ResponseEntity<>(fillXaRepository.save(_xa), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/xa/{id}")
    public ResponseEntity<HttpStatus> deleteFillXa4326(@PathVariable("id") long id) {
        try {
            fillXaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/xa")
    public ResponseEntity<HttpStatus> deleteAllFillXa4326s() {
        try {
            fillXaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}