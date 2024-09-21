<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>New Match | Tennis Match Tracker</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f7f9fc;
        }
        nav {
            background-color: #34495e;
            color: white;
            padding: 15px;
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
            background-image: url('tennis-bg-newmatch.jpg'); /* Optional: Background image */
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
        form {
            max-width: 500px;
            margin: 50px auto;
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
            margin-bottom: 8px;
            font-weight: bold;
            color: #34495e;
        }
        input[type="text"] {
            width: 95%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 1em;
        }
        input[type="text"]:focus {
            border-color: #3498db;
            outline: none;
        }
        .error {
            color: #e74c3c;
            margin-bottom: 10px;
            text-align: center;
        }
        button {
            width: 100%;
            padding: 15px;
            background-color: #3498db;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 1.1em;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }
        button:hover {
            background-color: #2980b9;
        }
        footer {
            text-align: center;
            padding: 15px;
            background-color: #34495e;
            color: #ecf0f1;
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
    <a href="new-match">New Match</a>
    <a href="matches">Matches</a>
</nav>

<header>
    <h1>Create a New Match</h1>
</header>

<form method="post" action="/new-match">
    <div class="error">
        ${errorMessage}
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
