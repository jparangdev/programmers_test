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

public class kakaoTest4 {

	private static Stream<Arguments> 파라미터_1() {
		return Stream.of(
			Arguments.of(new int[][]{new int[]{1, -7, -2, 1, -1},new int[]{2, 3, 0, -1, -2},new int[]{1, -1, 6, -1, -2},new int[]{-1, 1, -2, 0, 4},new int[]{-10, 5, -3, -1, 1}} ,18)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터_1")
	void 최대점수(int[][] data, int result) {
		int answer = 0;

		List<Integer> list = new ArrayList<>();

		go(data,new Person(data[0].length-1),list);

		Collections.sort(list, Collections.reverseOrder());
		answer = list.get(0);
		assertThat(answer)
			.isEqualTo(result);

	}

	public void go(int[][] data, Person person, List<Integer> list) {
		person.acc=data[0][0];
		goRight(data,person,list);
		goDown(data,person,list);
	}

	private void goRight(int[][] data, Person person, List<Integer> list) {
		person.right();
		person.turn(data[person.x][person.y]);
		if(person.isComplete()) {
			list.add(person.getAcc());
		} else {
			if(person.isGoRight()) goRight(data,person,list);
			if(person.isGoDown()) goDown(data,person,list);
		}
	}

	private void goDown(int[][] data, Person person, List<Integer> list) {
		person.down();
		person.turn(data[person.x][person.y]);
		if(person.isComplete()) {
			list.add(person.getAcc());
		} else {
			if(person.isGoRight()) goRight(data,person,list);
			if(person.isGoDown()) goDown(data,person,list);
		}
	}

	class Person {
		int x = 0;
		int y = 0;
		int acc = 0;
		int maxSize;

		public Person(int maxSize) {
			this.maxSize = maxSize;
		}

		public boolean isComplete() {
			return (x==maxSize && x==maxSize);
		}

		public int getAcc() {
			return acc;
		}

		public void setMaxSize(int maxSize) {
			this.maxSize = maxSize;
		}

		public void setAcc(int acc) {
			this.acc = acc;
		}

		public boolean right() {
			if(x==maxSize) return false;
			x++;
			return true;
		}

		public boolean down() {
			if(y==maxSize) return false;
			y++;
			return true;
		}

		public void turn(int data) {
			if(data == 0) {
				acc = acc * -1;
			} else {
				acc += data;
			}
		}

		public boolean isGoRight() {
			return x<maxSize;
		}

		public boolean isGoDown() {
			return y<maxSize;
		}
	}



}
