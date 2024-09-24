<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Finished Matches | MatchScore Tracker</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f9fc;
        }

        nav {
            background-color: #2c3e50;
            color: white;
            padding: 15px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        nav a {
            color: white;
            text-decoration: none;
            margin-right: 20px;
            font-weight: bold;
            text-transform: uppercase;
            transition: color 0.3s ease;
        }
        nav a:hover {
            color: #1abc9c;
        }

        .container {
            width: 80%;
            margin: 20px auto;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }

        form {
            margin-bottom: 20px;
            text-align: center;
        }

        form input[type="text"] {
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            width: 220px;
            font-size: 16px;
            margin-right: 10px;
        }

        form button {
            padding: 10px 15px;
            border: none;
            background-color: #2c3e50;
            color: #fff;
            border-radius: 4px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        form button:hover {
            background-color: #34495e;
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

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination button {
            margin: 0 10px;
            padding: 10px 15px;
            border: none;
            background-color: #2c3e50;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }

        .pagination button:hover {
            background-color: #34495e;
        }

        footer {
            text-align: center;
            padding: 15px;
            background-color: #2c3e50;
            color: white;
            position: fixed;
            width: 100%;
            bottom: 0;
            box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
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
        <label for="PlayerName">Search by name:</label>
        <input type="text" id="PlayerName" name="filter_by_player_name" placeholder="Player name here">
        <button type="submit">Search</button>
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
        <c:forEach var="matchDto" items="${finishedMatchesDto.matches}">
            <tr>
                <td>${matchDto.id}</td>
                <td>${matchDto.player1Name}</td>
                <td>${matchDto.player2Name}</td>
                <td>${matchDto.winnerName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="pagination">
        <c:if test="${finishedMatchesDto.page > 1}">
            <form action="matches" method="get">
                <input type="hidden" name="page" value="${finishedMatchesDto.page - 1}">
                <button type="submit">Previous</button>
            </form>
        </c:if>

        <span>Page ${finishedMatchesDto.page} of ${finishedMatchesDto.totalPages}</span>

        <c:if test="${finishedMatchesDto.page < finishedMatchesDto.totalPages}">
            <form action="matches" method="get">
                <input type="hidden" name="page" value="${finishedMatchesDto.page + 1}">
                <button type="submit">Next</button>
            </form>
        </c:if>
    </div>
</div>

<footer>
    <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>

</body>
</html>
