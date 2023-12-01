package com.dicogram.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dicogram.database.SqlSessionManager;
import com.dicogram.domain.dUsers;

/**
 * Servlet implementation class test
 */
public class test extends HttpServlet {

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		System.out.println(id);
		System.out.println(pw);
		SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
		SqlSession sqlSession = sqlSessionFactory.openSession();
		dUsers testu = new dUsers(id, pw);
		try {
			dUsers test1 = null;
			System.out.println("여기");
			test1 =  sqlSession.selectOne("test", testu);
			if(test1 != null) {
				System.out.println("여기");
				HttpSession session = request.getSession();
				session.setAttribute("test1", test1);
				response.sendRedirect("test.jsp");
				System.out.println('asd');
				System.out.println('asd2');
				
			}
			else {
				response.sendRedirect("test.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
	}

}
