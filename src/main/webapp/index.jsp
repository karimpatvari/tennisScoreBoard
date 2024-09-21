<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home | Tennis Match Tracker</title>
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
            padding: 15px;
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
            padding: 100px 0;
            background-color: #3498db;
            color: white;
            background-image: url('tennis-bg.jpg'); /* Optional: Add a background image related to tennis */
            background-size: cover;
            background-position: center;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
        }

        header h1 {
            font-size: 3.5em;
            margin: 0;
            font-weight: 700;
            text-shadow: 1px 1px 5px rgba(0, 0, 0, 0.2);
        }

        header p {
            font-size: 1.5em;
            margin: 15px 0 0;
            font-weight: 300;
        }

        section {
            padding: 40px;
            max-width: 900px;
            margin: 0 auto;
            text-align: center;
        }

        section h2 {
            font-size: 2.2em;
            margin-bottom: 20px;
            color: #2c3e50;
        }

        section p {
            font-size: 1.3em;
            line-height: 1.5em;
            color: #7f8c8d;
        }

        .btn {
            display: inline-block;
            padding: 15px 30px;
            margin: 30px 0;
            background-color: #e74c3c;
            color: white;
            text-decoration: none;
            font-size: 1.2em;
            font-weight: bold;
            text-transform: uppercase;
            border-radius: 5px;
            transition: background-color 0.3s ease;
        }

        .btn:hover {
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
    <h1>Welcome to Tennis Match Tracker</h1>
    <p>Track, manage, and score your tennis matches with ease!</p>
</header>

<section>
    <h2>Get Started</h2>
    <p>Whether you're tracking a single match or keeping tabs on multiple matches, Tennis Match Tracker has got you covered. Use the navigation above to get started.</p>
    <a class="btn" href="/new-match">Start a New Match</a>
</section>

<footer>
    <p>&copy; 2024 Tennis Match Tracker. All Rights Reserved.</p>
</footer>

</body>
</html>
