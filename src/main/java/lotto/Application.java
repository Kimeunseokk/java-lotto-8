package lotto;
import lotto.Lotto;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void checkmoney(String str){
        int money = Integer.parseInt(str);
        if(money%1000!=0) throw new IllegalArgumentException("1000원단위의 금액을 입력해주세요.");
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해주세요");
        String str = Console.readLine();

        checkmoney(str);
    }
}
