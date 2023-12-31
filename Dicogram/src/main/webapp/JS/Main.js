

// Main.js

function handleButtonClick(page) {
    if (page === 'home') {
        // 새 창을 생성하거나 기존 창을 엽니다.
        var newWindow = window.open("", "_blank", "width=600,height=600");

        // 새 창 객체가 생성되었는지 확인합니다.
        if (newWindow) {
            // 두 번째 HTML 코드의 내용을 새 창에 작성합니다.
            newWindow.document.write(`
               <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>인스타그램 피드</title>
                    <link rel="stylesheet" href="./CSS/Menu01_Home_newpost.css">
                </head>
                <body>
                <form id="postForm" method="post" action="post">
                <div id="feed-container2">
                    <div id="post-form">
                        <table  align="center">
                            <tr>
                                <td> <label for="post-title">게시물 제목</label></td>
                                <td> <input type="text" id="post-title" placeholder="제목을 입력하세요."name="postname" ></td>
                            </tr>
                        </table>
                    </div>    
            
                </div>
                <div id="post-form">
                    <textarea id="post-input" placeholder="게시물을 작성하세요" name="pcontent"></textarea>
                    
                    <div class="form-group">
                       <input type="file" id="fileInput" accept="image/*" name="image">
                  
                  <br>
                        <br>
                        <input name="tags" placeholder="태그을 작성하세요">
                        
                        
                    </div>
                    <table id="cancel_post">
                        <tr>
                            <td><button id="cancel-button" onClick="window.close()">취소</button></td>
                            <td><button id="post-button" type="submit" onclick="uploadFile()">게시</button></td>
                        </tr>
                    </table>
                </div>
                </form>
                <div id="post-list"></div>
            </div>
            <script>
   
    function uploadFile() {
        const fileInput = document.getElementById('fileInput');
        const file = fileInput.files[0];

        if (file) {
            const formData = new FormData();
            formData.append('imageFile', file);

            fetch('http://localhost:8089/Dicogram/postUp', {
                method: 'POST',
                body: formData
            })
            .then(response => response.text())
            .then(data => {
                console.log('업로드 성공:', data);
            })
            .catch(error => {
                console.log('업로드 실패:', error);
            });
        } else {
            console.warn('파일을 선택해주세요.');
        }
    }
   
     </script>
           
            
                </body>
                </html>
            `);
        
        }
    }
}