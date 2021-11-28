package example.dynamic;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
* 아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)
12 = 55 / 5 + 5 / 5
12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.
이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

제한사항
N은 1 이상 9 이하입니다.
number는 1 이상 32,000 이하입니다.
수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
최솟값이 8보다 크면 -1을 return 합니다.
입출력 예
* N	number	return
5	12	4
2	11	3
입출력 예 설명
예제 #1
문제에 나온 예와 같습니다.

예제 #2
11 = 22 / 2와 같이 2를 3번만 사용하여 표현할 수 있습니다.
* */
public class Dynamic1_1 {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(5, 12, 4)
			, Arguments.of(2, 11, 3)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void N으로_표현(int n, int number, int result) {

		List<Integer> resultList = new ArrayList<>();

		play(n, 0, number, 0, resultList, String.valueOf(n));

		int answer = resultList.stream().mapToInt(i -> i).min().orElse(-1);

		assertThat(answer)
			.isEqualTo(result);
	}

	private void play(int n, int acc, int number, int count, List<Integer> resultList, String str) {

		if (count > 8)
			return;

		if (acc >= Integer.MAX_VALUE || acc <= Integer.MIN_VALUE)
			return;

		if (acc == number) {
			resultList.add(count);
		}

		play(n, Integer.parseInt(acc + "" + n), number, count + 1, resultList, str + n);
		play(n, acc + n, number, count + 1, resultList, str + "+" + n);
		play(n, acc - n, number, count + 1, resultList, str + "-" + n);
		play(n, acc / n, number, count + 1, resultList, str + "/" + n);
		play(n, acc * n, number, count + 1, resultList, str + "*" + n);
	}
}
