<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Tennis Match Tracker</title>
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
        header h1 {
            font-size: 3em;
        }
        section {
            padding: 20px;
            max-width: 800px;
            margin: 0 auto;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin: 20px 0;
            background-color: #008CBA;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
        .btn:hover {
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
    <h1>Welcome to Tennis Match Tracker</h1>
    <p>Track, manage, and score your tennis matches easily!</p>
</header>

<section>
    <h2>Get Started</h2>
    <p>Use the navigation above to start a new match or browse completed matches.</p>
    <a class="btn" href="/new-match">Start a New Match</a>
</section>

<footer>
    <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>

</body>
</html>
