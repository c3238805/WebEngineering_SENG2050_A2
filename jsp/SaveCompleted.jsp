<jsp:useBean id="newGame" class = "pkg.GameClass" scope="session"/>

<!DOCTYPE html>
    <head>
        <title>Save Completed </title>
        <link href="/A2/css/style.css" rel = "stylesheet"/>
    </head>

    <body>
        <h1>Save Completed </h1> 
        
        <hr>

       <p>Back to Main Page </p>
       <form action="MainPage" method="POST">
            
          <button> Back to Main Page </button>
        </form>
        <p>
            This page has been accessed <c:out>${accessed}</c:out> time(s).
        </p>
    </body>

</html>