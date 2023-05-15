function checkBoardWriter(htmlForm) {

    const code    = document.getElementById("code").value;
    const writer  = document.getElementById("writer").value;
    const title   = document.getElementById("title").value;
    const context = document.getElementById("context").value;

    const data = {
        clientCode : code,
        boardWriter : writer,
        boardTitle : title,
        boardContext : context
    }

    // console.log(data);

    $.ajax({
        "url" : `addBoard`,
        "method" : "POST",
        "timeout" : 0,
        "data" : JSON.stringify(data),
        contentType:"application/json",
    }).done(function (isRun) {
        location.href="boardList"
    })
}