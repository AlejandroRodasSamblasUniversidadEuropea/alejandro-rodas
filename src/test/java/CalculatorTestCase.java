import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.model.Calculator;

public class CalculatorTestCase {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Multiply two positive integers")
    void testMutiplyNumbers() {
        assertEquals(12, calculator.multiply(2, 6));
    }

    @Test
    @DisplayName("Concatenate two valid strings")
    void testConcat() {
        assertEquals("Carlos Ramirez", calculator.concat("Carlos ", "Ramirez"));
    }

    @Test
    @DisplayName("Concat with first param null = empty")
    void testConcatFirstNull() {
        assertEquals("empty", calculator.concat(null, "Ramirez"));
    }

    @Test
    @DisplayName("Sum normal")
    void testNormalSum() {
        assertEquals(3, calculator.sum(1, 2));
    }

    @Test
    @DisplayName("Discount 20%")
    void testDiscount() {
        assertEquals(80, calculator.discount(100, 20));
    }

    @Test
    @DisplayName("Calculate total of valid list")
    void testCalculateTotal() {
        List<Double> cantidades = new ArrayList<>();
        cantidades.add(4.0);
        cantidades.add(6.0);
        assertEquals(10, calculator.calculateTotal(cantidades));
    }
}
