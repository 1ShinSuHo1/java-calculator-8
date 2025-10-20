package calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public static int add(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }
        String trimmed = input.trim();
        String[] numbers = splitNumbers(trimmed);
        return sum(numbers);
    }

    private static String[] splitNumbers(String input) {
        if (input.startsWith("//")) {
            int newlineIndex = input.indexOf("\n");
            if (newlineIndex == -1) {
                throw new IllegalArgumentException();
            }

            String delimiter = input.substring(2, newlineIndex).trim();
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

            int number;
            try {
                number = Integer.parseInt(value);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException();
            }

            if (number < 0) {
                throw new IllegalArgumentException();
            }

            result += number;
        }
        return result;
    }
}
