package org;

public class Calculator {

    private int value;

    public void switchOn(){
        value = 0;
    }

    public void add(int a){
        value += a;
    }

    public void multiply(int a){
        value *= a;
    }

    public void subtract(int a){
        value -= a;
    }
    public  void divide(int a){
        value /= a;
    }

    public int getValue(){
        return value;
    }
}
