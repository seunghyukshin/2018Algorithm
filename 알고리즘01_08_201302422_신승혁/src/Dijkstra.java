public class Dijkstra {
	static int arraylength;

	public static void main(String[] args) {

		int max = Integer.MAX_VALUE;
		int[][] w = new int[][] { { 0, 10, 3, max, max }, { max, 0, 1, 2, max }, { max, 4, 0, 8, 2 },
				{ max, max, max, 0, 7 }, { max, max, max, 9, 0 } };
		;
		int[] d = new int[] { 0, max, max, max, max };
		arraylength = d.length;
		Node[] Q = new Node[arraylength + 1];
		Node[] S = new Node[arraylength];
		for (int i = 0; i < arraylength; i++) {
			Q[i + 1] = new Node((char) (65 + i), d[i]);
		}
		buildMinHeap(Q);

		for (int i = 0; arraylength >= 1; i++) {
			S[i] = extract_min(Q);
			System.out.println("======================================================");
			System.out.println("S[" + i + "] : d[" + S[i].name + "] = " + S[i].d);
			System.out.println("------------------------------------------------------");

			int u = (int) S[i].name - 65;
			for (int k = 0; k < arraylength; k++) {
				System.out.print("Q[" + k + "] : d[" + Q[k + 1].name + "] = " + Q[k + 1].d);
				int v = (int) Q[k + 1].name - 65;
				if (w[u][v] != max && d[u] + w[u][v] < d[v]) {
					d[v] = d[u] + w[u][v];

					for (int j = 1; j < arraylength + 1; j++) {
						if ((int) Q[j].name - 65 == v) {
							Q[j].d = d[u] + w[u][v];
							System.out.print(" -> d[" + Q[j].name + "] = " + Q[j].d);
							buildMinHeap(Q);
							break;
						}
					}
				}
				System.out.println("\n");
			}
		}
		System.out.println();
	}
	
	public static void minHeapify(Node[] S, int i) {
		int least;
		int Left = LeftChild(i);
		int Right = RightChild(i);
		if (Left <= arraylength && S[Left].d < S[i].d) {
			least = Left;
		} else {
			least = i;
		}
		if (Right <= arraylength && S[Right].d < S[least].d) {
			least = Right;
		}
		if (least != i) {
			swap(S, i, least);
			minHeapify(S, least);
		}
	}

	public static void buildMinHeap(Node[] S) {
		for (int i = arraylength / 2; i > 0; i--) {
			minHeapify(S, i);
		}
	}

	public static int LeftChild(int i) {
		return 2 * i;
	}

	public static int RightChild(int i) {
		return 2 * i + 1;
	}

	public static Node extract_min(Node[] S) {
		Node root = S[1];
		h_delete(S, S[1]);

		return root;
	}

	private static void swap(Node[] a, int i, int j) {
		// 배열에 노드 값을 스왑
		Node temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void h_delete(Node[] S, Node x) {
		for (int i = 1; i <= arraylength; i++) {
			if (S[i] == x) {
				S[i] = S[arraylength];
				S[arraylength--] = null;
				minHeapify(S, i);
			}
		}
	}
	public static void insert(Node[] a, Node x) {
		// S에 원소 x를 새로 넣는다.
		
		a[size++] = x;
		buildMaxHeap(a); // 넣을때마다 빌드힙
	}
	private static class Node {
		char name;
		int d;

		Node(char name, int d) {
			this.name = name;
			this.d = d;
		}
	}
}
