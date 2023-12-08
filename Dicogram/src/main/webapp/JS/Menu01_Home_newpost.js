
document.addEventListener('DOMContentLoaded', function() {
    const postInput = document.getElementById('post-input');
    const fileInput = document.getElementById('file-input');
    const postButton = document.getElementById('post-button');
    const postList = document.getElementById('post-list');
    const postTitleInput = document.getElementById('post-title');

    postButton.addEventListener('click', function() {
        const postText = postInput.value.trim();
        const postTitle = postTitleInput.value.trim();
        const file = fileInput.files[0];

        if (postText !== '' || file) {
            createPost(postTitle, postText, file);
            postTitleInput.value = '';
            postInput.value = '';
            fileInput.value = ''; // 파일 선택 초기화
            // 여기에 게시물 및 사진을 서버에 전송하는 로직 추가 가능
        }
    });


    // 여기에 서버로부터 게시물 및 사진을 가져오는 로직 추가 가능
});