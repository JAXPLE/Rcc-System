function checkBox() {

    $(".checkbox").on("click", "#checkall", function () {
        $(this).parents(".checkbox").find('input').prop("checked", $(this).is(":checked"));
    });


    $(".checkbox").on("click", ".check", function () {
        var is_checked = true;

        $(".checkbox .check").each(function () {
            is_checked = is_checked && $(this).is(":checked");
        });

        $("#checkall").prop("checked", is_checked);
    });

}

function checkPopUp() {

    $(document).ready(function () {
        $(".button-open").click(function () {
            $(".popup-box").show();
            $("#mask").fadeIn(100);
        });
        $(".button-close").click(function () {
            $(".popup-box").hide();
            $("#mask").fadeOut(100);
        });

        // 팝업 중앙 정렬
        var $layerPopup = $(".popup-box");
        var left = ($(window).scrollLeft() + ($(window).width() - $layerPopup.width()) / 2);
        var top = ($(window).scrollTop() + ($(window).height() - $layerPopup.height()) / 2);
        $layerPopup.css({"left": left, "top": top, "position": "absolute"});
        $("body").css("position", "relative").append($layerPopup);
    });

}

function findAddr() {
    new daum.Postcode({
        oncomplete: function (data) {

            var addr = '';

            if (data.userSelectedType === 'R') { // 도로명 주소를 선택했을 경우(R)
                addr = data.roadAddress;
            } else { // 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            document.getElementById('zonecode').value = data.zonecode;
            $("#address").val(addr);

        }
    }).open();
}

function joinClick() {
    const clientName = document.getElementById("name").value;
    const clientPhone = document.getElementById("phone").value;
    const clientPassword = document.getElementById("password").value;
    const clientAddress = document.getElementById("address").value
        + "" + document.getElementById("detailAdress").value;
    const clientType = document.getElementById("type").value;
    const kakaoId = document.getElementById("kakaoid").value;
    const phone2 = document.getElementById("phone2").value;



    let check = true;

    if (clientName === "") {
        alert("이름을 입력하세요");
        check = false;
    }
    if (clientPhone === "") {
        alert("번호를 입력하세요");
        check = false;
    }
    if (clientPassword === "") {
        alert("비밀번호를 입력하세요");
        check = false;
    }
    if (clientAddress === "") {
        alert("주소를 입력하세요");
        check = false;
    }
    if(phone2 === "" || phone2 != code2){
        alert("핸드폰인증을 해주세요");
        check = false;
    }

    if (check) {
        const userData = {
            clientName: clientName,
            clientPassword: clientPassword,
            clientPhone: clientPhone,
            clientAddress: clientAddress,
            clientType: clientType,
            clientIsused: true,
            kakaoId: kakaoId,
        };

        $.ajax({
            "url": "join",
            "method": "POST",
            "timeout": 0,
            "data": JSON.stringify(userData),
            contentType: "application/json",
        }).done(function (result) {
            if (result != null) {
                alert("환영합니다!");
                location.href = "clientLogin";
            } else {
                alert("회원가입 실패하였습니다.");
                location.href = "/";
            }
        })

    }

}

function clientPhoneduplication() {

    const clientPhone = document.getElementById("phone").value;

    let check = true;

    if (clientPhone === "") {
        alert("번호를 입력하세요");
        check = false;
    }

    if (check) {

        $.ajax({
            "url": "duplication",
            "method": "POST",
            "timeout": 0,
            "data": {
                clientPhone: clientPhone,
            },
        }).done(function (isRun) {
            if (!isRun) {
                alert("사용가능한 번호입니다 !");
                $("#phone2").attr("disabled",true);
                $("#phone").attr("disabled",true);
            } else {
                alert("이미 사용중인 번호입니다.");
                document.getElementById("phone").value = "";
                document.getElementById("phone2").value = "";
                $("#phone2").attr("disabled",true);
                $("#phone").attr("disabled",false);
            }
        })

    }

}

function phoneCheck(){
    const phone = document.getElementById("phone").value;
    if(phone != ""){
        alert("인증번호 발송이 완료되었습니다.");
        $("#phone").attr("disabled",true);
        $.ajax({
            type:"GET",
            url:"phoneCheck",
            data :{
                phone : phone,
            },
            cache : false,
            success:function(data){
                if(data == "error"){
                    alert("휴대폰 번호가 올바르지 않습니다.");
                    $("#phone").attr("autofocus",true);
                }else{
                    $("#phone2").attr("disabled",false);
                    code2 = data;
                }
            }
        });
    }
    else{
        alert("번호를 입력해주세요");
    }

};

//휴대폰 인증번호 대조
function checkNumber(){
    const phone = document.getElementById("phone").value;

    if(phone != ""){
        if($("#phone2").val() == code2){
            clientPhoneduplication();
        }else{
            alert("일치하지 않습니다");
            $(this).attr("autofocus",true);
        }
    }
    else{
        alert("인증번호를 입력해주세요");
    }

};
