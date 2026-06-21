# CST8411 Math Toolkit

CST8411 Math Toolkit is a dependency-free Java library for common arithmetic
operations. It provides a reusable interface, a standard implementation, a
static convenience facade, and an executable command-line demonstration.

Repository: https://github.com/Abdullahi-114/cst8411-math-toolkit

Release downloads: https://github.com/Abdullahi-114/cst8411-math-toolkit/releases

## Requirements

- Java Development Kit (JDK) 17 or later to build
- Java Runtime Environment (JRE) 17 or later to run
- No third-party dependencies

## Build

From PowerShell in the project directory:

```powershell
.\build.ps1
```

The build creates:

- `dist/math-toolkit-1.0.0.jar`
- `dist/math-toolkit-1.0.0-sources.jar`
- `dist/SHA256SUMS.txt`

## Run the executable JAR

```powershell
java -jar dist\math-toolkit-1.0.0.jar add 12 8
java -jar dist\math-toolkit-1.0.0.jar sqrt 81
java -jar dist\math-toolkit-1.0.0.jar percentage 25 200
```

## Add the JAR to another project

Copy `math-toolkit-1.0.0.jar` into the consuming project's `lib` directory,
then compile and run with it on the classpath:

```powershell
javac -cp "lib\math-toolkit-1.0.0.jar" MyApplication.java
java -cp ".;lib\math-toolkit-1.0.0.jar" MyApplication
```

## Usage example

```java
import ca.algonquin.cst8411.mathtoolkit.MathToolkit;

public class MyApplication {
    public static void main(String[] args) {
        double total = MathToolkit.add(15, 27);
        double remainder = MathToolkit.modulus(17, 5);
        double percent = MathToolkit.percentage(45, 60);

        System.out.println(total);      // 42.0
        System.out.println(remainder);  // 2.0
        System.out.println(percent);    // 75.0
    }
}
```

## API

The library supports addition, subtraction, multiplication, division,
modulus, exponentiation, square root, and percentage calculations. Invalid
operations such as division by zero and square root of a negative number throw
`ArithmeticException` with a clear message.

## Version

Current release: `1.0.0`

## License

This project is released under the MIT License.
