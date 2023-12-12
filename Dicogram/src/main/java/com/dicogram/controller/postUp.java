package com.dicogram.controller;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.dicogram.domain.DataBoardDAO;
@MultipartConfig
public class postUp extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("postUp에 들어옴");
        DataBoardDAO dao = new DataBoardDAO();
        int userName = dao.selectSeq()+1;
        
            try {
                response.setHeader("Access-Control-Allow-Origin", "*");
                Part imagePart = request.getPart("imageFile");

                String fileName = getSubmittedFileName(imagePart);
                
                System.out.println("결과값 userName : "+ userName);
                System.out.println("파일성공");
                
                // 이미지를 저장할 디렉토리 경로
                String desktopPath = "C:\\Users\\smart\\git\\dicogram\\Dicogram\\src\\main\\webapp\\image";

                // 이미지를 저장할 디렉토리 경로
                System.out.println(desktopPath);
                String uploadDirectory = desktopPath + File.separator + "post";
                // 디렉토리가 없다면 생성
                File uploadDir = new File(uploadDirectory);
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }

                // 이미지 저장
                try (InputStream input = imagePart.getInputStream()) {
                    Files.copy(input, new File(uploadDirectory,userName + fileName).toPath(),StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("userName확인 : "+ userName);
                }
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("이미지 업로드 성공");
            } catch (Exception e) {
               e.printStackTrace();
                System.out.println("ssss");
                response.getWriter().write("이미지 업로드 실패: " + e.getMessage());
            }
        }
    

    private String getSubmittedFileName(Part part) {
        System.out.println("cd :");
        for (String cd : part.getHeader("content-disposition").split(";")) {
            System.out.print(cd);
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('.')).trim().replace("\"", "");
                System.out.println("fileName =" + fileName);
                return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
}