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
}