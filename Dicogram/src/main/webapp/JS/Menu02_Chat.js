// 채팅방 목록 데이터
 const chatRooms = [
    { id: 1, name: '방1' },
    { id: 2, name: '방2' },
    { id: 3, name: '방3' },
    // 추가적인 채팅방 데이터는 필요에 따라 더 추가할 수 있습니다.
];

// 채팅방 목록을 동적으로 생성하는 함수
function renderChatList() {
    const chatListElement = document.getElementById('chatList');

    // 기존 목록 초기화
    chatListElement.innerHTML = '';

    // 각 채팅방에 대한 목록 아이템 생성
    chatRooms.forEach(room => {
        const listItem = document.createElement('li');
        listItem.classList.add('chatRoom');
        listItem.textContent = room.name;

        // 클릭 이벤트 처리
        listItem.addEventListener('click', () => {
            // 채팅방에 대한 동작을 수행 (예: 해당 채팅방으로 이동)
            console.log(`채팅방 ${room.name}으로 이동합니다.`);
        });

        // 목록에 아이템 추가
        chatListElement.appendChild(listItem);
    });
}

// 페이지 로드 시 채팅방 목록 생성
window.onload = renderChatList;