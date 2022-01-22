<jsp:useBean id="newGame" class = "pkg.GameClass" scope="session"/>

<!DOCTYPE html>
    <head>
        <title>Game Finish </title>
        <link href="/A2/css/style.css" rel = "stylesheet"/>
    </head>

    <body>
        <h1>Game Finish</h1> 
        
        <p>Current round : <c:out>${roundNo}</c:out></p>

        <p>The Price you won at Final  :  <c:out>${bankOffer}</c:out>  </p>
        
        
        <hr>

        <form action="NewGame" method="POST"  >
            <input type="hidden" id="finish" name="finish" value="finish"><br>
           <button>Back to Main Page</button>

       </form>

       <p>
        This page has been accessed <c:out>${accessed}</c:out> time(s).
        </p>

    </body>

</html>

