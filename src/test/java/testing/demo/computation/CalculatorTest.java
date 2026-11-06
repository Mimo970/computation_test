package testing.demo.computation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

// Runs the tests in the same order as the sample output: add, multiple, substract, divide
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CalculatorTest {

    // Change this to your own username
    private static final String USERNAME = "Alexander Rodriguez";

    private static int count = 0;
    private Calculator calculator;

    // ---------- Lifecycle hooks (Task 5) ----------

    @BeforeAll
    static void beforeAll() {
        System.out.printf("[Before All] Calculator Test suite starting ... by %s%n%n", USERNAME);
    }

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        calculator = new Calculator();
        System.out.printf("[Before Each] Starting Test #%d: %s%n", ++count, testInfo.getDisplayName());
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.printf("[After Each] Finished Test #%d: %s%n%n", count, testInfo.getDisplayName());
    }

    @AfterAll
    static void afterAll() {
        System.out.printf("%n[After All] completed %d test invocations by %s.%n", count, USERNAME);
    }

    // ---------- Task 1: add() with @MethodSource ----------

    @Order(1)
    @ParameterizedTest(name = "{0} + {1} = {2}")
    @DisplayName("Add two numbers")
    @MethodSource("provideAddData")
    void add_twoNumbers(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b));
    }

    static Stream<Arguments> provideAddData() {
        return Stream.of(
            Arguments.of(100, 2, 102),
            Arguments.of(100, -2, 98),
            Arguments.of(-100, 2, -98),
            Arguments.of(-100, -2, -102)
        );
    }

    // ---------- Task 3: multiple() with @CsvFileSource ----------

    @Order(2)
    @ParameterizedTest(name = "{0} * {1} = {2}")
    @DisplayName("Multiple two numbers")
    @CsvFileSource(resources = "/data/multiply.csv")
    void multiple_twoNumbers(int a, int b, int expected) {
        assertEquals(expected, calculator.multiple(a, b));
    }

    // ---------- Task 2: substract() with @CsvSource ----------

    @Order(3)
    @ParameterizedTest(name = "{0} - {1} = {2}")
    @DisplayName("Substract two numbers")
    @CsvSource({
        "100, 2, 98",
        "100, -2, 102",
        "-100, 2, -102",
        "-100, -2, -98"
    })
    void substract_twoNumbers(int a, int b, int expected) {
        assertEquals(expected, calculator.substract(a, b));
    }

    // ---------- Task 4: divide() negative test ----------

    @Order(4)
    @Test
    void divide_byZero() {
        assertThrows(IllegalArgumentException.class, () -> calculator.divide(10, 0));
    }
}

