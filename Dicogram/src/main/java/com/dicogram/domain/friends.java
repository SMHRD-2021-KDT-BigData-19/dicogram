package com.dicogram.domain;

public class friends {
	private String sendId;
	private String requestId;
	private String status;
	public friends(String sendId, String requestId, String status) {
		super();
		this.sendId = sendId;
		this.requestId = requestId;
		this.status = status;
	}
	public friends(String sendId, String requestId) {
		super();
		this.sendId = sendId;
		this.requestId = requestId;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}