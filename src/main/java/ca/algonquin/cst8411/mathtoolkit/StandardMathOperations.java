package ca.algonquin.cst8411.mathtoolkit;

/**
 * Provides the standard implementation of all toolkit operations.
 */
public final class StandardMathOperations implements MathOperations {
    @Override
    public double add(double first, double second) {
        return first + second;
    }

    @Override
    public double subtract(double first, double second) {
        return first - second;
    }

    @Override
    public double multiply(double first, double second) {
        return first * second;
    }

    @Override
    public double divide(double dividend, double divisor) {
        requireNonZero(divisor, "Cannot divide by zero");
        return dividend / divisor;
    }

    @Override
    public double modulus(double dividend, double divisor) {
        requireNonZero(divisor, "Cannot calculate modulus by zero");
        return dividend % divisor;
    }

    @Override
    public double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    @Override
    public double squareRoot(double value) {
        if (value < 0) {
            throw new ArithmeticException("Cannot calculate the square root of a negative value");
        }
        return Math.sqrt(value);
    }

    @Override
    public double percentage(double part, double whole) {
        requireNonZero(whole, "Cannot calculate a percentage with a zero whole");
        return (part / whole) * 100.0;
    }

    // Rejects a zero divisor before division-based operations.
    private static void requireNonZero(double value, String message) {
        if (value == 0.0) {
            throw new ArithmeticException(message);
        }
    }
}

