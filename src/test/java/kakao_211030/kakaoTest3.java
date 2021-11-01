package kakao_211030;

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

public class kakaoTest3 {

	private static Stream<Arguments> 파라미터_1() {
		return Stream.of(
			Arguments.of(new String[]{"0001 3 95", "0001 5 90", "0001 5 100", "0002 3 95", "0001 7 80", "0001 8 80", "0001 10 90", "0002 10 90", "0002 7 80", "0002 8 80", "0002 5 100", "0003 99 90"} ,new String[]{"0001", "0002"}),
			Arguments.of(new String[]{"1901 1 100", "1901 2 100", "1901 4 100", "1901 7 100", "1901 8 100", "1902 2 100", "1902 1 100", "1902 7 100", "1902 4 100", "1902 8 100", "1903 8 100", "1903 7 100", "1903 4 100", "1903 2 100", "1903 1 100", "1101 1 95", "1101 2 100", "1101 4 100", "1101 7 100", "1101 9 100", "1102 1 95", "1102 2 100", "1102 4 100", "1102 7 100", "1102 9 100"} ,new String[]{"1101", "1102", "1901", "1902", "1903"}),
			Arguments.of(new String[]{"1901 10 50", "1909 10 50"} ,new String[]{"NONE"})

		);
	}

	@ParameterizedTest
	@MethodSource("파라미터_1")
	void 부정(String[] logs, String[] result) {


		Map<String, Student> stMap = new HashMap<>();

		for(String log : logs) {
			String[] data = log.split(" ");
			if(stMap.containsKey(data[0])) {
				Student std = stMap.get(data[0]);
				std.put(data[1],data[2]);
			} else {
				Student std = new Student();
				std.put(data[1],data[2]);
				stMap.put(data[0], std);
			}
		}

		Set<String> bad = new HashSet<>();
		for(String aname : stMap.keySet()) {
			Student a = stMap.get(aname);
			for(String bname : stMap.keySet() ) {
				if(aname.equals(bname)) break;
				Student b = stMap.get(bname);
				if(a.check(b)) {
					bad.add(aname);
					bad.add(bname);
				}
			}
		}

		List<String> list = new ArrayList<>(bad);
		String[] answer;
		if(list.size() == 0) {
			answer = new String[]{"None"};
		} else {
			Collections.sort(list);
			answer = list.toArray(new String[list.size()]);
		}

		assertThat(answer)
			.containsExactly(result);

	}



	class Student {
		Map<Integer, Integer> exam = new HashMap<>();

		public void put(String num, String sco) {
			int number = Integer.parseInt(num);
			int score = Integer.parseInt(sco);
			exam.put(number,score);
		}

		public Map<Integer, Integer> getExam() {
			return exam;
		}

		public boolean checkCondition1(Student b) {
			if(exam.size() < 5) {
				return false;
			}
			return exam.size() == b.getExam().size();
		}

		public boolean checkCondition2(Student b) {
			for(Integer key : exam.keySet()) {
				if(!b.getExam().containsKey(key)) return false;
			}
			return true;
		}

		public boolean checkCondition3(Student b) {
			for(Integer key : exam.keySet()) {
				if(exam.get(key) != b.getExam().get(key)) return false;
			}
			return true;
		}

		public boolean check(Student b) {
			if(!checkCondition1(b)) return false;
			if(!checkCondition2(b)) return false;
			if(!checkCondition3(b)) return false;
			return true;
		}
	}



}
