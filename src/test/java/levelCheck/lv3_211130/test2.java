package levelCheck.lv3_211130;

/*

 * */

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class test2 {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(new int[] {0, 2, 1, 3}, new int[] {1, 2, 2, 5}, new int[] {3, 3, 4, 4}, new int[] {4, 1, 6, 3},
				new int[] {1, 6, 5, 7}, new int[] {5, 5, 7, 6}, new int[] {5, 8, 6, 10},
				12, new String[] {"0 0 1 1", "1 0 2 3", "2 0 3 1", "3 0 5 2", "0 3 4 4", "2 2 4 3", "4 3 5 5"})
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 첫번째(int[][] rectangles, String[] result) {
		String[] answer = new String[rectangles[0].length];

		assertThat(answer)
			.containsExactly(result);
	}

}
