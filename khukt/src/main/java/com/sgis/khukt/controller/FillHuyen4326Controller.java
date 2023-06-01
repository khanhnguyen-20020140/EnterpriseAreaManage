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

import com.sgis.khukt.model.FillHuyen4326;
import com.sgis.khukt.repository.FillHuyen4326Repository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/huyen")
public class FillHuyen4326Controller {
    @Autowired
    FillHuyen4326Repository fillHuyenRepository;

    @GetMapping("/huyens")
    public ResponseEntity<List<FillHuyen4326>> getAllFillHuyen4326s(@RequestParam(required = false) String ten) {
        try {
            List<FillHuyen4326> huyen = new ArrayList<FillHuyen4326>();

            if (ten == null) {
                fillHuyenRepository.findAll().forEach(huyen::add);
            } else {
                fillHuyenRepository.findByTen(ten).forEach(huyen::add);
            }

            if (huyen.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(huyen, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/huyen/{id}")
    public ResponseEntity<FillHuyen4326> getFillHuyen4326ById(@PathVariable("id") long id) {
        Optional<FillHuyen4326> huyenData = fillHuyenRepository.findById(id);

        if (huyenData.isPresent()) {
            return new ResponseEntity<>(huyenData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/huyen")
    public ResponseEntity<FillHuyen4326> createFillHuyen4326(@RequestBody FillHuyen4326 huyen) {
        try {
            FillHuyen4326 _huyen = fillHuyenRepository
                    .save(new FillHuyen4326());
            return new ResponseEntity<>(_huyen, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/huyen/{id}")
    public ResponseEntity<FillHuyen4326> updateFillHuyen4326(@PathVariable("id") long id, @RequestBody FillHuyen4326 huyen) {
        Optional<FillHuyen4326> huyenData = fillHuyenRepository.findById(id);

        if (huyenData.isPresent()) {
            FillHuyen4326 _huyen = huyenData.get();
            _huyen.setTen(huyen.getTen());
            _huyen.setDientich(huyen.getDientich());
            _huyen.setMadoituong(huyen.getMadoituong());
            _huyen.setManhandang(huyen.getManhandang());
            _huyen.setNgaycapnha(huyen.getNgaycapnha());
            _huyen.setNgaythunha(huyen.getNgaythunha());
            _huyen.setShapeArea(huyen.getShapeArea());
            return new ResponseEntity<>(fillHuyenRepository.save(_huyen), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/huyen/{id}")
    public ResponseEntity<HttpStatus> deleteFillHuyen4326(@PathVariable("id") long id) {
        try {
            fillHuyenRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/huyen")
    public ResponseEntity<HttpStatus> deleteAllFillHuyen4326s() {
        try {
            fillHuyenRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}