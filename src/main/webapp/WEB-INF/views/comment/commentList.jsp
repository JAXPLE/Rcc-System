<%--
  Created by IntelliJ IDEA.
  User: kown
  Date: 2023-04-28
  Time: 오후 5:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<c:import url="../header.jsp"/>
<body>
    <div>
        const log = ${sessionScope.log.clientName}
        <section>
            <div>
                <table id="commentList"/>
            </div>
        </section>
    </div>
    <script src="../script/comment/commentList.js"></script>
</body>
<c:import url="../footer.jsp"></c:import>
</html>