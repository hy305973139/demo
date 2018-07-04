package com.example.demo.service;

import com.example.demo.config.Command;
import com.example.demo.dao.PriceDao;
import com.example.demo.dao.UserDao;
import com.example.demo.dao.ZhuangDao;
import com.example.demo.entity.Price;
import com.example.demo.entity.User;
import com.example.demo.entity.Zhuang;
import com.example.demo.socket.DiscardServerHandler;
//import com.example.demo.socket.SocketServer;
import com.example.demo.util.GaoDiWeiZhuanHuan;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huang on 2018/4/9.
 */
@Service
public class UserService {

    @Autowired
    private ZhuangDao zhuangDao;
    @Autowired
    private PriceDao priceDao;
    @Autowired
    private UserDao userDao;
    //发送数据缓冲区
    private static ByteBuffer rBuffer = ByteBuffer.allocate(8192);

    /**
     * 充电功能（SHDL20170108对应的IP  192.168.100.201）
     * @param number
     * @param userid
     * @return
     */
    public int chong(String number,String userid){
        Zhuang zhuang = zhuangDao.findZhuang(number);
        if(zhuang.getStatus()==1){
            return Command.USERING;
        }
        zhuang.setUserid(userid);
        zhuang.setNumber(number);
        zhuang.setStatus(1);
        zhuangDao.updateZhuang(zhuang);
        User user = userDao.findUserById(userid);
        String send = "11070008000000"+"00"+"00000001" +"00000000"+"00000000"+"00000000"+
                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"ff"+"01"+user.getKahao()+"00"+"01000000";//即时充电。。充满ok
//        String send = "11070008000000"+"00"+"00000000" +"00000000"+"01000000"+"78000000"+
//                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"ff"+"01"+kahao+"00"+"01000000";//即时充电  时间控制充电
        User user1 = userDao.findUserByKahao(user.getKahao());
        if(null!=user&user.getPowernumber()<5){
            return Command.CHADIANLIANG;
        }
//        String send = "11070008000000"+"00"+"00000000" +"00000000"+"02000000"+hexMoney+
//                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"ff"+"01"+kahao+"00"+"01000000";//即时充电  金额控制充电ok
//        String send = "11070008000000"+"00"+"00000000" +"00000000"+"03000000"+"0a000000"+
//                new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+"ff"+"00"+kahao+"00"+"01000000";//即时充电  电量控制充电
        int res = delivery2(send,number);
        return res;
    }

    /**
     * 关机
     * @param number
     * @return
     */
    public int shutDown(String number){
        String send = "11050008000000"+"00"+"0b000000"+"01040055000000";//关机
        int res = delivery2(send,number);
        return res;
    }

    /**
     * 立即停止充电
     * @param number
     * @return
     */
    public int stop(String number){
        String send = "11050008000000"+"00"+"02000000"+"01040055000000";//立即停止充电功能
//        if("SHDL20170108".equals(num)){
//            ip = "192.168.100.201";
//        }
        int res = delivery2(send,number);
        return res;
    }
    //查询电费费率
    public Price show(String number){
        String send = "114d04";//发生1101查询费率
        int res = delivery2(send,number);
        HashMap<String, Channel> map = DiscardServerHandler.channleMap;
        Channel channel = map.get(number);
        String ip = channel.remoteAddress().toString().substring(1).split(":")[0];
        Zhuang zhuang = zhuangDao.findZhuangByIp(ip);
        if(zhuang==null){
            zhuang = new Zhuang();
            zhuang.setIp(ip);
            zhuang.setNumber(number);
            zhuangDao.addZhuang(zhuang);
        }
        Price price = priceDao.findPriceByNumber(number);
        return price;
    }

    public int danjia(String number,int price){
        String p = Integer.toHexString(price);
        if(p.length()==1){
            p ="0"+p+"00";
        }else if(p.length()==2){
            p = p+"00";
        }else if(p.length()==3){
            p = p.substring(1,3)+"0"+p.substring(0,1);
        }else if(p.length()==4){
            p = p.substring(2,4)+p.substring(0,2);
        }
        String send = "114f0400001800"+p+"00000000000000000000"+"0000000000000000"+"0000000000000000"+"0000000000000000"+"0000000000000000";//发送1103设置费率
        int res = delivery2(send,number);
        return res;
    }



    /**
     * 给对应的充电桩发送控制命令
     * @param
     * @return
     */
//    public int delivery(String send,String ip){
//        send = GaoDiWeiZhuanHuan.fanhui(send);
//        System.err.println(send);
//        try {
//            while (true) {
//                Map<String, SocketChannel> map = SocketServer.map;
//                SocketChannel socketChannel = map.get(ip);
//                if(socketChannel!=null){
//                    dispatch(socketChannel,send);
//                    break;
//                }
//            }
//            return Command.SUCCESS;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Command.FAIL;
//        }
//    }
    public int delivery2(String send,String number){
        send = GaoDiWeiZhuanHuan.fanhui(send);
        System.err.println(send);
        try {
            while (true) {
                HashMap<String, Channel> map = DiscardServerHandler.channleMap;
                Channel channel = map.get(number);
                if(null!=channel){
                    dispatch2(channel,send);
                    break;
                }
            }
            return Command.SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return Command.FAIL;
        }
    }

//    /**
//     * 给对应的桩编码对应的ip发相应的控制报文
//     * @param client
//     * @param responseMsg
//     * @throws IOException
//     */
//    private void dispatch(SocketChannel client, String responseMsg) throws IOException {
//        Socket s = client.socket();
//        OutputStream os = s.getOutputStream();
//        byte[] bys = new byte[responseMsg.length()/2];
//        for (int i =0;i<responseMsg.length();i=i+2){
//            int b = Integer.parseInt(responseMsg.substring(i, i + 2), 16);
//            bys[i/2] = (byte) b;
//        }
//        rBuffer = ByteBuffer.allocate(responseMsg.length()/2);
//        rBuffer.put(bys);
//        rBuffer.flip();
//        client.write(rBuffer);
//    }
    //给充电桩发送报文
    private void dispatch2(Channel channel, String responseMsg) throws IOException {
        byte[] bytes = stringToByte(responseMsg);
        ByteBuf buf = Unpooled.copiedBuffer(bytes);
        channel.writeAndFlush(buf);
    }

    //返回的字符串报文变成bytes数组
    public byte[] stringToByte(String s){
        byte[] temp=new byte[s.length()/2];
        int ps=0;
        for (int i=0;i<s.length();i+=2){
            int num = Integer.parseInt(s.substring(i, i + 2), 16);
            temp[ps]=(byte)num;
            ps++;
        }
        return temp;
    }
}
