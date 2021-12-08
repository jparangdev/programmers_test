package levelCheck.level3;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/*
*
* 
* {{1000, 1000}} : 1000
{{0, 1}, {0, 1}, {0, 1}} : 2
{{0, 3}, {1, 9}, {2, 6}, {30, 3}} : 7
{{0, 10}, {4, 10}, {15, 2}, {5, 11}} : 15
{{10, 10}, {30, 10}, {50, 2}, {51, 2}} : 6
{{1, 9}, {1, 4}, {1, 5}, {1, 7}, {1, 3}} : 13
{{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 34}, {15, 2}, {35, 43}, {26, 1}} : 72
{{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 2}, {15, 34}, {35, 43}, {26, 1}} : 72
{{0, 5}, {2, 10}, {100000000000, 2}}
* *
*
* */

public class DiskController {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			// Arguments.of(new int[][] {{0, 3}, {1, 9}, {2, 6}}, 9)
			// , Arguments.of(new int[][] {{0, 10}, {2, 10}, {9, 10}, {15, 2}}, 14)
			// , Arguments.of(new int[][] {{0, 10}, {2, 12}, {9, 19}, {15, 17}}, 25)
			// , Arguments.of(new int[][] {{0, 10}}, 10)
			// , Arguments.of(new int[][] {{10, 10}}, 10)
			// , Arguments.of(
			// 	new int[][] {{24, 10}, {28, 39}, {43, 20}, {37, 5}, {47, 22}, {20, 47}, {15, 34}, {15, 2}, {35, 43},
			// 		{26, 1}}, 72)
			// ,
			Arguments.of(
				new int[][] {{0, 2}, {1000000, 2}}, 2)

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 디스크컨트롤러(int[][] jobs, int result) {

		List<Node> list = new ArrayList<>();

		for (int[] job : jobs) {
			list.add(new Node(job[0], job[1]));
		}

		int cur = 0;
		sort(list, cur);
		cur = list.get(0).startTime;
		int sum = 0;
		for (int i = 0; i < jobs.length; i++) {
			sort(list, cur);
			sum += list.get(0).getResult(cur);
			cur += list.get(0).processTime;
			list.remove(0);
			if (list.size() > 0) {
				cur = Math.max(cur, list.get(0).startTime);
			}
		}

		int answer = sum / jobs.length;

		assertThat(answer)
			.isEqualTo(result);
	}

	public void sort(List<Node> list, int time) {
		Comparator<Node> comp = Comparator.comparing(n -> n.getOrder(time));
		list.sort(comp.thenComparing(Node::getProcessTime));
	}

	public class Node {
		int startTime;
		int processTime;

		public Node(int startTime, int processTime) {
			this.startTime = startTime;
			this.processTime = processTime;
		}

		public int getOrder(int cur) {
			int temp = cur > startTime ? 0 : startTime - cur;
			return temp;
		}

		public int getResult(int cur) {
			return cur + processTime - startTime;
		}

		public int getProcessTime() {
			return processTime;
		}

		public int getStartTime() {
			return startTime;
		}
	}
}
