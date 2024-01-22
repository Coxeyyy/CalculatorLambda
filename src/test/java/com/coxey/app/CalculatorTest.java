package com.coxey.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CalculatorTest {
    private Calculator calculator;
    private CalculatorHelper calculatorHelper;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
        calculatorHelper = mock(CalculatorHelper.class);
    }

    @Test
    void validExpression() {
        assertAll(
                () -> assertEquals(6.0, calculator.solveExpression("2 + 2 * 2")),
                () -> assertEquals(-163.0, calculator.solveExpression("5 + (8 - 15) * 24")),
                () -> assertEquals(-331.0, calculator.solveExpression("5 + ((8 - 15) * 2) * 24")),
                () -> assertEquals(10.0, calculator.solveExpression("2 + 2 * 2 ^ 2"))
        );
    }

    @Test
    void invalidExpression() {
        when(calculatorHelper.toPostfix(anyString())).thenThrow(NoOperatorException.class);
        assertThrows(NoOperatorException.class, () -> calculatorHelper.toPostfix("2 2"));
    }

}