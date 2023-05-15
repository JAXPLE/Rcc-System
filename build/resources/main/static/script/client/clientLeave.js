function checkDelete() {
    const clientPassword = document.getElementById("password").value;

    let check = true;

    if (clientPassword === "") {
        alert('비밀번호를 입력해주세요.');
        check = false;
    }


    if (check) {
        var UP;
        UP = confirm("정말 탈퇴 하시겠습니까?");

        if (UP) {
            $.ajax({
                "url": "delete",
                "method": "POST",
                "timeout": 0,
                "data": {
                    clientPassword: clientPassword,
                },
                success: function (isRun) {
                    console.log(isRun);

                    if(isRun){
                        alert("삭제되었습니다.")
                        location.href = "/";
                    }
                    else{
                        alert("비밀번호를 확인하세요")
                    }

                }
            })
        }

    }


}