package com.example.demo.dao;

import com.example.demo.entity.Zhuang;

public interface ZhuangDao {

    public int addZhuang(Zhuang zhuang);

    public int updateZhuang(Zhuang zhuang);

    public Zhuang findZhuang(String number);

    public Zhuang findZhuangByIp(String ip);
}
