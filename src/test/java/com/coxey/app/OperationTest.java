package com.coxey.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OperationTest {
    private Operation operation;

    @BeforeEach
    void setUp() {
        operation = new Operation();
    }

    @ParameterizedTest
    @CsvSource({
            "1, +, 1, 2",
            "1, -, 1, 0",
            "5, *, 5, 25",
            "10, /, 2, 5",
            "8, ^, 2, 64"
    }
    )
    void validApplyMethod(Double value1, String operator, Double value2, Double result) {
        operation.setOperation(operator);
        assertEquals(result, operation.apply(value1, value2));
    }

    @ParameterizedTest
    @ValueSource(doubles = {5.0, 6.0, 7.0})
    void validApplyMethodAnotherTest(Double value) {
        operation.setOperation("*");
        assertEquals(value * value, operation.apply(value, value));
    }

    @Test
    void invalidSetOperationMethod() {
        assertThrows(
                NoOperatorException.class,
                () -> operation.setOperation("&")
                );
    }
}