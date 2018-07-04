package com.example.demo.service;

import com.example.demo.dao.Baowen8Dao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Baowen6;
import com.example.demo.entity.Baowen8;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 2018/4/12.
 */
@Service
public class Baowen8Service {
    @Autowired
    private Baowen8Dao baowen8Dao;

    public int analyze(Baowen bw){
        System.out.println("添加8报文");
        String yuliu1 = bw.getShujuyu().substring(0, 4);
        String yuliu2 = bw.getShujuyu().substring(4, 8);
        String chongdianzhuangbiaoma = bw.getShujuyu().substring(8, 72);
        String chongdianqiangkou = bw.getShujuyu().substring(72, 74);
        String minglingzhixingjieguo = bw.getShujuyu().substring(74, 82);
        Baowen8 baowen8 = new Baowen8();
        baowen8.setBaowenid(bw.getId());
        baowen8.setYuliu1(yuliu1);
        baowen8.setYuliu2(yuliu2);
        baowen8.setChongdianzhuangbiaoma(chongdianzhuangbiaoma);
        baowen8.setChongdianqiangkou(chongdianqiangkou);
        baowen8.setMinglingzhixingjieguo(minglingzhixingjieguo);
        int res = baowen8Dao.addBaowen8(baowen8);
        return res;
    }

}
