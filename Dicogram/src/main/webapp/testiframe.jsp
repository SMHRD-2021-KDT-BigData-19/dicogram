<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<html>
<head>
    <title>Web Socket Chat</title>
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    <link rel="stylesheet" href="assets/css/test.css"/>
</head>
<body>




<div class="container">
<iframe src="page1.html" width="29.5%" height="100%" style="border: none;"></iframe>

	<div class="chatbox">
		<!-- ä�� ��� ���� -->
		<div class="top-bar">
			<img class="icon" src="./chatting_icon_white.png">
			<div class="name">roomname</div>
		</div>
		
 		<!-- ä�� ���� -->
		<div class="middle">
			<ul class="chatting-list">
				<!-- ä�� ������ �߰��˴ϴ�. -->
				
            </ul>
		</div>   
        
		<!-- ä�� �Է� -->
		<div class="bottom-bar" >
			<div class="chat">
				<input id="textMessage" type="text" placeholder="Type a message..." onkeydown="handleKeyDown(event)">
				<button onclick="sendMessage()" value="Send" type="button">����</button>
			</div>
		</div>
		
	</div>
	
	<!-- ���� ���� ��� -->
	<div class="enteredUsersList">
		<div class="users_top_bar">
			<img class="icon" src="./chatUsers_icon_white.png">
			<div class="usersList_title"><strong>Participants</strong></div>
		</div>
		<div class="users_Middle">
			<ul class="users-list">
				<li>
					<span class="usersImageName">
						<img class="usersProfileImage" src="./profile.png">
						<span class="usersName"><strong>(��) <%-- <%=loginUser.getNick()%> --%></strong></span>
					</span>

				</li>
				<%-- <% for(roomuserDTO m: loadallusers){ 
					if(!m.getUserid().equals(loginUser.getId())){%>
				<li>
					<span class="usersImageName">
						<img class="usersProfileImage" src="./profile.png">
						<span class="usersName"><%=m.getUserid()%></span>
					</span>
				</li>
				<%} }%> --%>
			</ul>
		</div>
	</div>
</div>		
</body>
</html>