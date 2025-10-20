package calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        String[] numbers = split(input);
        return sum(numbers);
    }

    private static String[] split(String input) {
        // 커스텀 구분자일 경우
        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf('\n'); // 줄바꿈 문자 위치
            if (newlineIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식이 올바르지 않습니다.");
            }
            String delimiter = input.substring(2, newlineIndex); // 구분자 추출
            if (delimiter.length() != 1) {
                throw new IllegalArgumentException("커스텀 구분자는 1글자여야 합니다.");
            }

            String numbers = input.substring(newlineIndex + 1); // 숫자 부분 추출
            return numbers.split(Pattern.quote(delimiter));
        }
        // 기본 구분자
        return input.split("[,:]");
    }

    private static int sum(String[] numbers) {
        int result = 0;
        for (String s : numbers) {
            if (s.trim().isEmpty()) {
                throw new IllegalArgumentException("빈 값이 포함되어 있습니다.");
            }
            int num;
            try {
                num = Integer.parseInt(s.trim());
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("정수가 아닌 값이 포함되었습니다: " + s);
            }
            if (num < 0) {
                throw new IllegalArgumentException("음수는 허용되지 않습니다: " + num);
            }
            result += num;
        }
        return result;
    }
}
