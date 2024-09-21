<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Match Score | Tennis Match Tracker</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f4f4;
    }
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
    }
    th, td {
      padding: 10px;
      text-align: center;
    }
    nav {
      background-color: #333;
      padding: 10px;
      color: white;
    }
    nav a {
      color: white;
      text-decoration: none;
      margin-right: 15px;
    }
    nav a:hover {
      text-decoration: underline;
    }
    header {
      text-align: center;
      margin-bottom: 20px;
    }
    button {
      padding: 10px 20px;
      background-color: #008CBA;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      margin: 5px;
    }
    button:hover {
      background-color: #005f73;
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

<header>
  <h1>Match Score</h1>
</header>

<!-- Display matchEntity score table -->
<table style="width: 50%; margin: 0 auto;">
  <tr>
    <th>Player</th>
    <th>Sets</th>
    <th>Games</th>
    <th>Points</th>
  </tr>
  <tr>
    <td>${matchScore.player1.name}</td>
    <td>${matchScore.player1Sets}</td>
    <td>${matchScore.player1Games}</td>
    <td>${matchScore.player1Points}</td>
  </tr>
  <tr>
    <td>${matchScore.player2.name}</td>
    <td>${matchScore.player2Sets}</td>
    <td>${matchScore.player2Games}</td>
    <td>${matchScore.player2Points}</td>
  </tr>
</table>

<!-- Form to update the score -->
<form method="post" action="/match-score?uuid=${matchScore.matchId}" style="text-align: center; margin-top: 20px;">
  <button type="submit" name="winner" value="${matchScore.player1.id}">Player 1 wins a point</button>
  <button type="submit" name="winner" value="${matchScore.player2.id}">Player 2 wins a point</button>
</form>

<footer>
  <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>
</body>
</html>
