package com.example.demo.util;

import java.util.Arrays;
import java.util.UUID;

public class GaoDiWeiZhuanHuan {
	/**
	 * 	高低位转换
	 * @param str
	 * @return
	 */
	public static String zhuanhuan(String str){
		String sss = "";
		for(int i = 0 ;i<str.length();i=i+2){
			sss += str.substring(str.length()-2-i,str.length()-i);
		}
		return sss;
	}

	public static String stringToChars(String str){
		char[] chars = new char[str.length()/2];
		for(int i = 0 ;i<str.length();i=i+2){
			String ss= str.substring(i,i+2);
			char b = (char)Integer.parseInt(ss, 16);
			chars[i/2] = b;
		}
		String result ="";
		for (char c:chars){
			if(c>31){
				result = result + c +"";
			}
		}
		return result;
	}

	/**
	 * 字符串变成64位的16进制编码
	 * @param num
	 * @return
	 */
	public static String strToChar(String num) {
		char[] chars = num.toCharArray();
		String result = "";
        for (char c :chars){
            int a = c;
            String s = Integer.toHexString(a);
			result += s;
        }
		int length = result.length();
		if(length<64){
			for(int i =0;i<64-length;i++){
				result += "0";
			}
		}
		return result;
	}

	/**
	 * 校验和域
	 * @param str
	 * @return
	 */
	public static int calcuShCheckbit(String  str) {
		byte[] data = new byte[str.length()/2];
		for (int i = 0; i < str.length(); i=i+2) {
			String strs = str.substring(i, i+2);
			byte b = (byte) Integer.parseInt(strs,16);
			data[i/2] = b;
		}
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}
		return sum & 0xff;
	}

    /**
     * 自动加入aaf5+长度+版本域+校验和域
     * @param str(序列号域+CMD+数据域)
     * @return
     */
	public static String fanhui(String str){
        int len = str.length()/2+6;
        String changduyu = "";
        if(len>=0&&len<=15){
            changduyu = "0"+Integer.toHexString(len);
        }else {
            changduyu = Integer.toHexString(len);
        }
        if(changduyu.length()==2){
            changduyu =changduyu+"00";
        }else if(changduyu.length()==3){
            changduyu = changduyu.substring(1,3)+"0"+changduyu.substring(0,1);
        }else if(changduyu.length()==4){
            changduyu = changduyu.substring(2,4)+changduyu.substring(0,2);
        }
        int cc = calcuShCheckbit(str.substring(2, str.length()));
        String jym = "";
        if(cc>=0&&cc<=15){
            jym = "0"+Integer.toHexString(cc);
        }else {
            jym = Integer.toHexString(cc);
        }
        str = "aaf5"+changduyu+"10"+str+jym;
		return str;
	}

    //获取uuid32位的
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
