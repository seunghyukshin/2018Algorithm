import java.util.ArrayList;

public class Prim {
	final static int infinity = Integer.MAX_VALUE;

	public static void main(String[] args) {
		String[] v = new String[] { "a", "b", "c", "d", "e", "f", "g", "h", "i" };
		int[] key = new int[v.length];
		int[][] w = new int[v.length][v.length];
		String[] ¥ð = new String[v.length];
		ArrayList<String> q = new ArrayList<String>();

		for (int i = 0; i < w.length; i++) {
			for (int j = 0; j < w.length; j++) {
				w[i][j] = infinity;
			}
		}
		add(w, "a", "b", 4);
		add(w, "a", "h", 8);

		add(w, "b", "h", 11);
		add(w, "b", "c", 8);

		add(w, "c", "d", 7);
		add(w, "c", "f", 4);
		add(w, "c", "i", 2);

		add(w, "d", "e", 9);
		add(w, "d", "f", 14);

		add(w, "e", "f", 10);

		add(w, "f", "g", 2);

		add(w, "g", "h", 1);
		add(w, "g", "i", 6);

		add(w, "h", "i", 7);

		for (int i = 0; i < v.length; i++) {
			insert(q, v[i]);
		}
		for (int i = 0; i < key.length; i++) {
			key[i] = infinity;
		}
		key[index("a")] = 0;
		buildminheap(q, key);

		while (q.size() > 1) {
			String u = extract_min(q);
			System.out.println("w<" + ¥ð[index(u)] + "," + u + "> = " + key[index(u)]);
			for (int i = 0; i < w.length; i++) {
				if (w[index(u)][i] != infinity && q.contains(vertex(i)) && w[index(u)][i] < key[i]) {
					key[i] = w[index(u)][i];
					¥ð[i] = u;
				}
			}
			buildminheap(q, key);
		}
		int sum = 0;
		for (int i = 0; i < key.length; i++) {
			sum += key[i];
		}
		System.out.println("w<MST> = " + sum);
	}

	private static void add(int[][] w, String string, String string2, int i) {
		w[index(string2)][index(string)] = w[index(string)][index(string2)] = i;
	}

	private static String vertex(int index) {
		switch (index) {
		case 0:
			return "a";
		case 1:
			return "b";
		case 2:
			return "c";
		case 3:
			return "d";
		case 4:
			return "e";
		case 5:
			return "f";
		case 6:
			return "g";
		case 7:
			return "h";
		case 8:
			return "i";
		default:
			return "";
		}
	}

	private static int index(String vertex) {
		switch (vertex) {
		case "a":
			return 0;
		case "b":
			return 1;
		case "c":
			return 2;
		case "d":
			return 3;
		case "e":
			return 4;
		case "f":
			return 5;
		case "g":
			return 6;
		case "h":
			return 7;
		case "i":
			return 8;
		default:
			return 5;
		}
	}

	private static void buildminheap(ArrayList<String> q, int[] key) {
		for (int i = q.size() / 2; i >= 1; i--) {
			minheapify(q, key, i);
		}
	}

	private static void minheapify(ArrayList<String> q, int[] key, int i) {
		int Left = 2 * i;
		int Right = 2 * i + 1;
		int least;
		if (Left <= q.size() - 1 && key[index(q.get(Left))] < key[index(q.get(i))]) {
			least = Left;
		} else {
			least = i;
		}
		if (Right <= q.size() - 1 && key[index(q.get(Right))] <= key[index(q.get(least))]) {
			least = Right;
		}
		if (least != i) {
			String temp = q.get(i);
			q.set(i, q.get(least));
			q.set(least, temp);
			minheapify(q, key, least);
		}
	}

	public static String extract_min(ArrayList<String> q) {
		String min = q.remove(1);
		return min;
	}

	private static void insert(ArrayList<String> q, String v) {
		if (q.size() == 0) {
			q.add(0, "");
		}
		q.add(v);
	}
}