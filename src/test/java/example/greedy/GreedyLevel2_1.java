package example.greedy;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class GreedyLevel2_1 {

	/*
문제 설명
조이스틱으로 알파벳 이름을 완성하세요. 맨 처음엔 A로만 이루어져 있습니다.
ex) 완성해야 하는 이름이 세 글자면 AAA, 네 글자면 AAAA

조이스틱을 각 방향으로 움직이면 아래와 같습니다.

▲ - 다음 알파벳
▼ - 이전 알파벳 (A에서 아래쪽으로 이동하면 Z로)
◀ - 커서를 왼쪽으로 이동 (첫 번째 위치에서 왼쪽으로 이동하면 마지막 문자에 커서)
▶ - 커서를 오른쪽으로 이동
예를 들어 아래의 방법으로 "JAZ"를 만들 수 있습니다.

- 첫 번째 위치에서 조이스틱을 위로 9번 조작하여 J를 완성합니다.
- 조이스틱을 왼쪽으로 1번 조작하여 커서를 마지막 문자 위치로 이동시킵니다.
- 마지막 위치에서 조이스틱을 아래로 1번 조작하여 Z를 완성합니다.
따라서 11번 이동시켜 "JAZ"를 만들 수 있고, 이때가 최소 이동입니다.
만들고자 하는 이름 name이 매개변수로 주어질 때, 이름에 대해 조이스틱 조작 횟수의 최솟값을 return 하도록 solution 함수를 만드세요.

제한 사항
name은 알파벳 대문자로만 이루어져 있습니다.
name의 길이는 1 이상 20 이하입니다.
입출력 예
name	return
"JEROEN"	56
"JAN"	23
* */

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of("ABAAAAAAAAABB", 7)
			// , Arguments.of("AAABBAA", 6)
			// , Arguments.of("AABBAA", 5)
			// , Arguments.of("BBAAAA", 5)
			// , Arguments.of("ABAAAB", 6)
			// , Arguments.of("JAZ", 11)
			// , Arguments.of("JAAZ", 11)
			// , Arguments.of("JEROEN", 56)
			// , Arguments.of("JAN", 23)
			// , Arguments.of("AAAAAAAAA", 0)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 조이스틱(String name, int result) {
		int answer = 0;

		for (char c : name.toCharArray()) {
			answer += upDown(c);
		}
		answer += rightLeft(name);
		assertThat(answer)
			.isEqualTo(result);
	}

	private int upDown(char c) {
		int diff = c - 'A';

		if (diff > 13) {
			return (diff * -1) + 26;
		}
		return diff;
	}

	private int rightLeft(String input) {
		if (input.length() == 1) {
			return 0;
		}
		int maxIdx = 0;
		int maxCnt = 0;
		int aSize = 0;
		for (int i = 0; i < input.length(); i++) {
			char c = input.charAt(i);
			if (c == 'A') {
				aSize++;
				maxCnt++;
				if (maxCnt < aSize) {
					maxIdx = i;
				}
			} else {
				aSize = 0;
			}
		}
		return 0;
	}

}
