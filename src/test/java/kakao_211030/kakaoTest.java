package kakao_211030;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class kakaoTest {

	private static Stream<Arguments> 파라미터_1() {
		return Stream.of(
			Arguments.of(new String[] {"A B C D", "A D", "A B D", "B D"}, 2, 7),
			Arguments.of(new String[] {"JAY", "JAY ELLE JAY MAY", "MAY ELLE MAY", "ELLE MAY", "ELLE ELLE ELLE", "MAY"},
				3, 8)

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터_1")
	void 쿠폰(String[] id_list, int k, int result) {

		Map<String, Integer> cus = new HashMap<>();
		int answer = 0;

		for (String day : id_list) {
			String[] ids = day.split(" ");
			Set<String> idSet = new HashSet<>();
			Arrays.asList(ids).addAll(idSet);
			for (String id : idSet) {
				if (cus.containsKey(id)) {
					int cnt = cus.get(id);
					if (cnt < k) {
						cus.put(id, cnt + 1);
						answer++;
					}
				} else {
					cus.put(id, 1);
					answer++;
				}
			}
		}

		assertThat(answer)
			.isEqualTo(result);
	}

}
