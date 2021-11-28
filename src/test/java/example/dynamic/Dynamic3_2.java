package example.dynamic;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
* 위와 같은 삼각형의 꼭대기에서 바닥까지 이어지는 경로 중, 거쳐간 숫자의 합이 가장 큰 경우를 찾아보려고 합니다.
* 아래 칸으로 이동할 때는 대각선 방향으로 한 칸 오른쪽 또는 왼쪽으로만 이동 가능합니다.
* 예를 들어 3에서는 그 아래칸의 8 또는 1로만 이동이 가능합니다.
* 삼각형의 정보가 담긴 배열 triangle이 매개변수로 주어질 때,
* 거쳐간 숫자의 최댓값을 return 하도록 solution 함수를 완성하세요.
*
* 제한사항
* 삼각형의 높이는 1 이상 500 이하입니다.
* 삼각형을 이루고 있는 숫자는 0 이상 9999 이하의 정수입니다.
*
* 입출력 예
triangle	result
[[7], [3, 8], [8, 1, 0], [2, 7, 4, 4], [4, 5, 2, 6, 5]]	30
* */
public class Dynamic3_2 {

	public static int max = 0;

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(new int[][] {new int[] {7}, new int[] {3, 8}, new int[] {8, 1, 0}, new int[] {2, 7, 4, 4},
				new int[] {4, 5, 2, 6, 5}}, 30)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 삼각형(int[][] triangle, int result) {

		down(0, 0, triangle, 0);

		int answer = max;

		assertThat(answer)
			.isEqualTo(result);
	}

	public void down(int cur, int idx, int[][] floor, int acc) {
		if (floor.length == cur) {
			max = Math.max(max, acc);
			return;
		}

		acc += floor[cur][idx];

		down(cur + 1, idx, floor, acc);
		down(cur + 1, idx + 1, floor, acc);
	}

}
