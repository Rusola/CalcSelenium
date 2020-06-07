package a.data_driven;

import org.Calculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

/* UNIT TEST , no Selenium dependency used */

class UnitTestForCalc {
    private Calculator calc; // declare a variable

    @BeforeEach
    void setUp(){
        calc = new Calculator(); // initialize the variable
        calc.switchOn();
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data_for_calc.csv", numLinesToSkip = 1)
    void testMyCalc(int operand1, char sign, int operand2, int expected) {
        calc.add(operand1);
        switch (sign){
            case '+' -> calc.add(operand2);
            case '-' -> calc.subtract(operand2);
            case '*' -> calc.multiply(operand2);
            case '/' -> calc.divide(operand2);
        }

        int found_result = calc.getValue();
        System.out.println(found_result);
        assertEquals(expected, found_result);
    }

    @Test // тестировать правильные ли выпадают ошибки нужно отделтными непараметризированым тестами
    void testExceptions() {
        // проверяю выпадет ли ошибка при делении 1/0
        calc.add(1);
        assertThrows(ArithmeticException.class, // спец assertion,к пройдет толко если выбросится ошибка указанного класса
                () -> {calc.divide(0);}
                ); //  2nd параметр это действия которые приводят к выбросу exception(запись вида lambda expression
    }
}
