<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 5:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="style/board/boardWrite.css">
<html>
<c:import url="../header.jsp"/>
<body>
    <div class = "container">
        <section>
            <form method="post">
                <input type="hidden" value="${sessionScope.log.clientCode}" id="code">
                <div class = "title">
                    <th><input type="hidden" value="${sessionScope.log.clientName}" id="writer">${sessionScope.log.clientName}</th>
                </div>
                <div class = "title">
                    <th><span>제목</span></th>
                    <th><textarea id="title"></textarea></th>
                </div>
                <div>
                    <th><span>내용</span></th>
                    <th><textarea id="context"></textarea></th>
                </div>
                <input type="button" value="작성하기" onclick="checkBoardWriter(form)">
            </form>
        </section>
    </div>
    <script src="../script/board/boardWriter.js"></script>
</body>
<c:import url="../footer.jsp"/>
</html>
