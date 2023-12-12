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

public class UsersDeleteCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		Users loginUser = (Users)session.getAttribute("loginUser");
		String chkPw = request.getParameter("chkPw");
		String inputid = request.getParameter("chkId");
		System.out.println(chkPw);
		System.out.println(inputid);
		
		UsersDAO dao = new UsersDAO();
		
		String alertMessage = "";
        String nextPage = "";
		if (inputid.equals(loginUser.getUserid()) && chkPw.equals(loginUser.getPw())) {
			if(dao.deleteUser(loginUser.getUserid()) > 0) {
				session.removeAttribute("loginUser");
				System.out.println("회원탈퇴 성공");
				alertMessage = "회원탈퇴 성공";
		        nextPage = "LoginPage.html";
			} else {
				System.out.println("회원탈퇴 실패했습니다.");
				alertMessage = "회원탈퇴 실패했습니다.";
				nextPage = "Menu05_Mypage.jsp";
			}
		}else {
			System.out.println("개인정보가 틀렸습니다.");
			alertMessage = "개인정보가 틀렸습니다.";
			nextPage = "Menu05_Mypage.jsp";
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
