package calculator;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = Console.readLine();

        // 커스텀 구분자인 경우 다음 줄 입력 추가로 받기
        if (input.startsWith("//")) {
            String numbers = Console.readLine();
            input = input + "\n" + numbers;
        }

        int result = StringCalculator.add(input);
        System.out.println("결과 : " + result);
    }
}
