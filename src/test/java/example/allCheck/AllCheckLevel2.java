package example.allCheck;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AllCheckLevel2 {

	/*
문제 설명
한자리 숫자가 적힌 종이 조각이 흩어져있습니다. 흩어진 종이 조각을 붙여 소수를 몇 개 만들 수 있는지 알아내려 합니다.

각 종이 조각에 적힌 숫자가 적힌 문자열 numbers가 주어졌을 때, 종이 조각으로 만들 수 있는 소수가 몇 개인지 return 하도록 solution 함수를 완성해주세요.

제한사항
numbers는 길이 1 이상 7 이하인 문자열입니다.
numbers는 0~9까지 숫자만으로 이루어져 있습니다.
"013"은 0, 1, 3 숫자가 적힌 종이 조각이 흩어져있다는 의미입니다.
입출력 예 설명
예제 #1
[1, 7]으로는 소수 [7, 17, 71]를 만들 수 있습니다.

예제 #2
[0, 1, 1]으로는 소수 [11, 101]를 만들 수 있습니다.

11과 011은 같은 숫자로 취급합니다.
	* */

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of("17", 3),
			Arguments.of("011", 2)

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 소수찾기(String numbers, int answer) {
		Set<Integer> set = new HashSet<>();
		int[] nums = new int[numbers.length()];
		for (int i = 0; i < numbers.length(); i++) {
			nums[i] = Integer.parseInt(numbers.substring(i, i + 1));
		}
		for (int r = 1; r <= numbers.length(); r++) {
			permutation(nums, 0, r, set);
		}

		int result = set.size();

		assertThat(result)
			.isEqualTo(answer);

	}

	// 순열
	private void permutation(int[] input, int depth, int r, Set<Integer> numSet) {
		if (depth == r) {
			StringBuilder numStr = new StringBuilder();
			for (int i = 0; i < r; i++) {
				numStr.append((input[i]));
			}
			int num = Integer.parseInt(numStr.toString());
			if (isPrime(num) && num > 1)
				numSet.add(num);
		}

		for (int i = depth; i < input.length; i++) {
			swap(input, depth, i);
			permutation(input, depth + 1, r, numSet);
			swap(input, depth, i);
		}
	}

	private void swap(int[] input, int a, int b) {
		int temp = input[a];
		input[a] = input[b];
		input[b] = temp;
	}

	private boolean isPrime(int num) {
		for (int i = 2; i < num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

}
