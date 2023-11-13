package christmas;

import camp.nextstep.edu.missionutils.Console;

import java.util.Calendar;

public class View {
    public void displayEventPlannerGreeting(String message) {
        System.out.println(message);
    }

    public String inputVisitDate(String message) {
        System.out.println(message);
        String input = Console.readLine();
        // validate input
        return input;
    }

    public String inputMenu(String message) {
        System.out.println(message);
        String input = Console.readLine();
        // validate Menu
        return input;
    }

    public void displayEventBenefitsPreview(Calendar date) {
        System.out.println(
                date.get(Calendar.MONTH) + 1 + "월 "
                        + date.get(Calendar.DATE) + "일"
                        + "에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"
        );
    }
}
