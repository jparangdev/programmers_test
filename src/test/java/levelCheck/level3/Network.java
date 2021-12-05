package levelCheck.level3;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//TODO : 완료
public class Network {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(
				3, new int[][] {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}, 2)
			,
			Arguments.of(
				3, new int[][] {{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}, 1)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 네트워크(int n, int[][] computers, int result) {
		int answer = 0;
		List<Computer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(new Computer());
		}

		for (int i = 0; i < computers.length; i++) {
			int[] net = computers[i];
			for (int j = 0; j < net.length; j++) {
				if (i == j || net[j] == 0)
					continue;
				Computer parent = list.get(i);
				parent.add(list.get(j));
			}
		}

		for (int i = 0; i < list.size(); i++) {
			setNet(list.get(i), i + 1);
		}

		list.forEach(c -> System.out.println(c.networkNum + ","));

		answer = (int)list.stream().mapToInt(c -> c.networkNum).distinct().count();

		assertThat(answer).isEqualTo(result);

	}

	private void setNet(Computer com, int netNum) {
		if (com.networkNum > 0) {
			com.networkNum = Math.min(com.networkNum, netNum);
			return;
		}
		com.networkNum = netNum;
		for (Computer child : com.list) {
			setNet(child, netNum);
		}
	}

	public class Computer {
		int networkNum = 0;
		List<Computer> list = new ArrayList<>();

		public void add(Computer computer) {
			list.add(computer);
		}

	}
}
