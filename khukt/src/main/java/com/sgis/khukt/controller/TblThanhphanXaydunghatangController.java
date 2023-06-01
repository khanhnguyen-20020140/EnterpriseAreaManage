package com.sgis.khukt.controller;

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

import com.sgis.khukt.model.TblThanhphanXaydunghatang;
import com.sgis.khukt.repository.TblThanhphanXaydunghatangRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/thanhphanXDHT")
public class TblThanhphanXaydunghatangController {
    @Autowired
    TblThanhphanXaydunghatangRepository thanhphanXDHTRepository;

    @GetMapping("/prethanhphanXDHT/{id}")
    public Integer preThanhphanXaydunghatang(@PathVariable("id") Integer id) {
        List<TblThanhphanXaydunghatang> ThanhphanXaydunghatang = new ArrayList<TblThanhphanXaydunghatang>();

        Integer preId = -1;
        ThanhphanXaydunghatang = thanhphanXDHTRepository.findAll(Sort.by(Sort.Order.by("idDuanXdht"),
                Sort.Order.by("id")
        ));
        for (int i = 0; i < ThanhphanXaydunghatang.size(); i++) {
            if (ThanhphanXaydunghatang.get(i).getId().equals(id)) {
                break;
            } else {
                preId = ThanhphanXaydunghatang.get(i).getId();
            }

        }


        return preId;

    }

    @GetMapping("/thanhphanXDHTs")
    public ResponseEntity<List<TblThanhphanXaydunghatang>> getAllTblThanhphanXaydunghatangs(@RequestParam(required = false) String ten) {
        try {
            List<TblThanhphanXaydunghatang> thanhphanXDHT = new ArrayList<TblThanhphanXaydunghatang>();

            if (ten == null) {
                thanhphanXDHTRepository.findAll(Sort.by(Sort.Order.by("idDuanXdht"),
                        Sort.Order.by("id")
                )).forEach(thanhphanXDHT::add);
            } else {
                thanhphanXDHTRepository.findByTen(ten).forEach(thanhphanXDHT::add);
            }

            if (thanhphanXDHT.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(thanhphanXDHT, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/thanhphanXDHT/{id}")
    public ResponseEntity<TblThanhphanXaydunghatang> getTblThanhphanXaydunghatangById(@PathVariable("id") Integer id) {
        Optional<TblThanhphanXaydunghatang> thanhphanXDHTData = thanhphanXDHTRepository.findById(id);

        if (thanhphanXDHTData.isPresent()) {
            return new ResponseEntity<>(thanhphanXDHTData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/thanhphanXDHTbyDuan/{id}")
    public ResponseEntity<List<TblThanhphanXaydunghatang>> getTblThanhphanXaydunghatangByIdDuan(@PathVariable("id") Integer id) {
        List<TblThanhphanXaydunghatang> thanhphanXDHTData = thanhphanXDHTRepository.findByIdDuan(id);
            return new ResponseEntity<>(thanhphanXDHTData, HttpStatus.OK);
    }



    @PostMapping("/thanhphanXDHT")
    public ResponseEntity<TblThanhphanXaydunghatang> createTblThanhphanXaydunghatang(@RequestBody TblThanhphanXaydunghatang thanhphanXDHT) {
        try {
            TblThanhphanXaydunghatang _thanhphanXDHT = thanhphanXDHTRepository
                    .save(thanhphanXDHT);
            return new ResponseEntity<>(_thanhphanXDHT, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/thanhphanXDHT/{id}")
    public ResponseEntity<TblThanhphanXaydunghatang> updateTblThanhphanXaydunghatang(@PathVariable("id") Integer id, @RequestBody TblThanhphanXaydunghatang thanhphanXDHT) {
        Optional<TblThanhphanXaydunghatang> thanhphanXDHTData = thanhphanXDHTRepository.findById(id);

        if (thanhphanXDHTData.isPresent()) {
            TblThanhphanXaydunghatang _thanhphanXDHT = thanhphanXDHTData.get();
            _thanhphanXDHT.setTen(thanhphanXDHT.getTen());
            _thanhphanXDHT.setDonvitinh(thanhphanXDHT.getDonvitinh());
            _thanhphanXDHT.setKhoiluong(thanhphanXDHT.getKhoiluong());
            _thanhphanXDHT.setMucdautu(thanhphanXDHT.getMucdautu());
            _thanhphanXDHT.setIdDuanXdht(thanhphanXDHT.getIdDuanXdht());
            _thanhphanXDHT.setNhomdmId(thanhphanXDHT.getNhomdmId());



            return new ResponseEntity<>(thanhphanXDHTRepository.save(_thanhphanXDHT), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/thanhphanXDHT/{id}")
    public ResponseEntity<HttpStatus> deleteTblThanhphanXaydunghatang(@PathVariable("id") Integer id) {
        try {
            thanhphanXDHTRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/thanhphanXDHT")
    public ResponseEntity<HttpStatus> deleteAllTblThanhphanXaydunghatangs() {
        try {
            thanhphanXDHTRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}