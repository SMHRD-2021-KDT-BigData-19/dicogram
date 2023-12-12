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
		
		// 메세지 내용을 DB에 저장하기 위해 SQL실행
		request.setCharacterEncoding("UTF-8");
		
		String message = request.getParameter("message");
		String userid = request.getParameter("userid");
		long roomid = Long.parseLong(request.getParameter("roomid"));

		messageDTO MessageToDBMem = new messageDTO(roomid, userid, message);
		
		UsersDAO dao = new UsersDAO();
		int cnt = dao.MessageToDB(MessageToDBMem);
		
		if(cnt>0) {
			System.out.println("db에 메세지 저장 성공^^");
		}
		else System.out.println("db에 메세지 저장 에러ㅠㅠ");
	}

}
