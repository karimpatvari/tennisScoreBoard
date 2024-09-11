<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>New Match</title>
</head>
<body>
<nav>
    <div class="nav-items">
        <a href="/">Home</a>
        <a href="new-match">New</a>
    </div>
</nav>
    <p>New match</p>

    <form method="post" action="new-match">
        <div>
            <label for="player1Name">First player name:</label>
            <input type="text" id="player1Name" name="player1Name" >
        </div>

        <div>
            <label for="player2Name">Second player name:</label>
            <input type="text" id="player2Name" name="player2Name">
        </div>

        <div>
            <button type="submit">Start</button>
        </div>
    </form>

</body>
</html>
