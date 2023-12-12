package com.dicogram.domain;

public class NickPropathDTO {
	private String userid;
	private String nick;
	private String profile;
	   
	public NickPropathDTO() {
		super();
	}
	
	public NickPropathDTO(String userid, String nick, String profile) {
		super();
		this.userid = userid;
		this.nick = nick;
		this.profile = profile;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
   
}
