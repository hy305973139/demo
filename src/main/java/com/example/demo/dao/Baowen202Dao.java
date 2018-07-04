package com.example.demo.dao;

import com.example.demo.entity.Baowen202;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface Baowen202Dao {
	public int add(Baowen202 baowen202);

	public Baowen202 findBaowen202byKaishi(@Param("chongdiankahao")String chongdiankahao,@Param("chongdiankaishishijian")String chongdiankaishishijian,
                          @Param("chongdianjieshushijian")String chongdianjieshushijian);
}
