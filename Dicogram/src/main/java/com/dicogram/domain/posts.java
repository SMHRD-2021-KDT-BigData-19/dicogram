package com.dicogram.domain;

public class posts {
   private int postid;
   private String userid;
   private String postname;
   private String pcontent;
   private String tags;
   private String image;
   private int cnt;
   private String createday;
   private int parentid;
   private int commentid;
   private String room;

   public posts(String postname, String pcontent, String tags, String image) {
      super();
      this.postname = postname;
      this.pcontent = pcontent;
      this.tags = tags;
      this.image = image;
   }

   public posts() {
      super();
   }

   public posts(String userid, String postname, String pcontent, String tags, String image, String room) {
      super();
      this.userid = userid;
      this.postname = postname;
      this.pcontent = pcontent;
      this.tags = tags;
      this.image = image;
      this.room = room;
   }

   public posts(int postid, String userid, String postname, String pcontent, String tags, String image, int cnt,
         String createday, int parentid, int commentid, String room) {
      super();
      this.postid = postid;
      this.userid = userid;
      this.postname = postname;
      this.pcontent = pcontent;
      this.tags = tags;
      this.image = image;
      this.cnt = cnt;
      this.createday = createday;
      this.parentid = parentid;
      this.commentid = commentid;
      this.room = room;
   }

   public int getPid() {
      return postid;
   }

   public void setPid(int pid) {
      this.postid = pid;
   }

   public String getUserid() {
      return userid;
   }

   public void setUserid(String userid) {
      this.userid = userid;
   }

   public String getPostname() {
      return postname;
   }

   public void setPostname(String postname) {
      this.postname = postname;
   }

   public String getPcontent() {
      return pcontent;
   }

   public void setPcontent(String pcontent) {
      this.pcontent = pcontent;
   }

   public String getTags() {
      return tags;
   }

   public void setTags(String tags) {
      this.tags = tags;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public int getCnt() {
      return cnt;
   }

   public void setCnt(int cnt) {
      this.cnt = cnt;
   }

   public String getCreateday() {
      return createday;
   }

   public void setCreateday(String createday) {
      this.createday = createday;
   }

   public int getParentid() {
      return parentid;
   }

   public void setParentid(int parentid) {
      this.parentid = parentid;
   }

   public int getCommentid() {
      return commentid;
   }

   public void setCommentid(int commentid) {
      this.commentid = commentid;
   }

   public String getRoom() {
      return room;
   }

   public void setRoom(String room) {
      this.room = room;
   }

   public int getPostid() {
      return postid;
   }

   public void setPostid(int postid) {
      this.postid = postid;
   }
}