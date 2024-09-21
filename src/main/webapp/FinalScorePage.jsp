<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Final Score | Tennis Match Tracker</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f0f2f5;
      color: #333;
    }

    nav {
      background-color: #2c3e50;
      padding: 15px;
      text-align: center;
      box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
    }

    nav a {
      color: #ecf0f1;
      text-decoration: none;
      margin: 0 20px;
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
      margin: 30px 0;
      color: #2c3e50;
    }

    header h1 {
      font-size: 2.5em;
      margin: 0;
      font-weight: 700;
    }

    table {
      width: 60%;
      margin: 20px auto;
      border-collapse: collapse;
      background-color: #fff;
      box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    }

    th, td {
      padding: 15px;
      text-align: center;
      font-size: 1.1em;
    }

    th {
      background-color: #3498db;
      color: #fff;
      font-size: 1.2em;
      text-transform: uppercase;
    }

    td {
      background-color: #ecf0f1;
    }

    tr:nth-child(even) td {
      background-color: #f9f9f9;
    }

    tr:hover td {
      background-color: #dceefb;
      transition: background-color 0.3s ease;
    }

    p {
      text-align: center;
      font-size: 1.4em;
      color: #34495e;
      margin-top: 20px;
    }

    p strong {
      color: #e74c3c;
      font-size: 1.5em;
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

    a.button {
      display: inline-block;
      padding: 10px 20px;
      background-color: #3498db;
      color: #fff;
      text-decoration: none;
      font-size: 1.2em;
      font-weight: bold;
      text-transform: uppercase;
      border-radius: 5px;
      transition: background-color 0.3s ease;
      margin-top: 30px;
    }

    a.button:hover {
      background-color: #1abc9c;
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

<!-- Final match score table -->
<table>
  <tr>
    <th>Player</th>
    <th>Sets Won</th>
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
<p>Winner is <strong>${matchScore.winner.name}</strong>!</p>

<!-- Link back to the homepage -->
<p><a class="button" href="/">Back to Home</a></p>

<footer>
  <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>

</body>
</html>
