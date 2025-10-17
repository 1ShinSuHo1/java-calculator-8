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
        for (String raw : numbers) {
            String s = raw.trim();
            if (s.isEmpty()) {
                throw new IllegalArgumentException("빈 토큰은 허용되지 않습니다.");
            }
            int n;
            try {
                n = Integer.parseInt(s);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("정수가 아닌 값이 포함되었습니다: " + s);
            }
            if (n < 0) {
                throw new IllegalArgumentException("음수는 입력할 수 없습니다: " + n);
            }
            result += n;
        }
        return result;
    }
}
