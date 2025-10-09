package testing.demo.computation;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private static int count = 0;
    private Calculator calculator;

    @BeforeAll
    static void beforeAll() {
        System.out.printf("[BeforeAll] Test suite starting...%n%n");
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        calculator = new Calculator();
        System.out.printf("[BeforeEach] Starting Test #%d: %s%n", ++count, testInfo.getDisplayName());
    }

    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("Add with CSV data")
    @CsvFileSource(resources = "/data/add.csv") 
    void add_twoNumbers(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    @Test
    @DisplayName("Divide by zero throws IllegalArgumentException")
    void divide_byZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        // Optionally null out large resources
        calculator = null;
        System.out.printf("[AfterEach] Finished Test #%d: %s%n%n", count, testInfo.getDisplayName());
    }

    @AfterAll
    static void afterAll() {
        System.out.printf("%n[AfterAll] Completed %d test invocation(s).%n", count);
    }
}

