package com.dicogram.domain;

public class messageDTO {
	private long roomid;
	private String userid;
	private String mcontent;
	private String createday;
	

	public messageDTO() {
		super();
	}
	
	public messageDTO(String userid, String mcontent, String createday) {
		super();
		this.userid = userid;
		this.mcontent = mcontent;
		this.createday = createday;
	}

	public messageDTO(long roomid, String userid, String mcontent) {
		super();
		this.roomid = roomid;
		this.userid = userid;
		this.mcontent = mcontent;
	}

	public long getRid() {
		return roomid;
	}

	public void setRid(long roomid) {
		this.roomid = roomid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getMcontent() {
		return mcontent;
	}

	public void setMcontent(String mcontent) {
		this.mcontent = mcontent;
	}
	
	public String getCreateday() {
		return createday;
	}

	public void setCreateday(String createday) {
		this.createday = createday;
	}
}
