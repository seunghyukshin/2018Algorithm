import java.io.File;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MaxPriorityQueue {
	static int size = 1;

	public static void main(String[] args) throws Exception {
		Scanner scanFile = new Scanner(new File("data03.txt"));
		Scanner scan = new Scanner(System.in);
		Node[] S = new Node[100];
	
		while (scanFile.hasNext()) {
			StringTokenizer st = new StringTokenizer(scanFile.nextLine(), ",");

			int key = Integer.parseInt(st.nextToken());
			String value = st.nextToken();
			Node x = new Node(key, value);
			insert(S, x);
		} // ������ �о�鿩�� ��� ������ �迭�� ����
		
		
		printNode(S);

		System.out.println("----------------------------------------------------");
		System.out.println("1. �۾� �߰�   2. �ִ밪   3. �ִ� �켱���� �۾� ó��");
		System.out.println("4. ���� Ű�� ����            5. �۾� ����      6.����");
		System.out.println("----------------------------------------------------");

		int inputNumber = scan.nextInt();
		while (inputNumber != 6) {
			switch (inputNumber) {
			case 1:
				System.out.println("�߰��� �۾���ȣ�� �Է��Ͽ� �ּ���.");
				int key = scan.nextInt();
				System.out.println("�۾��� �̸��� �Է��Ͽ� �ּ���.");
				String value = scan.next();
				Node x = new Node(key, value);
				insert(S, x);
				printNode(S);
				
				break;
			case 2:
				System.out.println("�ִ밪 : " + max(S).key + ", " + max(S).value);
				
				break;
			case 3:
				extract_max(S);
				System.out.println("�Ѱ��� �۾��� ó���Ǿ����ϴ�.");
				printNode(S);
				
				break;
			case 4:
				System.out.println("�켱������ ������ų ��带 �������ּ���.");
				int index=scan.nextInt();
				System.out.println("������ų ���� �������ּ���.");
				int increaseKey = scan.nextInt();
				increase_key(S,index,increaseKey);
				
				break;
			case 5:
				System.out.println("������ ��带 �������ּ���.");
				int deleteKey =scan.nextInt(); 
				h_delete(S,deleteKey);
				System.out.println("�Ѱ��� �۾��� ���ŵǾ����ϴ�.");
				printNode(S);
				
				break;
			default:
				break;
			}
			System.out.println("-------------------------------------------");
			System.out.println("1. �۾� �߰�   2. �ִ밪   3. �ִ� �켱���� �۾� ó��");
			System.out.println("4. ���� Ű�� ����            5. �۾� ����      6.����");
			System.out.println("-------------------------------------------");
			inputNumber = scan.nextInt();
		}
	}

	public static Node[] buildMaxHeap(Node[] a) {
		// �� ��忡 ���� heapify
		for (int i = (size - 1) / 2; i > 0; i--){
			maxHeapify(a, i);
		}
		
		return a;
	}

	private static Node[] maxHeapify(Node[] a, int i) {
		// ���ڽ�,�����ڽĵ�� ���ؼ� �����Ͽ� ū���� ����
		int Left = LeftChild(i);
		int Right = RightChild(i);
		int largest;
		if (Left <= size && a[Left].key > a[i].key){
			largest = Left;
		}else{
			largest = i;
		}			
		if (Right < size && a[Right].key > a[largest].key){
			largest = Right;
		}			
		if (largest != i) {
			swap(a, i, largest);
			return maxHeapify(a, largest);
		}
		return a;
	}

	private static int LeftChild(int i) {
		return 2 * i;
	}

	private static int RightChild(int i) {
		return 2 * i + 1;
	}

	public static void insert(Node[] a, Node x) {
		// S�� ���� x�� ���� �ִ´�.
		if (size == a.length){
			resize(a);
		}
		
		a[size++] = x;
		buildMaxHeap(a); // ���������� ������
	}

	public static Node max(Node[] S) {
		// S���� Ű���� �ִ��� ���Ҹ� return
		return S[1];
	}

	public static void extract_max(Node[] a) {
//		// S���� Ű���� �ִ��� ���Ҹ� �����Ѵ�.		
		Node tempNode= a[--size];
		a[1]=tempNode;
		
		buildMaxHeap(a);
	}

	public static void increase_key(Node[] S, int i, int k) { 
		// ������ x�� Ű���� k�� ������Ų��.
		// �̶� k�� x�� ���� Ű������ �۾����� �ʴ´ٰ� �����Ѵ�.
		if (S[i].key < k) {
			S[i].key = k;
			buildMaxHeap(S);
			printNode(S);
		} else{
			System.out.println("�켱���� ���� Ȯ�����ּ���.");
		}
	}// �ε��� i�� ����� Ű���� k�� ����
			
	public static void h_delete(Node[] S, int i) {
		// S���� ���x�� �����Ѵ�. ������ Maxheap ����
		
		swap(S, 1, i);
		extract_max(S);
		buildMaxHeap(S);
	} // �ε��� i�� ��带 ����

	private static void swap(Node[] a, int i, int j) {
		// �迭�� ��� ���� ����
		Node temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void printNode(Node[] a) {
		//�迭�� ��� ������ ����Ʈ
		int i = 1;
		while (i < size) {
			System.out.println("["+(i)+"] "+a[i].key + ", " + a[i].value);
			i++;
		}
	}

	protected static void resize(Node[] a) {
		// �迭 ũ�� Ȯ��0-]
		Node[] aa = new Node[2 * a.length];
		System.arraycopy(a, 0, aa, 0, size);
		a = aa;
	}
}

class Node {
	int key;
	String value;

	public Node(int key, String value) {
		this.key = key;
		this.value = value;
	}

}
