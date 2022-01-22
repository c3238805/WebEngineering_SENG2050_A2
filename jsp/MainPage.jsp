<jsp:useBean id="newGame" class = "pkg.GameClass" scope="session"/>
<!DOCTYPE html>
    <head>
        <title>Main Page jsp</title>
        <link href="/A2/css/style.css" rel = "stylesheet"/>
    </head>

    <body>
        <h1>Deal or No Deal</h1>
        <h2>Welcome to Deal or No Deal Games.</h2>
       
        <form action="NewGame" method="POST" >
            
            <label for="username">Name:</label>
            <input type="text" id="username" name="username" required><br><br>

            <label for="passphrase">passphrase:</label>
            <input type="text" id="passphrase" name="passphrase" required><br><br>

            <button type="submit" name="GameType" value="StartNewGame">Start a New game</button>
            <button type="submit" name="GameType" value="LoadGame" >Load Game</button>
            
        </form>
        

    </body>

</html>