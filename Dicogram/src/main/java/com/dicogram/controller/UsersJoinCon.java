package com.dicogram.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dicogram.domain.Users;
import com.dicogram.domain.UsersDAO;

public class UsersJoinCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String pw = request.getParameter("pw");
		String nick = request.getParameter("nick");
		String email = request.getParameter("email");
		
		Users joinUser = new Users(name,userid,pw,nick,email);
		
		UsersDAO dao = new UsersDAO();
		int cnt = dao.JoinUser(joinUser);
		
		String alertMessage = "";
        String nextPage = "";
		if(cnt>0) {
			System.out.println("회원가입 성공");//콘솔창에 출력
			HttpSession session = request.getSession();
			// session에 정보를 저장
			session.setAttribute("Users", joinUser);
			alertMessage = "회원가입 성공";
			nextPage = "LoginPage.html";
		}else {
			System.out.println("회원가입 실패");
			alertMessage = "회원가입 실패";
			nextPage = "RegisterForm.html";
		}
		
		// alert창으로 메세지 보이기
        String confirmScript = "<script>"
                + "var confirmResult = confirm('"+alertMessage+"');"
                + "if (confirmResult) {"
                + "	window.location.href = '" + nextPage + "';"
                + "}"
                + "</script>";
        
		response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println(confirmScript);

	}

}