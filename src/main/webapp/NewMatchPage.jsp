<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match | Tennis Match Tracker</title>
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
        header {
            text-align: center;
            padding: 50px 0;
            background-color: #008CBA;
            color: white;
        }
        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        form div {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
        }
        input[type="text"] {
            width: 90%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
        button {
            padding: 10px 20px;
            background-color: #008CBA;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
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
    <a href="new-match">New Match</a>
    <a href="matches">Matches</a>
</nav>

<header>
    <h1>Create a New Match</h1>
</header>

<form method="post" action="/new-match">

    <div >
        <p style="color: brown">${errorMessage}</p>
    </div>

    <div>
        <label for="player1Name">First Player Name:</label>
        <input type="text" id="player1Name" name="player1Name" value="${player1NameValue}">
    </div>
    <div>
        <label for="player2Name">Second Player Name:</label>
        <input type="text" id="player2Name" name="player2Name" value="${player2NameValue}">
    </div>
    <div>
        <button type="submit">Start Match</button>
    </div>
</form>

<footer>
    <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>

</body>
</html>
