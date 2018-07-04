package com.example.demo.service;

import com.example.demo.dao.Baowen106Dao;
import com.example.demo.dao.ZhuangDao;
import com.example.demo.entity.Baowen;
import com.example.demo.entity.Baowen106;
import com.example.demo.entity.Zhuang;
import com.example.demo.socket.DiscardServerHandler;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by huang on 2018/4/9.
 */
@Service
public class Baowen106Service {
    @Autowired
    private Baowen106Dao baowen106Dao;
    @Autowired
    private ZhuangDao zhuangDao;
    public String analyze(Baowen bw, Channel channel){
        System.out.println("106报文存档");
        String yuliu1 = bw.getShujuyu().substring(0, 4);
        String yuliu2 = bw.getShujuyu().substring(4, 8);
        String number = (bw.getShujuyu().substring(8, 72));
        DiscardServerHandler.channleMap.put(number, channel);
        Byte[] data = new Byte[number.length()];
        for (int i = 0; i < number.length(); i = i + 2) {
            String ssss = number.substring(i, i + 2);
            byte b = (byte) Integer.parseInt(ssss, 16);
            data[i / 2] = b;
        }
        String ip = channel.remoteAddress().toString().substring(1).split(":")[0];
        Zhuang zhuang = new Zhuang();
        zhuang.setNumber(number);
        zhuang.setIp(ip);
        Zhuang zhuang1 = zhuangDao.findZhuang(number);
        if(zhuang1!=null){
            zhuangDao.updateZhuang(zhuang);
        }else {
            zhuangDao.addZhuang(zhuang);
        }
        String baoliu = (bw.getShujuyu().substring(72, 74));
        String banben = (bw.getShujuyu().substring(74, 82));
        String xiangmuleixing = (bw.getShujuyu().substring(82, 86));
        String qidongcishu = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(86, 94));
        String shangchuangmoshi = (bw.getShujuyu().substring(94, 96));
        String qiandaojiangeshijian = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(96, 100));
        String neibubianliang = (bw.getShujuyu().substring(100, 102));
        String geshu = (bw.getShujuyu().substring(102, 104));
        String xintiaozhouqi = (bw.getShujuyu().substring(104, 106));
        String chaoshicishu = (bw.getShujuyu().substring(106, 108));
        String chongdianjilushuliang = GaoDiWeiZhuanHuan.zhuanhuan(bw.getShujuyu().substring(108, 116));
        String xitongshijian = (bw.getShujuyu().substring(116, 132));
        String baoliu16 = (bw.getShujuyu().substring(132, 148));
        String baoliu17 = (bw.getShujuyu().substring(148, 164));
        String baoliu18 = (bw.getShujuyu().substring(164, 180));
        Baowen106 baowen106 = new Baowen106();
        baowen106.setBaowenId(bw.getId());
        baowen106.setYuliu1(yuliu1);
        baowen106.setYuliu2(yuliu2);
        baowen106.setNumber(number);
        baowen106.setBaoliu(baoliu);
        baowen106.setBanben(banben);
        baowen106.setXiangmuleixing(xiangmuleixing);
        baowen106.setQidongcishu(qidongcishu);
        baowen106.setShangchuangmoshi(shangchuangmoshi);
        baowen106.setQiandaojiangeshijian(qiandaojiangeshijian);
        baowen106.setNeibubianliang(neibubianliang);
        baowen106.setGeshu(geshu);
        baowen106.setXintiaozhouqi(xintiaozhouqi);
        baowen106.setChaoshicishu(chaoshicishu);
        baowen106.setChongdianjilushuliang(chongdianjilushuliang);
        baowen106.setXitongshijian(xitongshijian);
        baowen106.setBaoliu16(baoliu16);
        baowen106.setBaoliu17(baoliu17);
        baowen106.setBaoliu18(baoliu18);
        int row = baowen106Dao.addBaowen106(baowen106);
//						String res = "110700"+yuliu1+yuliu2+"00"+"00000000" +"00000000"+"00000000"+"00000000"+"20180330163304ff"+"01"+"2d00000000000000000000000000000000000000000000000000000000000000"+"00"+"01000000";
        String res = bw.getXuliehaoyu()+"6900"+"0800"+"0000";
        res = GaoDiWeiZhuanHuan.fanhui(res);
        return res;
    }
}
