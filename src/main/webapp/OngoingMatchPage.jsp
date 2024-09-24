<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Match Score | Tennis Match Tracker</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f4f7f6;
      color: #333;
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
      font-size: 14px;
      transition: color 0.3s ease;
    }

    nav a:hover {
      color: #1abc9c;
    }

    header {
      text-align: center;
      padding: 60px 0;
      background-color: #3498db;
      color: white;
      background-image: url('tennis-bg-score.jpg'); /* Optional: Add a relevant background image */
      background-size: cover;
      background-position: center;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    header h1 {
      font-size: 3em;
      margin: 0;
      font-weight: 700;
      text-shadow: 1px 1px 5px rgba(0, 0, 0, 0.2);
    }

    table {
      width: 80%;
      margin: 30px auto;
      border-collapse: collapse;
      box-shadow: 0 3px 6px rgba(0, 0, 0, 0.1);
    }

    th, td {
      border: 1px solid #ddd;
      padding: 15px;
      text-align: center;
      font-size: 1.1em;
    }

    th {
      background-color: #34495e;
      color: white;
    }

    td {
      background-color: #ecf0f1;
    }

    form {
      text-align: center;
      margin-top: 30px;
    }

    button {
      padding: 15px 30px;
      background-color: #e74c3c;
      color: white;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      font-size: 1.1em;
      font-weight: bold;
      margin: 0 10px;
      transition: background-color 0.3s ease;
    }

    button:hover {
      background-color: #c0392b;
    }

    footer {
      text-align: center;
      padding: 15px;
      background-color: #2c3e50;
      color: #ecf0f1;
      position: fixed;
      width: 100%;
      bottom: 0;
      box-shadow: 0 -2px 5px rgba(0, 0, 0, 0.1);
    }

    footer p {
      margin: 0;
      font-size: 14px;
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

<!-- Display match score table -->
<table>
  <tr>
    <th>Player</th>
    <th>Sets Won</th>
    <th>Games Won</th>
    <th>Points</th>
  </tr>
  <tr>
    <td>${matchScore.player1.name}</td>
    <td>${matchScore.player1.sets}</td>
    <td>${matchScore.player1.games}</td>
    <td>${matchScore.player1.points}</td>
  </tr>
  <tr>
    <td>${matchScore.player2.name}</td>
    <td>${matchScore.player2.sets}</td>
    <td>${matchScore.player2.games}</td>
    <td>${matchScore.player2.points}</td>
  </tr>
</table>

<!-- Form to update the score -->
<form method="post" action="/match-score?uuid=${matchScore.matchId}">
  <button type="submit" name="winner" value="${matchScore.player1.id}">Player 1 Scores</button>
  <button type="submit" name="winner" value="${matchScore.player2.id}">Player 2 Scores</button>
</form>

<footer>
  <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>

</body>
</html>
