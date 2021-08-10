package by.academy.jc.klimakhovich_ht2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCalculator {
    @Test
    void testCalc() {
        Calculator classUnderTest = new Calculator();
        assertEquals(11.7, classUnderTest.sum(5, 6.7), "sum should have 11.7");
        assertEquals(14, classUnderTest.multi(3.5, 4), "multiplication should have 14");
    }
}
