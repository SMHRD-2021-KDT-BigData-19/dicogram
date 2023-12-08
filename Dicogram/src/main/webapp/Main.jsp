<%@page import="java.util.List"%>
<%@page import="com.dicogram.domain.DataBoardDAO"%>
<%@page import="com.dicogram.domain.posts"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	DataBoardDAO dao = new DataBoardDAO();
    	List<posts> postlist = dao.selectAll();
    	
    	
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>사이드바 메뉴</title>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css">
<link rel="stylesheet" href="./CSS/Main.css">

</head>
<body>

    <div id="header">
        <div class="search-box">
            <input type="text" class="search-txt" name="search"placeholder="Type to search">
            <a class="search-btn" href="#">
              <i class="fas fa-search"></i>
            </a>
          </div>
    </div>
    <div id="sidebar">
        <img id="sidelogo" src=".\image\dicogram_logo.png" alt="">
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


        <div id="home-content" class="content-box">
            <button class="content-button" onclick="handleButtonClick('home')">글쓰기</button>
			<%for(posts m:postlist){ %>
            <div id="feed-container">
                <div class="post" id="post1">
                    <div class="post-header">
                        <img src="./image/dragonmin.png" alt="User1" width="40px" height="40px">
                        <p class="username">user1 &nbsp; &nbsp; &nbsp; &nbsp;</p><a><%=m.getPostname()%></a>
                    </div>
                    <div class="post-content">
                        
                        <br>
                        <div class="caption"> <p><%=m.getPcontent()%></p></div>
                        <br><span><%=m.getRoom()%> &nbsp; &nbsp; </span> <span><%=m.getTags()%></span>
                    </div>
                    
                    <div class="post-actions">
                        <button onclick="toggleComments('post1')">댓글</button>
                    </div>
                    <div class="post-actions">
                        <div class="comments" id="commentspost1">
                            <!-- 댓글이 여기에 추가됩니다. -->
                        </div>
                    </div>
                </div>

            </div>
            <% } %>	


        </div>
    </div>

    <script src="./JS/Main.js"></script>
    <script>
        function toggleComments(postId) {
            var commentsDiv = document.getElementById('comments' + postId);
            commentsDiv.classList.toggle('show-comments');

            if (commentsDiv.classList.contains('show-comments')) {
                var commentInput = document.createElement('input');
                commentInput.type = 'text';
                commentInput.placeholder = '댓글을 입력하세요';
                commentInput.classList.add('comment-input');

                var commentButton = document.createElement('button');
                commentButton.textContent = '작성';
                commentButton.classList.add('comment-button');
                commentButton.addEventListener('click', function () {
                    addComment(postId, commentInput.value);
                });

                var commentArea = document.createElement('div');
                commentArea.classList.add('comment-area');

                commentArea.appendChild(commentInput);
                commentArea.appendChild(commentButton);

                commentsDiv.appendChild(commentArea);
            } else {
                var commentArea = commentsDiv.querySelector('.comment-area');
                if (commentArea) {
                    commentsDiv.removeChild(commentArea);
                }
            }
        }

        function addComment(postId, comment) {
            var commentsDiv = document.getElementById('comments' + postId);
            var commentList = document.createElement('ul');
            commentList.classList.add('comment-list');

            var newComment = document.createElement('li');
            newComment.textContent = comment;

            commentList.appendChild(newComment);
            commentsDiv.appendChild(commentList);
        }
        
        
    </script>

</body>

</html>