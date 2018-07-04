package com.example.demo.service;

import com.example.demo.dao.PriceDao;
import com.example.demo.dao.ZhuangDao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Price;
import com.example.demo.entity.Zhuang;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Baowen1102Service {

    @Autowired
    private PriceDao priceDao;
    @Autowired
    private ZhuangDao zhuangDao;
    @Transactional
    public String analyze(Baowen baowen,Channel channel){
        System.out.println(" 1102报文解析");
        String shujuyu = baowen.getShujuyu();
        String ip = channel.remoteAddress().toString().substring(1).split(":")[0];
        Zhuang zhuang = zhuangDao.findZhuangByIp(ip);
        Price price = priceDao.findPrice(zhuang.getNumber());
        if(price!=null){
            price.setShijianduan1(stringToString(shujuyu.substring(0,2))+":"+stringToString(shujuyu.substring(2,4))+"-"+
                    stringToString(shujuyu.substring(4,6))+":"+stringToString(shujuyu.substring(6,8)));
            price.setShijianduan2(stringToString(shujuyu.substring(16,18))+":"+stringToString(shujuyu.substring(18,20))+"-"+
                    stringToString(shujuyu.substring(20,22))+":"+stringToString(shujuyu.substring(22,24)));
            price.setShijianduan3(stringToString(shujuyu.substring(32,34))+":"+stringToString(shujuyu.substring(34,36))+"-"+
                    stringToString(shujuyu.substring(36,38))+":"+stringToString(shujuyu.substring(38,40)));
            price.setShijianduan4(stringToString(shujuyu.substring(48,50))+":"+stringToString(shujuyu.substring(50,52))+"-"+
                    stringToString(shujuyu.substring(52,54))+":"+stringToString(shujuyu.substring(54,56)));
            price.setPrice1((double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(shujuyu.substring(8,16)),16)/100);
            price.setPrice2((double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(shujuyu.substring(24,32)),16)/100);
            price.setPrice3((double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(shujuyu.substring(40,48)),16)/100);
            price.setPrice4((double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(shujuyu.substring(56,64)),16)/100);
            priceDao.updatePrice(price);
        }else {
            price = new Price();
            price.setNumber(zhuang.getNumber());
            price.setShijianduan1(stringToString(shujuyu.substring(0,2))+":"+stringToString(shujuyu.substring(2,4))+"-"+
                    stringToString(shujuyu.substring(4,6))+":"+stringToString(shujuyu.substring(6,8)));
            price.setShijianduan2(stringToString(shujuyu.substring(16,18))+":"+stringToString(shujuyu.substring(18,20))+"-"+
                    stringToString(shujuyu.substring(20,22))+":"+stringToString(shujuyu.substring(22,24)));
            price.setShijianduan3(stringToString(shujuyu.substring(32,34))+":"+stringToString(shujuyu.substring(34,36))+"-"+
                    stringToString(shujuyu.substring(36,38))+":"+stringToString(shujuyu.substring(38,40)));
            price.setShijianduan4(stringToString(shujuyu.substring(48,50))+":"+stringToString(shujuyu.substring(50,52))+"-"+
                    stringToString(shujuyu.substring(52,54))+":"+stringToString(shujuyu.substring(54,56)));
            price.setPrice1((double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(shujuyu.substring(8,16)),16)/100);
            price.setPrice2((double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(shujuyu.substring(24,32)),16)/100);
            price.setPrice3((double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(shujuyu.substring(40,48)),16)/100);
            price.setPrice4((double) Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(shujuyu.substring(56,64)),16)/100);
            priceDao.addPrice(price);
        }
        return "0000";
    }

    public String stringToString(String str){
        int i = Integer.parseInt(str,16);
        if(i<10){
            return "0"+i;
        }
        return ""+i;
    }
}
