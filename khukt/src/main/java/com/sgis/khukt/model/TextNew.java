package com.sgis.khukt.model;

import com.sgis.khukt.repository.TblKetquaSanxuatkinhdoanhRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextNew {
    @Autowired
    TblKetquaSanxuatkinhdoanhRepository ketquaSanxuatkinhdoanhRepository;



    public void test(){
        System.out.println(ketquaSanxuatkinhdoanhRepository.findAll());

    }
}
