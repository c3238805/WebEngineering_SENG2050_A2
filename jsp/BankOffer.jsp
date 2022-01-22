<jsp:useBean id="newGame" class = "pkg.GameClass" scope="session"/>

<!DOCTYPE html>
    <head>
        <title>Bank Offer </title>
        <link href="/A2/css/style.css" rel = "stylesheet"/>
    </head>

    <body>
        <h1>Bank Offer</h1> 
        
        <p>Current round :<c:out>${roundNo}</c:out> </p>

        <p>BankOffer : <c:out>${bankOffer}</c:out>   </p>
        

        <hr>
        <label>largest amount unrevealed : <c:out>${largest}</c:out> </label><br>
        <label>briefcases are yet to be opened in the whole Game : <c:out>${notYetopend}</c:out></label>

        <form action="NewGame" method="Post"  >
            <input type="hidden" id="checksave" name="checksave" value="checksave"><br> 
           <button>NO DEAL</button>     

       </form>

       <form action="NewGame" method="POST">
          <input type="hidden" id="deal" name="deal" value="deal"><br>  
          <button> DEAL </button>
        </form>

        <p>
            This page has been accessed <c:out>${accessed}</c:out> time(s).
        </p>
    </body>

</html>