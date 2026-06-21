package ca.algonquin.cst8411.mathtoolkit;

/**
 * Exposes convenient static access to the standard arithmetic operations.
 */
public final class MathToolkit {
    private static final MathOperations STANDARD = new StandardMathOperations();

    // Prevents construction because this class contains only utility methods.
    private MathToolkit() {
    }

    /** Returns the sum of two values. */
    public static double add(double first, double second) {
        return STANDARD.add(first, second);
    }

    /** Returns the difference between two values. */
    public static double subtract(double first, double second) {
        return STANDARD.subtract(first, second);
    }

    /** Returns the product of two values. */
    public static double multiply(double first, double second) {
        return STANDARD.multiply(first, second);
    }

    /** Returns the quotient of two values. */
    public static double divide(double dividend, double divisor) {
        return STANDARD.divide(dividend, divisor);
    }

    /** Returns the remainder after division. */
    public static double modulus(double dividend, double divisor) {
        return STANDARD.modulus(dividend, divisor);
    }

    /** Raises a base value to an exponent. */
    public static double power(double base, double exponent) {
        return STANDARD.power(base, exponent);
    }

    /** Returns the principal square root of a value. */
    public static double squareRoot(double value) {
        return STANDARD.squareRoot(value);
    }

    /** Returns the part as a percentage of the whole. */
    public static double percentage(double part, double whole) {
        return STANDARD.percentage(part, whole);
    }
}

