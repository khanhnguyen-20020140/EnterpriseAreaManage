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

import com.sgis.khukt.model.TblStyleFill;
import com.sgis.khukt.repository.TblStyleFillRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/stylefill")
public class TblStyleFillController {
    @Autowired
    TblStyleFillRepository stylefillRepository;

    @GetMapping("/stylefills")
    public ResponseEntity<List<TblStyleFill>> getAllTblStyleFills() {
        try {
            List<TblStyleFill> stylefill = new ArrayList<TblStyleFill>();
            
            //stylefillRepository.findAll().forEach(stylefill::add);
            stylefillRepository.findAllOrderByID().forEach(stylefill::add);

            if (stylefill.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(stylefill, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/stylefill/{id}")
    public ResponseEntity<TblStyleFill> getTblStyleFillById(@PathVariable("id") long id) {
        Optional<TblStyleFill> stylefillData = stylefillRepository.findById(id);

        if (stylefillData.isPresent()) {
            return new ResponseEntity<>(stylefillData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/stylefill")
    public ResponseEntity<TblStyleFill> createTblStyleFill(@RequestBody TblStyleFill stylefill) {
        try {
            TblStyleFill _stylefill = stylefillRepository
                    .save(new TblStyleFill());
            return new ResponseEntity<>(_stylefill, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/stylefill/{id}")
    public ResponseEntity<TblStyleFill> updateTblStyleFill(@PathVariable("id") long id, @RequestBody TblStyleFill stylefill) {
        Optional<TblStyleFill> stylefillData = stylefillRepository.findById(id);

        if (stylefillData.isPresent()) {
            TblStyleFill _stylefill = stylefillData.get();
            _stylefill.setFillAntialias(_stylefill.getFillAntialias());
            _stylefill.setFillColor(_stylefill.getFillColor());
            _stylefill.setFillOpacity(_stylefill.getFillOpacity());
            _stylefill.setFillOutlineColor(_stylefill.getFillOutlineColor());
            _stylefill.setFillSortKey(_stylefill.getFillSortKey());
            _stylefill.setFillTranslate(_stylefill.getFillTranslate());
            _stylefill.setFillTranslateAnchor(_stylefill.getFillTranslateAnchor());
            _stylefill.setVisibility(_stylefill.getVisibility());
            
            return new ResponseEntity<>(stylefillRepository.save(_stylefill), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/stylefill/{id}")
    public ResponseEntity<HttpStatus> deleteTblStyleFill(@PathVariable("id") long id) {
        try {
            stylefillRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/stylefill")
    public ResponseEntity<HttpStatus> deleteAllTblStyleFills() {
        try {
            stylefillRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}