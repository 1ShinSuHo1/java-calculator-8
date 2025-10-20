package calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        String trimmed = input.trim();
        String[] numbers = split(trimmed);
        return sum(numbers);
    }

    public static String[] split(String input) {
        if (input == null || input.isBlank()) {
            return new String[]{"0"};
        }

        String delimiter = ",|:"; // 기본 구분자

        if (input.startsWith("//")) {
            int delimiterIndex = input.indexOf("\n");
            if (delimiterIndex == -1) {
                throw new IllegalArgumentException("커스텀 구분자 형식 오류");
            }
            delimiter = input.substring(2, delimiterIndex);
            input = input.substring(delimiterIndex + 1);
        }

        return input.split(delimiter);
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
