package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2020/9/16.
 */
class CalculatorTest {
    @ParameterizedTest
    @MethodSource("provideArguments")
    public void assertAddResult(String numbers, int sum) {
        Integer result = Calculator.add(numbers);
        assertThat(result).isEqualTo(sum);
    }

    private static Stream<Arguments> provideArguments() {
        return Stream.of(
                Arguments.of("", "0"),
                Arguments.of("1", "1"),
                Arguments.of("1,2", "3"),
                Arguments.of("1,2,4,6", "13"),

                Arguments.of("1\n2,3", "6"),
                Arguments.of("//;\n1;2", "3"),

                Arguments.of("1001,2", "2"),

                Arguments.of("//[***]\n1***2***3", "6"),
                Arguments.of("//[*][%]\n1*2%3", "6"),
                Arguments.of("//[*,+][+%]\n1*,2+%3", "6"),
                Arguments.of("//[*,+][,+%][+-]\n1*,+2,+%3+-1", "7")
        );
    }

    @Test
    void should_throw_exception_when_number_is_negative() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> Calculator.add("-1,2"));
        assertThat(runtimeException.getMessage()).isEqualTo("negatives not allowed");

    }

    @Test
    void should_throw_exception_when_numbers_has_multiple_negative() {
        RuntimeException runtimeException = Assertions.assertThrows(RuntimeException.class, () -> Calculator.add("-1,2,-2"));
        assertThat(runtimeException.getMessage()).isEqualTo("negatives not allowed:-1,-2");

    }

}