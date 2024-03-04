<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>View MyTasks</title>
    <!-- css -->
    <link href="<c:url value="/css/bootstrap.min.css"/>" rel="stylesheet" type="text/css">
    <!-- js -->
    <script type="text/javascript" src="<c:url value="/js/bootstrap.bundle.min.js"/>"></script>
</head>
<body>
    <h1>Users</h1>

    <c:if test="${addUserSuccess}">
        <div>Successfully added User with ISBN: ${savedUser.isbn}</div>
    </c:if>

    <c:url var="add_user_url" value="/book/addUser"/>
    <form:form action="${add_user_url}" method="post" modelAttribute="user">
        <form:label path="isbn">ISBN: </form:label> <form:input type="text" path="isbn"/>
        <form:label path="name">Book Name: </form:label> <form:input type="text" path="name"/>
        <form:label path="author">Author Name: </form:label> <form:input path="author"/>
        <input type="submit" value="submit"/>
    </form:form>

    <div class="table-responsive">
        <table class="table table-striped table-hover">
            <caption>List of users</caption>
            <thead class="table-light">
                <tr>
                    <th class="text-center">ISBN</th>
                    <th class="text-center">Name</th>
                    <th class="text-center">Author</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.isbn}</td>
                        <td>${user.name}</td>
                        <td>${user.author}</td>
                    </tr>
                </c:forEach>
            </tbody>
            <tfoot>
                <tr>
                    <th class="text-center">foot ISBN</th>
                    <th class="text-center">foot Name</th>
                    <th class="text-center">foot Author</th>
                </tr>
            </tfoot>
        </table>
    </div>

</body>
</html>

