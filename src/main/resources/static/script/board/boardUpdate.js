const boardCode = location.search.split('=')[1];
// console.log('boardCode',boardCode);
$(document).ready(function () {
    const urlParams = new URLSearchParams(location.search);
    const boardCode = urlParams.get('boardCode');
    $.ajax({
        url: `/getBoard?boardCode=${boardCode}`,
        method: "GET",
        timeout: 0
    }).done(response => {
        const board = response; // 서버로부터 받은 데이터를 board 변수에 할당
        $('#boardUpdate').append(`
            <div id="edit">
                <span>제목</span>
                <textarea id="title" placeholder="${board.boardTitle}"></textarea>
                <span>내용</span>
                <textarea id="context" placeholder="${board.boardContext}"></textarea>
            </div>
        `);
    });
});

function editBoard(htmlForm) {
    const code= location.search.split('=')[1];
    const title      = document.getElementById("title").value;
    const context    = document.getElementById("context").value;

    const data = {
        boardCode : code,
        boardTitle : title,
        boardContext : context,
        boardModifyDate : now()
    }
    // console.log(data);

    $.ajax({
        url : "/updateBoard",
        method : "PUT",
        timeout : 0,
        data: JSON.stringify(data),
        contentType:"application/json",
    }).done(function () {
        location.href="boardList"
    })
}

function now() {
    const currentDate = new Date();
    return currentDate.toISOString(); // ISO 8601 형식의 문자열로 반환
}