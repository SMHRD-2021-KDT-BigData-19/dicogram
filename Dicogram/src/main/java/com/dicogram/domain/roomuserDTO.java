package com.dicogram.domain;

public class roomuserDTO {
	private long roomid;
	private String roomname;
	private String userid;
	
	public roomuserDTO() {
		super();
	}

	public roomuserDTO(long roomid, String roomname, String userid) {
		super();
		this.roomid = roomid;
		this.roomname = roomname;
		this.userid = userid;
	}

	public roomuserDTO(long roomid, String userid) {
		super();
		this.roomid = roomid;
		this.userid = userid;
	}

	public roomuserDTO(String roomname, String userid) {
		super();
		this.roomname = roomname;
		this.userid = userid;
	}

	public long getRoomid() {
		return roomid;
	}

	public void setRoomid(long roomid) {
		this.roomid = roomid;
	}

	public String getRoomname() {
		return roomname;
	}

	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}
