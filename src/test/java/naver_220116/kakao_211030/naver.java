package naver_220116.kakao_211030;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class naver {

	private static Stream<Arguments> 파라미터_1() {
		return Stream.of(
			Arguments.of(new int[] {1,2,2,1}, true),
			Arguments.of(new int[] {7,7,7}, false),
			Arguments.of(new int[] {1,2,2,3}, false)

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터_1")
	void 페어(int[] A, boolean result){
		assertThat(solution(A)).isEqualTo(result);

	}


	public boolean solution(int[] A) {
		// write your code    in Java SE 8
		Set<Integer> pair = new HashSet<>();
		if(A.length % 2 != 0) return false;

		for(int i = 0; i<A.length; i++) {
			if(pair.contains(A[i])) {
				pair.remove(A[i]);
			} else {
				pair.add(A[i]);
			}
		}

		return !(pair.size() > 1);
	}

}
