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

import com.sgis.khukt.model.TblStyleIcon;
import com.sgis.khukt.repository.TblStyleIconRepository;

@CrossOrigin(origins = {"http://localhost:8080","http://10.168.1.196:8080"})
@RestController
@RequestMapping("/styleicon")
public class TblStyleIconController {
    @Autowired
    TblStyleIconRepository styleiconRepository;

    @GetMapping("/styleicons")
    public ResponseEntity<List<TblStyleIcon>> getAllTblStyleIcons() {
        try {
            List<TblStyleIcon> styleicon = new ArrayList<TblStyleIcon>();
            
            //styleiconRepository.findAll().forEach(styleicon::add);
            styleiconRepository.findAllOrderByID().forEach(styleicon::add);

            if (styleicon.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(styleicon, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/styleicon/{id}")
    public ResponseEntity<TblStyleIcon> getTblStyleIconById(@PathVariable("id") long id) {
        Optional<TblStyleIcon> styleiconData = styleiconRepository.findById(id);

        if (styleiconData.isPresent()) {
            return new ResponseEntity<>(styleiconData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/styleicon")
    public ResponseEntity<TblStyleIcon> createTblStyleIcon(@RequestBody TblStyleIcon styleicon) {
        try {
            TblStyleIcon _styleicon = styleiconRepository
                    .save(new TblStyleIcon());
            return new ResponseEntity<>(_styleicon, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/styleicon/{id}")
    public ResponseEntity<TblStyleIcon> updateTblStyleIcon(@PathVariable("id") long id, @RequestBody TblStyleIcon styleicon) {
        Optional<TblStyleIcon> styleiconData = styleiconRepository.findById(id);

        if (styleiconData.isPresent()) {
            TblStyleIcon _styleicon = styleiconData.get();
            _styleicon.setIconAllowOverlap(_styleicon.getIconAllowOverlap());
            _styleicon.setIconAnchor(_styleicon.getIconAnchor());
            _styleicon.setIconColor(_styleicon.getIconColor());
            _styleicon.setIconHaloBlur(_styleicon.getIconHaloBlur());
            _styleicon.setIconHaloColor(_styleicon.getIconHaloColor());
            _styleicon.setIconHaloWidth(_styleicon.getIconHaloWidth());
            _styleicon.setIconIgnorePlacement(_styleicon.getIconIgnorePlacement());
            _styleicon.setIconImage(_styleicon.getIconImage());
            _styleicon.setIconKeepUpright(_styleicon.getIconKeepUpright());
            _styleicon.setIconOffset(_styleicon.getIconOffset());
            _styleicon.setIconOpacity(_styleicon.getIconOpacity());
            _styleicon.setIconOptional(_styleicon.getIconOptional());
            _styleicon.setIconOverlap(_styleicon.getIconOverlap());
            _styleicon.setIconPadding(_styleicon.getIconPadding());
            _styleicon.setIconPitchAlignment(_styleicon.getIconPitchAlignment());
            _styleicon.setIconRotate(_styleicon.getIconRotate());
            _styleicon.setIconRotationAlignment(_styleicon.getIconRotationAlignment());
            _styleicon.setIconSize(_styleicon.getIconSize());
            _styleicon.setIconTextFit(_styleicon.getIconTextFit());
            _styleicon.setIconTextFitPadding(_styleicon.getIconTextFitPadding());
            _styleicon.setIconTranslate(_styleicon.getIconTranslate());
            _styleicon.setIconTranslateAnchor(_styleicon.getIconTranslateAnchor());
            _styleicon.setVisibility(_styleicon.getVisibility());
            
            return new ResponseEntity<>(styleiconRepository.save(_styleicon), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/styleicon/{id}")
    public ResponseEntity<HttpStatus> deleteTblStyleIcon(@PathVariable("id") long id) {
        try {
            styleiconRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/styleicon")
    public ResponseEntity<HttpStatus> deleteAllTblStyleIcons() {
        try {
            styleiconRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}