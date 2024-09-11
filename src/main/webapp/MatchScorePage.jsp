<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<style>
    table, th, td {
        border:1px solid black;
    }
</style>
<head>
    <title>Match score</title>
</head>
<body>
<nav>
    <div class="nav-items">
        <a href="/">Home</a>
        <a href="new-match">New</a>
    </div>
</nav>

<h1>Match score</h1>
    <table style="width:50%">
        <tr>
            <th>Player</th>
            <th>Sets</th>
            <th>Games</th>
            <th>Points</th>
        </tr>
        <tr>
            <td>${player1Name}</td>
            <td>${player1Sets}</td>
            <td>${player1Games}</td>
            <td>${player1Points}</td>
        </tr>
        <tr>
            <td>${player2Name}</td>
            <td>${player2Sets}</td>
            <td>${player2Games}</td>
            <td>${player2Points}</td>
        </tr>
    </table>

<form method="post" action="match-score?uuid=${matchId}">
    <input type="submit" name="player1point" value="Player 1 wins a point">
    <input type="submit" name="player2point" value="Player 2 wins a point">
</form>

</body>
</html>
