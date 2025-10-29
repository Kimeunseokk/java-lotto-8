package lotto;

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
        checknumber(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
    }

    private void checknumber(List<Integer> numbers){
        Set<Integer> num = new HashSet<>(numbers);
        if(num.size() != 6) throw new IllegalArgumentException("[ERRROR] 중복 숫자가 입력되었습니다.");
    }
    // TODO: 추가 기능 구현
}
