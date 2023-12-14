<!-- Member lm 사용해서 로그인된 사용자 정보 가져오기 위해 사용하는 헤더파일 -->
<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.dicogram.database.SqlSessionManager"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.dicogram.domain.NickPropathDTO"%>
<%@page import="com.dicogram.domain.messageDTO"%>
<%@page import="com.dicogram.domain.roomuserDTO"%>
<%@page import="com.dicogram.domain.UsersDAO"%>
<%@page import="com.dicogram.domain.Users"%>
<%@page import="org.apache.ibatis.reflection.SystemMetaObject"%>
<%@page import="java.util.List"%>
<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 

<!DOCTYPE html>

<%
	SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	SqlSession sqlSession = sqlSessionFactory.openSession();

 	// 세션에 저장되어있는 회원의 정보 가져오기
	Users loginUser = (Users)session.getAttribute("loginUser");	

	// 채팅방 코드 가져오기
	String roomid = request.getParameter("roomid");
	System.out.println("채팅방 입장 성공!!!!!");
	System.out.println("방id: "+roomid);
	
	// 방 참여자 DB에서 불러오기
	UsersDAO daoUsers = new UsersDAO();
	List<roomuserDTO> loadallusers = daoUsers.loadAllUsers(roomid);

	String roomname = loadallusers.get(0).getRoomname();
	
	// 메세지 DB에서 불러오기(방 id, 사용자 id)
	UsersDAO daoMessages = new UsersDAO();
	roomuserDTO loadAllMessageMem = new roomuserDTO(Long.parseLong(roomid), loginUser.getUserid());
	List<messageDTO> loadAllMessage = daoMessages.loadAllMessage(loadAllMessageMem);

	String mynick = sqlSession.selectOne("onlyN", loginUser.getUserid());
	String mypath = loginUser.getPropath();

%>

<html>
<head>
    <title>Web Socket Chat</title>
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="CSS/chatRoom_client.css"/>
</head>
<body>
<div class="container">
	<iframe src="iframe_voiceChat.html" width="29.5%" height="100%" style="border: none;"></iframe>
	<div class="chatbox">
		<!-- 채팅 상단 제목 -->
		<div class="top-bar">
			<img class="icon" src="image/chatting_icon_white.png">
			<div class="name"><strong><%=roomname %></strong></div>
			
			<!-- 버튼에 이미지 넣기 -->
			<button type="button">
  				<img class="backToMenu2btn" src="./image/ToMenu2.png" alt="대체 텍스트" onclick="backToMenu2()" >
			</button>
		</div>
		
 		<!-- 채팅 내용 -->
		<div class="middle">
			<ul class="chatting-list">
				<!-- 채팅 내용이 추가됩니다. -->
				
            </ul>
		</div>   
        
		<!-- 채팅 입력 -->
		<div class="bottom-bar" >
			<div class="chat">
				<input id="textMessage" type="text" placeholder="Type a message..." onkeydown="handleKeyDown(event)">
				<button onclick="sendMessage()" value="Send" type="button">전송</button>
			</div>
		</div>
		
	</div>

	<!-- 참여 유저 목록 -->
	<div class="enteredUsersList">
		<div class="users_top_bar">
			<img class="icon" src="image/chatUsers_icon_white.png">
			<div class="usersList_title"><strong>Participants</strong></div>
		</div>
		<div class="users_Middle">
			<ul class="users-list">
				<li>
					<span class="usersImageName">
						<img class="usersProfileImage" src="<%=mypath%>">
						<span class="usersName"><strong>(나) <%=mynick%></strong></span>
					</span>
				</li>
				<!-- 유저 추가 -->
			</ul>
		</div>
	</div>
</div>		
<!-- 	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>	 -->
    <script type="text/javascript">
    	var roomid = "<%=roomid %>";
    	var nick = "<%=mynick%>";
    	var username = "<%=loginUser.getUserid()%>";
    	var profile = "<%=mypath%>";
    	var URL = "172.30.1.79:8089";
    	
        // WebSocket 서버와 연결 (자동으로 접속 시작 - onopen 함수 호출)
        var webSocket = new WebSocket("ws://"+ URL +"/Dicogram/BroadSocket_multiServers/" + roomid);
        
        // WebSocket 서버와 연결이 되면 호출되는 함수
        webSocket.onopen = function(event) {
            
            // 기존 채팅 내용 DB에서 불러오기
            <% for(messageDTO m: loadAllMessage){
				
            	String uNick = sqlSession.selectOne("onlyN", m.getUserid());
            	String uPath = sqlSession.selectOne("onlyP", m.getUserid());
				
				if(m.getUserid().equals(loginUser.getUserid())) {%>
					myMessage('<%=m.getMcontent()%>','<%=m.getCreateday().substring(11, 16)%>');
				<%}else{%>
					receivedMessage('<%=uNick%>','<%=m.getMcontent()%>','<%=m.getCreateday().substring(11, 16)%>','<%=uPath%>');
   			<%}} %>
   			
   			// 채팅방 참여자 목록 DB에서 불러오기
   			<% for(roomuserDTO m: loadallusers){
   				
   				String uNick = sqlSession.selectOne("onlyN", m.getUserid());
   				String uPath = sqlSession.selectOne("onlyP", m.getUserid());
   				
				if(!m.getUserid().equals(loginUser.getUserid())){%>
					userList('<%=uNick%>','<%=uPath%>');
			<%} }%>
            
        	// 메세지 불러올 때 스크롤 항상 아래로
        	scrollToBottom();
        };

        // WebSocket 서버와 연결이 끊기면 호출되는 함수
        webSocket.onclose = function(event) {
        	
        };

        // WebSocket 서버와 통신 중 에러가 발생하면 호출되는 함수
        webSocket.onerror = function(event) {

        };
  		
        // WebSocket 서버로부터 메시지가 오면 호출되는 함수
		webSocket.onmessage = function(event) {
        	// 사용자 이름을 감싸진 패턴
		    const pattern = /^\{\{.+?\}\}/;
			const pattern2 = /^\{\{\{.+?\}\}\}/;
		    const data = event.data;

        	if(data === "New client entered") ImportAllppUsers();
        	else{
        		var matches = data.match(pattern);

        		// 보낸사람 닉네임 추출
        		const username = matches[0].slice(2, -2);
        		console.log("사용자 이름: " + username);

        		var matches = data.replace(pattern, "");
        		var matches2 = matches.match(pattern2);

        		// 보낸사람 프로필 추출
        		const profile = matches2[0].slice(3, -3);
        		console.log("프로필: " + profile);

        		// 메세지 추출
        		const message = matches.replace(pattern2, "");
        		console.log("메시지: " + message);
                
             	// 메세지 받은 시간 추출
    		    const currentTime = new Date();
    		    const time = currentTime.getHours() + ":" + currentTime.getMinutes().toString().padStart(2, '0');
    		    
    		    receivedMessage(username, message, time, profile);
    		    scrollToBottom();
        	}		    
		};
		
        // Send 버튼을 누르면 호출되는 함수
        function sendMessage() {
            var message = document.getElementById("textMessage");
            var currentTime = new Date();
            var time = currentTime.getHours() + ":" + currentTime.getMinutes().toString().padStart(2, '0');
            
            if(message.value != ""){
	            webSocket.send("{{" + nick + "}}" + "{{{" + profile + "}}}" + message.value);
            	myMessage(message.value, time)
            	
            	// 메세지 파일 db에 저장
            	MessageToDB(message.value);
            	
            	message.value = "";
                scrollToBottom();
            }
        }
        
     	// 메세지 파일 db에 저장
		function MessageToDB(message){
			fetch('/Dicogram/MessageToDBCon', {
        		method: 'POST',
           	  	headers: {
           	    'Content-Type': 'application/x-www-form-urlencoded'
           	},
           		body: 'message=' + encodeURIComponent(message) + '&userid=' + encodeURIComponent(username) + '&roomid=' + encodeURIComponent(roomid)
			})
			.then(function(response) {
         	if (response.ok) {
         	    return response.text();
         	} else {
         	    throw new Error('MessageToDB failed.');
         	}
         	})
         	.then(function(result) {
         	  console.log(result);
         	})
         	.catch(function(error) {
         	  console.log(error);
         	});
		}

        // Disconnect 버튼을 누르면 호출되는 함수
        function disconnect() {
            webSocket.close();
        }

        const chatList = document.querySelector(".chatting-list");
        
     	// 메세지 받으면 띄우는 함수
        function receivedMessage(userid,msg,time, profile) {
        	const dom = document.createElement("li");
        	// 클래스 추가
        	dom.classList.add("received");
        	dom.innerHTML =
        		'<img class="profileImage" src="'+ profile +'">'+
        			'<span class="Text">'+
                	'<span class="user">'+ userid +'</span>'+
                	'<span class="message">'+ msg +'</span>'+
            	'</span>'+
            	'<span class="time">'+ time +'</span>';

        	chatList.appendChild(dom);
        }
        
     	// 메세지 보내면 띄우는 창
        function myMessage(msg,time) {
        	const dom = document.createElement("li");
        	dom.classList.add("send");
        	dom.innerHTML =
    			'<span class="Text">'+
            		'<span class="message">'+ msg +'</span>'+
        		'</span>'+
        		'<span class="time">'+ time +'</span>';
        	chatList.appendChild(dom);
        }
     	
     	// 참여자 목록 불러오기
     	const usersList = document.querySelector(".users-list");
     	function userList(nick, profile) {
        	const dom = document.createElement("li");
        	dom.innerHTML =
				'<span class="usersImageName">'+
					'<img class="usersProfileImage" src="'+ profile +'">'+
					'<span class="usersName">'+nick+'</span>'+
				'</span>';
				usersList.appendChild(dom);
        }
     	
     	// 채팅 리스트(Menu2)돌아가기 버튼
     	function backToMenu2() {
    		window.location.href = 'Menu02_Chat.jsp';
		}

     	// 엔터누르면 메세지 전송되는 함수
     	function handleKeyDown(event) {
		    if (event.key === "Enter") sendMessage();
  		}
     	
     	// 스크롤을 항상 아래로 이동시키는 함수
		function scrollToBottom() {
			const middle = document.querySelector(".middle");
		    middle.scrollTo(0, middle.scrollHeight);
		}
    </script>
    
</body>
</html>