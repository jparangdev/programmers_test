package kakao_211030;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class kakaoTest2 {

	private static Stream<Arguments> 파라미터_1() {
		return Stream.of(
			Arguments.of(5,3,new int[]{5,2}),
			Arguments.of(4,1,new int[]{3,2}),
			Arguments.of(3,10,new int[]{2,1})

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터_1")
	void 점프(int size, int jump, int[] result) {
		int[][] sq = new int[size][size];


	}

	private int[] go(int x, int y, int[][] sq, int jump) {
		int maxIdx = sq.length-1;
		int[] xy = new int[]{x,y};
		int goX = xy[0];



		return null;
	}


	class Point {
		int x;
		int y;

		int maxIndex;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			maxIndex = 1;
		}

		public void setMaxIndex(int maxIndex) {
			this.maxIndex = maxIndex;
		}

		public void go(int jump) {
			int diff = 0;
			if(x+jump > maxIndex) {
				x = maxIndex;
				diff = x+jump-maxIndex;
			}
		}
	}



}
