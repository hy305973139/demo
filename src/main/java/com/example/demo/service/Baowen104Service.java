package com.example.demo.service;

import com.example.demo.dao.Baowen104Dao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Baowen103;
import com.example.demo.entity.Baowen104;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 2018/4/9.
 */
@Service
public class Baowen104Service {
    @Autowired
    private Baowen104Dao baowen104Dao;

    public String analyze(Baowen bw){
        System.out.println("104报文开始解析");
        String yuliu1 = (bw.getShujuyu().substring(0, 4));
        String yuliu2 = (bw.getShujuyu().substring(4, 8));
        String number = (bw.getShujuyu().substring(8, 72));
        String chongdianqiangshuliang = (bw.getShujuyu().substring(72, 74));
        String chongdiankouhao = (bw.getShujuyu().substring(74, 76));
        String chongdianqiangleixing = (bw.getShujuyu().substring(76, 78));
        String gongzuozhuangtai = (bw.getShujuyu().substring(78, 80));
        String dangqianSOC = (bw.getShujuyu().substring(80, 82));
        String gaojingzhuangtai = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(82, 90));
        String chelianjiezhuangtai = (bw.getShujuyu().substring(90, 92));
        String beicichongdianfeiyong = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(92, 100));
        String neibubianliang2 = (bw.getShujuyu().substring(100, 108));
        String neibubianliang3 = (bw.getShujuyu().substring(108, 116));
        String zhiliuchongdiandianya = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(116, 120));
        String zhiliuchongdiandianliu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(120, 124));
        String bmsxuqiudianya = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(124, 128));
        String bmsxuqiudianliu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(128, 132));
        String bmschongdianmoshi = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(132, 134));
        String jiaoliuAdianya = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(134, 138));
        String jiaoliuBdianya = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(138, 142));
        String jiaoliuCdianya = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(142, 146));
        String jiaoliuAdianliu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(146, 150));
        String jiaoliuBdianliu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(150, 154));
        String jiaoliuCdianliu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(154, 158));
        String chengyuchongdianshijian = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(158, 162));
        String chongdianshichang = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(162, 170));
        String beicichongdianleijichongdiandianliang = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(170, 178));
        String chongdianqiandianbiaodushu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(178, 186));
        String dangqiandianbiaodushu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(186, 194));
        String chongdianqidongfangshi = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(194, 196));
        String chongdiancelue = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(196, 198));
        String chongdianceluecanshu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(198, 206));
        String yuyuebiaozhi = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(206, 208));
        String chongdiankahao = (bw.getShujuyu().substring(208, 272));
        String yuyuechaoshishijian = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(272, 274));
        String yuyuekaishichongdianshijian = (bw.getShujuyu().substring(274, 290));
        String chongdianqiankayue = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(290, 298));
        String yuliu = (bw.getShujuyu().substring(298, 306));
        String chongdiangonglv = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(306, 314));
        String xitongbianliang3 = (bw.getShujuyu().substring(314, 322));
        String xitongbianliang4 = (bw.getShujuyu().substring(322, 330));
        String xitongbianliang5 = (bw.getShujuyu().substring(330, 338));
        Baowen104 baowen104 = new Baowen104();
        baowen104.setBaowenId(bw.getId());
        baowen104.setYuliu1(yuliu1);
        baowen104.setYuliu2(yuliu2);
        baowen104.setNumber(number);
        baowen104.setChongdianqiangshuliang(chongdianqiangshuliang);
        baowen104.setChongdiankouhao(chongdiankouhao);
        baowen104.setChongdianqiangleixing(chongdianqiangleixing);
        baowen104.setGongzuozhuangtai(gongzuozhuangtai);
        baowen104.setDangqianSOC(dangqianSOC);
        baowen104.setGaojingzhuangtai(gaojingzhuangtai);
        baowen104.setChelianjiezhuangtai(chelianjiezhuangtai);
        baowen104.setBeicichongdianfeiyong(beicichongdianfeiyong);
        baowen104.setNeibubianliang2(neibubianliang2);
        baowen104.setNeibubianliang3(neibubianliang3);
        baowen104.setZhiliuchongdiandianya(zhiliuchongdiandianya);
        baowen104.setZhiliuchongdiandianliu(zhiliuchongdiandianliu);
        baowen104.setBmsxuqiudianya(bmsxuqiudianya);
        baowen104.setBmsxuqiudianliu(bmsxuqiudianliu);
        baowen104.setBmschongdianmoshi(bmschongdianmoshi);
        baowen104.setJiaoliuAdianya(jiaoliuAdianya);
        baowen104.setJiaoliuBdianya(jiaoliuBdianya);
        baowen104.setJiaoliuCdianya(jiaoliuCdianya);
        baowen104.setJiaoliuAdianliu(jiaoliuAdianliu);
        baowen104.setJiaoliuBdianliu(jiaoliuBdianliu);
        baowen104.setJiaoliuCdianliu(jiaoliuCdianliu);
        baowen104.setChengyuchongdianshijian(chengyuchongdianshijian);
        baowen104.setChongdianshichang(chongdianshichang);
        baowen104.setBeicichongdianleijichongdiandianliang(beicichongdianleijichongdiandianliang);
        baowen104.setChongdianqiandianbiaodushu(chongdianqiandianbiaodushu);
        baowen104.setDangqiandianbiaodushu(dangqiandianbiaodushu);
        baowen104.setChongdianqidongfangshi(chongdianqidongfangshi);
        baowen104.setChongdiancelue(chongdiancelue);
        baowen104.setChongdianceluecanshu(chongdianceluecanshu);
        baowen104.setYuyuebiaozhi(yuyuebiaozhi);
        baowen104.setChongdiankahao(chongdiankahao);
        baowen104.setYuyuechaoshishijian(yuyuechaoshishijian);
        baowen104.setYuyuekaishichongdianshijian(yuyuekaishichongdianshijian);
        baowen104.setChongdianqiankayue(chongdianqiankayue);
        baowen104.setYuliu(yuliu);
        baowen104.setChongdiangonglv(chongdiangonglv);
        baowen104.setXitongbianliang3(xitongbianliang3);
        baowen104.setXitongbianliang4(xitongbianliang4);
        baowen104.setXitongbianliang5(xitongbianliang5);
        baowen104Dao.addBaowen104(baowen104);
        //返回103报文
        Baowen103 baowen103 = new Baowen103();
        baowen103.setBaowen104Id(baowen104.getId());
        baowen103.setYuliu1(yuliu1);
        baowen103.setYuliu2(yuliu2);
        baowen103.setChongdiankouhao(chongdiankouhao);
        baowen103.setChongdiankahao(chongdiankahao);
        int aaa = Integer.parseInt(baowen104.getChongdianqiankayue(), 16);
        int bbb = Integer.parseInt(baowen104.getBeicichongdianleijichongdiandianliang(), 16);
        if (aaa > bbb) {
            String str = Integer.toHexString(aaa - bbb).toUpperCase();
            StringBuffer buff = new StringBuffer();
            for (int i = str.length(); i > 0; i -= 2) {
                buff.append(str.substring(i - 2, i));
            }
            baowen103.setKayue(buff.toString());
        } else if (aaa == bbb) {
            baowen103.setKayue("00000000");
        }
        baowen103.setDangqianchongdianjine(beicichongdianleijichongdiandianliang);
        String res = bw.getXuliehaoyu()+"6700"+baowen103.getYuliu1() + baowen103.getYuliu2() + baowen103.getChongdiankouhao() +
                baowen103.getChongdiankahao() + baowen103.getKayue() + baowen103.getDangqianchongdianjine();
        res = GaoDiWeiZhuanHuan.fanhui(res);
        if("03".equals(baowen104.getGongzuozhuangtai())){
            baowen104.setGongzuozhuangtai("02");
            baowen104.setDangqianSOC("00");
            baowen104.setChongdianshichang("00000000");
            baowen104Dao.addBaowen104(baowen104);
        }
        return res;
    }
}
