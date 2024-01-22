package com.coxey.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorHelperTest {
    private CalculatorHelper calculatorHelper;
    private CalculatorHelper calculatorHelperSpy;

    @BeforeEach
    void setUp() {
        calculatorHelper = new CalculatorHelper();
        calculatorHelperSpy = spy(CalculatorHelper.class);
    }

    @Test
    void validInfixToPostfixConvertation() {
        assertAll(
                () -> assertEquals("2  2  2 * +", calculatorHelper.toPostfix("2 + 2 * 2")),
                () -> assertEquals("5  8  15 -  24 * +", calculatorHelper.toPostfix("5 + (8 - 15) * 24"))

        );
    }

    @Test
    void invalidInfixToPostfixConvertation() {
        when(calculatorHelperSpy.toPostfix("2 + 2")).thenReturn("2 + 2");
        assertAll(
                () -> assertNotEquals("2 2 +", calculatorHelperSpy.toPostfix("2 + 2"))
        );
    }

    @Test
    void methodToPostfixThrowNoOperatorException() {
        boolean exceptionThrow = false;
        try {
            calculatorHelper.toPostfix("2 2");
        } catch (NoOperatorException e) {
            exceptionThrow = true;
        }
        assertTrue(exceptionThrow);
    }

    @Test
    void validOperator() {
        assertAll(
                () -> assertTrue(calculatorHelper.isOperator("+")),
                () -> assertTrue(calculatorHelper.isOperator("*")),
                () -> assertTrue(calculatorHelper.isOperator("^"))
        );
    }

    @Test
    void invalidOperator() {
        assertAll(
                () -> assertFalse(calculatorHelper.isOperator("&")),
                () -> assertFalse(calculatorHelper.isOperator("7")),
                () -> assertFalse(calculatorHelper.isOperator(")"))
        );
    }
}