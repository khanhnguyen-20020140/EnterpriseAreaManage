//package com.sgis.khukt.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.sgis.khukt.model.TblThongkeLaodong;
//import com.sgis.khukt.repository.TblThongkeLaodongRepository;
//
//@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
//@RestController
//@RequestMapping("/thongkeLaodong")
//public class TblThongkeLaodongController {
//    @Autowired
//    TblThongkeLaodongRepository thongkeLaodongRepository;
//
//    @GetMapping("/thongkeLaodongs")
//    public ResponseEntity<List<TblThongkeLaodong>> getAllTblThongkeLaodongs() {
//        try {
//            List<TblThongkeLaodong> thongkeLaodong = new ArrayList<TblThongkeLaodong>();
//
//            thongkeLaodongRepository.findAll().forEach(thongkeLaodong::add);
//
//            if (thongkeLaodong.isEmpty()) {
//                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//            }
//
//            return new ResponseEntity<>(thongkeLaodong, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/thongkeLaodong/{id}")
//    public ResponseEntity<TblThongkeLaodong> getTblThongkeLaodongById(@PathVariable("id") long id) {
//        Optional<TblThongkeLaodong> thongkeLaodongData = thongkeLaodongRepository.findById(id);
//
//        if (thongkeLaodongData.isPresent()) {
//            return new ResponseEntity<>(thongkeLaodongData.get(), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @PostMapping("/thongkeLaodong")
//    public ResponseEntity<TblThongkeLaodong> createTblThongkeLaodong(@RequestBody TblThongkeLaodong thongkeLaodong) {
//        try {
//            TblThongkeLaodong _thongkeLaodong = thongkeLaodongRepository
//                    .save(new TblThongkeLaodong());
//            return new ResponseEntity<>(_thongkeLaodong, HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PutMapping("/thongkeLaodong/{id}")
//    public ResponseEntity<TblThongkeLaodong> updateTblThongkeLaodong(@PathVariable("id") long id, @RequestBody TblThongkeLaodong thongkeLaodong) {
//        Optional<TblThongkeLaodong> thongkeLaodongData = thongkeLaodongRepository.findById(id);
//
//        if (thongkeLaodongData.isPresent()) {
//            TblThongkeLaodong _thongkeLaodong = thongkeLaodongData.get();
//            _thongkeLaodong.setLaodongCaodang(_thongkeLaodong.getLaodongCaodang());
//            _thongkeLaodong.setLaodongTrungcap(_thongkeLaodong.getLaodongTrungcap());
//            _thongkeLaodong.setLaodongTudaihocTrolen(_thongkeLaodong.getLaodongTudaihocTrolen());
//            _thongkeLaodong.setNam(_thongkeLaodong.getNam());
//            _thongkeLaodong.setNgaybaocao(_thongkeLaodong.getNgaybaocao());
//            _thongkeLaodong.setNu(_thongkeLaodong.getNu());
//            _thongkeLaodong.setTongsoLaodong(_thongkeLaodong.getTongsoLaodong());
//
//            return new ResponseEntity<>(thongkeLaodongRepository.save(_thongkeLaodong), HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
//
//    @DeleteMapping("/thongkeLaodong/{id}")
//    public ResponseEntity<HttpStatus> deleteTblThongkeLaodong(@PathVariable("id") long id) {
//        try {
//            thongkeLaodongRepository.deleteById(id);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @DeleteMapping("/thongkeLaodong")
//    public ResponseEntity<HttpStatus> deleteAllTblThongkeLaodongs() {
//        try {
//            thongkeLaodongRepository.deleteAll();
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//    }
//}