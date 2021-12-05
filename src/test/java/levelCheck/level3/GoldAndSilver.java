package levelCheck.level3;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//TODO : 고민하기
public class GoldAndSilver {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(
				90, 500, new int[] {70, 70, 0}, new int[] {0, 0, 500}, new int[] {100, 100, 2}, new int[] {4, 8, 1}, 50)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 금은_이동하기(int a, int b, int[] g, int[] s, int[] w, int[] t) {

	}

	public class City {
		int gold;
		int silver;
		int weight;
		int time;

		public City(int gold, int silver, int weight, int time) {
			this.gold = gold;
			this.silver = silver;
			this.weight = weight;
			this.time = time;
		}

	}

}
