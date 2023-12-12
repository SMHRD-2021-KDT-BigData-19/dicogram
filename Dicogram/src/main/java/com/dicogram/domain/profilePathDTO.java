package com.dicogram.domain;

public class profilePathDTO {
	private String userid;
	private String profile;
	
	public profilePathDTO(String userid, String profile) {
		super();
		this.userid = userid;
		this.profile = profile;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	
}
