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

import com.sgis.khukt.model.LineDuongbo4326;
import com.sgis.khukt.repository.LineDuongbo4326Repository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/duongbo")
public class LineDuongbo4326Controller {
    @Autowired
    LineDuongbo4326Repository lineDuongboRepository;

    @GetMapping("/duongbos")
    public ResponseEntity<List<LineDuongbo4326>> getAllLineDuongbo4326s(@RequestParam(required = false) String ten) {
        try {
            List<LineDuongbo4326> duongbo = new ArrayList<LineDuongbo4326>();

            if (ten == null) {
                lineDuongboRepository.findAll().forEach(duongbo::add);
            } else {
                lineDuongboRepository.findByTen(ten).forEach(duongbo::add);
            }

            if (duongbo.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(duongbo, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/duongbo/{id}")
    public ResponseEntity<LineDuongbo4326> getLineDuongbo4326ById(@PathVariable("id") long id) {
        Optional<LineDuongbo4326> duongboData = lineDuongboRepository.findById(id);

        if (duongboData.isPresent()) {
            return new ResponseEntity<>(duongboData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/duongbo")
    public ResponseEntity<LineDuongbo4326> createLineDuongbo4326(@RequestBody LineDuongbo4326 duongbo) {
        try {
            LineDuongbo4326 _duongbo = lineDuongboRepository
                    .save(new LineDuongbo4326());
            return new ResponseEntity<>(_duongbo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/duongbo/{id}")
    public ResponseEntity<LineDuongbo4326> updateLineDuongbo4326(@PathVariable("id") long id, @RequestBody LineDuongbo4326 duongbo) {
        Optional<LineDuongbo4326> duongboData = lineDuongboRepository.findById(id);

        if (duongboData.isPresent()) {
            LineDuongbo4326 _duongbo = duongboData.get();
            _duongbo.setTen(duongbo.getTen());            
            _duongbo.setMadoituong(duongbo.getMadoituong());
            _duongbo.setManhandang(duongbo.getManhandang());
            _duongbo.setNgaycapnha(duongbo.getNgaycapnha());
            _duongbo.setNgaythunha(duongbo.getNgaythunha());
            _duongbo.setDorong(duongbo.getDorong());
            _duongbo.setLoaichatli(_duongbo.getLoaichatli());
            _duongbo.setLoaiduongb(_duongbo.getLoaiduongb());
            _duongbo.setLoaihientr(_duongbo.getLoaihientr());
            _duongbo.setLoaiketcau(_duongbo.getLoaiketcau());
            _duongbo.setTentuyen1(_duongbo.getTentuyen1());
            _duongbo.setTentuyen2(_duongbo.getTentuyen2());
            _duongbo.setTentuyen3(_duongbo.getTentuyen3());
            return new ResponseEntity<>(lineDuongboRepository.save(_duongbo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/duongbo/{id}")
    public ResponseEntity<HttpStatus> deleteLineDuongbo4326(@PathVariable("id") long id) {
        try {
            lineDuongboRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/duongbo")
    public ResponseEntity<HttpStatus> deleteAllLineDuongbo4326s() {
        try {
            lineDuongboRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}