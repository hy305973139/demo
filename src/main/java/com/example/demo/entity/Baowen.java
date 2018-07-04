package com.example.demo.entity;

/**
 * Created by huang on 2018/3/29.
 */
public class Baowen {
	private long id;

	private String qishiyu;
	private String changduyu;
	private String banbenyu;
	private String xuliehaoyu;
	private String minglingdaiCMD;
	private String shujuyu;
	private String jiaoyanyu;
	public String getQishiyu() {
		return qishiyu;
	}
	public void setQishiyu(String qishiyu) {
		this.qishiyu = qishiyu;
	}
	public String getChangduyu() {
		return changduyu;
	}
	public void setChangduyu(String changduyu) {
		if(changduyu.length()>3){
			this.changduyu = changduyu.substring(2,4)+changduyu.substring(0,2);
		}
	}
	public String getBanbenyu() {
		return banbenyu;
	}
	public void setBanbenyu(String banbenyu) {
		this.banbenyu = banbenyu;
	}
	public String getXuliehaoyu() {
		return xuliehaoyu;
	}
	public void setXuliehaoyu(String xuliehaoyu) {
		this.xuliehaoyu = xuliehaoyu;
	}
	public String getMinglingdaiCMD() {
		return minglingdaiCMD;
	}
	public void setMinglingdaiCMD(String minglingdaiCMD) {
		String sss = "";
		for(int i = 0 ;i<minglingdaiCMD.length();i=i+2){
			sss += minglingdaiCMD.substring(minglingdaiCMD.length()-2-i,minglingdaiCMD.length()-i);
		}
		this.minglingdaiCMD=sss;
	}
	public String getJiaoyanyu() {
		return jiaoyanyu;
	}
	public void setJiaoyanyu(String jiaoyanyu) {
		this.jiaoyanyu = jiaoyanyu;
	}
	public String getShujuyu() {
		return shujuyu;
	}
	public void setShujuyu(String shujuyu) {
		this.shujuyu = shujuyu;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
}
