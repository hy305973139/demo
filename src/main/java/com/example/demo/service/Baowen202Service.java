package com.example.demo.service;

import com.example.demo.dao.Baowen102Dao;
import com.example.demo.dao.Baowen202Dao;
import com.example.demo.dao.OrdersystemDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.ZhuangDao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Baowen102;
import com.example.demo.entity.Baowen202;
import com.example.demo.entity.Ordersystem;
import com.example.demo.entity.User;
import com.example.demo.entity.Zhuang;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;

/**
 * Created by huang on 2018/4/9.
 */
@Service
public class Baowen202Service {
    @Autowired
    private Baowen202Dao baowen202Dao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ZhuangDao zhuangDao;
    @Autowired
    private OrdersystemDao ordersystemDao;

    @Transactional
    public String analyze(Baowen bw){
        System.out.println("202充电记录上传报文");
        String yuliu1 = (bw.getShujuyu().substring(0, 4));
        String yuliu2 = (bw.getShujuyu().substring(4, 8));
        String number = (bw.getShujuyu().substring(8, 72));
        String chongdianqiangweizhileixing = bw.getShujuyu().substring(72, 74);
        String chongdianqiangkou = bw.getShujuyu().substring(74, 76);
        String chongdiankahao = bw.getShujuyu().substring(76, 140);
        String chongdiankaishishijian = bw.getShujuyu().substring(140, 156);
        String chongdianjieshushijian = bw.getShujuyu().substring(156, 172);
        String chongdianshijianchangdu = bw.getShujuyu().substring(172, 180);
        String kaishiSOC = bw.getShujuyu().substring(180, 182);
        String jieshuSOC = bw.getShujuyu().substring(182, 184);
        String chongdianjieshuyuanyin = bw.getShujuyu().substring(184, 192);
        String bencichongdiandianliang = bw.getShujuyu().substring(192, 200);
        String chongdianqiandianbiaodushu = bw.getShujuyu().substring(200, 208);
        String chongdianhoudianbiaodushu = bw.getShujuyu().substring(208, 216);
        String beicichongdianjine = bw.getShujuyu().substring(216, 224);
        String beiyong = bw.getShujuyu().substring(224, 232);
        String chongdianqiankayue = bw.getShujuyu().substring(232, 240);
        String dangqianchongdianjilusuoyin = bw.getShujuyu().substring(240, 248);
        String zongchongdianjilutiaomu = bw.getShujuyu().substring(248, 256);
        String yuliu = bw.getShujuyu().substring(256, 258);
        String chongdiancelue = bw.getShujuyu().substring(258, 260);
        String chongdianceluecanshu = bw.getShujuyu().substring(260, 268);
        String cheliangVIN = bw.getShujuyu().substring(268, 302);
        String chepaihao = bw.getShujuyu().substring(302, 318);
        String shiduan1 = bw.getShujuyu().substring(318, 322);
        String shiduan2 = bw.getShujuyu().substring(322, 326);
        String shiduan3 = bw.getShujuyu().substring(326, 330);
        String shiduan4 = bw.getShujuyu().substring(330, 334);
        String shiduan5 = bw.getShujuyu().substring(334, 338);
        String shiduan6 = bw.getShujuyu().substring(338, 342);
        String shiduan7 = bw.getShujuyu().substring(342, 346);
        String shiduan8 = bw.getShujuyu().substring(346, 350);
        String shiduan9 = bw.getShujuyu().substring(350, 354);
        String shiduan10 = bw.getShujuyu().substring(354, 358);
        String shiduan11 = bw.getShujuyu().substring(358, 362);
        String shiduan12 = bw.getShujuyu().substring(362, 366);
        String shiduan13 = bw.getShujuyu().substring(366, 370);
        String shiduan14 = bw.getShujuyu().substring(370, 374);
        String shiduan15 = bw.getShujuyu().substring(374, 378);
        String shiduan16 = bw.getShujuyu().substring(378, 382);
        String shiduan17 = bw.getShujuyu().substring(382, 386);
        String shiduan18 = bw.getShujuyu().substring(386, 390);
        String shiduan19 = bw.getShujuyu().substring(390, 394);
        String shiduan20 = bw.getShujuyu().substring(394, 398);
        String shiduan21 = bw.getShujuyu().substring(398, 402);
        String shiduan22 = bw.getShujuyu().substring(402, 406);
        String shiduan23 = bw.getShujuyu().substring(406, 410);
        String shiduan24 = bw.getShujuyu().substring(410, 414);
        String shiduan25 = bw.getShujuyu().substring(414, 418);
        String shiduan26 = bw.getShujuyu().substring(418, 422);
        String shiduan27 = bw.getShujuyu().substring(422, 426);
        String shiduan28 = bw.getShujuyu().substring(426, 430);
        String shiduan29 = bw.getShujuyu().substring(430, 434);
        String shiduan30 = bw.getShujuyu().substring(434, 438);
        String shiduan31 = bw.getShujuyu().substring(438, 442);
        String shiduan32 = bw.getShujuyu().substring(442, 446);
        String shiduan33 = bw.getShujuyu().substring(446, 450);
        String shiduan34 = bw.getShujuyu().substring(450, 454);
        String shiduan35 = bw.getShujuyu().substring(454, 458);
        String shiduan36 = bw.getShujuyu().substring(458, 462);
        String shiduan37 = bw.getShujuyu().substring(462, 466);
        String shiduan38 = bw.getShujuyu().substring(466, 470);
        String shiduan39 = bw.getShujuyu().substring(470, 474);
        String shiduan40 = bw.getShujuyu().substring(474, 478);
        String shiduan41 = bw.getShujuyu().substring(478, 482);
        String shiduan42 = bw.getShujuyu().substring(482, 486);
        String shiduan43 = bw.getShujuyu().substring(486, 490);
        String shiduan44 = bw.getShujuyu().substring(490, 494);
        String shiduan45 = bw.getShujuyu().substring(494, 498);
        String shiduan46 = bw.getShujuyu().substring(498, 502);
        String shiduan47 = bw.getShujuyu().substring(502, 506);
        String shiduan48 = bw.getShujuyu().substring(506, 510);
        String qidongfangshi = bw.getShujuyu().substring(510, 512);
        Baowen202 baowen202 = new Baowen202();
        baowen202.setBaowenId(bw.getId());
        baowen202.setYuliu1(yuliu1);
        baowen202.setYuliu2(yuliu2);
        baowen202.setNumber(number);
        baowen202.setChongdianqiangweizhileixing(chongdianqiangweizhileixing);
        baowen202.setChongdianqiangkou(chongdianqiangkou);
        baowen202.setChongdiankahao(chongdiankahao);
        baowen202.setChongdiankaishishijian(chongdiankaishishijian);
        baowen202.setChongdianjieshushijian(chongdianjieshushijian);
        baowen202.setChongdianshijianchangdu(chongdianshijianchangdu);
        baowen202.setKaishiSOC(kaishiSOC);
        baowen202.setJieshuSOC(jieshuSOC);
        baowen202.setChongdianjieshuyuanyin(chongdianjieshuyuanyin);
        baowen202.setBencichongdiandianliang(bencichongdiandianliang);
        baowen202.setChongdianqiandianbiaodushu(chongdianqiandianbiaodushu);
        baowen202.setChongdianhoudianbiaodushu(chongdianhoudianbiaodushu);
        baowen202.setBeicichongdianjine(beicichongdianjine);
        baowen202.setBeiyong(beiyong);
        baowen202.setChongdianqiankayue(chongdianqiankayue);
        baowen202.setDangqianchongdianjilusuoyin(dangqianchongdianjilusuoyin);
        baowen202.setZongchongdianjilutiaomu(zongchongdianjilutiaomu);
        baowen202.setYuliu(yuliu);
        baowen202.setChongdiancelue(chongdiancelue);
        baowen202.setChongdianceluecanshu(chongdianceluecanshu);
        baowen202.setCheliangVIN(cheliangVIN);
        baowen202.setChepaihao(chepaihao);
        baowen202.setShiduan1(shiduan1);
        baowen202.setShiduan2(shiduan2);
        baowen202.setShiduan3(shiduan3);
        baowen202.setShiduan4(shiduan4);
        baowen202.setShiduan5(shiduan5);
        baowen202.setShiduan6(shiduan6);
        baowen202.setShiduan7(shiduan7);
        baowen202.setShiduan8(shiduan8);
        baowen202.setShiduan9(shiduan9);
        baowen202.setShiduan10(shiduan10);
        baowen202.setShiduan11(shiduan11);
        baowen202.setShiduan12(shiduan12);
        baowen202.setShiduan13(shiduan13);
        baowen202.setShiduan14(shiduan14);
        baowen202.setShiduan15(shiduan15);
        baowen202.setShiduan16(shiduan16);
        baowen202.setShiduan17(shiduan17);
        baowen202.setShiduan18(shiduan18);
        baowen202.setShiduan19(shiduan19);
        baowen202.setShiduan20(shiduan20);
        baowen202.setShiduan21(shiduan21);
        baowen202.setShiduan22(shiduan22);
        baowen202.setShiduan23(shiduan23);
        baowen202.setShiduan24(shiduan24);
        baowen202.setShiduan25(shiduan25);
        baowen202.setShiduan26(shiduan26);
        baowen202.setShiduan27(shiduan27);
        baowen202.setShiduan28(shiduan28);
        baowen202.setShiduan29(shiduan29);
        baowen202.setShiduan30(shiduan30);
        baowen202.setShiduan31(shiduan31);
        baowen202.setShiduan32(shiduan32);
        baowen202.setShiduan33(shiduan33);
        baowen202.setShiduan34(shiduan34);
        baowen202.setShiduan35(shiduan35);
        baowen202.setShiduan36(shiduan36);
        baowen202.setShiduan37(shiduan37);
        baowen202.setShiduan38(shiduan38);
        baowen202.setShiduan39(shiduan39);
        baowen202.setShiduan40(shiduan40);
        baowen202.setShiduan41(shiduan41);
        baowen202.setShiduan42(shiduan42);
        baowen202.setShiduan43(shiduan43);
        baowen202.setShiduan44(shiduan44);
        baowen202.setShiduan45(shiduan45);
        baowen202.setShiduan46(shiduan46);
        baowen202.setShiduan47(shiduan47);
        baowen202.setShiduan48(shiduan48);
        baowen202.setQidongfangshi(qidongfangshi);

        Baowen202 bw202 = baowen202Dao.findBaowen202byKaishi(chongdiankahao, chongdiankaishishijian, chongdianjieshushijian);
        

        int result = 0;
        if(!baowen202.equals(bw202)){
            result = baowen202Dao.add(baowen202);
        }
        String res = bw.getXuliehaoyu()+"c900"+yuliu1+yuliu2+chongdianqiangkou+chongdiankahao;
        res =  GaoDiWeiZhuanHuan.fanhui(res);
        if(result>0){
            User user = userDao.findUserByKahao(chongdiankahao);
            String feiyong = GaoDiWeiZhuanHuan.zhuanhuan(bencichongdiandianliang);
            //跟钱相关的
//            double qian = (double) Integer.parseInt(feiyong, 16)/100;
//            user.setBalance(-qian);
            double powernumber = (double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(bencichongdiandianliang), 16)/100;
            user.setPowernumber(-powernumber);
            userDao.updateUser(user);
            Zhuang zhuang = new Zhuang();
//            String s = GaoDiWeiZhuanHuan.stringToChars(number);
            zhuang.setNumber(number);
            zhuang.setStatus(0);
            zhuangDao.updateZhuang(zhuang);
            Ordersystem os = new Ordersystem();
            os.setUserid(user.getId());
            os.setPowernumber(powernumber);
            os.setStatus(1);
            String ordernumber = GaoDiWeiZhuanHuan.getUUID();
            os.setOrdernumber(ordernumber);
            ordersystemDao.addOrdersystem(os);
            return res;
        }
        return res;
    }
}

