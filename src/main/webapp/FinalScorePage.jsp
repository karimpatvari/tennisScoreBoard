<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Final Score | Tennis Match Tracker</title>
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
    p {
      text-align: center;
      font-size: 1.2em;
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
  <h1>Final Match Score</h1>
</header>

<!-- Final matchEntity score table -->
<table style="width: 50%; margin: 0 auto;">
  <tr>
    <th>Player</th>
    <th>Sets</th>
  </tr>
  <tr>
    <td>${matchScore.player1.name}</td>
    <td>${matchScore.player1Sets}</td>
  </tr>
  <tr>
    <td>${matchScore.player2.name}</td>
    <td>${matchScore.player2Sets}</td>
  </tr>
</table>

<!-- Display the winner -->
<p>Winner is <strong>${matchScore.winner.name}</strong></p>

<!-- Link back to the homepage -->
<p><a href="/">Back to Home</a></p>

<footer>
  <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>
</body>
</html>
