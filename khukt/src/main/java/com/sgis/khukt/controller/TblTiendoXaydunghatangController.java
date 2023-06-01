package com.sgis.khukt.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

import com.sgis.khukt.model.TblTiendoXaydunghatang;
import com.sgis.khukt.repository.TblTiendoXaydunghatangRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/tiendoXDHT")
public class TblTiendoXaydunghatangController {
    @Autowired
    TblTiendoXaydunghatangRepository tiendoXDHTRepository;

    @GetMapping("/tiendoXDHTs")
    public ResponseEntity<List<TblTiendoXaydunghatang>> getAllTblTiendoXaydunghatangs() {
        try {
            List<TblTiendoXaydunghatang> tiendoXDHT = tiendoXDHTRepository.findAllXDHT();

            if (tiendoXDHT.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHT, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tiendoXDHTcosos")
    public ResponseEntity<List<TblTiendoXaydunghatang>> getAllTblTiendoXaydunghatangcosos() {
        try {
            List<TblTiendoXaydunghatang> tiendoXDHT = new ArrayList<TblTiendoXaydunghatang>();

            tiendoXDHT=  tiendoXDHTRepository.findAllDTSX();

            if (tiendoXDHT.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHT, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getNgaybaocaobyIdduan/{id}")
    public List<String> getNgaybaocaobyIdduanxdht(@PathVariable("id") Integer id){
        return tiendoXDHTRepository.getNgaybaocaobyIdduanxdht(id);
    }

    @GetMapping("/getIdTiendo/{id}/{date}")
    public Integer getIdTiendoxdht(@PathVariable("id") Integer id,@PathVariable("date") String date){
        return tiendoXDHTRepository.getIdTiendoxdht(id, java.sql.Date.valueOf(date));
    }


    @GetMapping("/getNgaybaocaobyIdduandtsx/{id}")
    public List<String> getNgaybaocaobyIdduandtsx(@PathVariable("id") Integer id){
        return tiendoXDHTRepository.getNgaybaocaobyIdduandtsx(id);
    }

    @GetMapping("/getIdTiendodtsx/{id}/{date}")
    public Integer getIdTiendodtsx(@PathVariable("id") Integer id,@PathVariable("date") String date){
        return tiendoXDHTRepository.getIdTiendodtsx(id, java.sql.Date.valueOf(date));
    }


    @GetMapping("/tiendoXDHT/{id}")
    public ResponseEntity<TblTiendoXaydunghatang> getTblTiendoXaydunghatangById(@PathVariable("id") Integer id) {
        Optional<TblTiendoXaydunghatang> tiendoXDHTData = tiendoXDHTRepository.findById(id);

        if (tiendoXDHTData.isPresent()) {
            return new ResponseEntity<>(tiendoXDHTData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tiendoXDHT")
    public ResponseEntity<TblTiendoXaydunghatang> createTblTiendoXaydunghatang(@RequestBody TblTiendoXaydunghatang tiendoXDHT) {
        try {
            TblTiendoXaydunghatang _tiendoXDHT = tiendoXDHTRepository
                    .save(tiendoXDHT);

            return new ResponseEntity<>(_tiendoXDHT, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tiendoXDHT/{id}")
    public ResponseEntity<TblTiendoXaydunghatang> updateTblTiendoXaydunghatang(@PathVariable("id") Integer id, @RequestBody TblTiendoXaydunghatang tiendoXDHT) {
        Optional<TblTiendoXaydunghatang> tiendoXDHTData = tiendoXDHTRepository.findById(id);

        if (tiendoXDHTData.isPresent()) {
            TblTiendoXaydunghatang _tiendoXDHT = tiendoXDHTData.get();
            _tiendoXDHT.setDuanXdhtId(tiendoXDHT.getDuanXdhtId());
            _tiendoXDHT.setNgaybaocao(tiendoXDHT.getNgaybaocao());
            
            return new ResponseEntity<>(tiendoXDHTRepository.save(_tiendoXDHT), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tiendoXDHT/{id}")
    public ResponseEntity<HttpStatus> deleteTblTiendoXaydunghatang(@PathVariable("id") Integer id) {
        try {
            tiendoXDHTRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tiendoXDHT")
    public ResponseEntity<HttpStatus> deleteAllTblTiendoXaydunghatangs() {
        try {
            tiendoXDHTRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}