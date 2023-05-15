function check(htmlForm) {


    
    
    
    
    const id = document.getElementById("id").value;
    const pass = document.getElementById("pass").value;
    const name = document.getElementById("name").value;

    let check=true;



    if (id === "") {
        alert("아이디가 업습니다");
check=false;
    } else if(pass==="")
    {
        alert("비밀번호가 업습니다");
        check=false;

    }else if(name==="")
    {
        alert("이름이 업습니다");
        check=false;

    }



    if(check==true){
        console.log(id);
        console.log(pass);
        console.log(name);
        alert("성공");

        htmlForm.submit();



    }else{
alert("실패")

        location.href="login";

    }



}
