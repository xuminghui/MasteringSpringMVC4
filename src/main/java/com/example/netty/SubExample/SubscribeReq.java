package com.example.netty.SubExample;

import java.io.Serializable;

public class SubscribeReq implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int subReqID;
	private String userName;
	private String productName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getSubReqID() {
		return subReqID;
	}
	public void setSubReqID(int subReqID) {
		this.subReqID = subReqID;
	}
	
}
