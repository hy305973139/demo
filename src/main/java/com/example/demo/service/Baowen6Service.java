package com.example.demo.service;

import com.example.demo.dao.Baowen6Dao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Baowen6;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 2018/4/12.
 */
@Service
public class Baowen6Service {
    @Autowired
    private Baowen6Dao baowen6Dao;

    public int analyze(Baowen bw){
        System.out.println("添加6报文");
        String yuliu1 = bw.getShujuyu().substring(0, 4);
        String yuliu2 = bw.getShujuyu().substring(4, 8);
        String chongdianzhuangbiaoma = bw.getShujuyu().substring(8, 72);
        String chongdianqiangkou = bw.getShujuyu().substring(72, 74);
        String minglingqishibiaozhi = bw.getShujuyu().substring(74, 82);
        String minglinggeshu = bw.getShujuyu().substring(82, 84);
        String minglingzhixingjieguo = bw.getShujuyu().substring(84, 86);
        Baowen6 baowen6 = new Baowen6();
        baowen6.setBaowenid(bw.getId());
        baowen6.setYuliu1(yuliu1);
        baowen6.setYuliu2(yuliu2);
        baowen6.setChongdianzhuangbiaoma(chongdianzhuangbiaoma);
        baowen6.setChongdianqiangkou(chongdianqiangkou);
        baowen6.setMinglingqishibiaozhi(minglingqishibiaozhi);
        baowen6.setMinglinggeshu(minglinggeshu);
        baowen6.setMinglingzhixingjieguo(minglingzhixingjieguo);
        int res = baowen6Dao.addBaowen6(baowen6);
        return res;
    }
}
