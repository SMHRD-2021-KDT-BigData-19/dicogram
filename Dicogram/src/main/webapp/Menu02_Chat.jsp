<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
        <img src="./image/dragonmin.png" id="profile_img">
        <div id="menu">
            <a href="#" onclick="location.href='Main.jsp'">
                <img src="./image/home_button.png" class="nohover">
                <img src="./image/home_blue.png" class="onhover">
                HOME
            </a>
           

            <a href="#" onclick="location.href='Menu02_Chat.jsp'">
                <img src="./image/chat_button.png" class="nohover">
                <img src="./image/chat_blue.png" class="onhover">
                CHAT
            </a>

          

            <a href="#" onclick="location.href='Menu04_Friends.jsp'">
                <img src="./image/friends_button.png" class="nohover">
                <img src="./image/friends_blue.png" class="onhover">
                FRIENDS
            </a>

            <a href="#" onclick="location.href='Menu05_Mypage.jsp'">
                <img src="./image/mypage_button.png" class="nohover">
                <img src="./image/mypage_blue.png" class="onhover">
                MYPAGE
            </a>
        </div>
    </div>

    
    <div id="content">
        <div id="chat-content" class="content-box">
            <button class="content-button">+ Chat</button>
            <h1>채팅방 목록</h1>

            <ul id="chatList"></ul>


        </div>
       
    </div>
    <div id="content" >
        
        <div id="post-content" class="content-box">
            <b><p>오픈채팅방목록</p></b>
            <input type="text" id="search-input" placeholder="채팅방 검색...">
            <ul id="chat-list"></ul>
            
        </div>
    </div>    

    <div id="modal_background" class="hidden_modal">
        <div class="container" id="modal_container">
            <h1>채팅방 만들기</h1>

            <form action="createChatCon" method="post" id="chatForm">
                <button class="image-btn">채팅방 이미지</button><br>
                <label for="chatRoomName">채팅방 이름:</label>
                <input type="text" id="chatRoomName" name="chatRoomName"><br>

                <!-- 공개 비공개 -->
                <table>
                    <tr>
                        <td><label for="generateOpenOrNot">비공개</label><br></td>
                        <td><input type="checkbox" id="generateOpenOrNot" name="openOrNot" value="공개여부"></td>
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
                    <input type="text" id="chatRoomPW" name="chatRoomPW"><br>
                </div>
                <input type="submit" value="완료">
            </form>
        </div>
    </div>

    <script src="./JS/Menu03_Openchat.js"></script>
    <script src="./JS/Menu02_Chat_New.js"></script>
    <script src="./JS/Menu02_Chat.js"></script>

</body>

</html>