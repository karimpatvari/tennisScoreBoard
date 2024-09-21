<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Error | Tennis Match Tracker</title>
  <style>
    body {
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background-color: #f9f9f9;
      margin: 0;
      padding: 20px;
    }
    .error-container {
      max-width: 600px;
      margin: 0 auto;
      padding: 20px;
      background-color: #fff;
      border: 1px solid #ddd;
      border-radius: 8px;
      box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
      text-align: center;
    }
    h1 {
      color: #d9534f;
      font-size: 2em;
    }
    p {
      color: #333;
      font-size: 1.1em;
    }
    a {
      text-decoration: none;
      color: #0275d8;
      font-weight: bold;
      transition: color 0.3s;
    }
    a:hover {
      color: #0056b3;
    }
    .btn {
      display: inline-block;
      padding: 10px 15px;
      margin-top: 20px;
      background-color: #0275d8;
      color: white;
      border-radius: 5px;
      text-decoration: none;
      transition: background-color 0.3s;
    }
    .btn:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="error-container">
  <h1>Error</h1>
  <p>Oops! Something went wrong while processing your request. Please try again later or contact support if the issue persists.</p>
  <p>
    <a class="btn" href="/">Go back to the main page</a>
  </p>
</div>
</body>
</html>
