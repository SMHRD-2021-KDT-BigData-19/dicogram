package com.dicogram.domain;

public class roomsDTO {
	private long roomid;
	private String roomname;
	private String userid;
	private String createDay;
	private String setting;
	private String pw;
	
	public roomsDTO() {
		super();
	}
	public roomsDTO(long roomid, String roomname, String pw) {
		super();
		this.roomid = roomid;
		this.roomname = roomname;
		this.pw = pw;
	}
	
	public roomsDTO(String roomname, String userid, String setting, String pw) {
		super();
		this.roomname = roomname;
		this.userid = userid;
		this.setting = setting;
		this.pw = pw;
	}

	public roomsDTO(long roomid, String roomname, String userid, String setting, String pw) {
		super();
		this.roomid = roomid;
		this.roomname = roomname;
		this.userid = userid;
		this.setting = setting;
		this.pw = pw;
	}

	public roomsDTO(long roomid, String roomname) {
		super();
		this.roomid = roomid;
		this.roomname = roomname;
	}

	public long getRoomid() {
		return roomid;
	}

	public void setRoomid(long roomid) {
		this.roomid = roomid;
	}

	public String getRname() {
		return roomname;
	}

	public void setRname(String roomname) {
		this.roomname = roomname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCreateDay() {
		return createDay;
	}

	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}
	
}
