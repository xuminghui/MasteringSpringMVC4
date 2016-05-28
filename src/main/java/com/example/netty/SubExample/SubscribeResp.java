package com.example.netty.SubExample;

import java.io.Serializable;

public class SubscribeResp implements Serializable {

	private static final long serialVersionUID = 1L;
	private int subReqID;
	private String respCode;
	private String desc;
	public int getSubReqID() {
		return subReqID;
	}
	public void setSubReqID(int subReqID) {
		this.subReqID = subReqID;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
