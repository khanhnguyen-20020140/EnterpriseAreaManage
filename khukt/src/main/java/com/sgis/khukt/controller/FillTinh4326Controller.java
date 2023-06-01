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

import com.sgis.khukt.model.FillTinh4326;
import com.sgis.khukt.repository.FillTinh4326Repository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/tinh")
public class FillTinh4326Controller {
    @Autowired
    FillTinh4326Repository fillTinhRepository;

    @GetMapping("/tinhs")
    public ResponseEntity<List<FillTinh4326>> getAllFillTinh4326s(@RequestParam(required = false) String ten) {
        try {
            List<FillTinh4326> tinh = new ArrayList<FillTinh4326>();

            if (ten == null) {
                fillTinhRepository.findAll().forEach(tinh::add);
            } else {
                fillTinhRepository.findByTen(ten).forEach(tinh::add);
            }

            if (tinh.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tinh, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tinh/{id}")
    public ResponseEntity<FillTinh4326> getFillTinh4326ById(@PathVariable("id") long id) {
        Optional<FillTinh4326> tinhData = fillTinhRepository.findById(id);

        if (tinhData.isPresent()) {
            return new ResponseEntity<>(tinhData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tinh")
    public ResponseEntity<FillTinh4326> createFillTinh4326(@RequestBody FillTinh4326 tinh) {
        try {
            FillTinh4326 _tinh = fillTinhRepository
                    .save(new FillTinh4326());
            return new ResponseEntity<>(_tinh, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tinh/{id}")
    public ResponseEntity<FillTinh4326> updateFillTinh4326(@PathVariable("id") long id, @RequestBody FillTinh4326 tinh) {
        Optional<FillTinh4326> tinhData = fillTinhRepository.findById(id);

        if (tinhData.isPresent()) {
            FillTinh4326 _tinh = tinhData.get();
            _tinh.setTen(tinh.getTen());
            _tinh.setDientich(tinh.getDientich());
            _tinh.setMadoituong(tinh.getMadoituong());
            _tinh.setManhandang(tinh.getManhandang());
            _tinh.setNgaycapnha(tinh.getNgaycapnha());
            _tinh.setNgaythunha(tinh.getNgaythunha());
            _tinh.setShapeArea(tinh.getShapeArea());
            return new ResponseEntity<>(fillTinhRepository.save(_tinh), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tinh/{id}")
    public ResponseEntity<HttpStatus> deleteFillTinh4326(@PathVariable("id") long id) {
        try {
            fillTinhRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tinh")
    public ResponseEntity<HttpStatus> deleteAllFillTinh4326s() {
        try {
            fillTinhRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}