$(document).ready(function () {

    $.ajax({
        url: "getClient",
        method: "GET",
        timeout: 0,


    }).done(client => {
        $('#container').append(`
    <input type="text" name="name" id="name" placeholder="이름" value="${client.clientName}" readonly>
    <input type="text" name="phone" id="phone" placeholder="전화번호" value="${client.clientPhone}"readonly>
    <input type="text" name="password" id="password" placeholder="비밀번호">
    <input type="text" name="zonecode" id="zonecode" placeholder="우편번호" readonly>
    <input type="text" name="address" id="address" placeholder="주소" value="${client.clientAddress}" readonly>
    <input type="text" name="detailAdress" id="detailAdress" placeholder="상세주소">
    <button type="button" id="addressSearch" onclick="findAddr()">주소검색</button>

    <input type="button" onclick="updateClick()" value="회원수정">
    `);

    })
});

function updateClick() {

    const clientPhone = document.getElementById("phone").value;
    const clientPassword = document.getElementById("password").value;
    const clientAddress = document.getElementById("address").value
        + "" + document.getElementById("detailAdress").value;

    let check = true;

    if (clientPassword === "") {
        alert("비밀번호를 입력하세요");
        check = false;
    }
    if (clientAddress === "") {
        alert("주소를 입력하세요");
        check = false;
    }

    if (check) {

        const userData = {
            clientPassword: clientPassword,
            clientPhone: clientPhone,
            clientAddress: clientAddress,
        };

        $.ajax({

            "url": "update",
            "method": "POST",
            "timeout": 0,
            "data": JSON.stringify(userData),
            contentType: "application/json",

            success: function () {
                alert("회원수정 되었습니다! 다시 로그인해주세요");
                location.href = "clientLogin"
            }

        })
    }
}