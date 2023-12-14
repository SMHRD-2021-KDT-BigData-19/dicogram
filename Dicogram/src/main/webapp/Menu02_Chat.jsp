<%@page import="com.dicogram.domain.loadAllOpenChatRoomsDTO"%>
<%@page import="com.dicogram.domain.roomsDTO"%>
<%@page import="java.util.List"%>
<%@page import="com.dicogram.domain.UsersDAO"%>
<%@page import="com.dicogram.domain.Users"%>
<%@page import="com.dicogram.domain.roomuserDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%	

	//세션에 저장되어있는 회원의 정보 가져오기
	Users loginUser = (Users)session.getAttribute("loginUser");
	
 	// 참여한 채팅방 모두 불러오기
	UsersDAO daoMyChat = new UsersDAO();
	List<roomuserDTO> loadMyChatRoom = daoMyChat.loadMyChatRoom(loginUser.getUserid());
	
	// 모든 채팅방 불러오기
	UsersDAO daoOpenChat = new UsersDAO();
	List<loadAllOpenChatRoomsDTO> loadOpenChatRoom = daoOpenChat.loadOpenChatRoom(loginUser.getUserid());

%>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사이드바 메뉴</title>
    <link rel="stylesheet" href="./CSS/Menu02_Chat_New.css">
    <link rel="stylesheet" href="./CSS/Menu02_Chat.css">
    <link rel="stylesheet" href="./CSS/Menu03_Openchat.css">
</head>
<body>
	<div id="header">
        
    </div>
    <div id="sidebar">
        <img id="sidelogo" src="./image/dicogram_logo.png" alt="">
        <img src="<%=loginUser.getPropath() %>" id="profile_img">
        <div id="menu">
            <a href="Main.jsp">
                <img src="./image/home_button.png" class="nohover">
                <img src="./image/home_blue.png" class="onhover">
                HOME
            </a>
            <a href="Menu02_Chat.jsp">
                <img src="./image/chat_button.png" class="nohover">
                <img src="./image/chat_blue.png" class="onhover">
                CHAT
            </a>
            <a href="Menu04_Friends.jsp">
                <img src="./image/friends_button.png" class="nohover">
                <img src="./image/friends_blue.png" class="onhover">
                FRIENDS
            </a>
            <a href="Menu05_Mypage.jsp">
                <img src="./image/mypage_button.png" class="nohover">
                <img src="./image/mypage_blue.png" class="onhover">
                MYPAGE
            </a>
            <a href="UsersLogoutCon" >
                LOGOUT
            </a>
        </div>
    </div>
	
	<!-- 내가 참여한 채팅 리스트 -->
	<div class="myChatbox">
    	<div class="chatTitle"><h1>내 채팅방 목록</h1></div>
	    <div class="myChatList">
			<ul>
				<li><a href="EnterRoomCon?roomid=156" >메세지 불러오기 테스트 방</a></li>
				<% for(roomuserDTO m: loadMyChatRoom){ %>
						<li>
							<a href="EnterRoomCon?roomid=<%=m.getRoomid()%>"><%=m.getRoomname() %></a>
						</li>
				<%} %>
				
			</ul>
		</div>
	</div>
	
	<!-- 오픈 채팅 리스트 -->
	<div class="openChatbox">
		<div class="chatTitle"><h1>오픈 채팅방 목록</h1></div>
		<input type="text" id="search-input" placeholder="채팅방 검색...">
		<div class="openChatList">
			<ul>
				<% for(loadAllOpenChatRoomsDTO m: loadOpenChatRoom){ %>
					<li>
						<a href="EnterRoomCon?roomid=<%=m.getRoomid()%>"><%=m.getRoomname()%></a>
					</li>
				<%} %>
				
			</ul>
		</div>
	</div>
	
	<!-- 채팅방 생성 -->
	<button class="content-button">+ Chat</button>
	
	<!-- 채팅방 생성 모달창 -->
    <div id="modal_background" class="hidden_modal">
        <div class="container" id="modal_container">
            <h1>채팅방 만들기</h1>
            <form action="createChatRoomCon" method="post" id="chatForm">
                <label for="chatRoomName">채팅방 이름:</label>
                <input type="text" id="chatRoomName" name="roomname"><br>
                <!-- 공개 비공개 -->
                <table>
                    <tr>
                        <td><label for="generateOpenOrNot">공개</label><br></td>
                        <td><input type="checkbox" id="generateOpenOrNot" name="setting" value="Y"></td>
                    </tr>
                </table>
                <!-- 암호 생성 체크박스 -->
                <table>
                    <tr>
                        <td> <label for="generatePasswordCheckbox">암호 생성</label><br></td>
                        <td> <input type="checkbox" id="generatePasswordCheckbox" value="암호"></td>
                    </tr>
                </table>
                <!-- 암호 입력란 -->
                <div id="passwordInput" style="display: none;">
                    <label for="chatRoomPW">암호:</label>
                    <input type="text" id="chatRoomPW" name="pw"><br>
                </div>
                <input type="submit" value="완료">
            </form>
        </div>
    </div>
    

    <script src="./JS/Menu02_Chat_New.js"></script>
    <script src="./JS/Menu02_Chat.js"></script>

</body>

</html>