<jsp:useBean id="newGame" class = "pkg.GameClass" scope="session"/>
<!DOCTYPE html>
    <head>
        <title>Start of New Game</title>
        <link href="/A2/css/style.css" rel = "stylesheet"/>
        
        <script type="text/javascript">    // disable user to go back to the game to change any case value.
            function disableBack() { window.history.forward(); }
            setTimeout("disableBack()", 0);
            window.onunload = function () { null };
        </script>

    </head>

    <body>

        <h1>Start of New Game</h1> 

        <p>Current round : <c:out>${roundNo}</c:out></p>   
        <p>briefcases are yet to be opened in the current round : <c:out>${notopendCurrent}</c:out></p>
        
        <form action=NewGame method=POST >       
            <table class ="center">
                <tr>
                    <td>
                        <button name="caseNo" type="submit" value="0"  id= "case0"><c:out>${case0}</c:out></button>
                        <button name="caseNo" type="submit" value="1"  id= "case1"><c:out>${case1}</c:out></button>
                        <button name="caseNo" type="submit" value="2"  id= "case2"><c:out>${case2}</c:out></button>
                        <button name="caseNo" type="submit" value="3"  id= "case3"><c:out>${case3}</c:out></button>
                    </td>                
                </tr>
                <tr>                
                    <td>
                        <button name="caseNo" type="submit" value="4"  id= "case4"><c:out>${case4}</c:out></button>
                        <button name="caseNo" type="submit" value="5"  id= "case5"><c:out>${case5}</c:out></button>
                        <button name="caseNo" type="submit" value="6"  id= "case6"><c:out>${case6}</c:out></button>
                        <button name="caseNo" type="submit" value="7"  id= "case7"><c:out>${case7}</c:out></button>
                    </td>                
                </tr>
                <tr>               
                    <td>
                        <button name="caseNo" type="submit" value="8"  id= "case8"><c:out>${case8}</c:out></button>
                        <button name="caseNo" type="submit" value="9"  id= "case9"><c:out>${case9}</c:out></button>
                        <button name="caseNo" type="submit" value="10"  id= "case10"><c:out>${case10}</c:out></button>
                        <button name="caseNo" type="submit" value="11"  id= "case11"><c:out>${case11}</c:out></button>
                    </td>
                </tr>
            </table>
        </form>
        <p>
            This page has been accessed <c:out>${accessed}</c:out> time(s).
        </p>

        <hr>
        
        


    </body>

</html>