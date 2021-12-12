package levelCheck.level3;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FarNode {

	public static Stream<Arguments> paramters() {
		return Stream.of(
			Arguments.of(	new int[][]{{3, 6}, {4, 3}, {3, 2}, {1, 3}, {1, 2}, {2, 4}, {5, 2}})
		);
	}

	@ParameterizedTest
	@MethodSource("parameters")
	void 가장먼노드() {
		
	}


}
