import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author huisheng.jin
 * @date 2020/1/15.
 */
public class StringCalculator {
    private final List<String> numberList;

    private StringCalculator(String delimiter, String numberList) {
        this.numberList = Arrays.asList(numberList.split(delimiter));
        checkNumberValid();
    }

    private void checkNumberValid() {
        IntStream intStream = numberList.stream().mapToInt(Integer::parseInt).filter(number -> number < 0);
        String exceptionMsg = intStream.mapToObj(String::valueOf).collect(Collectors.joining(","));
        if (StringUtils.isNotBlank(exceptionMsg)) {
            if (exceptionMsg.contains(",")) {
                throw new IllegalArgumentException("negatives not allowed:" + exceptionMsg);
            } else {
                throw new IllegalArgumentException("negatives not allowed");
            }
        }
    }

    public static Integer add(String str) {
        if (StringUtils.isEmpty(str)) {
            return 0;
        }
        checkInput(str);
        return createStringCalculator(str).calculate();
    }

    private static void checkInput(String str) {
        if (str.contains(",\n")) {
            throw new IllegalArgumentException("input illegal");
        }
    }

    private static StringCalculator createStringCalculator(String str) {
        if (str.startsWith("//")) {
            String newDelimiter = str.substring(2, 3);
            String numbers = str.substring(4);
            return new StringCalculator(newDelimiter, numbers);
        } else {
            return new StringCalculator(",|\n", str);
        }
    }

    private Integer calculate() {
        return numberList.stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }
}
