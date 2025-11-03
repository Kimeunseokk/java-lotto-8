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
            List<Integer> number = Arrays.stream(str.split(",")).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
            new Lotto(number);
            System.out.println();
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
        List<Integer> list = new ArrayList<>(Randoms.pickUniqueNumbersInRange(START, END, RESULT));
        Collections.sort(list);
        return list;
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

    public static boolean bonus(List<Integer> numList, int bonus){
        return numList.contains(bonus);
    }

    public static int bonusscore(){
        while(true){
            System.out.println("보너스 번호를 입력해 주세요.");
            try {
                String str = Console.readLine();
                System.out.println();
                return Integer.parseInt(str);
            } catch (NumberFormatException e) {
                // TODO: handle exception
                throw new IllegalArgumentException("[ERROR] 숫자만 입력 가능합니다.");
            }
        }

    }

    public static void printresult(List<List<Integer>> allList, List<Integer> resultList, int money, int bonus){

        int[] result = calculate(allList, resultList, bonus);
        printlottoresult(result);

        double profit = calculateProfit(result, money);
        System.out.println("총 수익률은 " + String.format("%.1f", profit) + "%입니다.");
    }

    public static void printlottoresult(int[] result){
        System.out.print("당첨 통계\n---\n");
        System.out.println("3개 일치 (5,000원) - " + result[3]+ "개");
        System.out.println("4개 일치 (50,000원) - "+ result[4]+ "개");
        System.out.println("5개 일치 (1,500,000원) - "+ result[5]+ "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - "+ result[6]+ "개");
        System.out.println("6개 일치 (2,000,000,000원) - "+ result[7]+ "개");
    }

    private static double calculateProfit(int[] result, int money) {
        long total = 0;
        total += result[3] * 5_000L;
        total += result[4] * 50_000L;
        total += result[5] * 1_500_000L;
        total += result[6] * 30_000_000L;
        total += result[7] * 2_000_000_000L;
        return (double) total / money * 100;
    }

    public static int[] calculate(List<List<Integer>> allList, List<Integer> resultList, int bonus){
        int[] result = new int[8];
        for (List<Integer> numList : allList) {
            int count = count(numList, resultList);
            if (count < 3) continue;
            if (count == 5 && bonus(numList, bonus)) {
                result[6]++;
                continue;
        }   
        result[count]++;
    }
        return result;
    }

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("구입금액을 입력해주세요");
        int money = repeatMoney();

        System.out.println("\n"+money/1000+"개를 구매했습니다.");
        List<List<Integer>> Lotto = randnumber(money/1000);

        System.out.println("\n당첨 번호를 입력해 주세요.");
        List<Integer> win = winnumber();
        
        int bonus = bonusscore();

        printresult(Lotto, win, money, bonus);

    }
}
