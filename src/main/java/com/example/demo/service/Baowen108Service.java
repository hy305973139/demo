package com.example.demo.service;

import com.example.demo.dao.Baowen102Dao;
import com.example.demo.dao.Baowen108Dao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Baowen102;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 2018/4/9.
 */
@Service
public class Baowen108Service {
    @Autowired
    private Baowen108Dao baowen108Dao;

    public String analyze(Baowen bw){
        System.out.println("108心跳包");
//        if(row>1){
//            return res;
//        }
        return "0000";
    }
}
