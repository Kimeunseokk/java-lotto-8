package lotto;
import lotto.Lotto;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import camp.nextstep.edu.missionutils.Console;

public class Application {

    private static final int START = 1;
    private static final int END = 45;
    private static final int RESULT = 6; 

    public static void checkmoney(String str){
        try{
            int money = Integer.parseInt(str);
            if(money%1000 != 0) throw new IllegalArgumentException("[ERROR] 입력단위를 1000으로 입력해주세요."); 
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("[ERROR] 숫자만 입력가능합니다.");
        }
    }
    
    public static int repeatMoney(){
        while(true){
            try{
                String str = Console.readLine();
                checkmoney(str);
                return Integer.parseInt(str);
            }
            catch(IllegalArgumentException e){
                System.out.println("[ERROR] 금액을 다시 입력해주세요");
            }
        }
    }

    public static List<Integer> winnumber(){
        while (true) {
        try {
            String str = Console.readLine();
            List<Integer> number = Arrays.stream(str.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            new Lotto(number);
            return number;
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 숫자만 입력 가능합니다. 다시 입력해주세요.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    }

    private static List<List<Integer>> randnumber(int count){
        List<List<Integer>> all = new ArrayList<>();
        for(int i =0; i<count; i++){
            List<Integer> lotto = rand();
            all.add(lotto);
            System.out.println(lotto);
        }
        return all;
    }
    private static List<Integer> rand(){
        List<Integer> list = Randoms.pickUniqueNumbersInRange(START, END, RESULT);
        Collections.sort(list);
        return list;
    }

    public static void resultsystem(List<List<Integer>> alllist, List<Integer> resultList){
        for(List<Integer> numList : alllist){
            int count = count(numList,resultList);
            System.out.println(count + "개 일치");
        }
    }
    public static int count(List<Integer> numList, List<Integer> resultList){
        int count = 0;
        for(Integer num : numList){
            if(resultList.contains(num)){
                count++;
            }
        }
        return count;
    }

    public static void printresult(List<List<Integer>> allList, List<Integer> winList, int money){

    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해주세요");
        int money = repeatMoney();

        System.out.println("\n"+money/1000+"개를 구매했습니다.");
        List<List<Integer>> Lotto = randnumber(money/1000);

        System.out.println("\n당첨 번호를 입력해 주세요.");
        List<Integer> win = winnumber();
        
        resultsystem(Lotto, win);

        printresult(Lotto, win, money);

    }
}
