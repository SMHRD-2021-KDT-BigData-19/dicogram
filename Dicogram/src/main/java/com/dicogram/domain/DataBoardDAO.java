package com.dicogram.domain;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dicogram.database.SqlSessionManager;

public class DataBoardDAO {
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	   SqlSession sqlSession = sqlSessionFactory.openSession();
	   
	   public List<posts> selectAll(){
	      List<posts> postlist = null;
	      
	      try {
	         //SQL문 실행시 넣어줄 조건(매개변수)이 없다.
	         // selectLIst()메소드 사용시에도 매개변수 필요 없다.
	         postlist = sqlSession.selectList("selectAll");
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         sqlSession.close();
	      }
	      
	      
	      
	      return postlist;
	      
	      
	   }// selectAll 끝
	   
	   
	   public int selectSeq(){
	      int seq = 1;
	      
	      try {
	         //SQL문 실행시 넣어줄 조건(매개변수)이 없다.
	         // selectLIst()메소드 사용시에도 매개변수 필요 없다.
	         seq = sqlSession.selectOne("postid");
	         
	      } catch (Exception e) {
	         e.printStackTrace();
	      } finally {
	         sqlSession.close();
	      }

	      return seq;

	   }// selectAll 끝

}
