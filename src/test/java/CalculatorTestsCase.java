import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.model.Calculator;

public class CalculatorTestsCase {
    
    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Test to check multiplication of two integers")
    void testMutiplynumbers(){
        int result = calculator.multiply(2, 6);
        assertEquals(result, 12);
    }
    @Test
    @DisplayName("Test to check multiplication of a integer with 0")
    void testMutiplyx0(){
        int result = calculator.multiply(2, 0);
        assertEquals(result, 0);
    }
    @Test
    @DisplayName("Test to check multiplication of two integers, one negative")
    void testMutiplyNegatives(){
        int result = calculator.multiply(-2, 6);
        assertEquals(result, -12);
    }

    @Test
    @DisplayName("Test to check concatenation of two strings")
    void testConcat() {
        String result = calculator.concat("Carlos ", "Ramirez");
        assertEquals(result, "Carlos Ramirez");
    }

    @Test
    @DisplayName("Test to check concatenation of two strings, one is null")
    void testConcatNull() {
        String result = calculator.concat(null, "Ramirez");
        assertEquals("empty",result);
    }

    @Test
    @DisplayName("Test normal summ")
    void TestNormalSumm(){
        double result = calculator.sum(1, 2);
        assertEquals(result, 3);
    }

    @Test
    @DisplayName("Test summ with negatives")
    void TestNegativeSumm(){
        double result = calculator.sum(-1, 2);
        assertEquals(result, 1);
    }

    @Test
    @DisplayName("Test discount")
    void TestDiscount(){
        double result =calculator.discount(100, 20);
        assertEquals(result, 80);
    }

    @Test
    @DisplayName("Test discount 0%")
    void TestDiscount0(){
        double result =calculator.discount(100, 0);
        assertEquals(result, 100);
    }

    @Test
    @DisplayName("Test discount 0%")
    void TestDiscount100(){
        double result =calculator.discount(100, 100);
        assertEquals(result, 0);
    }
    @Test
    @DisplayName("Test discount ilegal amount")
    void TestDiscountPercentLessThanZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            calculator.discount(100, -5);
        });
    }
    @Test
    @DisplayName("Test calcule total")
    void TestCalculeTotal(){
        List<Double> cantidades = new ArrayList<>();
        cantidades.add(4.0);
        cantidades.add(6.0);
        double result = calculator.calculateTotal(cantidades);
        assertEquals(result, 10);
    }
    @Test
    @DisplayName("Test calcule empty list")
    void TestCalculeEmpty(){
        List<Double> cantidades = new ArrayList<>();
        double result= calculator.calculateTotal(cantidades);
        assertEquals(result, 0.0);
    }
}
