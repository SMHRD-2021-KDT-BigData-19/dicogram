package com.dicogram.domain;

public class posts {
	private int Pid;
	private String userId;
	private String pName;
	private String pContent;
	private String tags;
	private String imagePath;
	private int cnt;
	private String createDay;
	private int postId;
	private int grade;
	private String roomPath;
	public posts(int pid, String userId, String pName, String pContent, String tags, String imagePath,
			String roomPath) {
		super();
		Pid = pid;
		this.userId = userId;
		this.pName = pName;
		this.pContent = pContent;
		this.tags = tags;
		this.imagePath = imagePath;
		this.roomPath = roomPath;
	}
	public posts(int pid, String userId, String pContent, int cnt, String createDay, int postId, int grade) {
		super();
		Pid = pid;
		this.userId = userId;
		this.pContent = pContent;
		this.cnt = cnt;
		this.createDay = createDay;
		this.postId = postId;
		this.grade = grade;
	}
	public int getPid() {
		return Pid;
	}
	public void setPid(int pid) {
		Pid = pid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpContent() {
		return pContent;
	}
	public void setpContent(String pContent) {
		this.pContent = pContent;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public String getCreateDay() {
		return createDay;
	}
	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getRoomPath() {
		return roomPath;
	}
	public void setRoomPath(String roomPath) {
		this.roomPath = roomPath;
	}
	
}