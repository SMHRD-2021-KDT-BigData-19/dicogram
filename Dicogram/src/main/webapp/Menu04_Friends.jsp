<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사이드바 메뉴</title>
    <link rel="stylesheet" href="./CSS/Menu04_Friends.css">
    <style>
    	body {
    background: linear-gradient(to top, #daefff, #DAEFFF);
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    padding: 0;
    display: flex;
    position: relative;
    height: auto;

}
@font-face {
    font-family: 'NanumSquareNeo-Variable';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_11-01@1.0/NanumSquareNeo-Variable.woff2') format('woff2');
    font-weight: normal;
    font-style: normal;
}
#profileForm,button,#friends-content {
    font-family: 'NanumSquareNeo-Variable', sans-serif;
}
body {
    font-family: 'Arial', sans-serif;
    margin: 20px;
    margin: 0;
    padding: 0;
    background-color: #f2f2f2;
    
}

#sidebar {
    width: 200px;
    height: 100vh;
    background-color: #fff;
    color: #474747;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding-top: 20px;
    box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1);
    position: fixed;
    z-index: 2000;
}
#sidelogo {
    width: 100px;
    height: 100px;
    margin-top: 20px;
}
#menu {
    display: flex;
    flex-direction: column;
    margin-top: 10px;
}

#menu a {
    color: #474747;
    text-decoration: none;
    padding: 6px;
    padding-left: 5px;
    margin: 5px;
    text-align: left;
    border-radius: 5px;
    transition: background-color 0.3s ease-in-out;
    font-size: 13px;
}

#menu a:hover {
    color: #2A8BF2;
    text-shadow: #c7dbf0;
}

#content {
    
    padding: 20px;
    align-items: center;
    justify-content: center;
   
}


.content-box {
    margin-top: 50px;
    margin-left: 330px;
    background-color: #fff;
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    margin-bottom: 20px;
      
}



.nohover,.onhover {
    height: 11px;
    width: 11x;
    margin-right: 3px;
}

.onhover {
    display: none;
}

#menu a:hover img.onhover {
    display: inline;
}

#menu a:not(:hover) img.onhover {
    display: none;
}

#menu a:hover img.nohover {
    display: none;
}

#menu a:not(:hover) img.nohover {
    display: inline;
}
#profile_img {
    margin-top: 25px;
    border: 0.5px solid gray;
    border-radius: 50%;
    height: 120px;
    width: 120px;
    cursor: pointer;
}



#content {
    margin-top: 50px; /* 헤더의 높이에 맞춰 조절 */
}
.content-button {
    position: absolute;
    top: 30px; /* 원하는 위치로 조절 */
    right: 200px; /* 원하는 위치로 조절 */
    padding: 8px 20px;
    background-color: #4CAF50;
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

/*친구목록*/
#FsearchInput {
    padding: 8px;
    border: 1px solid #ccc;
    border-radius: 5px;
    font-size: 16px;
}

#friendList {
    list-style: none;
    padding: 0;
}

#friendList li {
    background-color: #fff;
    padding: 10px;
    margin: 5px;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    transition: background-color 0.3s ease-in-out;
}

#friendList li:hover {
    background-color: #f0f0f0;
}
#friends-content {
    width: 300px;
}
#mypage-content h1 {
    margin-top: 0px;
}
 button{
    background-color: #3498db;
    color: #fff;
    border: none;
    padding: 8px;
    cursor: pointer;
 border-radius: 5px;
}
.remove-friend {
    align-items: right;
}
    
    </style>
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
       function removeFriend(button) {
            var friendListItem = button.parentElement;
            friendListItem.remove();
        }
}
function acceptFriendRequest(button) {
    var friendListItem = button.parentElement;
    var friendName = friendListItem.querySelector('.friend-name').textContent;

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