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

import com.sgis.khukt.model.TblQuyhoachPoint;
import com.sgis.khukt.repository.TblQuyhoachPointRepository;

@CrossOrigin(origins = {"http://localhost:8080", "http://10.168.1.196:8080"})
@RestController
@RequestMapping("/quyhoachpoint")
public class TblQuyhoachPointController {

    @Autowired
    TblQuyhoachPointRepository quyhoachpointRepository;

    @GetMapping("/quyhoachpoints")
    public ResponseEntity<List<TblQuyhoachPoint>> getAllTblQuyhoachPoints() {
        try {
            List<TblQuyhoachPoint> quyhoachpoint = new ArrayList<TblQuyhoachPoint>();

            quyhoachpointRepository.findAll().forEach(quyhoachpoint::add);

            if (quyhoachpoint.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(quyhoachpoint, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/quyhoachpoint/{id}")
    public ResponseEntity<TblQuyhoachPoint> getTblQuyhoachPointById(@PathVariable("id") Integer id) {
        Optional<TblQuyhoachPoint> quyhoachpointData = quyhoachpointRepository.findById(id);

        if (quyhoachpointData.isPresent()) {
            return new ResponseEntity<>(quyhoachpointData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/quyhoachpoint")
    public ResponseEntity<TblQuyhoachPoint> createTblQuyhoachPoint(@RequestBody TblQuyhoachPoint quyhoachpoint) {
        try {
            TblQuyhoachPoint _quyhoachpoint = quyhoachpointRepository
                    .save(new TblQuyhoachPoint());
            return new ResponseEntity<>(_quyhoachpoint, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/quyhoachpoint/{id}")
    public ResponseEntity<TblQuyhoachPoint> updateTblQuyhoachPoint(@PathVariable("id") Integer id, @RequestBody TblQuyhoachPoint quyhoachpoint) {
        Optional<TblQuyhoachPoint> quyhoachpointData = quyhoachpointRepository.findById(id);

        if (quyhoachpointData.isPresent()) {
            TblQuyhoachPoint _quyhoachpoint = quyhoachpointData.get();
//            _quyhoachpoint.setGhichu(_quyhoachpoint.getGhichu());
//            _quyhoachpoint.setGeom(_quyhoachpoint.getGeom());
            _quyhoachpoint.setIdDuanDtsx(quyhoachpoint.getIdDuanDtsx());

            return new ResponseEntity<>(quyhoachpointRepository.save(_quyhoachpoint), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/quyhoachpoint/{id}")
    public ResponseEntity<HttpStatus> deleteTblQuyhoachPoint(@PathVariable("id") Integer id) {
        try {
            quyhoachpointRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/quyhoachpoint")
    public ResponseEntity<HttpStatus> deleteAllTblQuyhoachPoints() {
        try {
            quyhoachpointRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
