package com.example.demo.service;

import com.example.demo.dao.Baowen102Dao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Baowen102;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 2018/4/9.
 */
@Service
public class Baowen102Service {
    @Autowired
    private Baowen102Dao baowen102Dao;

    public String analyze(Baowen bw){
        System.out.println("    102心跳包");
        String yuliu1 = bw.getShujuyu().substring(0, 4);
        String yuliu2 = bw.getShujuyu().substring(4, 8);
        String number = bw.getShujuyu().substring(8, 72);
        String xintiaoxuhao = bw.getShujuyu().substring(72, 76);
        Baowen102 baowen102 = new Baowen102();
        baowen102.setYuliu1(yuliu1);
        baowen102.setYuliu2(yuliu2);
        baowen102.setNumber(number);
        baowen102.setXintiaoxuhao(xintiaoxuhao);
//        int row = baowen102Dao.addBaowen102(baowen102);
        String res = bw.getXuliehaoyu()+"6500"+yuliu1+yuliu2+"0001";
        res = GaoDiWeiZhuanHuan.fanhui(res);
//        if(row>1){
            return res;
//        }
//        return res;
    }
}
