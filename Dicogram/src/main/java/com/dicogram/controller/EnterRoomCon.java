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
import com.dicogram.domain.roomuserDTO;

public class EnterRoomCon extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 채팅방 입장 전 기존 참여자인지 새로운 참여자인지 체크
		// 기존 참여자면 바로 채팅방 입장 > chatRoom_client.jsp
		// 새로운 참여자면 채팅방 비번 확인 후 > userdataToDBCon servlet
		
		// 방id 추출
		long roomid = Long.parseLong(request.getParameter("roomid"));
		
		// 사용자id 추출
		HttpSession session = request.getSession();
		Users loginUser = (Users)session.getAttribute("loginUser");
		String userid = loginUser.getUserid();
		
		// DAO 객체 생성
		UsersDAO dao = new UsersDAO();
		
		// 입장자 체크 (DB roomuser에 내 데이터가 저장 되었는지 확인)
		roomuserDTO checkEnterUserMem = new roomuserDTO(roomid, userid);
		roomuserDTO checkEnterUser = dao.checkEnterUser(checkEnterUserMem);
		 
		String confirmScript;
		String pw;
		// 새로운 참여자면
		if(checkEnterUser == null) {
			System.out.println("새로운 참여자입니다!!!!");
			
			// 채팅방 비밀번호 가져오기
			pw = dao.chatPWDAO(roomid);
			
			// 채팅방 비번이 없으면 보이는 화면
			if(pw == null) {
				confirmScript = "<script>"
                    + "var confirmResult = confirm('채팅방에 참여하시겠습니까?');"
                    + "if (confirmResult) {"
                    + "  	window.location.href = 'userdataToDBCon?roomid=" + roomid + "&userid=" + userid + "';"
                    + "} else {"
                    + "  	alert('취소되었습니다.');"
                    + "  	window.location.href = 'Menu02_Chat.jsp';"
                    + "}"
                    + "</script>";
			}
			else { // 채팅방 비번이 있으면 alert창 보임
				confirmScript = "<script>"
                    + "var confirmResult = confirm('채팅방에 참여하시겠습니까?');"
                    + "if (confirmResult) {"
                    + "	var password = prompt('채팅방 비밀번호를 입력하세요.');"
                    + "	if (password === '" + pw +"') {" // 수정: 비밀번호가 일치할 때의 조건 수정
                    + "  	window.location.href = 'userdataToDBCon?roomid=" + roomid + "&userid=" + userid + "';"
                    + "	} else { alert('비밀번호가 틀렸습니다.'); window.location.href = 'Menu02_Chat.jsp';}"
                    + "} else {"
                    + "  	alert('취소되었습니다.');"
                    + "  	window.location.href = 'Menu02_Chat.jsp';"	
                    + "}"
                    + "</script>";
			}
			
            response.setContentType("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(confirmScript);

		}
		else {
			System.out.println("기존 참여자입니다.");
			response.sendRedirect("chatRoom_client.jsp?roomid=" + request.getParameter("roomid"));
		}

	}

}
