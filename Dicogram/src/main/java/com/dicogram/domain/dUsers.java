package com.dicogram.domain;

public class dUsers {
	private String id;
	private String pw;
	private String nick;
	private String email;
	private int cnt;
	private String status;
	private String proPath;
	private String endDate;
	
	
	public dUsers(String id, String pw, String nick, String email, String proPath) {
		super();
		this.id = id;
		this.pw = pw;
		this.nick = nick;
		this.email = email;
		this.proPath = proPath;
	}
	public dUsers(String pw, String nick, String proPath) {
		super();
		this.pw = pw;
		this.nick = nick;
		this.proPath = proPath;
	}
	public dUsers(String id, String status, String endDate, String aa) {
		super();
		this.id = id;
		this.status = status;
		this.endDate = endDate;
	}
	public dUsers(String id, String pw) {
		super();
		this.id = id;
		this.pw = pw;
	}
	public dUsers(String pw) {
		super();
		this.pw = pw;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProPath() {
		return proPath;
	}
	public void setProPath(String proPath) {
		this.proPath = proPath;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}