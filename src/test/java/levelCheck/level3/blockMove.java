package levelCheck.level3;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//TODO : 고민하기
public class blockMove {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(
				new int[][] {{0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0}}, 7)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 블록_이동하기(int[][] board, int result) {

	}

	public class Dron {
		public int x1 = 0;
		public int y1 = 0;
		public int x2 = 0;
		public int y2 = 1;

		public void right() {

		}
	}

}
