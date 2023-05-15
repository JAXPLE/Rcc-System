<%@ page import="com.example.rcc_system.domian.board.Board" %><%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 5:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="style/board/boardList.css">
<html>
<c:import url="../header.jsp"/>
<body>
<div class="container">
    <section>
        <table>
            <thead>
                <select id="searchType" name="searchType">
                    <option value="ALL" selected>전체</option>
                    <option value="CODE">게시글 코드</option>
                    <option value="TITLE">제목</option>
                    <option value="CONTEXT">내용</option>
                    <option value="TITLE_AND_CONTEXT">제목 + 내용</option>
                    <option value="WRITER">작성자</option>
                </select>
                <div>
                    <textarea id="searchContext" placeholder="내용을 입력하세요"></textarea>
                </div>

                <div>
                    <input type="button" value="검색하기" onclick="search(1)">
                </div>
                <div id="writeBoard">
                    <div>
                        <c:if test="${not empty sessionScope.log}">
                            <input id="writeBtn" type="button" value="게시물작성" onclick="location.href='boardWrite'">
                        </c:if>
                    </div>
                </div>
            </thead>
            <tbody>
            <tr>
                <th>번호</th>
                <th>작성자</th>
                <th>제목</th>
                <th>내용</th>
                <th>작성일시</th>
            </tr>
            </tbody>
            <tfoot id="boardList"></tfoot>
        </table>

        <div id="pageIndex">

        </div>
    </section>
</div>
<script src="../script/board/boardList.js"></script>
</body>
<c:import url="../footer.jsp"/>
</html>