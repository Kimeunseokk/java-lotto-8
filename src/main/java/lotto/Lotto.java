package lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import camp.nextstep.edu.missionutils.Randoms;
import camp.nextstep.edu.missionutils.Console;



public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        checkrepeat(numbers);
        overnum(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }
    // TODO: 추가 기능 구현

    private void checkrepeat(List<Integer> numbers){
        Set<Integer> num = new HashSet<>(numbers);
        if(num.size() != 6) throw new IllegalArgumentException("[ERRROR] 중복 숫자가 입력되었습니다.");
    }

    private void overnum(List<Integer> numbers){
        for(int value : numbers){
            if(value < 1 || value > 45) throw new IllegalArgumentException("[ERROR] 범위를 벗어나는 숫자가 입력되었습니다.");
        }
    }

}
