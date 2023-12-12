<%@page import="org.apache.ibatis.session.SqlSession"%>
<%@page import="com.dicogram.database.SqlSessionManager"%>
<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@page import="com.dicogram.domain.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<% 
	/* SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	SqlSession sqlSession = sqlSessionFactory.openSession(); */
	/* String mypath = sqlSession.selectOne("onlyP", loginUser.getUserid()); */
	Users loginUser = (Users)session.getAttribute("loginUser");
%> 

<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사이드바 메뉴</title>
    <link rel="stylesheet" href="./CSS/Menu04_Friends.css">

</head>
<body>
 
<div id="header">
        
    </div>
    <div id="sidebar">
        <img id="sidelogo" src="./image/dicogram_logo.png" alt="">
        <img src="<%=loginUser.getPropath()%>" id="profile_img">
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
 <div id="content" >
        
        <div id="friends-content" class="content-box">
            <h1>친구 목록</h1>
              <!-- 친구 추가 버튼 -->
            <div id="friendButtons">
                <input type="text" id="FsearchInput" oninput="searchFriends()" placeholder="친구 검색">
                <button onclick="addFriend()">친구 추가</button>
            </div>

            <ul id="friendList">
                <li>
                    <span class="friend-name">친구 1</span>
                    <button class="remove-friend" onclick="removeFriend(this)">삭제</button>
                </li>
                <li>
                    <span class="friend-name">친구 2</span>
                    <button class="remove-friend" onclick="removeFriend(this)">삭제</button>
                </li>
                <li>
                    <span class="friend-name">친구 3</span>
                    <button class="remove-friend" onclick="removeFriend(this)">삭제</button>
                </li>
                <!-- 예시로 몇 명을 추가했습니다. 원하는 만큼 추가하세요. -->
            </ul>
        </div>
       
    </div>
    <div id="content">
        <div id="friends-content" class="content-box">
            <h2>친구요청</h2>
            <ul id="friendrequest">
                <li>
                    <span class="friend-name">친구 1</span>
                    <button class="accept-friend" onclick="acceptFriendRequest(this)">수락</button>
                    <button class="reject-friend" onclick="rejectFriendRequest(this)">거절</button>
                </li>
            </ul>
        </div>

    </div>
       
        <script>
        //친구목록
        function removeFriend(button) {
                    var friendListItem = button.parentElement;
                    console.log(button)
                    console.log(friendListItem)
                    friendListItem.remove();
                }
 
        function searchFriends() {
            // 검색어 가져오기
            var searchQuery = document.getElementById('searchInput').value.toLowerCase();

            // 친구 목록 가져오기
            var friends = document.getElementById('friendList').getElementsByTagName('li');

            // 각 친구에 대해 검색어와 일치하는지 확인하고 보이거나 숨기기
            for (var i = 0; i < friends.length; i++) {
                var friendName = friends[i].innerText.toLowerCase();
                if (friendName.includes(searchQuery)) {
                    friends[i].style.display = 'block';
                } else {
                    friends[i].style.display = 'none';
                }
            }
            // 친구 추가
            function addFriend() {
                    var friendName = prompt('추가할 친구의 이름을 입력하세요:');
                    if (friendName) {
                        var friendList = document.getElementById('friendList');
                        var newFriend = document.createElement('li');
                        newFriend.textContent = friendName;

                        friendList.appendChild(newFriend);
                    }
                }

               // 친구 삭제

        }
        function acceptFriendRequest(button) {
            var friendListItem = button.parentElement;

            var friendName = friendListItem.getElementsByTagName('span')[0].textContent;
            var friendList = document.getElementById('friendList');
            var newFriend = document.createElement('li');
            newFriend.className = "friend-name"
            newFriend.textContent = friendName;
            var b = document.createElement('button');
            b.className="remove-friend"
            b.textContent="삭제";
            newFriend.appendChild(b);
            friendList.appendChild(newFriend);
            b.onclick= function() {
                removeFriend(b)
            }

            // 여기에서 서버에 수락 확인과 같은 추가 작업을 수행할 수 있습니다.
            // 지금은 간단한 메시지를 콘솔에 기록하겠습니다.
            console.log(friendName + '님의 친구 요청을 수락했습니다.');

            // 선택적으로 친구 요청 항목을 목록에서 제거할 수 있습니다.
            friendListItem.remove();
        }

        function rejectFriendRequest(button) {
            var friendListItem = button.parentElement;
            var friendName = friendListItem.querySelector('.friend-name').textContent;

            // 여기에서 서버에 거절 확인과 같은 추가 작업을 수행할 수 있습니다.
            // 지금은 간단한 메시지를 콘솔에 기록하겠습니다.
            console.log(friendName + '님의 친구 요청을 거절했습니다.');

            // 선택적으로 친구 요청 항목을 목록에서 제거할 수 있습니다.
            friendListItem.remove();
        }

        </script>
    </body>
</html>