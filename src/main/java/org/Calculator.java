package org;
/*1) Поставить курсор на имя класса( это важно) и потом нажать alt+Enter or 2) Shift+COMMAND+T*/
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
