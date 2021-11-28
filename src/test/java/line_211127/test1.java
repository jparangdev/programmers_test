package line_211127;

/*
2차원 좌표 평면에 다양한 형태의 직사각형들이 놓여 있습니다. 모든 직사각형은 X축 위, Y축 오른쪽에 존재하며, 직사각형의 모든 변은 X축 또는 Y축에 평행합니다. 이 직사각형들을 아래 규칙에 따라 이동하려 합니다.

먼저, 직사각형을 아래 방향으로 더 움직일 수 없을 때까지 이동시킵니다.
이때, 각 직사각형은 Y축 방향으로만 이동하며, X축 방향으로는 이동할 수 없습니다.
다음으로 직사각형을 왼쪽 방향으로 더 움직일 수 없을 때까지 이동시킵니다.
이때, 각 직사각형은 X축 방향으로만 이동하며, Y축 방향으로는 이동할 수 없습니다.
직사각형은 X축, 또는 Y축에 닿으면 더는 움직이지 않으며, 각 직사각형은 다른 직사각형을 뚫거나 통과해서 움직일 수 없습니다. 또, 모든 직사각형은 회전이나 기울임 없이 X축, Y축에 평행하게 움직여야 합니다.

다음은 좌표평면 위에 놓인 직사각형이 움직이는 예시를 나타낸 그림입니다.

rect_move_1.png

먼저, 직사각형을 아래 방향으로 이동시키면 다음 그림과 같이 됩니다.

rect_move_2.png

다음으로, 직사각형을 왼쪽 방향으로 이동시키면 다음 그림과 같이 됩니다.

rect_move_3.png

위 그림이 모든 직사각형을 아래로 한 번, 왼쪽으로 한 번 이동시킨 최종 결과입니다. 위 예시에서 G번 직사각형의 경우 아래쪽 방향으로 더 움직일 수 있는 상태이나, 본 문제에서는 모든 직사각형을 아래로 한 번, 왼쪽으로 한 번만 이동시킨 결과를 구하면 됩니다.

직사각형이 담긴 2차원 배열 rectangles가 매개변수로 주어질 때, 직사각형들을 문제에 주어진 조건대로 이동시킨 후 각 직사각형의 위치를 배열에 담아 return 하도록 solution 함수를 완성해주세요.

제한사항
rectangles의 세로(행) 길이는 1 이상 100,000 이하입니다.
rectangles의 가로(열) 길이는 4 입니다.
rectangles의 각 행은 new int{x1, y1, x2, y2} 형태입니다.
x1, y1은 직사각형의 왼쪽 아래 꼭짓점의 x, y 좌표를 나타냅니다.
x2, y2는 직사각형의 오른쪽 위 꼭짓점의 x, y 좌표를 나타냅니다.
좌표값은 0 이상 1,000,000 이하인 정수입니다.
직사각형의 한 변의 길이는 1 이상 20 이하입니다.
직사각형이 겹쳐있는 등 잘못된 데이터는 입력으로 주어지지 않습니다.
return하는 배열은 각 직사각형의 위치를 담은 문자열 배열입니다.
각 직사각형의 최종 위치를 "x1 y1 x2 y2" 의 문자열 형태로 배열에 담아 return 해주세요
x1, y1은 직사각형의 왼쪽 아래 꼭짓점의 x, y 좌표입니다.
x2, y2는 직사각형의 오른쪽 위 꼭짓점의 x, y 좌표입니다.
x1, y1, x2, y2는 공백(스페이스) 하나로 구분해 주세요.
첫 번째 직사각형부터 순서대로 배열에 담으면 됩니다.
입출력 예
rectangles	result
new int{new int{0,2,1,3},new int{1,2,2,5},new int{3,3,4,4},new int{4,1,6,3},new int{1,6,5,7},new int{5,5,7,6},new int{5,8,6,10}}	new int{"0 0 1 1","1 0 2 3","2 0 3 1","3 0 5 2","0 3 4 4","2 2 4 3","4 3 5 5"}
입출력 예 설명
입출력 예 #1

문제의 예시와 같습니다.



 * */

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class test1 {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(new int[] {0, 2, 1, 3}, new int[] {1, 2, 2, 5}, new int[] {3, 3, 4, 4}, new int[] {4, 1, 6, 3},
				new int[] {1, 6, 5, 7}, new int[] {5, 5, 7, 6}, new int[] {5, 8, 6, 10},
				12, new String[] {"0 0 1 1", "1 0 2 3", "2 0 3 1", "3 0 5 2", "0 3 4 4", "2 2 4 3", "4 3 5 5"})
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 네번째(int[][] rectangles, String[] result) {
		String[] answer = new String[rectangles[0].length];

		List<Rec> list = new ArrayList<>();
		for (int[] rectangle : rectangles) {
			list.add(new Rec(rectangle[0], rectangle[1], rectangle[2], rectangle[3]));
		}

		assertThat(answer)
			.containsExactly(result);
	}

	private void down(List<Rec> list) {
		Collections.sort(list, Comparator.comparing(rec -> rec.getX1()));

		for (Rec rec : list) {

		}
	}

	public class Rec {
		int x1;
		int y1;
		int x2;
		int y2;

		public Rec(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}

		public boolean isInner(int x, int y) {
			if (y2 > y)
				return true;
			if (x2 > x)
				return true;
			return false;
		}

		public int getX1() {
			return x1;
		}

		public void setX1(int x1) {
			this.x1 = x1;
		}

		public int getY1() {
			return y1;
		}

		public void setY1(int y1) {
			this.y1 = y1;
		}

		public int getX2() {
			return x2;
		}

		public void setX2(int x2) {
			this.x2 = x2;
		}

		public int getY2() {
			return y2;
		}

		public void setY2(int y2) {
			this.y2 = y2;
		}

		public String toString() {
			return x1 + " " + y1 + " " + x2 + " " + y2;
		}
	}

}
