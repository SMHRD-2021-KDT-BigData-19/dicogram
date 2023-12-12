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


public class UsersUpdateCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("UsersUpdateCon에 들어옴");
		request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        Users loginUser = (Users) session.getAttribute("loginUser");
        String currentpw = request.getParameter("currentpw");
        String newpw = request.getParameter("newpw");
        String newnick = request.getParameter("newnick");

        if (loginUser == null) {
            // 로그인되지 않은 사용자가 접근한 경우
            response.sendRedirect("LoginPage.html");
            return;
        }

        String key = request.getParameter("key");
        System.out.println("key: " + key);

        // UsersDAO 객체 생성
        UsersDAO dao = new UsersDAO();
        
        
        String alertMessage = "";
        String nextPage = "";
        switch (key) {
            case "pw":
                // 비밀번호 변경
                System.out.println("현재비번: " + currentpw);
                System.out.println("바꿀비번: " + newpw);

                if (currentpw != null && newpw != null && currentpw.equals(loginUser.getPw())) {
                    // 현재 비밀번호가 일치하는지 확인 일치하면 새 비밀번호로 업데이트
                	loginUser.setPw(newpw);
                    if(dao.updateUser(loginUser)>0) {
                    	System.out.println("비밀번호 변경 성공");
                    	alertMessage = "비밀번호 변경 성공";
                    	nextPage = "Main.jsp";
                    } else {
                    	System.out.println("비밀번호 변경 실패");
                    	alertMessage = "비밀번호 변경 실패";
                    	nextPage = "Menu05_Mypage.jsp";
                    }
                } else {
                	System.out.println("현재 비밀번호가 일치하지 않습니다.");
                	alertMessage = "현재 비밀번호가 일치하지 않습니다.";
                	nextPage = "Menu05_Mypage.jsp";
                }
                break;

            case "nick":
                // 닉네임 변경
            	System.out.println("바꿀 닉네임: " + newnick);
            	
                if (newnick != null) {
                    loginUser.setNick(newnick);
                    if(dao.updateUser(loginUser)>0) {
                    	System.out.println("닉네임 변경 성공");
                    	alertMessage = "닉네임 변경 성공";
                    	nextPage = "Main.jsp";
                    } else {
                    	System.out.println("닉네임 변경 실패");
                    	alertMessage = "닉네임 변경 실패";
                    	nextPage = "Menu05_Mypage.jsp";
                    }
                } else {
                	System.out.println("닉네임을 입력해 주세요.");
                	alertMessage = "닉네임을 입력해 주세요.";
                	nextPage = "Menu05_Mypage.jsp";
                }
                break;

            default:
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
