package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(String input) {
        if (input == null || input.isEmpty()) {
            return 0;
        }
        return sum(split(input));
    }

    private static String[] split(String input) {
        if (input.startsWith("//")) {
            int newlineIdx = input.indexOf('\n');
            if (newlineIdx < 0) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }
            String delimiterPart = input.substring(2, newlineIdx);
            if (delimiterPart.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 1글자여야 합니다.");
            }
            String customDelimiter = Pattern.quote(delimiterPart);
            String numbersPart = input.substring(newlineIdx + 1);
            return numbersPart.split(customDelimiter);
        }
        // 기본 구분자: 쉼표, 콜론
        return input.split("[,:]");
    }

    private static int sum(String[] numbers) {
        int result = 0;
        for (String number : numbers) {
            result += Integer.parseInt(number);
        }
        return result;
    }
}
