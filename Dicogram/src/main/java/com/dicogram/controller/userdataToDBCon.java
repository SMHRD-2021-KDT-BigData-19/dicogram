package com.dicogram.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dicogram.domain.UsersDAO;
import com.dicogram.domain.roomuserDTO;

public class userdataToDBCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 방id 추출
		long roomid = Long.parseLong(request.getParameter("roomid"));
		String userid = request.getParameter("userid");

		UsersDAO dao = new UsersDAO();
		roomuserDTO userdataToDBMem = new roomuserDTO(roomid,userid);
		
		// db에 채팅방 처음 들어온 사용자 저장
		if(dao.userdataToDB(userdataToDBMem) > 0) {
			System.out.println("db에 참여자 저장 성공^^");
			response.sendRedirect("chatRoom_client.jsp?roomid=" + request.getParameter("roomid"));
		}
		else {
			System.out.println("db에 참여자 저장 에러ㅠㅠ");
		}
	}
}
