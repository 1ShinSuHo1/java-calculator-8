package calculator;

import java.util.regex.Pattern;

public class StringCalculator {
    public static int add(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        String[] numbers = split(input.trim());
        return sum(numbers);
    }

    public static String[] split(String input) {
        String delimiter = ",|:";
        String numbersPart = input;

        if (input.startsWith("//")) {
            // 진짜 줄바꿈(\n) 또는 문자열 "\n" 둘 다 대응
            int delimiterIndex = input.indexOf('\n');
            if (delimiterIndex == -1) {
                delimiterIndex = input.indexOf("\\n"); // 문자열로 입력될 가능성도 처리
            }
            if (delimiterIndex == -1) {
                throw new IllegalArgumentException();
            }

            delimiter = input.substring(2, delimiterIndex);
            numbersPart = input.substring(delimiterIndex + (input.charAt(delimiterIndex) == '\\' ? 2 : 1));
        }

        return numbersPart.split(Pattern.quote(delimiter));
    }

    private static int sum(String[] numbers) {
        int sum = 0;
        for (String token : numbers) {
            String value = token.trim();
            if (value.isEmpty()) {
                throw new IllegalArgumentException();
            }
            int number;
            try {
                number = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
            if (number < 0) {
                throw new IllegalArgumentException();
            }
            sum += number;
        }
        return sum;
    }
}
