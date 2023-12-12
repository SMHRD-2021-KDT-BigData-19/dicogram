
<%@page import="com.dicogram.database.SqlSessionManager"%>
<%@page import="com.dicogram.domain.Users"%>
<%@page import="org.apache.ibatis.session.SqlSession"%>

<%@page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사이드바 메뉴</title>
    <link rel="stylesheet" href="CSS/Menu05_Mypage.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            flex-direction: column;
           
        }

        h5 {
            font-size: 1.5em;
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
            border: 1px solid #ccc;
            padding: 15px;
            border-radius: 8px;
            width: 300px;
            margin-left: 600px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input {
            margin-bottom: 10px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        button {
            padding: 8px 12px;
            background-color: #2A8BF2;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background-color:#1e69ba;
        }

        a {
            margin-top: 20px;
            text-decoration: none;
            color: #fff;
        }

    </style>
</head>

<body>
<% System.out.println("하하하핳하하하하하하하하하하하하하하하하하하하하하하하하하하하하하하하하하");
	
	/* SqlSessionFactory sqlSessionFactory = SqlSessionManager.getSqlSession();
	SqlSession sqlSession = sqlSessionFactory.openSession(); */
	Users loginUser = (Users)session.getAttribute("loginUser");
	/* String mypath = loginUser.getPropath(); */
	/* String mypath = sqlSession.selectOne("onlyP", loginUser.getUserid());
	System.out.println(mypath); */
	/* String mypath = sqlSession.selectOne("onlyP", loginUser.getUserid()); */
%>    
    
   <div id="header">
        
   </div>
    <div id="sidebar">
        <img id="sidelogo" src="image/dicogram_logo.png" alt="">
        <img src="<%=loginUser.getPropath()%>" id="profile_img">
        <%-- <img src="images/profiles/<%=loginUser.getUserid() %>profile.png" id="profile_img"> --%>
        <div id="menu">
            <a href="Main.jsp">
                <img src="image/home_button.png" class="nohover">
                <img src="image/home_blue.png" class="onhover">
                HOME
            </a>
            <a href="Menu02_Chat.jsp">
                <img src="image/chat_button.png" class="nohover">
                <img src="image/chat_blue.png" class="onhover">
                CHAT
            </a>
            <a href="Menu04_Friends.jsp">
                <img src="image/friends_button.png" class="nohover">
                <img src="image/friends_blue.png" class="onhover">
                FRIENDS
            </a>
            <a href="Menu05_Mypage.jsp">
                <img src="image/mypage_button.png" class="nohover">
                <img src="image/mypage_blue.png" class="onhover">
                MYPAGE
            </a>
            <a href="UsersLogoutCon" >
                LOGOUT
            </a>
        </div>
    </div>

    
    <h5>회원정보수정</h5>
    <form action="UsersUpdateCon?key=pw" method="post">
        <p>회원 ID : <%=loginUser.getUserid() %></p>
        <h3>비밀번호 변경</h3>
        <label for="currentpw">현재 비밀번호</label>
        <input type="password" name="currentpw"><br>
        <label for="newpw">변경할 비밀번호</label>
        <input type="password" name="newpw"><br>
        <input type="submit" value="바꾸기">
    </form>

    <form action="UsersUpdateCon?key=nick" method="post">
        <h3>닉네임 변경</h3>
        <label for="newnick">변경할 닉네임</label>
        <input type="text" name="newnick">
        <input type="submit" value="바꾸기"><br>
    </form>

    <form id="proupload" enctype="multipart/form-data">
        <h3>프로필 사진 변경</h3>
        <input type="file" id="fileInput" accept="image/*">
        <button onclick="uploadFile()">파일 업로드</button>
        <br><br>
        <button onclick="location.href='Main.jsp'">메인화면 가기</button>
		<button onclick="confirmDelete()">회원 탈퇴하기</button>
    </form>
       
	<script>
    //탈퇴
    function confirmDelete() {
      if(window.confirm("탈퇴를 진행하시겠습니까?")&&window.confirm("탈퇴를 하시게 되면 모든 정보가 사라집니다."))
      {
    	  location.href = 'DeleteForm.jsp';
      }
   };
   
   document.getElementById('proupload').addEventListener('submit', function(event) {
       // 폼의 기본 동작(페이지 리로드)을 막음
       event.preventDefault();
   });
   
   /* 파일 업로드 */
   function uploadFile() {
       const fileInput = document.getElementById('fileInput');
       const file = fileInput.files[0];

       if (file) {
           const formData = new FormData();
           formData.append('imageFile', file);

             fetch('/Dicogram/profileUpCon', {
                method: 'POST',
                body: formData
             })
             .then(response => response.text())
             .then(data => {
               alert('프로필 사진이 변경되었습니다.');
                console.log('업로드 성공:', data);
                location.reload();
             })
             .catch(error => {
                console.log('콘솔 업로드 실패:', error.message);
             }); 

        } else {
            console.warn('파일을 선택해주세요.');
        }
    }
    

    </script>

</body>

</html>