package naver_220116.kakao_211030;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class naver3 {

	private static Stream<Arguments> 파라미터_1() {
		return Stream.of(
			Arguments.of("ACAX",16),
			Arguments.of("CODILITY",96),
			Arguments.of("AX",4),
			Arguments.of("",0),
			Arguments.of("ABA",8)

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터_1")
	void 유니크(String S, int result) {

		assertThat(solution(S)).isEqualTo(result);

	}

	public int solution(String S) {
		int result = 0;
		for (int end = 1; end <= S.length(); end++) {
			for(int start = 0; start < S.length()-end+1; start++) {
				String test = S.substring(start,start+end);
				result += uni(test);
			}
		}
		return result;
	}

	public int uni(String a) {
		Map<String, Integer> map = new HashMap<>();

		for (int i = 0; i < a.length(); i++) {
			 String s = a.substring(i,i+1);
			 if(map.containsKey(s)) {
				 int num = map.get(s);
				 map.put(s,num+1);
			 } else {
				 map.put(s,1);
			 }
		}

		return (int)map.keySet().stream().filter(k->map.get(k).equals(1)).count();
	}







}
