//package com.example.demo.socket;
//
//import com.example.demo.service.BaowenService;
//import com.example.demo.util.SpringUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.nio.ByteBuffer;
//import java.nio.channels.SelectionKey;
//import java.nio.channels.Selector;
//import java.nio.channels.ServerSocketChannel;
//import java.nio.channels.SocketChannel;
//import java.nio.charset.Charset;
//import java.sql.Timestamp;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//import java.util.Set;
//
///**
// * nio socket服务端
// */
//@Component
//public class SocketServer {
//    //接受数据缓冲区
//    private static ByteBuffer sBuffer = ByteBuffer.allocate(8192);
//    //发送数据缓冲区
//    private static ByteBuffer rBuffer = ByteBuffer.allocate(8192);
//    //选择器（叫监听器更准确些吧应该）
//    private static Selector selector;
//
//    public static Selector getSelector() {
//        return selector;
//    }
//
//    public static Map<String, SocketChannel> map = new HashMap<String, SocketChannel>();
//
//    /**
//     * 启动socket服务，开启监听
//     * @param port
//     * @throws IOException
//     */
//    public void startSocketServer(int port){
//        try {
//            //打开通信信道
//            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
//            //设置为非阻塞
//            serverSocketChannel.configureBlocking(false);
//            //获取套接字
//            ServerSocket serverSocket = serverSocketChannel.socket();
//            //绑定端口号
//            serverSocket.bind(new InetSocketAddress(port));
//            //打开监听器
//            selector = Selector.open();
//            //将通信信道注册到监听器
//            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//
//            //监听器会一直监听，如果客户端有请求就会进入相应的事件处理
//            while (true){
//                selector.select();//select方法会一直阻塞直到有相关事件发生或超时
//                Set<SelectionKey> selectionKeys = selector.selectedKeys();//监听到的事件
//                for (SelectionKey key : selectionKeys) {
//                    handle(key);
//                }
//                selectionKeys.clear();//清除处理过的事件
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 处理不同的事件
//     * @param selectionKey
//     * @throws IOException
//     */
//    private void handle(SelectionKey selectionKey) throws IOException {
//        ServerSocketChannel serverSocketChannel = null;
//        SocketChannel socketChannel = null;
//        try {
//           String requestMsg = "";
//           int count = 0;
//           if (selectionKey.isAcceptable()) {
//               //每有客户端连接，即注册通信信道为可读
//               serverSocketChannel = (ServerSocketChannel)selectionKey.channel();
//               socketChannel = serverSocketChannel.accept();
//               socketChannel.configureBlocking(false);
//               socketChannel.register(selector, SelectionKey.OP_READ);
//               String ip = socketChannel.getRemoteAddress().toString().split(":")[0].substring(1);
//               map.put(ip,socketChannel);
//           }
//           else if (selectionKey.isReadable()) {
//               System.out.print(new Timestamp(System.currentTimeMillis())+"    ");
//               socketChannel = (SocketChannel)selectionKey.channel();
//               String ip = socketChannel.getRemoteAddress().toString().split(":")[0].substring(1);
//               rBuffer.clear();
//               count = socketChannel.read(rBuffer);
//               //读取数据
//               String str = "";
//               if (count > 0) {
//                   rBuffer.flip();
//                   byte[] bbb = rBuffer.array();
//                   for (int i = 0; i < count; i++) {
//                       String hex ="";
//                       if(bbb[i]<=15&&bbb[i]>=0){
//                           hex = "0"+Integer.toHexString(bbb[i] & 0xFF);
//                       }else {
//                           hex = Integer.toHexString(bbb[i] & 0xFF);
//                       }
//                       str += hex;
//                   }
//               }
////            返回值
//               String responseMsg = SpringUtil.getBean(BaowenService.class).getBaowen(str.toString(),ip);
////               System.out.println("返回值："+responseMsg);
//               byte[] bys = new byte[responseMsg.length()/2];
//               for (int i =0;i<responseMsg.length();i=i+2){
//                   int b = Integer.parseInt(responseMsg.substring(i, i + 2), 16);
//                   bys[i/2] = (byte) b;
//               }
//               sBuffer = ByteBuffer.allocate(responseMsg.length()/2);
//               sBuffer.put(bys);
//               sBuffer.flip();
//               socketChannel.write(sBuffer);
//           }
//       }catch (Exception e){
//            e.printStackTrace();
//            socketChannel.close();
//       }
//    }
//
//
//
//}