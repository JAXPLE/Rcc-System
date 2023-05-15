$(document).ready(function() {
    search();
});
function search(requestPage) {
    const searchType = $('#searchType').val();
    const searchText = document.getElementById("searchContext").value;

    $.ajax({
        url: "/searchGetBoard",
        method: "POST",
        timeout: 0,
        data: JSON.stringify({
            "searchType": searchType,
            "searchText": searchText,
            "page": requestPage
        }),
        contentType: "application/json",
    }).done(response => {
        console.log(response.size);
        $('#boardList').empty();
        $('#pageIndex').empty();
        response.list.forEach(board => {
            const dateOptions = {
                month: '2-digit',
                day: '2-digit',
                timeZone: 'Asia/Seoul',
            };
            const formattedDate = new Intl.DateTimeFormat('ko-KR', dateOptions).format(new Date(board.boardGenerationDate));
            console.log(board);
            $('#boardList').append(`
            <tr>
                <td><span value=${board.boardCode}>${board.boardCode}</span></td>
                <td><span value="${board.boardWriter}" onclick="location.href='/boardDetail?boardCode=${board.boardCode}'">${board.boardWriter}</span></td>
                <td><span value="${board.boardTitle}" onclick="location.href='/boardDetail?boardCode=${board.boardCode}'">${board.boardTitle}</span></td>
                <td><span value=${board.boardContext} onclick="location.href='/boardDetail?boardCode=${board.boardCode}'">${board.boardTitle}</span></td>
                <td>${formattedDate.replace('.', '/').replace('.', '').replace(' ', '')}</td>
            </tr>
            `);
        });
        for (let i = 0; i < response.size; i++) {
            $('#pageIndex').append(`
                <span id="pageIndexBtn" onclick="search(${i})">${i+1} </span>
            `)
        }
    });
}