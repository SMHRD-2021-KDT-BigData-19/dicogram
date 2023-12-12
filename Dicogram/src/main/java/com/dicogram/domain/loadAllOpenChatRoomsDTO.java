package com.dicogram.domain;

public class loadAllOpenChatRoomsDTO {
	private long roomid;
	private String roomname;
	private String pw;
	
	public loadAllOpenChatRoomsDTO() {
		super();
	}

	public loadAllOpenChatRoomsDTO(long roomid, String roomname, String pw) {
		super();
		this.roomid = roomid;
		this.roomname = roomname;
		this.pw = pw;
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

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

}
