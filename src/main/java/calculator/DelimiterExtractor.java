package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author huisheng.jin
 * @date 2020/9/17.
 */
public class DelimiterExtractor {
    public static final String SPECIAL_CHARACTERS = "* . ? + $ ^ [ ] ( ) { } | \\ /";

    String extract(String numbers) {
        String substring = numbers.substring(numbers.indexOf("//") + 2, numbers.indexOf("\n"));
        if (substring.contains("[") && substring.contains("]")) {
            return extractDelimiter(substring);
        } else {
            return substring;
        }
    }

    private String extractDelimiter(String str) {
        List<String> list = extractDelimiterGroup(str);
        return list.stream().flatMap(item -> Arrays.asList(item.split("")).stream()).map(this::convert).collect(Collectors.joining("|"));
    }

    private List<String> extractDelimiterGroup(String str) {
        Pattern p = Pattern.compile("\\[(.*?)]");
        Matcher m = p.matcher(str);
        List<String> list = new ArrayList<>();
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }

    private String convert(String item) {
        if (SPECIAL_CHARACTERS.contains(item)) {
            return "\\" + item;
        } else {
            return item;
        }
    }

}
