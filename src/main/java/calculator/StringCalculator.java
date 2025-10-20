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

    private static String[] split(String input) {
        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf('\n');
            if (newlineIndex == -1) {
                throw new IllegalArgumentException();
            }
            String delimiter = input.substring(2, newlineIndex);
            if (delimiter.length() != 1) {
                throw new IllegalArgumentException();
            }
            String numbersPart = input.substring(newlineIndex + 1).trim();
            return numbersPart.split(Pattern.quote(delimiter));
        }
        return input.split("[,:]");
    }

    private static int sum(String[] numbers) {
        int result = 0;
        for (String token : numbers) {
            String value = token.trim();
            if (value.isEmpty()) {
                throw new IllegalArgumentException();
            }
            int num;
            try {
                num = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }
            if (num < 0) {
                throw new IllegalArgumentException();
            }
            result += num;
        }
        return result;
    }
}
