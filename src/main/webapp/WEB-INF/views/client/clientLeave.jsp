<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 4:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../style/client/clientLeave.css">
</head>
<body>
<div class="container">
    <section class="drop">
        <input type="hidden" name="command" value="dropcustomer">
        <h2 id="title">회원탈퇴</h2>
        <label for="password">비밀번호:</label>
        <input type="password" id="password" name="password">
        <input type="button" value="탈퇴" onclick="checkDelete()">
    </section>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../script/client/clientLeave.js"></script>
</body>
</html>
