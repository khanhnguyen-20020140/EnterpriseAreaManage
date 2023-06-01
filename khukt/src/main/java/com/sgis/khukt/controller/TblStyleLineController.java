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

import com.sgis.khukt.model.TblStyleLine;
import com.sgis.khukt.repository.TblStyleLineRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/styleline")
public class TblStyleLineController {
    @Autowired
    TblStyleLineRepository stylelineRepository;

    @GetMapping("/stylelines")
    public ResponseEntity<List<TblStyleLine>> getAllTblStyleLines() {
        try {
            List<TblStyleLine> styleline = new ArrayList<TblStyleLine>();
            
            //stylelineRepository.findAll().forEach(styleline::add);
            stylelineRepository.findAllOrderByID().forEach(styleline::add);

            if (styleline.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(styleline, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/styleline/{id}")
    public ResponseEntity<TblStyleLine> getTblStyleLineById(@PathVariable("id") long id) {
        Optional<TblStyleLine> stylelineData = stylelineRepository.findById(id);

        if (stylelineData.isPresent()) {
            return new ResponseEntity<>(stylelineData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/styleline")
    public ResponseEntity<TblStyleLine> createTblStyleLine(@RequestBody TblStyleLine styleline) {
        try {
            TblStyleLine _styleline = stylelineRepository
                    .save(new TblStyleLine());
            return new ResponseEntity<>(_styleline, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/styleline/{id}")
    public ResponseEntity<TblStyleLine> updateTblStyleLine(@PathVariable("id") long id, @RequestBody TblStyleLine styleline) {
        Optional<TblStyleLine> stylelineData = stylelineRepository.findById(id);

        if (stylelineData.isPresent()) {
            TblStyleLine _styleline = stylelineData.get();
            _styleline.setLineBlur(_styleline.getLineBlur());
            _styleline.setLineCap(_styleline.getLineCap());
            _styleline.setLineColor(_styleline.getLineColor());
            _styleline.setLineDasharray(_styleline.getLineDasharray());
            _styleline.setLineGapWidth(_styleline.getLineGapWidth());
            _styleline.setLineGradient(_styleline.getLineGradient());
            _styleline.setLineJoin(_styleline.getLineJoin());
            _styleline.setLineMiterLimit(_styleline.getLineMiterLimit());
            _styleline.setLineOffset(_styleline.getLineOffset());
            _styleline.setLineOpacity(_styleline.getLineOpacity());
            _styleline.setLineRoundLimit(_styleline.getLineRoundLimit());
            _styleline.setLineSortKey(_styleline.getLineSortKey());
            _styleline.setLineTranslate(_styleline.getLineTranslate());
            _styleline.setLineTranslateAnchor(_styleline.getLineTranslateAnchor());
            _styleline.setVisibility(_styleline.getVisibility());
            
            return new ResponseEntity<>(stylelineRepository.save(_styleline), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/styleline/{id}")
    public ResponseEntity<HttpStatus> deleteTblStyleLine(@PathVariable("id") long id) {
        try {
            stylelineRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/styleline")
    public ResponseEntity<HttpStatus> deleteAllTblStyleLines() {
        try {
            stylelineRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}