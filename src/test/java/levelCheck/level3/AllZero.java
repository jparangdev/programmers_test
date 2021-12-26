package levelCheck.level3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

//TODO : 고민하기
public class AllZero {

	private static Stream<Arguments> 파라미터() {
		return Stream.of(
			Arguments.of(
				new int[] {-5,0,2,1,2}, new int[][] {{0,1},{3,4},{2,3},{0,3}}, 9)
		);
	}

	@ParameterizedTest
	@MethodSource("파라미터")
	void 모두0으로만들기(int[] a, int[][] edges, int result) {

		long answer = -1;
		if(Arrays.stream(a).sum() == 0) {
			List<Node> nodes =  new ArrayList<>();
			for (int i = 0; i < a.length; i++) {
				int i1 = a[i];
				nodes.add(new Node(i,i1));
			}

			for (int[] edge : edges) {
				nodes.get(edge[0]).add(nodes.get(edge[1]));
				nodes.get(edge[1]).setParent(nodes.get(edge[1]));

			}

			answer = Integer.MAX_VALUE;
			for (Node node : nodes) {
				// if(node.isEdge) {
				//
				// }
			}
		}






		// long answer = -2;

	}


	public class Node {
		int num;
		int weight;
		Node[] child = new Node[2];
		Node parent;

		public Node(int num, int weight) {
			this.num = num;
			this.weight = weight;
		}

		public void add(Node node) {
			if(child[0] == null) {
				child[0] = node;
			} else if(child[1] == null) {
				child[1] = node;
			}
		}

		public void sum(int input) {
			weight += input;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}
	}

}
