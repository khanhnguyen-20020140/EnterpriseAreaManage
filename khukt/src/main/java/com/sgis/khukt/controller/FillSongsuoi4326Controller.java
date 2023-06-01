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

import com.sgis.khukt.model.FillSongsuoi4326;
import com.sgis.khukt.repository.FillSongsuoi4326Repository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/songsuoi")
public class FillSongsuoi4326Controller {
    @Autowired
    FillSongsuoi4326Repository fillSongsuoiRepository;

    @GetMapping("/songsuois")
    public ResponseEntity<List<FillSongsuoi4326>> getAllFillSongsuoi4326s(@RequestParam(required = false) String ten) {
        try {
            List<FillSongsuoi4326> songsuoi = new ArrayList<FillSongsuoi4326>();

            if (ten == null) {
                fillSongsuoiRepository.findAll().forEach(songsuoi::add);
            } else {
                fillSongsuoiRepository.findByTen(ten).forEach(songsuoi::add);
            }

            if (songsuoi.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(songsuoi, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/songsuoi/{id}")
    public ResponseEntity<FillSongsuoi4326> getFillSongsuoi4326ById(@PathVariable("id") long id) {
        Optional<FillSongsuoi4326> songsuoiData = fillSongsuoiRepository.findById(id);

        if (songsuoiData.isPresent()) {
            return new ResponseEntity<>(songsuoiData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/songsuoi")
    public ResponseEntity<FillSongsuoi4326> createFillSongsuoi4326(@RequestBody FillSongsuoi4326 songsuoi) {
        try {
            FillSongsuoi4326 _songsuoi = fillSongsuoiRepository
                    .save(new FillSongsuoi4326());
            return new ResponseEntity<>(_songsuoi, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/songsuoi/{id}")
    public ResponseEntity<FillSongsuoi4326> updateFillSongsuoi4326(@PathVariable("id") long id, @RequestBody FillSongsuoi4326 songsuoi) {
        Optional<FillSongsuoi4326> songsuoiData = fillSongsuoiRepository.findById(id);

        if (songsuoiData.isPresent()) {
            FillSongsuoi4326 _songsuoi = songsuoiData.get();
            _songsuoi.setTen(songsuoi.getTen());
            _songsuoi.setLoaitrangt(songsuoi.getLoaitrangt());
            _songsuoi.setMadoituong(songsuoi.getMadoituong());
            _songsuoi.setManhandang(songsuoi.getManhandang());
            _songsuoi.setNgaycapnha(songsuoi.getNgaycapnha());
            _songsuoi.setNgaythunha(songsuoi.getNgaythunha());
            _songsuoi.setShapeArea(songsuoi.getShapeArea());
            return new ResponseEntity<>(fillSongsuoiRepository.save(_songsuoi), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/songsuoi/{id}")
    public ResponseEntity<HttpStatus> deleteFillSongsuoi4326(@PathVariable("id") long id) {
        try {
            fillSongsuoiRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/songsuoi")
    public ResponseEntity<HttpStatus> deleteAllFillSongsuoi4326s() {
        try {
            fillSongsuoiRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}