<jsp:useBean id="newGame" class = "pkg.GameClass" scope="session"/>
<!DOCTYPE html>
    <head>
        <title>Error Page</title>
        <link href="/A2/css/style.css" rel = "stylesheet"/>
    </head>

    <body>
        <h1>Error</h1>
        <h2>The User name or passphrase provided does not match!</h2>

        <p>To start a new Game , please enter new User name and passphrase.</p>
        <p>Or Enter correct username and passphrase to Start a new Game or Load Game.</p>

        
        <form action="MainPage" method="POST" >
            
            <button> Back to Main Page </button>
        </form>
        
    </body>

</html>