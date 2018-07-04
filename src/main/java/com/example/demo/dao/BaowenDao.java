package com.example.demo.dao;

import com.example.demo.entity.Baowen;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaowenDao {
	public int addBaowen(Baowen baowen);
}
