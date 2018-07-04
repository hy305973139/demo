package com.example.demo.dao;


import com.example.demo.entity.Baowen104;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Baowen104Dao {
	public int addBaowen104(Baowen104 baowen104);

	public List<Baowen104> findBaowen104(String number);
}
