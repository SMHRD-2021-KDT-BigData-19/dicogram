package com.dicogram.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.dicogram.database.SqlSessionManager;
import com.dicogram.domain.Users;
import com.dicogram.domain.profilePathDTO;

@MultipartConfig
public class profileUpCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   	System.out.println("profileUpCon에 들어왔습니다.");
    	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
    	SqlSession sqlSession = sqlSessionFactory.openSession();
    	
        try {
        	Part imagePart = request.getPart("imageFile");
        	String itype = getSubmittedFileName2(imagePart);
        	System.out.println("itype: " + itype);
        	HttpSession session = request.getSession();
        	Users loginUser = (Users)session.getAttribute("loginUser");
        	String fileName = loginUser.getUserid() + "profile." + itype;
        	
        	// 이미지를 저장할 디렉토리 절대 경로
            String uploadDirectory = "C:\\Users\\smart\\git\\dicogram\\Dicogram\\src\\main\\webapp\\images\\profiles\\";
            System.out.println(uploadDirectory + fileName);
        	
        	// 디렉토리가 없다면 생성
        	File uploadDir = new File(uploadDirectory);
        	if (!uploadDir.exists()) {
        		uploadDir.mkdirs();
        	}

            // 이미지 저장
            try (InputStream input = imagePart.getInputStream()) {
                Files.copy(input, new File(uploadDirectory, fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
                
                // 이미지 경로를 db에 상대 경로로 저장
                profilePathDTO user1 = new profilePathDTO(loginUser.getUserid(), "./images/profiles/" + fileName);
                
                int cnt = sqlSession.update("upprofile", user1);
                if (cnt > 0) {
                    System.out.println("수정 성공");
                    sqlSession.commit();
                    // DB에 저장한 경로를 세션에 업데이트
                    loginUser.setPropath("./images/profiles/" + fileName);
					/* session.setAttribute("loginUser", loginUser); */
                    
                } else {
                	System.out.println("수정 실패");
                	sqlSession.rollback();
                }
            }
            
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("이미지 업로드 성공");
        } catch (Exception e) {
           	e.printStackTrace();
            response.getWriter().write("이미지 업로드 실패: " + e.getMessage());
        }finally {
         sqlSession.close();
      }
        
    }
    private String getSubmittedFileName2(Part part) {
       System.out.println(part);
        for (String cd : part.getHeader("content-disposition").split(";")) {
           System.out.println("cd부분");
           System.out.println(cd);
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('.') + 1).trim().replace("\"", "");
                System.out.println(fileName);
                // 파일 경로를 안전하게 추출
                Path path = Paths.get(fileName);
                return path.getFileName().toString();
            }
        }
        return null;
	}

}
