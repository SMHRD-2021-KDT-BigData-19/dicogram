function validateLogin() {
    var username = document.getElementById('username').value;
    var password = document.getElementById('password').value;

    // 간단한 유효성 검사, 실제 서버 측 검사로 대체해야 함
    if (username === 'your_username' && password === 'your_password') {
        // 다른 페이지로 리디렉션 (원하는 페이지로 'dashboard.html' 대체)
        window.location.href = 'Main.html';
    } else {
        alert('유효하지 않은 아이디 또는 비밀번호입니다');
    }
}