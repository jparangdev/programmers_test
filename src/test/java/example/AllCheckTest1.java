package example;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class AllCheckTest1 {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(new int[] {1, 2, 3, 4, 5}, new int[] {1}),
			Arguments.of(new int[] {1, 3, 2, 4, 2}, new int[] {1, 2, 3})

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 모의고사(int[] inputs, int[] result) {

		List<Student> list = new ArrayList<>();
		Student a = new Student(1, new int[] {1, 2, 3, 4, 5});
		a.check(inputs);
		list.add(a);
		Student b = new Student(2, new int[] {2, 1, 2, 3, 2, 4, 2, 5});
		b.check(inputs);
		list.add(b);
		Student c = new Student(3, new int[] {3, 3, 1, 1, 2, 2, 4, 4, 5, 5});
		c.check(inputs);
		list.add(c);
		int max = list.stream()
			.mapToInt(Student::getResult).max().orElse(0);
		int[] answer = list.stream()
			.filter(s -> s.getResult() == max)
			.mapToInt(Student::getName)
			.sorted()
			.toArray();

		assertThat(answer)
			.containsExactly(result);
	}

	public class Student {
		private int name;
		private int[] pattern;
		private int result;

		public Student(int name, int[] pattern) {
			this.name = name;
			this.pattern = pattern;
		}

		public void check(int[] answers) {
			int result = 0;
			int len = pattern.length;
			for (int i = 0; i < answers.length; i++) {
				int answer = answers[i];
				if (answer == pattern[i % len]) {
					result++;
				}
			}
			this.result = result;
		}

		public int getResult() {
			return result;
		}

		public int getName() {
			return name;
		}
	}

}
