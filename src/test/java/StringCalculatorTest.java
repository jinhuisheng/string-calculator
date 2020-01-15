import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author huisheng.jin
 * @date 2020/1/15.
 */
class StringCalculatorTest {
    @Test
    void should_return_zero_when_input_is_empty() {
        assertThat(StringCalculator.add("")).isEqualTo(0);
    }

    @Test
    void should_return_success_when_input_one_number() {
        assertThat(StringCalculator.add("1")).isEqualTo(1);
        assertThat(StringCalculator.add("2")).isEqualTo(2);
    }

    @Test
    void should_return_success_when_input_two_numbers() {
        assertThat(StringCalculator.add("1,1")).isEqualTo(2);
        assertThat(StringCalculator.add("3,9")).isEqualTo(12);
    }

    @Test
    void should_return_success_when_input_multiple_numbers() {
        assertThat(StringCalculator.add("1,1,1")).isEqualTo(3);
        assertThat(StringCalculator.add("1,1,2,1,3")).isEqualTo(8);
    }

    @Test
    void should_return_success_when_input_with_newlines() {
        assertThat(StringCalculator.add("1\n1")).isEqualTo(2);
        assertThat(StringCalculator.add("1\n1,1")).isEqualTo(3);
    }

    @Test
    void should_throw_exception_when_input_illegal() {
        Assertions.assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("1,\n")
                , "input illegal");
    }

    @Test
    void should_return_success_when_input_with_new_delimiter() {
        assertThat(StringCalculator.add("//;\n1;2")).isEqualTo(3);
    }

    @Test
    void should_throw_exception_when_input_negative_number() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("-1"),
                "negatives not allowed");
        assertThat(exception.getMessage()).isEqualTo("negatives not allowed");
    }

    @Test
    void should_throw_exception_when_input_negative_number_2() {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("2,-1"),
                "negatives not allowed");
        assertThat(exception.getMessage()).isEqualTo("negatives not allowed");
    }

    @Test
    void should_throw_exception_when_input_multiple_negative_numbers_and_show_them_in_exception_message() {
        IllegalArgumentException illegalArgumentException = Assertions.assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("2,-1,3,-2"),
                "negatives not allowed:-1,-2");
        assertThat(illegalArgumentException.getMessage()).isEqualTo("negatives not allowed:-1,-2");

    }
}

