<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="style/board/boardUpdate.css">
<html>
<c:import url="../header.jsp"/>
<body>
    <div class = "container">
        <form id="boardud" method="PUT">
            <table id="boardUpdate" value="${sessionScope.log.clientCode}" >
            </table>
        </form>
        <button id="editButton" onclick="editBoard()">수정하기</button>
    </div>
    <script src="../script/board/boardUpdate.js"></script>
</body>
</html>
