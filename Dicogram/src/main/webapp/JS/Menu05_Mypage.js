// 플러스 버튼 클릭 시 파일 입력 필드 열기
document.getElementById('uploadLabel').addEventListener('click', function() {
    document.getElementById('profilePicture').click();
});

// 파일 선택 시 프로필 이미지 업데이트
document.getElementById('profilePicture').addEventListener('change', function() {
    var profileImage = document.getElementById('profileImage');
    var fileInput = document.getElementById('profilePicture');

    if (fileInput.files.length > 0) {
        var reader = new FileReader();
        reader.onload = function (e) {
            profileImage.src = e.target.result;
        };
        reader.readAsDataURL(fileInput.files[0]);
    }
});

// 저장 버튼 클릭 시 호출되는 함수
function saveProfile() {
    // 입력된 값 가져오기
    var fullName = document.getElementById("fullName").value;
    var email = document.getElementById("email").value;
    var bio = document.getElementById("bio").value;

    // 비밀번호 관련 값 가져오기
    var currentPassword = document.getElementById("currentPassword").value;
    var newPassword = document.getElementById("newPassword").value;

    // 여기에서 서버로 프로필 정보와 비밀번호 변경 정보를 전송하거나 필요한 처리를 수행할 수 있습니다.
    // 이 예제에서는 간단하게 콘솔에 출력합니다.
    console.log("닉네임: " + fullName);
    console.log("이메일: " + email);
    console.log("현재 비밀번호: " + currentPassword);
    console.log("새로운 비밀번호: " + newPassword);

    // 저장 완료 메시지
    alert("프로필이 저장되었습니다.");
}

document.addEventListener('DOMContentLoaded', function() {
    // 프로필 이미지 엘리먼트를 가져옵니다.
    var profileImgButton = document.getElementById('profile_img');

    // 프로필 이미지에 클릭 이벤트 리스너를 추가합니다.
    profileImgButton.addEventListener('click', function() {
        // 프로필 이미지가 클릭되었을 때 'mypage'를 인자로 전달하여 showContent 함수를 호출합니다.
        showContent('mypage');
    });
});

// 'mypage-content'에서 버튼 클릭 이벤트를 처리하는 함수
function saveProfile() {
    // 프로필 저장에 관한 로직을 여기에 추가합니다.
    alert('프로필이 저장되었습니다!'); // 실제 로직으로 대체하세요.
}