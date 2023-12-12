package com.dicogram.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dicogram.domain.NickPropathDTO;
import com.dicogram.domain.UsersDAO;
import com.google.gson.Gson;

public class profileAndNickCon extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("profileAndNickCon에 들어옴");
		request.setCharacterEncoding("UTF-8");
		
		// JSP에서 전송한 변수 읽기
        String userid = request.getParameter("userid");

        // 여기서 myVariable을 사용하여 작업 수행
        System.out.println("userid: " + userid);
		
        // DAO 객체 생성
     	UsersDAO dao = new UsersDAO();
     	NickPropathDTO NP = dao.NickPropathDAO(userid);
        
     	System.out.println("nick: " + NP.getNick());
     	System.out.println("profile: " + NP.getProfile());
        
     	// Gson 라이브러리 사용
     	String jsonResponse = new Gson().toJson(NP);

     	response.setContentType("application/json");
     	response.setCharacterEncoding("UTF-8");
     	response.getWriter().write(jsonResponse);
	}

}
