package com.example.demo.controller;

import com.example.demo.dao.Baowen104Dao;
import com.example.demo.dao.OrdersystemDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.ZhuangDao;
import com.example.demo.entity.Baowen104;
import com.example.demo.entity.Ordersystem;
import com.example.demo.entity.Price;
import com.example.demo.entity.User;
import com.example.demo.entity.Zhuang;
import com.example.demo.service.UserService;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import com.example.demo.util.JsonResult;
import com.example.demo.util.UuidRandom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2018/3/30.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ZhuangDao zhuangDao;
    @Autowired
    private Baowen104Dao baowen104Dao;
    @Autowired
    private OrdersystemDao ordersystemDao;

    /**
     * 充电
     * @return
     */
    @Transactional
    @PostMapping("/chongdian")
    public String chongdian(@RequestBody User user1,HttpServletRequest request){
        String num = "SHDL20170108";
        String number = GaoDiWeiZhuanHuan.strToChar(num);
        String userid = request.getParameter("userid");
        //绑定桩的userid和正在使用状态
//        Zhuang zhuang = zhuangDao.findZhuang(num);
//        if(zhuang.getStatus()==1){
//            return "充电桩正在有人使用";
//        }
//        zhuang.setUserid(userid);
//        zhuang.setNumber(num);
//        zhuang.setStatus(1);
//        zhuangDao.updateZhuang(zhuang);
//        User user = userDao.findUserById(userid);
//        String kahao = "2d00000000000000000000000000000000000000000000000000000000000000";
        int res = userService.chong(number, userid);
        if(res==0){
            return "充电失败";
        }else if(res ==2){
            return "账户余额不足";
        }else if(res ==3){
            return "是,请充值";
        }else if(res == 4){
            return "充电桩正在有人使用";
        }
        return "成功充电";
    }

    /**
     * 停止充电
     * @return
     */
    @RequestMapping("/stop")
    public String stop(HttpServletRequest request){
        String num = "SHDL20170108";
        String number = GaoDiWeiZhuanHuan.strToChar(num);
        String userid = request.getParameter("userid");
        Zhuang zhuang = zhuangDao.findZhuang(number);
        if(zhuang!=null&&zhuang.getStatus()==1){
            if(!userid.equals(zhuang.getUserid())){
                return "此充电桩有人正在使用";
            }
        }
        int res = userService.stop(number);
        if(res==0){
            return "停止失败";
        }
        return "停止成功";
    }

    @RequestMapping("/showPrice")
    public JsonResult show(HttpServletRequest request){
        String num = "SHDL20170108";
        String number = GaoDiWeiZhuanHuan.strToChar(num);
        String userid = request.getParameter("userid");
        Price price = userService.show(number);
        if(null == price){
            return new JsonResult(false,"fail");
        }
        return new JsonResult(true,price);
    }

    @PostMapping(value = "yue")
    public JsonResult yue(HttpServletRequest request){
        String userid = request.getParameter("userid");
        User user = userDao.findUserById(userid);
        return new JsonResult(true,user);
    }


    @PostMapping(value = "chongqian")
    @Transactional
    public JsonResult updateMoney(@RequestBody User user,HttpServletRequest request){
        String userid = request.getParameter("userid");
        user.setId(userid);
        int result = userDao.updateUser(user);
        User u = userDao.findUserById(user.getId());
        Ordersystem ordersystem = new Ordersystem();
        ordersystem.setPaybank(0);
        ordersystem.setUserid(user.getId());
        ordersystem.setPowernumber(user.getPowernumber());
        ordersystem.setStatus(0);
        ordersystem.setOrdernumber(GaoDiWeiZhuanHuan.getUUID());
        ordersystemDao.addOrdersystem(ordersystem);
        if(result>0){
            return new JsonResult(true,u);
        }
        return new JsonResult(false,"充值失败");
    }


    @PostMapping(value = "addUser")
    public JsonResult addUser(@RequestBody User user,HttpServletRequest request){
        String userid = request.getParameter("userid");
        user.setKahao(UuidRandom.randomHexString()+"000000000000000000000000000000000000000000000000");
        int result = userDao.addUser(user);
        if(result>0){
            return new JsonResult(true,"添加成功");
        }
        return new JsonResult(false,"添加失败");
    }

    @PostMapping(value = "getStatus")
    public JsonResult findStatus(HttpServletRequest request){
        String userid = request.getParameter("userid");
        String num = "SHDL20170108";
        String sss = GaoDiWeiZhuanHuan.strToChar(num);
        List<Baowen104> list = baowen104Dao.findBaowen104(sss);
        if(list.size()>0){
//            User user = userDao.findUserByKahao(list.get(0).getChongdiankahao());
            User user = userDao.findUserById(userid);
            Map<String,Object> map = new HashMap<>();
            map.put("dianliu",(double)Integer.parseInt(list.get(0).getZhiliuchongdiandianliu(),16)/10);
            map.put("dianya",(double)Integer.parseInt(list.get(0).getZhiliuchongdiandianya(),16)/10);
            map.put("beicichongdiandianliang",(double)Integer.parseInt(list.get(0).getBeicichongdianleijichongdiandianliang(),16)/100);
            map.put("soc",Integer.parseInt(list.get(0).getDangqianSOC(),16));
            map.put("yichongshijian",Integer.parseInt(list.get(0).getChongdianshichang(),16));
            if (user!=null){
                map.put("shengyudianliang",user.getPowernumber()-Integer.parseInt(list.get(0).getBeicichongdianleijichongdiandianliang(),16)/100);
            }else {
                map.put("shengyudianliang","充电后可查询");
            }
            map.put("chongdianzhuangtai",list.get(0).getGongzuozhuangtai());
            return new JsonResult(true,map);
        }
        return new JsonResult(false,"请检查充电枪");
    }


    @RequestMapping(value = "getOrder/{status}/{startPage}/{count}/{sessionid}")
    public JsonResult getOrder(@PathVariable("startPage") int startPage,@PathVariable("count") int count,@PathVariable("status") int status,
                               @PathVariable("sessionid")String sessionid,HttpServletRequest request){
//        try {
            String userid = request.getParameter("userid");
            List<Ordersystem> os = ordersystemDao.findByUserid(userid, (startPage-1)*count, count,status);
            int total = ordersystemDao.findTotal(userid, status);
            Map<String,Object> map = new HashMap<String, Object>();
            if(total%count==0){
                map.put("totalPage",total/count);
            }else {
                map.put("totalPage",total/count+1);
            }
            map.put("startPage",startPage);
            map.put("count",count);
            map.put("items",os);
            map.put("sessionid",sessionid);
        System.out.println(map);
            return new JsonResult(true,map);
//        }catch (Exception e){
//            return new JsonResult(false,"没有更多数据");
//        }
    }

}
