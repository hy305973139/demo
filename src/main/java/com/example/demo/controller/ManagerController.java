package com.example.demo.controller;

import com.example.demo.service.UserService;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/manager")
public class ManagerController {
    @Autowired
    private UserService userService;
    /**
     * 关机
     * @return
     */
    @RequestMapping("/shutDown")
    public String shutDown(){
        String num = "SHDL20170108";
        String number = GaoDiWeiZhuanHuan.strToChar(num);
        int res = userService.shutDown(number);
        if(res==0){
            return "关机失败";
        }
        return "关机成功";
    }

    /**
     * 修改价格
     * @param price
     * @return
     */
    @RequestMapping("/danjia/{price}")
    public String danjia(@PathVariable int price){
        String num = "SHDL20170108";
        String number = GaoDiWeiZhuanHuan.strToChar(num);
        int res = userService.danjia(number,price);
        if(res==0){
            return "fail";
        }
        return "success";
    }
}
