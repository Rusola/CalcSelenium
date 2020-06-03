package a.data_driven;

public class Calculator {
    private int value;

    /* default constructor will be used */

    public void switchOn(){
        value = 0; // default value when calc starts
    }
    public void subtract (int a){
        value -= a;
    }

    public void multiply (int a){
        value *= a;
    }

    public void divide (int a){
        value /= a;
    }

    public void add (int a){
        value += a;
    }

    public int getValue() {
        return value;
    }

}
