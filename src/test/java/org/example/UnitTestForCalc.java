package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.Assert.assertEquals;

/* UNIT TEST , no Selenium dependency used */

class UnitTestForCalc {
    private Calculator myCalc; // declare a variable

    @BeforeEach
    void setUp(){
        myCalc = new Calculator(); // initialize the variable
        myCalc.switchOn();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_for_calc.csv", numLinesToSkip = 1)
    void testMyCalc(int operand1, char sign, int operand2, int expected) {
        myCalc.add(operand1);
        switch (sign){
            case '+' -> myCalc.add(operand2);
            case '-' -> myCalc.subtract(operand2);
            case '*' -> myCalc.multiply(operand2);
            case '/' -> myCalc.divide(operand2);
        }

        int found_result = myCalc.getValue();
        System.out.println(found_result);
        assertEquals(expected, found_result);
    }
}
