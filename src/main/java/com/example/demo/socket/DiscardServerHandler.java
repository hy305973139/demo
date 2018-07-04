package com.example.demo.socket;

import com.example.demo.service.BaowenService;
import com.example.demo.util.SpringUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

import java.util.HashMap;


/**
 * @author
 * 处理服务端channel
 */
@Component
@ChannelHandler.Sharable
public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
    public static HashMap<String,Channel> channleMap=new HashMap();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        String ipport = channel.remoteAddress().toString();
        String ip = ipport.substring(1).split(":")[0];
//        System.out.println(ip);
//        channleMap.put(ip,channel);
        ByteBuf in=(ByteBuf)msg;
        //全部的报文
        byte[] bytes=new byte[in.readableBytes()];
        in.readBytes(bytes);
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex ="";
            if(bytes[i]<=15&&bytes[i]>=0){
                hex = "0"+Integer.toHexString(bytes[i] & 0xFF);
            }else {
                hex = Integer.toHexString(bytes[i] & 0xFF);
            }
            str.append(hex);
        }
        String responseMsg = SpringUtil.getBean(BaowenService.class).getBaowen(str.toString(),channel);
        response(ctx,stringToByte(responseMsg));
        in.resetReaderIndex();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public void response(ChannelHandlerContext ctx, byte[] resp){
        ByteBuf buf = Unpooled.copiedBuffer(resp);
        ctx.writeAndFlush(buf);
    }

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
