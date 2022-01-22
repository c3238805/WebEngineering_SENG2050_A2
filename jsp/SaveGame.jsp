<jsp:useBean id="newGame" class = "pkg.GameClass" scope="session"/>

<!DOCTYPE html>
    <head>
        <title>Save Game </title>
        <link href="/A2/css/style.css" rel = "stylesheet"/>
    </head>

    <body>
        <h1>Do you want to save Current Game ? </h1> 
        
        <p>Current round : <c:out>${roundNo}</c:out></p>
        <p>briefcases are yet to be opened in the whole Game : <c:out>${notYetopend}</c:out></p>
        <p>
            This page has been accessed <c:out>${accessed}</c:out> time(s).
        </p>
        <hr>

        <p>Do you want to Save current Game ?    </p>
        <form action="NewGame" method="POST"  >
            <input type="hidden" id="saveGame" name="saveGame" value="saveGame"><br> 
            <button type="submit" >Save Game</button>     

       </form>

       <p>Continue Game and back to Game Page </p>
       <form action="NewGame" method="POST">
        <input type="hidden" id="backtoGame" name="backtoGame" value="backtoGame"><br>  
          <button> Back to Game Page </button>
        </form>

    </body>

</html>