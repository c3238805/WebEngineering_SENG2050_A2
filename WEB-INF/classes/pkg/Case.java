package pkg;

import java.io.Serializable;

public class Case implements Serializable{

    private Double value = 0.0;
    private String face;
    private boolean removed = false;

    public Case(Double nValue,int nFace){
        this.value = nValue;
        this.face = Integer.toString(nFace);
    }
    public Case(Double nValue,String nFace){        // for read stored case input
        this.value = nValue;
        this.face = nFace;
    }

    public void setValue(double value){
        this.value = value;
    }

    public Double getValue(){
        return value;
    }

    public void setFace(String face){
        this.face = face;
    }
    public String getFace(){
        return face;
    }

    public boolean Removed()
    {
        return removed;
    }

    public void remove(){
        removed = true;
        face = "$" + Double.toString(value) ;   //set case to $
    }
}