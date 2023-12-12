package com.dicogram.domain;

public class Users {
	private String name;
	private String userid;
	private String pw;
	private String nick;
	private String email;
	private String propath;
	
	public Users() {
		super();
	}

	public Users(String name, String userid, String pw, String nick, String email) {
		super();
		this.name = name;
		this.userid = userid;
		this.pw = pw;
		this.nick = nick;
		this.email = email;
	}
	public Users(String userid, String pw) {
		super();
		this.userid = userid;
		this.pw = pw;
	}
	
	
	public Users(String name, String userid, String pw, String nick, String email, String propath) {
		super();
		this.name = name;
		this.userid = userid;
		this.pw = pw;
		this.nick = nick;
		this.email = email;
		this.propath = propath;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getPropath() {
		return propath;
	}
	public void setPropath(String propath) {
		this.propath = propath;
	}

}
