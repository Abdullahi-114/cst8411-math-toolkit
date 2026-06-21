package ca.algonquin.cst8411.mathtoolkit;

import java.util.Locale;

/**
 * Provides an executable demonstration for the packaged library.
 */
public final class MathToolkitDemo {
    // Prevents construction of the command-line entry point.
    private MathToolkitDemo() {
    }

    /** Runs one requested operation and prints its result. */
    public static void main(String[] args) {
        if (args.length == 0 || "help".equalsIgnoreCase(args[0])) {
            printUsage();
            return;
        }

        try {
            String operation = args[0].toLowerCase(Locale.ROOT);
            double result = calculate(operation, args);
            System.out.println("Result: " + result);
        } catch (IllegalArgumentException | ArithmeticException exception) {
            System.err.println("Error: " + exception.getMessage());
            System.exit(1);
        }
    }

    // Routes command-line input to the appropriate library operation.
    private static double calculate(String operation, String[] args) {
        if ("sqrt".equals(operation)) {
            requireArgumentCount(args, 2, "sqrt <value>");
            return MathToolkit.squareRoot(parseNumber(args[1]));
        }

        requireArgumentCount(args, 3, operation + " <first> <second>");
        double first = parseNumber(args[1]);
        double second = parseNumber(args[2]);

        return switch (operation) {
            case "add" -> MathToolkit.add(first, second);
            case "subtract" -> MathToolkit.subtract(first, second);
            case "multiply" -> MathToolkit.multiply(first, second);
            case "divide" -> MathToolkit.divide(first, second);
            case "modulus" -> MathToolkit.modulus(first, second);
            case "power" -> MathToolkit.power(first, second);
            case "percentage" -> MathToolkit.percentage(first, second);
            default -> throw new IllegalArgumentException("Unknown operation: " + operation);
        };
    }

    // Parses a numeric command-line argument with a clear failure message.
    private static double parseNumber(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Not a valid number: " + value, exception);
        }
    }

    // Ensures each operation receives the expected number of arguments.
    private static void requireArgumentCount(String[] args, int expected, String syntax) {
        if (args.length != expected) {
            throw new IllegalArgumentException("Expected: " + syntax);
        }
    }

    // Prints available operations and command-line examples.
    private static void printUsage() {
        System.out.println("CST8411 Math Toolkit 1.0.0");
        System.out.println("Usage: java -jar math-toolkit-1.0.0.jar <operation> <values>");
        System.out.println("Operations: add, subtract, multiply, divide, modulus, power, sqrt, percentage");
        System.out.println("Example: java -jar math-toolkit-1.0.0.jar add 12 8");
    }
}

