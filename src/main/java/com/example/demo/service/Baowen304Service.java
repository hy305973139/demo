package com.example.demo.service;

import com.example.demo.dao.Baowen304Dao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Baowen304;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 2018/4/9.
 */
@Service
public class Baowen304Service {
    @Autowired
    private Baowen304Dao baowen304Dao;

    public String analyze(Baowen bw){
        System.out.println("304报文解析");
        String yuliu1 = (bw.getShujuyu().substring(0, 4));
        String yuliu2 = (bw.getShujuyu().substring(4, 8));
        String number = (bw.getShujuyu().substring(8, 72));
        String gongzuozhuangtai = bw.getShujuyu().substring(72,74);
        String chelianjiezhuangtai = bw.getShujuyu().substring(74,76);
        String brmcheliangbianshibaowen = bw.getShujuyu().substring(76,204);
        String vbubaowen = bw.getShujuyu().substring(204,332);
        String bcpdonglixudianchichongdiancanshu = bw.getShujuyu().substring(332,364);
        String brodianchichongdianzhunbeijiuxuzhuangtai = bw.getShujuyu().substring(364,380);
        String bcldianchichongdianxuqiu = bw.getShujuyu().substring(380,396);
        String bcsdianchichongdianzongzhuangtai = bw.getShujuyu().substring(396,428);
        String bsmdonglixudianchizhuangtaixinxi = bw.getShujuyu().substring(428,444);
        String bstzhongzhichongdian = bw.getShujuyu().substring(444,460);
        String bsdbmstongjishuju = bw.getShujuyu().substring(460,476);
        String bembaowen = bw.getShujuyu().substring(476,492);
        Baowen304 baowen304 = new Baowen304();
        baowen304.setBaowenId(bw.getId());
        baowen304.setYuliu1(yuliu1);
        baowen304.setYuliu2(yuliu2);
        baowen304.setNumber(number);
        baowen304.setGongzuozhuangtai(gongzuozhuangtai);
        baowen304.setChelianjiezhuangtai(chelianjiezhuangtai);
        baowen304.setBrmcheliangbianshibaowen(brmcheliangbianshibaowen);
        baowen304.setVbubaowen(vbubaowen);
        baowen304.setBcpdonglixudianchichongdiancanshu(bcpdonglixudianchichongdiancanshu);
        baowen304.setBrodianchichongdianzhunbeijiuxuzhuangtai(brodianchichongdianzhunbeijiuxuzhuangtai);
        baowen304.setBcldianchichongdianxuqiu(bcldianchichongdianxuqiu);
        baowen304.setBcsdianchichongdianzongzhuangtai(bcsdianchichongdianzongzhuangtai);
        baowen304.setBsdbmstongjishuju(bsdbmstongjishuju);
        baowen304.setBsmdonglixudianchizhuangtaixinxi(bsmdonglixudianchizhuangtaixinxi);
        baowen304.setBstzhongzhichongdian(bstzhongzhichongdian);
        baowen304.setBembaowen(bembaowen);
        int result = baowen304Dao.addBaowen304(baowen304);
        String res = bw.getBanbenyu()+"2f01"+yuliu1+yuliu2;
        res = GaoDiWeiZhuanHuan.fanhui(res);
        if(result>1){
            return res;
        }
        return res;
    }
}
