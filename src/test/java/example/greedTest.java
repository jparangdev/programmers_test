package example;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class greedTest {

	private static Stream<Arguments> 파라미터_입력_1() {
		return Stream.of(
			Arguments.of(new int[]{1,2,3,4,5} ,new int[]{1}),
			Arguments.of(new int[]{1,3,2,4,2} ,new int[]{1,2,3})

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터_입력_1")
	void 모의고사(int[] inputs, int[] result) {

		// int[] answer= new int[1];
		int a = studentA(inputs);
		int b = studentB(inputs);
		int c = studentC(inputs);
		
		List<Integer> counts = new ArrayList<>();
		counts.add(a);



		counts.add(b);
		counts.add(c);
		
		int max = counts.stream().mapToInt(i->i).max().orElse(0);

		int[] answer = counts.stream().filter(i -> i == max).mapToInt(i->i).toArray();

		assertThat(answer)
			.containsExactly(result);
	}

	public int studentA(int[] answer) {
		int result = 0;
		for(int i = 0; i < answer.length; i++) {
			if((i%5)+1 == answer[i]) {
				result++;
			}
		}
		return result;
	}

	public int studentB(int[] answer) {
		int[] an = new int[]{1,3,4,5};
		int result = 0;
		int idx = 0;
		for(int i = 0; i < answer.length; i++) {
			int d = answer[i];
			if(i%2 == 0) {
				if(d == 2) result++;
			} else {
				if(d == an[idx%4]) {
					result++;
					idx++;
				}
			}
		}
		return result;
	}

	public int studentC(int[] answer) {
		int result = 0;
		for(int i = 1; i <= answer.length; i++) {
			if(i%5 == answer[i] || (i%5 == 0 && answer[i] == 5) ) {
				result++;
			}
		}
		return result;
	}
}
