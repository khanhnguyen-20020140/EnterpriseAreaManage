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

import com.sgis.khukt.model.FillDancu4326;
import com.sgis.khukt.repository.FillDancu4326Repository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/dancu")
public class FillDancu4326Controller {
    @Autowired
    FillDancu4326Repository fillDancuRepository;

    @GetMapping("/dancus")
    public ResponseEntity<List<FillDancu4326>> getAllFillDancu4326s(@RequestParam(required = false) String ten) {
        try {
            List<FillDancu4326> dancu = new ArrayList<FillDancu4326>();

            if (ten == null) {
                fillDancuRepository.findAll().forEach(dancu::add);
            } else {
                fillDancuRepository.findByTen(ten).forEach(dancu::add);
            }

            if (dancu.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(dancu, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/dancu/{id}")
    public ResponseEntity<FillDancu4326> getFillDancu4326ById(@PathVariable("id") long id) {
        Optional<FillDancu4326> dancuData = fillDancuRepository.findById(id);

        if (dancuData.isPresent()) {
            return new ResponseEntity<>(dancuData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/dancu")
    public ResponseEntity<FillDancu4326> createFillDancu4326(@RequestBody FillDancu4326 dancu) {
        try {
            FillDancu4326 _dancu = fillDancuRepository
                    .save(new FillDancu4326());
            return new ResponseEntity<>(_dancu, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/dancu/{id}")
    public ResponseEntity<FillDancu4326> updateFillDancu4326(@PathVariable("id") long id, @RequestBody FillDancu4326 dancu) {
        Optional<FillDancu4326> dancuData = fillDancuRepository.findById(id);

        if (dancuData.isPresent()) {
            FillDancu4326 _dancu = dancuData.get();
            _dancu.setTen(dancu.getTen());
            _dancu.setChieucao(dancu.getChieucao());
            _dancu.setMadoituong(dancu.getMadoituong());
            _dancu.setManhandang(dancu.getManhandang());
            _dancu.setNgaycapnha(dancu.getNgaycapnha());
            _dancu.setNgaythunha(dancu.getNgaythunha());
            _dancu.setShapeArea(dancu.getShapeArea());
            return new ResponseEntity<>(fillDancuRepository.save(_dancu), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/dancu/{id}")
    public ResponseEntity<HttpStatus> deleteFillDancu4326(@PathVariable("id") long id) {
        try {
            fillDancuRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/dancu")
    public ResponseEntity<HttpStatus> deleteAllFillDancu4326s() {
        try {
            fillDancuRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}