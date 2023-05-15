const boardCode = location.search.split('=')[1];

function deleteBoard(boardCode) {
    $.ajax({
        url: `/deleteBoard?boardCode=${boardCode}`,
        type: 'DELETE'
    }).done(response => {
        location.href = response;
    }).fail(error => {
        alert('error');
    });
}

function updateBoard(boardCode) {
    location.href = `boardUpdate?boardCode=${boardCode}`;
}

const commentCode = null;
function deleteComment(commentCode) {
    if (!confirm('정말 삭제하시겠습니까?')) {
        return;
    }

    $.ajax({
        url: `/deleteComment?commentCode=${commentCode}`,
        method: 'DELETE',
        success: function (response) {
            location.href=`/boardDetail?boardCode=${boardCode}`;
        }
    })
}
function editComment(commentCode) {
    const commentContextInput = document.getElementById(commentCode);
    if (commentContextInput.readOnly) {
        commentContextInput.removeAttribute('readonly');
        commentContextInput.readOnly = false;
    } else {
        // readOnly is false, create JSON data
        const jsonData = {
            commentCode: commentCode,
            commentContext: commentContextInput.value
        };
        updateComment(jsonData);
    }
}

function updateComment(data) {
    $.ajax({
        url: `/updateComment`,
        "method": "PUT",
        "timeout": 0,
        "data" : JSON.stringify(data),contentType:"application/json",
    }).done(function (isRun){
        location.href=`boardDetail?boardCode=${boardCode}`
    })
}

function addComment(htmlForm) {
    const commentWriter  = document.getElementById("writerName").value;
    const commentContext = document.getElementById("comment").value;
    const commentWriteDate = new Date();
    const CommentModifyDate= new Date();

    if (commentContext === null) {
        confirm('빈내용으로 작성할 수 없습니다.');
        return;
    }

    const data = {
        commentWriter : commentWriter,
        boardCode : boardCode,
        boardWriter : writerName,
        commentContext : commentContext,
        commentWriteDate : commentWriteDate,
        CommentModifyDate : CommentModifyDate
    }

    $.ajax({
        url: `/addComment`,
        "method": "POST",
        "timeout": 0,
        "data" : JSON.stringify(data),contentType:"application/json",
    }).done(function (isRun){
        location.href=`boardDetail?boardCode=${boardCode}`
    })
}