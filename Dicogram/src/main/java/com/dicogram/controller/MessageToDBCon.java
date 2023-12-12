package com.dicogram.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dicogram.domain.UsersDAO;
import com.dicogram.domain.messageDTO;



public class MessageToDBCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("MessageToDBCon에 들어왔습니다.");
		request.setCharacterEncoding("UTF-8");
		
		String message = request.getParameter("message");
		String userid = request.getParameter("userid");
		long roomid = Long.parseLong(request.getParameter("roomid"));
		
		System.out.println(message);
		System.out.println(userid);
		System.out.println(roomid);

		messageDTO MessageToDBMem = new messageDTO(roomid, userid, message);
		
		UsersDAO dao = new UsersDAO();
		int cnt = dao.MessageToDB(MessageToDBMem);
		
		if(cnt>0) {
			System.out.println("db에 메세지 저장 성공^^");
		}
		else System.out.println("db에 메세지 저장 에러ㅠㅠ");
	}

}
