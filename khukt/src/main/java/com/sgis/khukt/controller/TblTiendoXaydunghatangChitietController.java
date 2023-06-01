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

import com.sgis.khukt.model.TblTiendoXaydunghatangChitiet;
import com.sgis.khukt.repository.TblTiendoXaydunghatangChitietRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/tiendoXDHTChitiet")
public class TblTiendoXaydunghatangChitietController {
    @Autowired
    TblTiendoXaydunghatangChitietRepository tiendoXDHTChitietRepository;


//here
    @GetMapping("/tiendoXDHTChitietbyIdduan/{id}/{firstday}/{endday}")
    public ResponseEntity<List<TblTiendoXaydunghatangChitiet>> getTienDoChiTietByIdduan(@PathVariable("id") Integer id,@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            System.out.println("???");
            List<TblTiendoXaydunghatangChitiet> tiendoXDHTChitiet = new ArrayList<TblTiendoXaydunghatangChitiet>();

            tiendoXDHTChitiet=tiendoXDHTChitietRepository.getTienDoChiTietByIdduan(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));


            System.out.println(tiendoXDHTChitiet);
            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tiendoXDHTChitietbyIdduandtsx/{id}/{firstday}/{endday}")
    public ResponseEntity<List<TblTiendoXaydunghatangChitiet>> getTienDoChiTietByIdduandtsx(@PathVariable("id") Integer id,@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            System.out.println("???");
            List<TblTiendoXaydunghatangChitiet> tiendoXDHTChitiet = new ArrayList<TblTiendoXaydunghatangChitiet>();

            tiendoXDHTChitiet=tiendoXDHTChitietRepository.getTienDoChiTietByIdduandtsx(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday));


            System.out.println(tiendoXDHTChitiet);
            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping("/tiendoXDHTChitietbyIdduandtsx/{id}")
    public String getTenDuanByIdduandtsx(@PathVariable("id") Integer id){
        return tiendoXDHTChitietRepository.getTenDuanByIdduandtsx(id);
    }

    @GetMapping("/tiendoXDHTChitietbyIdduan/{id}")
    public String getTenDuanByIdduan(@PathVariable("id") Integer id){
        return tiendoXDHTChitietRepository.getTenDuanByIdduan(id);
    }



    @GetMapping("/getTenDuan/{id}")
    public String getTenDuan(@PathVariable("id") Integer id){
        return tiendoXDHTChitietRepository.getTenDuan(id);
    }

    @GetMapping("/getTenDuandtsx/{id}")
    public String getTenDuandtsx(@PathVariable("id") Integer id){
        return tiendoXDHTChitietRepository.getTenDuandtsx(id);
    }






    //here
    @GetMapping("/getIdduanhatangByKKT/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Integer>> getIdduanhatangByKKT(@PathVariable("id") Integer id,@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            List<Integer> tiendoXDHTChitiet = new ArrayList<Integer>();

            tiendoXDHTChitietRepository.getIdduanhatangByKKT(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday)).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getIdduanhatangByKCN/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Integer>> getIdduanhatangByKCN(@PathVariable("id") Integer id,@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            List<Integer> tiendoXDHTChitiet = new ArrayList<Integer>();

            tiendoXDHTChitietRepository.getIdduanhatangByKCN(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday)).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getIdduanhatangByDuan/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Integer>> getIdduanhatangByDuan(@PathVariable("id") Integer id,@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            List<Integer> tiendoXDHTChitiet = new ArrayList<Integer>();

            tiendoXDHTChitietRepository.getIdduanhatangByDuan(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday)).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getIdduanhatangcosoAll/{firstday}/{endday}")
    public ResponseEntity<List<Integer>> getIdduanhatangcosoAll(@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            List<Integer> tiendoXDHTChitiet = new ArrayList<Integer>();

            tiendoXDHTChitietRepository.getIdduanhatangcosoAll(java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday)).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                System.out.println("wtf");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {


            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getIdduanhatangAll/{firstday}/{endday}")
    public ResponseEntity<List<Integer>> getIdduanhatangAll(@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            List<Integer> tiendoXDHTChitiet = new ArrayList<Integer>();

            tiendoXDHTChitietRepository.getIdduanhatangAll(java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday)).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                System.out.println("wtf");
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {


            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }




    @GetMapping("/getIdduanhatangcosoByKKT/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Integer>> getIdduanhatangcosoByKKT(@PathVariable("id") Integer id,@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            List<Integer> tiendoXDHTChitiet = new ArrayList<Integer>();

            tiendoXDHTChitietRepository.getIdduanhatangcosoByKKT(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday)).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/getIdduanhatangcosoByKCN/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Integer>> getIdduanhatangcosoByKCN(@PathVariable("id") Integer id,@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            List<Integer> tiendoXDHTChitiet = new ArrayList<Integer>();

            tiendoXDHTChitietRepository.getIdduanhatangcosoByKCN(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday)).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getIdduanhatangcosoByDuan/{id}/{firstday}/{endday}")
    public ResponseEntity<List<Integer>> getIdduanhatangcosoByDuan(@PathVariable("id") Integer id,@PathVariable("firstday")String firstday, @PathVariable("endday")String endday) {

        try {
            List<Integer> tiendoXDHTChitiet = new ArrayList<Integer>();

            tiendoXDHTChitietRepository.getIdduanhatangcosoByDuan(id,java.sql.Date.valueOf(firstday),java.sql.Date.valueOf(endday)).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //here

    @GetMapping("/tiendoXDHTChitiets")
    public ResponseEntity<List<TblTiendoXaydunghatangChitiet>> getAllTblTiendoXaydunghatangChitiets() {
        try {
            List<TblTiendoXaydunghatangChitiet> tiendoXDHTChitiet = new ArrayList<TblTiendoXaydunghatangChitiet>();

            tiendoXDHTChitietRepository.findAll().forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tiendoXDHTChitiet/{id}")
    public ResponseEntity<TblTiendoXaydunghatangChitiet> getTblTiendoXaydunghatangChitietById(@PathVariable("id") Integer id) {
        Optional<TblTiendoXaydunghatangChitiet> tiendoXDHTChitietData = tiendoXDHTChitietRepository.findById(id);

        if (tiendoXDHTChitietData.isPresent()) {
            return new ResponseEntity<>(tiendoXDHTChitietData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getTenKhuchuyennganh/{id}")
    public String getTenKhuchuyennganh(@PathVariable("id") Integer id){
        return tiendoXDHTChitietRepository.getTenKhuchuyennganh(id);
    }

    @GetMapping("/getTenKhukinhte/{id}")
    public String getTenKhukinhte(@PathVariable("id") Integer id){
        return tiendoXDHTChitietRepository.getTenKhukinhte(id);
    }

    @GetMapping("/tiendoXDHTChitietbytiendoXDHT/{id}")
    public ResponseEntity<List<TblTiendoXaydunghatangChitiet>> getTblTiendoXaydunghatangChitietBytiendoXDHT(@PathVariable("id") Integer id) {

        try {
            List<TblTiendoXaydunghatangChitiet> tiendoXDHTChitiet = new ArrayList<TblTiendoXaydunghatangChitiet>();

            tiendoXDHTChitietRepository.getTienDoChiTietByIdTiendo(id).forEach(tiendoXDHTChitiet::add);

            if (tiendoXDHTChitiet.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(tiendoXDHTChitiet, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tiendoXDHTChitiet")
    public ResponseEntity<TblTiendoXaydunghatangChitiet> createTblTiendoXaydunghatangChitiet(@RequestBody TblTiendoXaydunghatangChitiet tiendoXDHTChitiet) {
        try {
            TblTiendoXaydunghatangChitiet _tiendoXDHTChitiet = tiendoXDHTChitietRepository
                    .save(tiendoXDHTChitiet);
            return new ResponseEntity<>(_tiendoXDHTChitiet, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tiendoXDHTChitiet/{id}")
    public ResponseEntity<TblTiendoXaydunghatangChitiet> updateTblTiendoXaydunghatangChitiet(@PathVariable("id") Integer id, @RequestBody TblTiendoXaydunghatangChitiet tiendoXDHTChitiet) {
        Optional<TblTiendoXaydunghatangChitiet> tiendoXDHTChitietData = tiendoXDHTChitietRepository.findById(id);

        if (tiendoXDHTChitietData.isPresent()) {
            TblTiendoXaydunghatangChitiet _tiendoXDHTChitiet = tiendoXDHTChitietData.get();
            _tiendoXDHTChitiet.setKhoiluongHoanthanh(tiendoXDHTChitiet.getKhoiluongHoanthanh());
            _tiendoXDHTChitiet.setThanhphan(tiendoXDHTChitiet.getThanhphan());
            _tiendoXDHTChitiet.setTiendoCapvon(tiendoXDHTChitiet.getTiendoCapvon());
            _tiendoXDHTChitiet.setTiendoGiaingan(tiendoXDHTChitiet.getTiendoGiaingan());
            _tiendoXDHTChitiet.setIdTrangthai(tiendoXDHTChitiet.getIdTrangthai());
            
            return new ResponseEntity<>(tiendoXDHTChitietRepository.save(_tiendoXDHTChitiet), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tiendoXDHTChitiet/{id}")
    public ResponseEntity<HttpStatus> deleteTblTiendoXaydunghatangChitiet(@PathVariable("id") Integer id) {
        try {
            tiendoXDHTChitietRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/tiendoXDHTChitiet")
    public ResponseEntity<HttpStatus> deleteAllTblTiendoXaydunghatangChitiets() {
        try {
            tiendoXDHTChitietRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}