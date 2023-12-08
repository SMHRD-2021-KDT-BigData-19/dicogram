<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사이드바 메뉴</title>
    <link rel="stylesheet" href="./CSS/Menu05_Mypage.css">
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
  <div id="content" >
        <div id="mypage-content" class="content-box">
                <form id="profileForm">
        
                    <h1>프로필 관리</h1>
                
                    <label for="uploadButton" id="uploadLabel">
                        <img id="profileImage" src="default-profile-image.jpg" alt="프로필 이미지" width="100" height="100">
                    </label>
                    <input type="file" id="profilePicture" name="profilePicture" accept="image/*">
            
                    <label for="fullName">닉네임:</label>
                    <input type="text" id="fullName" name="fullName" required>
            
                    <label for="email">이메일:</label>
                    <input type="email" id="email" name="email" required>
                    <div id="passwordSection">
                        
                        <label for="currentPassword">현재 비밀번호:</label>
                        <input type="password" id="currentPassword" name="currentPassword" required>
            
                        <label for="newPassword">새로운 비밀번호:</label>
                        <input type="password" id="newPassword" name="newPassword" required>
                    </div>
                    <br>
            
                    <button type="button" onclick="saveProfile()">프로필 저장</button>
    
    </form>   
    </div>
    </div>
    <script src="./JS/Menu05_Mypage.js"></script> 

</body>
</html>