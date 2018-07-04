package com.example.demo.service;

import com.example.demo.dao.BaowenDao;
import com.example.demo.entity.Baowen;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class BaowenService {
	@Autowired
    private BaowenDao baowenDao;
	@Autowired
    private Baowen6Service baowen6Service;
	@Autowired
    private Baowen104Service baowen104Service;
    @Autowired
    private Baowen102Service baowen102Service;
    @Autowired
    private Baowen106Service baowen106Service;
	@Autowired
	private Baowen108Service baowen108Service;
	@Autowired
	private Baowen202Service baowen202Service;
	@Autowired
	private Baowen302Service baowen302Service;
	@Autowired
	private Baowen304Service baowen304Service;
	@Autowired
	private Baowen1102Service baowen1102Service;

	public String getBaowen(String baowen,Channel channel) {
		System.out.print(baowen);
		if (baowen.startsWith("aaf5")) {
			if (baowen.length() > 20) {
				if (!"aaf5".equals(baowen)) {
					String qishiyu = baowen.substring(0, 4);
					String changduyu = baowen.substring(4, 8);
					int changdu = Integer.parseInt(GaoDiWeiZhuanHuan.zhuanhuan(changduyu), 16);
					String banbenyu = baowen.substring(8, 10);
					String xuliehaoyu = baowen.substring(10, 12);
					String minglingdaiCMD = baowen.substring(12, 16);
					String shujuyu = baowen.substring(16, 16 + (changdu - 9) * 2);
					String jiaoyanyu = baowen.substring(changdu*2-2, changdu*2);
                    int ss1 = Integer.parseInt(jiaoyanyu, 16);
                    int ss2 = GaoDiWeiZhuanHuan.calcuShCheckbit(minglingdaiCMD + shujuyu);
                    if(ss1!=ss2){
                        System.out.println("================================检验失败===============================");
                        return "0000";
                    }
					Baowen bw = new Baowen();
					bw.setQishiyu(qishiyu);
					bw.setChangduyu(changduyu);
					bw.setBanbenyu(banbenyu);
					bw.setXuliehaoyu(xuliehaoyu);
					bw.setMinglingdaiCMD(minglingdaiCMD);
					bw.setShujuyu(shujuyu);
					bw.setJiaoyanyu(jiaoyanyu);
                    int xuhao = Integer.parseInt(bw.getXuliehaoyu(), 16);
                    String xuhaoh = Integer.toHexString(xuhao + 1);
                    if(xuhaoh.length()==1){
                        xuhaoh = "0"+xuhaoh;
                    }
					if ("6800".equals(minglingdaiCMD)) {
						if (baowen.length() >= 356) {
							shujuyu = baowen.substring(16, 354);
							jiaoyanyu = baowen.substring(354, 356);
							bw.setShujuyu(shujuyu);
							bw.setJiaoyanyu(jiaoyanyu);
//							baowenDao.addBaowen(bw);
						}
					} else {
//						baowenDao.addBaowen(bw);
					}
					//6报文解析
					if("0006".equals(bw.getMinglingdaiCMD())){
                        baowen6Service.analyze(bw);
                    }
					//102报文解析
					if ("0066".equals(bw.getMinglingdaiCMD())) {
                        return baowen102Service.analyze(bw);
					}
					//104报文解析
					if ("0068".equals(bw.getMinglingdaiCMD())) {
						return baowen104Service.analyze(bw);
					}
                    //106报文解析
                    if ("006a".equals(bw.getMinglingdaiCMD())) {
                        return baowen106Service.analyze(bw,channel);
                    }
                    //报文解析202
					if ("00ca".equals(bw.getMinglingdaiCMD())) {
                        return baowen202Service.analyze(bw);
                    }
                    //302报文解析
                    if ("012e".equals(bw.getMinglingdaiCMD())) {
                        return baowen302Service.analyze(bw);
                    }
                    //报文解析304
                    if("0130".equals(bw.getMinglingdaiCMD())){
                        return baowen304Service.analyze(bw);
                    }
                    //108报文解析
                    if("006c".equals(bw.getMinglingdaiCMD())){
                        return baowen108Service.analyze(bw);
                    }
                    //1102存储
                    if("044e".equals(bw.getMinglingdaiCMD())){
						return baowen1102Service.analyze(bw,channel);
					}
                }
			}
		}
		return "0000";
	}
}
