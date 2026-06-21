package ca.algonquin.cst8411.mathtoolkit;

/**
 * Defines reusable arithmetic operations supplied by the toolkit.
 */
public interface MathOperations {
    /** Returns the sum of two values. */
    double add(double first, double second);

    /** Returns the difference between two values. */
    double subtract(double first, double second);

    /** Returns the product of two values. */
    double multiply(double first, double second);

    /** Returns the quotient of two values. */
    double divide(double dividend, double divisor);

    /** Returns the remainder after division. */
    double modulus(double dividend, double divisor);

    /** Raises a base value to an exponent. */
    double power(double base, double exponent);

    /** Returns the principal square root of a value. */
    double squareRoot(double value);

    /** Returns the part as a percentage of the whole. */
    double percentage(double part, double whole);
}

