function clientLogin(){
    const clientPhone = document.getElementById("clientPhone").value;
    const clientPassword = document.getElementById("clientPassword").value;

    $.ajax({
        "url": "login",
        "method": "POST",
        "timeout" : 0,
        "data": {
            clientPhone: clientPhone,
            clientPassword: clientPassword,
        },
    }).done(function (isRun){
        if(isRun){
            alert("어서오세요 환영합니다!");
            location.href="/";
        }
        else{
            alert("아이디 혹은 비밀번호가 틀렸습니다.");
        }
    })
}

