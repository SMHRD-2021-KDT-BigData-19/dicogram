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
		
		// 새로운 채팅방을 만들기 위한 코드
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		// 파라미터 수집
		String roomname = request.getParameter("roomname");
		String pw = request.getParameter("pw");
		String setting = request.getParameter("setting");
		// 공개여부 라디오 버튼 데이터 수집 버튼 클릭 안하면 null > "N"입력
		if(setting == null) setting = "N";
		
		Users loginUser = (Users)session.getAttribute("loginUser");
		String userid = loginUser.getUserid();
		
		// roomsDTO 객제를 생성해 roomname, userid, setting, pw 저장
		roomsDTO createChatRoomMem = new roomsDTO(roomname, userid, setting, pw);
		
		// DAO 선언 후 MemberMapper로 채팅방 내용 DB에 저장
		UsersDAO dao = new UsersDAO();
		int cnt = dao.createChatRoom(createChatRoomMem);
		
		if(cnt>0) {
			System.out.println("방 코드는: "+ createChatRoomMem.getRoomid());
			response.sendRedirect("userdataToDBCon?roomid=" + createChatRoomMem.getRoomid() + "&userid=" + userid);
		}
		else System.out.println("방 입장 입장 에러");
	}

}