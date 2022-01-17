package naver_220116.kakao_211030;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class naver2 {

	private static Stream<Arguments> 파라미터_1() {
		return Stream.of(
			// Arguments.of("azABaabza", 5),
			Arguments.of("AcZCbaBz", 8)
			// Arguments.of(),
			// Arguments.of()

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터_1")
	void 밸런스(String S, int result) {
		assertThat(solution(S)).isEqualTo(result);
	}

	public int solution(String S) {

		for (int size = 2; size <= S.length(); size++) {
			for(int start = 0; start < S.length()-size+1; start++) {
				String test = S.substring(start,start+size);
				if(isBalanced(test)) {
					return test.length();
				}
			}
		}

		return -1;
	}

	public boolean isBalanced(String input) {
		char[] chars = input.toCharArray();
		Arrays.sort(chars);

		if(input.length() <= 1) return false;

		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if(c >= 65 && c <= 90) {
				if(input.indexOf((c+"").toLowerCase(Locale.ROOT)) == -1) return false;
			} else if(c >= 97 && c <= 122) {
				if(input.indexOf((c+"").toUpperCase(Locale.ROOT)) == -1) return false;
			}
		}

		return true;
	}



}
