// 암호 생성 체크박스 요소를 가져옴
var generatePasswordCheckbox = document.getElementById("generatePasswordCheckbox");

// 암호 입력란 요소를 가져옴
var passwordInput = document.getElementById("passwordInput");

// 공개 비공개 채팅 요소 가져옴
var openOrNot = document.getElementById("generateOpenOrNot");

// 체크박스에 이벤트 리스너 추가
generatePasswordCheckbox.addEventListener("change", function () {
    // 체크박스의 상태에 따라 암호 입력란을 숨기거나 보이기
    if (generatePasswordCheckbox.checked) {
        passwordInput.style.display = "block";  // 보이기
    } else {
        passwordInput.style.display = "none";  // 숨기기
    }
});

// 폼의 submit 이벤트를 감지하고 name 속성을 설정
chatForm.addEventListener("submit", function (event) {
    // 체크박스의 상태에 따라 name 속성을 설정
    var openValue = openOrNot.checked ? "Y" : "N";
    document.getElementsByName("openOrNot")[0].value = openValue;
});



// Menu02_Chat.js

function handleButtonClick(section) {
    if (section === 'chat') {
        openCreateChatWindow();
    }
}

function openCreateChatWindow() {
    // URL 설정
    const url = '/HTML/Menu02_Chat_New.html';

    // 창 열기
    const newWindow = window.open(url, '_blank', 'width=400,height=500');

    // 새 창이 정상적으로 열렸을 때의 처리
    if (newWindow) {
        // 새 창이 로드될 때의 이벤트 감지
        newWindow.addEventListener('load', function () {
            // 여기에 초기화 코드 추가 (예: 필요한 초기화 함수 호출)
        });
    } else {
        // 창 열기에 실패한 경우 사용자에게 메시지 표시
        alert('창을 열 수 없습니다.');
    }
}

// 모달 창 컨트롤
const new_chat_btn = document.querySelector("button[class='content-button']")
new_chat_btn.addEventListener('click', () => {
    const modal_window = document.querySelector("div[id='modal_background']")
    modal_window.setAttribute("class", "")
})
window.addEventListener("keydown", (e) => {
    if (e.key === 'Escape') {
        const modal_window = document.querySelector("div[id='modal_background']")
        const chatroom_input = document.querySelector("input[id='chatRoomName']")
        const password_input = document.querySelector("input[id='chatRoomPW']")
        const openornot_input = document.querySelector("input[id='generateOpenOrNot']")
        const generatepassword_input = document.querySelector("input[id='generatePasswordCheckbox']")
        modal_window.setAttribute("class", "hidden_modal")
        chatroom_input.value = ""
        password_input.value = ""
        openornot_input.checked = false
        generatepassword_input.checked = false
    }
})
