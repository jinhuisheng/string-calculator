package calculator;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
public class Calculator {
    private String numbers;
    private String delimiter;

    private Calculator(String numbers) {
        if (numbers.startsWith("//")) {
            this.delimiter = new DelimiterExtractor().extract(numbers);
            this.numbers = numbers.substring(numbers.indexOf("\n") + 1);
        } else {
            this.delimiter = ",|\n";
            this.numbers = numbers;
        }
    }

    public static Integer add(String numbers) {
        Calculator calculator = new Calculator(numbers);
        return numbers.isEmpty() ? 0 : calculator.sum();
    }

    private int sum() {
        checkNegative();
        return extractNumbers().sum();
    }

    private void checkNegative() {
        long negativeCount = extractNumbers().filter(number -> number < 0).count();
        if (negativeCount == 1) {
            throw new RuntimeException("negatives not allowed");
        }
        if (negativeCount > 1) {
            String negatives = extractNumbers()
                    .filter(number -> number < 0)
                    .mapToObj(String::valueOf).collect(Collectors.joining(","));
            throw new RuntimeException("negatives not allowed:" + negatives);
        }
    }

    private IntStream extractNumbers() {
        return Arrays.stream(numbers.split(delimiter))
                .filter(number -> !number.isEmpty())
                .mapToInt(Integer::parseInt)
                .filter(number -> number <= 1000);
    }
}
