package pkg;
import java.util.List;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class GameClass implements Serializable{
    private AtomicInteger counter;

    private Case[] cases = new Case[12];
    private String caseFace;    // for case's face 
    private Double caseValue;   // for case's value
    private int count = 0;      // count the number of case opened
    private double total;   // calulate the totle case value been opened.
    private int roundNo;
    private double maxValue;        //get the largest value of case that is not opened. 

    public GameClass(){
        caseSetup();
        setroundNo();
        this.counter = new AtomicInteger(0);             
    }

    public void ExistingCases(Case [] c){
        this.cases = c.clone();
               
    }

    public void caseSetup() {

        List<Double> values = Arrays.asList(0.5, 1.0, 10.0, 100.0, 200.0, 500.0, 1000.0, 2000.0, 5000.0,10000.0,20000.0,50000.0);
        Collections.shuffle(values);        // shuffle the cases

        for (int i = 0; i < cases.length; i++) {    //add the case back in to case[i]
            Double amount = values.get(i);
            cases[i] = new Case(amount, i + 1);
        }
    }
    // to display the case face.
    public String showCase(int i ){
        // return the input number case's face. 
        caseFace =  cases[i].getFace() ;   
        return caseFace;     
    }
    public double getValue(int i ){
        // return the input number case's value. 
        caseValue = cases[i].getValue() ;   
        return caseValue;     
    }
    

    //calculate the total amout for the case been revaled.
    public double totalAmount(){
        total = 0;  
        for (int j = 0; j < cases.length; j++) {
            if (cases[j].getFace() .contains("$")) {
                total += cases[j].getValue();
            }
        }
        return total;
    }
   
    public int notYetopend(){
        count = 0;
        for (int j = 0; j < cases.length; j++) {
            if (cases[j].getFace().contains("$") ) {
                count ++;
            }
        }
        count = 12-count;
        return count;
    }
    public int notOpendCurrentRound(){
        int currentNotOpend = 0;
        if(getroundNo() == 1 ){
            currentNotOpend = notYetopend()-8;
            
        }
        if(getroundNo() == 2 ){            
            currentNotOpend = notYetopend()-5;
           
        }
        if(getroundNo() == 3 ){
            currentNotOpend = notYetopend()-3;
           
        }
        if(getroundNo() == 4 ){
            currentNotOpend = notYetopend()-2;
            
        }
        if(getroundNo() == 5 ){
            currentNotOpend = notYetopend()-1;
            
        }
        return currentNotOpend;
    }

    // for case that been selected then remove case in the class
    public void setcaseNo(int caseNo){
        cases[caseNo].remove();  //set the case's face to "$"
    }

    //bank offer method 
    public double bankOffer(){
        double overallAmount = 0;
        double bankOffer = 0;
        for (int j = 0; j < cases.length; j++) {
            overallAmount += cases[j].getValue();    // calculate the overallAmnount for all 12 case.                       
        }
        bankOffer = (overallAmount - totalAmount()) / notYetopend() ;  // remaining cases amount
        return bankOffer;
    }


    //count the round 
    public void setroundNo() {
        int numOpenCase = 0;
        for (int j = 0; j < cases.length; j++) {
            if (cases[j].getFace().contains("$")) {
                numOpenCase ++;
            }
        }
        // 4 cases for the 1 round
        if(numOpenCase <=4){    
            roundNo = 1;
        }
        // 3 cases for the 2 round
        else if(numOpenCase <=7){    
            roundNo = 2;
        }
        // 2 cases for the 3 round
        else if(numOpenCase <=9){    
            roundNo = 3;
        }
        // 1 cases for the 4 round
        else if(numOpenCase <=10){    
            roundNo = 4;
        }
        // 4 cases for the first round
        else if(numOpenCase <=11){    
            roundNo = 5;
        }
        else roundNo = 0;  // only 5 round and maximent 11 case can be opend, 
                        //return 0 for other opreation to call.
    }

    public int getroundNo(){
        return roundNo;
    }
    public void setnextround(){
        
        if(roundNo == 5){
            roundNo = 0;
        }
        else {
            roundNo = roundNo+1;
        }
    }


    //if reach to certain number of case , set call the bank to true 
    public boolean callbank(){
        if(notYetopend() == 8 && getroundNo() == 1){
            return true ; 
        }
        if(notYetopend() == 5 && getroundNo() == 2){
            return true ; 
        }
        if(notYetopend() == 3 && getroundNo() == 3){
            return true ; 
        }
        if(notYetopend() == 2 && getroundNo() == 4){
            return true ; 
        }
        if(notYetopend() == 1 && getroundNo() == 5){
            return true ; 
        }
        else 
            return false ;
    }

    public double getMaxCaseValue(){
        maxValue = 0.5  ;// set the smallest price 0.5 to default, then use it to compare with other case
        for (int j = 0; j < cases.length; j++) {
            if (!cases[j].getFace().contains("$")) {    // get un-opened case
                if(cases[j].getValue()>= maxValue){
                    maxValue = cases[j].getValue();
                }
                
                
            }
        }
        return maxValue;
    }

    // for outprint the number of time the page been accessed
    public int getNextValue() {
        return this.counter.incrementAndGet();
    }

    public void resetGame(){
        caseSetup();
        setroundNo();
        count = 0;
        total=0;
        
    }

}