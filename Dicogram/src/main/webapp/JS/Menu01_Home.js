document.addEventListener('DOMContentLoaded', function() {
    const feedContainer = document.getElementById('feed-container');

    function toggleComments(postId) {
        const commentsContainer = document.getElementById(`comments${postId}`);
        commentsContainer.classList.toggle('show-comments');
    }

    function formatDate(timestamp) {
        const date = new Date(timestamp);
        const options = { year: 'numeric', month: 'long', day: 'numeric', hour: 'numeric', minute: 'numeric' };
        return new Intl.DateTimeFormat('ko-KR', options).format(date);
    }

    function addPostToFeed(post) {
        const postElement = document.createElement('div');
        postElement.className = 'post';

        postElement.innerHTML = `
            <div class="post-header">
                <img src="${post.profileImage}" alt="${post.username}" width="40px" height="40px">
                <span class="username">${post.username}</span>
            </div>
            <div class="post-content">
                <img src="${post.imageUrl}" alt="${post.caption}">
                <div class="caption">${post.caption}</div>
            </div>
            <div class="post-actions">
                <button onclick="toggleComments('${post.id}')">댓글</button>
                <div class="date" data-timestamp="${post.timestamp}">${formatDate(post.timestamp)}</div>
            </div>
            <div class="comments" id="comments${post.id}">
                <!-- 댓글이 나타날 곳 -->
                <!-- 이곳에 동적으로 댓글이 추가됩니다. -->
            </div>
        `;

        feedContainer.appendChild(postElement);
    }

    const posts = [
        {
            id: '1',
            username: 'user1',
            profileImage: 'profile.jpg',
            imageUrl: 'image1.jpg',
            caption: 'Beautiful sunset!',
            timestamp: 1638382000000
        },
        {
            id: '2',
            username: 'user2',
            profileImage: 'profile.jpg',
            imageUrl: 'image2.jpg',
            caption: 'Exploring new places.',
            timestamp: 1638295600000
        }
        // Add more posts as needed
    ];

    posts.forEach(addPostToFeed);
});
