package com.example.demo.dao;

import com.example.demo.entity.Ordersystem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface OrdersystemDao {

    public int addOrdersystem(Ordersystem ordersystem);

    public List<Ordersystem> findByUserid(@Param("userid") String userid,@Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize,@Param("status") int status);

    public int findTotal(@Param("userid")String userid,@Param("status") int status);
}
