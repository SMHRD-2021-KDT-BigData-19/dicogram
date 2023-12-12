package com.dicogram.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dicogram.database.SqlSessionManager;
import com.dicogram.domain.Users;
import com.dicogram.domain.UsersDAO;

public class UsersloginCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userid = request.getParameter("userid");
		String pw = request.getParameter("pw");
		
		Users loginUser = new Users(userid,pw);
	
		UsersDAO dao = new UsersDAO();
		Users login = dao.LoginUser(loginUser);
	
		if (login != null) { // 로그인 성공시 session에 저장
			HttpSession session = request.getSession();
			// session에 정보를 저장
			session.setAttribute("loginUser", login);
			System.out.println("로그인 성공");
            
			response.sendRedirect("Main.jsp"); // 메인페이지로 이동
			
		} else { // 로그인 실패
			System.out.println("로그인실패.");
			// alert창으로 메세지 보이기
	        String confirmScript = "<script>"
	                + "var confirmResult = confirm('로그인실패');"
	                + "if (confirmResult) {"
	                + "	window.location.href = 'LoginPage.html';"
	                + "}"
	                + "</script>";
	        response.setContentType("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.println(confirmScript);
		}
	}

}