package com.dicogram.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dicogram.domain.Users;
import com.dicogram.domain.UsersDAO;
import com.dicogram.domain.roomsDTO;

public class createChatRoomCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("createChatRoomCon에 들어옴");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		String roomname = request.getParameter("roomname");
		String pw = request.getParameter("pw");
		String setting = request.getParameter("setting");
		if(setting == null) setting = "N";
		
		Users loginUser = (Users)session.getAttribute("loginUser");
		String userid = loginUser.getUserid();
		
		System.out.println("방이름: " + roomname);
		System.out.println("비번: " + pw);
		System.out.println("공개여부: " + setting);
		System.out.println("만든 사람: " + userid);

		// rooms DB에 채팅방 새로 저장
		roomsDTO createChatRoomMem = new roomsDTO(roomname, userid, setting, pw);
		
		UsersDAO dao = new UsersDAO();
		int cnt = dao.createChatRoom(createChatRoomMem);
		
		if(cnt>0) {
			System.out.println("방 코드는: "+ createChatRoomMem.getRoomid());
			response.sendRedirect("userdataToDBCon?roomid=" + createChatRoomMem.getRoomid() + "&userid=" + userid);
		}
		else System.out.println("방 입장 입장 에러");
	}

}