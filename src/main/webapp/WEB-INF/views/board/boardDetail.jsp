<%--f
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 5:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="style/board/boardDetail.css">
<html>
<c:import url="../header.jsp"/>
<body>
    <div class = "container">
        <section>
            <div class="content-container" id="${sessionScope.log.clientCode}">
                <table id="boardDetailPrint">
                    <tr>
                        <th>${board.boardCode}</th>
                        <th>${board.boardTitle}</th>
                        <th>${board.boardContext}</th>
                    </tr>
                </table>
                <table id="commentPrint">
                    <c:forEach var="comment" items="${commentList}">
                        <tr>
                            <div id="commentInfo">
                                <td>${comment.commentWriter}</td>
                                <td><input type="text" id="${comment.commentCode}" value="${comment.commentContext}" placeholder="${comment.commentContext}" readonly></td>
                                <td>${comment.commentModifyDate}</td>
                                <td>
                            </div>
                                <c:if test="${comment.commentWriter eq sessionScope.log.clientName}">
                                    <button onclick="editComment(${comment.commentCode})">수정</button>
                                    <button onclick="deleteComment(${comment.commentCode})">삭제</button>
                                </c:if>
                            </td>
                            <td id="editButton">
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <div>
                <c:if test="${not empty sessionScope.log}">
                    <input type="hidden" id="writerName" value="${sessionScope.log.clientName}">
                    <th><textarea id="comment" placeholder="댓글을 입력하세요"></textarea></th>
                    <th><input type="button" id="button-writer" value="작성하기" onclick="addComment(form)"></th>
                    <c:if test="${board.boardWriter eq sessionScope.log.clientName}">
                       <div class="">
                        <button id="editButton" onclick="updateBoard(${param.boardCode})">수정하기</button>
                        <button id="editButton" onclick="deleteBoard(${board.boardCode})">삭제하기</button>
                       </div>
                    </c:if>
                </c:if>
            </div>
        </section>
    </div>
    <script src="../script/board/boardDetail.js"></script>
</body>
<c:import url="../footer.jsp"></c:import>
</html>
