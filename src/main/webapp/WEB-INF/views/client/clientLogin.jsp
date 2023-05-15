
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>

</head>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<link rel="stylesheet" href="../style/client/clientLogin.css">

<body>

<div class="form-container">
    <input type="text" name="clientPhone" id="clientPhone" placeholder="핸드폰번호" autofocus>
    <input type="password" name="clientPassword" id="clientPassword" placeholder="비밀번호">
    <input id="loginBtn" type="button" onclick="clientLogin()" value="로그인">
    <div class="button-login">
        <img src="//k.kakaocdn.net/14/dn/btqbjxsO6vP/KPiGpdnsubSq3a0PHEGUK1/o.jpg" onclick="loginWithKakao()"/>
    </div>
</div>



<script src="../script/client/clientLogin.js"></script>
<script src="../script/client/kakaoLogin.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</body>

</html>
