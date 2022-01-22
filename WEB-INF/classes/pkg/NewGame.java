package pkg;
import java.io.*;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/NewGame"})
public class NewGame extends HttpServlet{
     private GameClass newGame = new GameClass();   // get GameClass setup
     private  GameManager store = new GameManager();
     private  boolean isNewGame; 

	public void doGet (HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
      
        
    }
    @Override
    public void doPost (HttpServletRequest request , HttpServletResponse response) throws ServletException , IOException {
        

        HttpSession session=request.getSession();
        session=request.getSession();

        session.setAttribute("newGame",newGame);
        session.setAttribute("store",store);
        GameClass newGame = (GameClass) session.getAttribute("newGame");
        GameManager store = (GameManager) session.getAttribute("store");

        if(request.getParameter("username")!=null && request.getParameter("passphrase")!=null){
                store.setUserName(request.getParameter("username"));        //store user name and passphrase into GameManager
                store.setPassphrase(request.getParameter("passphrase"));
            }
                
//================================================================================= 
        if(request.getParameter("GameType")!=null&&request.getParameter("GameType").equals("StartNewGame")){     // if user want to start a new game 
            isNewGame = true;
            
        }      
        else if (request.getParameter("GameType")!=null&&request.getParameter("GameType").equals("LoadGame")){   // if user want to load game
            isNewGame = false;
            
        }

//=================================================================================  
        // only if user already have game stored, and want to start a new game.             
        if((store.checkuser(store.getUserName(),store.getPassphrase()) == true)&& isNewGame == true  ){
                          
            store.deleteData(store.getUserName(),store.getPassphrase());// delete existing data from database 
            store.restexist();  //reset the gameManager exist.
            
        }
        // if user want to load game
        else if ((store.checkuser(store.getUserName(),store.getPassphrase()) == true)&& isNewGame == false ){
             
            store.read(store.getUserName(),store.getPassphrase());      //pass username and passphrase into parameter
            newGame.ExistingCases(store.getStoreCase());        // set case to stored cases
            newGame.setroundNo();
            newGame.setnextround();
            store.deleteData(store.getUserName(),store.getPassphrase());// delete existing data from database 
            store.restexist();  //reset the gameManager exist.
            store.restuserexist(); 
            isNewGame = true;   // after deleted the data, now the user consider as new game
        }
        else if(((store.checkuser(store.getUserName(),store.getPassphrase()) == false) && (store.checkusername(store.getUserName()) == true))
                || (store.checkuser(store.getUserName(),store.getPassphrase()) == false)&& isNewGame == false){// if user doesnt exist and want to load game OR only username match===> show error page
            // if user name exist, but not match the its Passphrase
            store = new GameManager();
            request.getSession().invalidate();  // close session
            newGame.resetGame();        // reset new case
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/ErrorPage.jsp");
            dispatcher.forward(request, response);
        }
        
        session.setAttribute("GameClass",newGame); 

        if(request.getParameter("caseNo") != null ){ // check if any case been open
            int toint = Integer.parseInt(request.getParameter("caseNo"));  
            newGame.setcaseNo(toint);
        } 
        
        
        for(int i =0;i <12;i++){        // setup the attribute for showing the case's face
            session.setAttribute("case"+i ,newGame.showCase(i));
            
        }   
            
        session.setAttribute("username",store.getUserName());
        session.setAttribute("passphrase",store.getPassphrase());

        for(int i=0; i <12 ; i++){
            session.setAttribute("timeOutStore"+i,newGame.showCase(i));
            session.setAttribute("timeOutStoreValue"+i,newGame.getValue(i));
        }
        session.setAttribute("accessed",newGame.getNextValue());     // to show how many time the page been accessed.
        session.setAttribute("bankOffer" ,newGame.bankOffer());     // set bank offer                 
        session.setAttribute("notYetopend" ,newGame.notYetopend()); //setAttribute for display number of cases not yet opend                
        session.setAttribute("notopendCurrent" ,newGame.notOpendCurrentRound()); //setAttribute for display number of cases not yet opend in current round
        session.setAttribute("roundNo" ,newGame.getroundNo());  // for display the round number
        session.setAttribute("largest" ,newGame.getMaxCaseValue());  // for display the largest amount unrevealed
                   
        
            if(newGame.callbank() == true && newGame.getroundNo()!= 5  ){   
                newGame.setnextround(); // call the bank and move to next round
                RequestDispatcher dispatcher2 =getServletContext().getRequestDispatcher("/jsp/BankOffer.jsp");
                dispatcher2.forward(request, response);
                
            }
            else if (request.getParameter("checksave") !=null){
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/SaveGame.jsp");
                dispatcher.forward(request, response);
            }
            else if (request.getParameter("deal") !=null){  //if user deal
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/FinishGame.jsp");
                dispatcher.forward(request, response);
                
            }
            else if ((request.getParameter("finish") !=null) ){  //if user deal                
                request.getSession().invalidate();  // close session
                newGame.resetGame();        // reset new case
                
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/MainPage");
                dispatcher.forward(request, response);
            }
            else if (request.getParameter("saveGame") !=null){
                store.insert(store.getUserName(),store.getPassphrase(),newGame);// to save game into sql
                request.getSession().invalidate();  // close session
                newGame.resetGame();        // reset new case
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/SaveCompleted.jsp");
                dispatcher.forward(request, response);
                
                            
            }  
            else if (( newGame.getroundNo() == 5 &&  newGame.notOpendCurrentRound() == 0)){
                           
               RequestDispatcher dispatcher2 =getServletContext().getRequestDispatcher("/jsp/FinishGame.jsp");
               dispatcher2.forward(request, response);
               
            } 
            else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/NewGame.jsp");
                dispatcher.forward(request, response);
            } 
        }
                            
        

  

       
    
	

}
