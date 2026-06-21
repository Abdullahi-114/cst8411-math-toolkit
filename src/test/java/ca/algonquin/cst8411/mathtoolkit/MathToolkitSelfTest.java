package ca.algonquin.cst8411.mathtoolkit;

/**
 * Runs dependency-free checks against the public library API.
 */
public final class MathToolkitSelfTest {
    private static int checks;

    // Prevents construction of the test runner.
    private MathToolkitSelfTest() {
    }

    /** Executes all checks and fails the build if a result is incorrect. */
    public static void main(String[] args) {
        assertEquals(5.0, MathToolkit.add(2.0, 3.0), "addition");
        assertEquals(2.0, MathToolkit.subtract(5.0, 3.0), "subtraction");
        assertEquals(6.0, MathToolkit.multiply(2.0, 3.0), "multiplication");
        assertEquals(5.0, MathToolkit.divide(10.0, 2.0), "division");
        assertThrows(() -> MathToolkit.divide(10.0, 0.0), "division by zero");
        assertEquals(1.0, MathToolkit.modulus(10.0, 3.0), "modulus");
        assertThrows(() -> MathToolkit.modulus(10.0, 0.0), "modulus by zero");
        assertEquals(8.0, MathToolkit.power(2.0, 3.0), "power");
        assertEquals(5.0, MathToolkit.squareRoot(25.0), "square root");
        assertThrows(() -> MathToolkit.squareRoot(-25.0), "negative square root");
        assertEquals(12.5, MathToolkit.percentage(25.0, 200.0), "percentage");
        assertThrows(() -> MathToolkit.percentage(25.0, 0.0), "percentage with zero whole");

        System.out.println("All " + checks + " checks passed.");
    }

    // Confirms that an operation returns the expected value.
    private static void assertEquals(double expected, double actual, String label) {
        checks++;
        if (Double.compare(expected, actual) != 0) {
            throw new AssertionError(label + ": expected " + expected + " but received " + actual);
        }
    }

    // Confirms that an invalid operation throws ArithmeticException.
    private static void assertThrows(Runnable operation, String label) {
        checks++;
        try {
            operation.run();
        } catch (ArithmeticException expected) {
            return;
        }
        throw new AssertionError(label + ": expected ArithmeticException");
    }
}

