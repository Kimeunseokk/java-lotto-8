package lotto;
import lotto.Lotto;
import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void checkmoney(String str){
        try{
            int money = Integer.parseInt(str);
            if(money%1000 != 0) throw new IllegalArgumentException("[ERROR] 입력단위를 1000으로 입력해주세요."); 
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 숫자만 입력가능합니다.");
        }
    }
    
    public static void repeatMoney(){
        while(true){
            try{
                String str = Console.readLine();
                checkmoney(str);
                return;
            }
            catch(IllegalArgumentException e){
                System.out.println("[ERROR] 금액을 다시 입력해주세요");
            }
        }
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해주세요");
        
        repeatMoney();
    }
}
