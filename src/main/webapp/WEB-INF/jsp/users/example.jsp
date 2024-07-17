<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>Example</title>
</head>
<body>
    <h1>Title</h1>

    <form >
        <input type="button" value="My button" onClick='location:href="/users/add-example"' />
    </form>
    <div>
        <p>First name: <span th:text="${example}" /></p>
        <p th:text>Last name: Potter</p>
    </div>
</body>
</html>

