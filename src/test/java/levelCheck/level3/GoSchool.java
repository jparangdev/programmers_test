package levelCheck.level3;

import static org.assertj.core.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

// 이진법으로 다시풀기
public class GoSchool {

	long sum = 0;

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(
				4, 3, new int[][] {{2, 2}}, 4)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 등굣길(int m, int n, int[][] puddles, int result) {
		int answer = 0;

		Set<String> pset = new HashSet<>();
		for (int i = 0; i < puddles.length; i++) {
			int[] puddle = puddles[i];
			pset.add(puddle[0] + "" + puddle[1]);
		}

		go(m, n, 1, 1, pset);

		answer = (int)(sum % 1000000007);

		assertThat(answer).isEqualTo(result);

	}

	public void go(int m, int n, int x, int y, Set<String> pset) {
		if (x == m && n == y) {
			sum++;
			return;
		}

		if (x + 1 <= m && !pset.contains((x + 1) + "" + y)) {
			go(m, n, x + 1, y, pset);
		}
		if (y + 1 <= n && !pset.contains(x + "" + (y + 1))) {
			go(m, n, x, y + 1, pset);
		}
	}

}
