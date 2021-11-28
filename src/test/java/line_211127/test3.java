package line_211127;

/*
사회적 거리 유지를 위해 공연 관람객들의 좌석을 배정하려 합니다. 공연장의 각 좌석은 1 x 1 크기 정사각형 모양이며, 전체 좌석은 n x n 크기 정사각 격자 모양입니다. 관람객에게 좌석을 배정하는 규칙은 다음과 같습니다.

제일 처음 입장하는 관람객은 1행 1열 좌석을 배정받습니다.
두 번째 관람객부터는 먼저 좌석을 배정받은 다른 관람객 중 가장 가까운 관람객까지의 거리가 최대인 좌석을 배정합니다.
만약 그런 좌석이 여러 개라면 열 번호가 가장 작은 좌석을 배정합니다.
만약 열 번호가 가장 작은 좌석이 여러 개라면 행 번호가 가장 작은 좌석을 배정합니다.
두 좌석 사이의 거리는 행 번호 차이 + 열 번호 차이입니다.
예를 들어 [2행, 3열]과 [5행, 1열] 사이의 거리는 |2 - 5| + |3 - 1| = 5입니다(| |는 절댓값 기호입니다).
다음은 5 x 5 크기 관람석에 좌석을 배정하는 예시입니다.

seat_1.png

제일 처음 입장하는 1번 관람객은 1행 1열에 배정합니다.
2번 관람객은 1번 관람객까지의 거리가 최대인 5행 5열에 배정합니다.
3번 관람객을 배정하기 위해 각 좌석에서 가장 가까운 관람객까지의 거리를 구하면 다음과 같습니다.
seat_6.png

이때, 가장 가까운 관람객까지의 거리가 최대인 좌석은 [5행 1열], [4행 2열], [3행 3열], [2행 4열], [1행 5열]입니다. 이중 열 번호가 가장 작은 [5행 1열]에 3번 관람객을 배정합니다.
3번 관람객을 배정한 후 각 좌석에서 가장 가까운 관람객까지의 거리는 다음과 같습니다.
seat_7.png

3번 관람객을 배치한 후 [4행 2열]의 가장 가까운 관람객까지의 거리가 4 → 2로 변하며, 따라서 4번 관람객을 배치할 좌석은 [3행 3열]이 됩니다.
아래 그림은 최대 수용 인원인 25명까지 관람객을 배치한 결과입니다.
seat_2.png

좌석의 크기 n, 관람객 수 k 가 매개변수로 주어질 때, k 번째 관람객이 배정받는 좌석의 위치를 return 하도록 solution 함수를 완성해주세요.

제한사항
n은 5 이상 50 이하인 자연수입니다.
k는 1 이상 n2 이하인 자연수입니다.
정답은 [행 번호, 열 번호] 형태로 return 해주세요.
입출력 예
n	k	result
5	12	[4,4]
5	16	[1,2]
6	13	[4,5]
입출력 예 설명
입출력 예 #1

seat_3.png

입출력 예 #2

seat_4.png

입출력 예 #3

seat_5.png



 * */

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class test3 {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(5, 12, new int[] {4, 4})
			// , Arguments.of(5, 16, new int[] {1, 2})
			// , Arguments.of(6, 13, new int[] {4, 5})
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 세번째(int n, int k, int[] result) {
		int answer = 0;

		List<Point> sitList = new ArrayList<>();

		Point[][] arr = new Point[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = new Point(i, j);
			}
		}

		Point p = arr[0][0];
		p.setSit(true);
		sitList.add(p);
		for (int i = 0; i < k; i++) {
			cal(sitList, n, arr);
			System.out.println("//////////////////");
			printf(arr);
		}

		assertThat(answer)
			.isEqualTo(result);
	}

	private void cal(List<Point> list, int n, Point[][] arr) {
		Map<String, Object> data = new HashMap<>();
		int max = 0;
		int x = 0;
		int y = 0;
		for (Point point : list) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					int dis = arr[i][j].getMaxDistance();
					dis += point.getDistance(arr[i][j]);
					arr[i][j].setMaxDistance(dis);
					if (max < dis) {
						x = i;
						y = j;
					}
				}
			}
		}
		Point p = arr[x][y];
		list.add(p);
		p.setSit(true);
	}

	private void printf(Point[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr.length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public class Point {
		private int x;
		private int y;
		private boolean sit;
		private int maxDistance;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
			this.sit = false;
		}

		public int getMaxDistance() {
			return maxDistance;
		}

		public void setMaxDistance(int maxDistance) {
			this.maxDistance = maxDistance;
		}

		public int getDistance(int x, int y) {
			return Math.abs(this.x - x) + Math.abs(this.y - y);
		}

		public int getDistance(Point p) {
			if (p.isSit())
				return 0;
			return Math.abs(this.x - p.getX()) + Math.abs(this.y - p.getY());
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}

		public boolean isSit() {
			return sit;
		}

		public void setSit(boolean sit) {
			this.sit = sit;
		}

		public String toString() {
			if (sit) {
				return String.format("[  %s]", "sit");
			}
			return String.format("[ %d, %d]", x, y);
		}
	}

}
