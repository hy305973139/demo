package com.example.demo.controller;

import com.example.demo.util.JsonResult;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Arrays;

@RestController
public class SampleController {

    private static final String serverURL = "http://vop.baidu.com/server_api";
    private static String token = "";
    private static final String testFileName = "test.pcm"; // 百度语音提供技术支持
    //put your own params here
    // 下面3个值要填写自己申请的app对应的值
    private static final String apiKey = "NXDNWfpEHq4I26FzI5XobqZA";
    private static final String secretKey = "984535a3f7eaf0acb692c64c2814206e";
    private static String cuid ;
    static {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            System.out.println("ip:"+ia.toString());
            cuid = getLocalMac(ia);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("parseVideo")
    public JsonResult parseVideo(@RequestParam("filedata")MultipartFile filedata) throws Exception {
        getToken();
        String s = method2(filedata);
        return new JsonResult(true,s);
    }

    private static String getLocalMac(InetAddress ia) throws SocketException {
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for(int i=0; i<mac.length; i++) {
            if(i!=0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i]&0xff;
            String str = Integer.toHexString(temp);
            if(str.length()==1) {
                sb.append("0"+str);
            }else {
                sb.append(str);
            }
        }
        return sb.toString().toUpperCase();
    }
    private static void getToken() throws Exception {
        String getTokenURL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials" +
                "&client_id=" + apiKey + "&client_secret=" + secretKey;
        HttpURLConnection conn = (HttpURLConnection) new URL(getTokenURL).openConnection();
        token = new JSONObject(printResponse(conn)).getString("access_token");
    }

    private static void method1(MultipartFile filedata) throws Exception {
//        File pcmFile = new File(testFileName);
//        File pcmFile = ResourceUtils.getFile("classpath:test.wav");
        HttpURLConnection conn = (HttpURLConnection) new URL(serverURL).openConnection();
        String filename = filedata.getOriginalFilename();
        String[] splits = filename.split(".");
        // construct params
        JSONObject params = new JSONObject();
        params.put("format", splits[1]);
        params.put("rate", 16000);
        params.put("channel", "1");
        params.put("token", token);
        params.put("lan", "zh");
        params.put("cuid", cuid);
        params.put("len", filedata.getSize());
        params.put("speech", DatatypeConverter.printBase64Binary(loadFile(filedata)));

        // add request header
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

        conn.setDoInput(true);
        conn.setDoOutput(true);

        // send request
        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
        wr.writeBytes(params.toString());
        wr.flush();
        wr.close();

        printResponse(conn);
    }

    private static String method2(MultipartFile filedata) throws Exception {
        try {
            String filename = filedata.getOriginalFilename();
            String[] splits = filename.split("\\.");
            HttpURLConnection conn = (HttpURLConnection) new URL(serverURL
                    + "?cuid=" + cuid + "&token=" + token).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "audio/"+splits[1]+"; rate=16000");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            // send request
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(loadFile(filedata));
            wr.flush();
            wr.close();
            String result = getUtf8String(printResponse(conn));
            JSONObject jsonObject = new JSONObject(result);
            int err_no = jsonObject.getInt("err_no");
            if(err_no!=0){
                HttpURLConnection conn1 = (HttpURLConnection) new URL(serverURL
                        + "?cuid=" + cuid + "&token=" + token).openConnection();
                conn1.setRequestMethod("POST");
                conn1.setRequestProperty("Content-Type", "audio/"+splits[1]+"; rate=8000");
                conn1.setDoInput(true);
                conn1.setDoOutput(true);
                // send request
                DataOutputStream wr1 = new DataOutputStream(conn1.getOutputStream());
                wr1.write(loadFile(filedata));
                wr1.flush();
                wr1.close();
                result = getUtf8String(printResponse(conn1));
            }
            return result;
        }catch (Exception e){

        }
        return "文件类型或者频率不对";
    }

    private static String printResponse(HttpURLConnection conn) throws Exception {
        if (conn.getResponseCode() != 200) {
            return "";
        }
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        String line;
        StringBuffer response = new StringBuffer();
        while ((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();
    }

    private static byte[] loadFile(MultipartFile filedata) throws IOException {
        InputStream is = filedata.getInputStream();
        long length = filedata.getSize();
        byte[] bytes = new byte[(int) length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            is.close();
            throw new IOException("Could not completely read file " + filedata.getOriginalFilename());
        }

        is.close();
        return bytes;
    }

    // GBK编码转为UTF-8
    private static String getUtf8String(String s) throws UnsupportedEncodingException
    {
        StringBuffer sb = new StringBuffer();
        sb.append(s);
        String xmlString = "";
        String xmlUtf8 = "";
        xmlString = new String(sb.toString().getBytes("UTF-8"));
        xmlUtf8 = URLEncoder.encode(xmlString , "UTF-8");

        return URLDecoder.decode(xmlUtf8, "UTF-8");
    }
}
