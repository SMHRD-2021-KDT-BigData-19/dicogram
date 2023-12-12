package com.dicogram.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dicogram.database.SqlSessionManager;
import com.dicogram.domain.posts;

public class post extends HttpServlet {
   private static final long serialVersionUID = 1L;

   protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("UTF-8");
      
      String name = request.getParameter("postname");
      String content = request.getParameter("pcontent");
      String tag = request.getParameter("tags");
      String img = request.getParameter("image");

      SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
      SqlSession sqlSession = sqlSessionFactory.openSession();
      posts newPost = new posts(name,content,tag,img);
      
      try {
         int existingPost = sqlSession.insert("test1", newPost);
         if (existingPost > 0) {
            sqlSession.commit();

         } else {
            sqlSession.rollback();
            sqlSession.close();
            response.sendRedirect("insert.jsp");
         }
      } catch (Exception e) {
         // 예외 처리를 수행하세요.
         e.printStackTrace();
      } finally {
         sqlSession.close();
      }
   }
}

   