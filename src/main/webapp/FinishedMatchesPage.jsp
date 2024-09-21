<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Finished Matches | MatchScore Tracker</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        nav {
            background-color: #333;
            color: white;
            padding: 10px;
        }
        nav a {
            color: white;
            text-decoration: none;
            margin-right: 15px;
        }
        nav a:hover {
            text-decoration: underline;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        form {
            margin-bottom: 20px;
            text-align: center;
        }

        form input[type="text"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 200px;
            font-size: 16px;
        }

        form button {
            padding: 10px 15px;
            border: none;
            background-color: #333;
            color: #fff;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
        }

        form button:hover {
            background-color: #555;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table th, table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        table th {
            background-color: #f4f4f4;
            color: #333;
        }

        table tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        table tr:hover {
            background-color: #f1f1f1;
        }

        footer {
            text-align: center;
            padding: 10px;
            background-color: #333;
            color: white;
            position: fixed;
            width: 100%;
            bottom: 0;
        }

    </style>
</head>
<body>

<nav>
    <a href="/">Home</a>
    <a href="/new-match">New Match</a>
    <a href="/matches">Matches</a>
</nav>

<div class="container">
    <h1>Finished Matches</h1>

    <form method="get" action="matches">
        <div>
            <label for="PlayerName">Search by name:</label>
            <input type="text" id="PlayerName" name="filter_by_player_name" placeholder="Player name here">
            <button type="submit">Search</button>
        </div>
    </form>

    <table>
        <thead>
        <tr>
            <th>Match ID</th>
            <th>First Player</th>
            <th>Second Player</th>
            <th>Winner</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="matchEntity" items="${matchEntities}">
            <tr>
                <td>${matchEntity.id}</td>
                <td>${matchEntity.player1.name}</td>
                <td>${matchEntity.player2.name}</td>
                <td>${matchEntity.winner.name}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>


<table>
    <tr>
        <!-- Only show "Previous" button if current page is greater than 1 -->
        <td>
            <c:if test="${page > 1}">
                <form action="matches" method="get">
                    <input type="hidden" name="page" value="${page - 1}">
                    <button type="submit">Previous</button>
                </form>
            </c:if>
        </td>

        <!-- Display current page number -->
        <td>Page ${page} of ${totalPages}</td>

        <!-- Only show "Next" button if current page is less than total pages -->
        <td>
            <c:if test="${page < totalPages}">
                <form action="matches" method="get">
                    <input type="hidden" name="page" value="${page + 1}">
                    <button type="submit">Next</button>
                </form>
            </c:if>
        </td>
    </tr>
</table>

<footer>
    <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>

</body>
</html>

