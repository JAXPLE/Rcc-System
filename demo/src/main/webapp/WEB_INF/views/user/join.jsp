<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-25
  Time: 오후 6:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/style/join.css">
</head>
<body>

<form method="post" id="form" action="/join">

    <input type="text" id="id" name="id" placeholder="아이디">

    <input type="text" id="pass" name="pass" placeholder="비번">

    <input type="text" id="name" name="name" placeholder="이름">

    <input type="button" onclick="check(this.form)" value="입력">
</form>


</body>

<script src="/script/validation.js"></script>

</html>
