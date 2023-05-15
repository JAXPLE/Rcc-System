let log = "initial value"; // 변수를 선언하고 초기 값을 설정합니다.
console.log(log); // 변수를 사용합니다.

$(document).ready(function (){
    log = sessionStorage.getItem(log);
    console.log(log);
    $.ajax({
        url : "/getAllComment",
        method : "GET",
        timeout: 0
    }).done(response => {
        response.forEach(comment => {
            $('#commentList').append(`
                <tr>
                    <td><a value=${comment.getBoardCode}></a></td>
                    <td><a value=${comment.commentContext}></a></td>
                    <td><a value=${comment.getCommentWriteDate}></a></td>
                </tr>
            `)
        })
    })
})