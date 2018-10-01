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
		} // 파일을 읽어들여서 노드 생성후 배열에 삽입
		
		
		printNode(S);

		System.out.println("----------------------------------------------------");
		System.out.println("1. 작업 추가   2. 최대값   3. 최대 우선순위 작업 처리");
		System.out.println("4. 원소 키값 증가            5. 작업 제거      6.종료");
		System.out.println("----------------------------------------------------");

		int inputNumber = scan.nextInt();
		while (inputNumber != 6) {
			switch (inputNumber) {
			case 1:
				System.out.println("추가할 작업번호를 입력하여 주세요.");
				int key = scan.nextInt();
				System.out.println("작업의 이름을 입력하여 주세요.");
				String value = scan.next();
				Node x = new Node(key, value);
				insert(S, x);
				printNode(S);
				
				break;
			case 2:
				System.out.println("최대값 : " + max(S).key + ", " + max(S).value);
				
				break;
			case 3:
				extract_max(S);
				System.out.println("한개의 작업이 처리되었습니다.");
				printNode(S);
				
				break;
			case 4:
				System.out.println("우선순위를 증가시킬 노드를 선택해주세요.");
				int index=scan.nextInt();
				System.out.println("증가시킬 값을 설정해주세요.");
				int increaseKey = scan.nextInt();
				increase_key(S,index,increaseKey);
				
				break;
			case 5:
				System.out.println("제거할 노드를 선택해주세요.");
				int deleteKey =scan.nextInt(); 
				h_delete(S,deleteKey);
				System.out.println("한개의 작업이 제거되었습니다.");
				printNode(S);
				
				break;
			default:
				break;
			}
			System.out.println("-------------------------------------------");
			System.out.println("1. 작업 추가   2. 최대값   3. 최대 우선순위 작업 처리");
			System.out.println("4. 원소 키값 증가            5. 작업 제거      6.종료");
			System.out.println("-------------------------------------------");
			inputNumber = scan.nextInt();
		}
	}

	public static Node[] buildMaxHeap(Node[] a) {
		// 각 노드에 대해 heapify
		for (int i = (size - 1) / 2; i > 0; i--){
			maxHeapify(a, i);
		}
		
		return a;
	}

	private static Node[] maxHeapify(Node[] a, int i) {
		// 왼자식,오른자식들과 비교해서 스왑하여 큰값을 취함
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
		// S에 원소 x를 새로 넣는다.
		if (size == a.length){
			resize(a);
		}
		
		a[size++] = x;
		buildMaxHeap(a); // 넣을때마다 빌드힙
	}

	public static Node max(Node[] S) {
		// S에서 키값이 최대인 원소를 return
		return S[1];
	}

	public static void extract_max(Node[] a) {
//		// S에서 키값이 최대인 원소를 제거한다.		
		Node tempNode= a[--size];
		a[1]=tempNode;
		
		buildMaxHeap(a);
	}

	public static void increase_key(Node[] S, int i, int k) { 
		// 원소의 x의 키값을 k로 증가시킨다.
		// 이때 k는 x의 현재 키값보다 작아지지 않는다고 가정한다.
		if (S[i].key < k) {
			S[i].key = k;
			buildMaxHeap(S);
			printNode(S);
		} else{
			System.out.println("우선순위 값을 확인해주세요.");
		}
	}// 인덱스 i의 노드의 키값을 k로 변경
			
	public static void h_delete(Node[] S, int i) {
		// S에서 노드x를 제거한다. 제거후 Maxheap 유지
		
		swap(S, 1, i);
		extract_max(S);
		buildMaxHeap(S);
	} // 인덱스 i의 노드를 제거

	private static void swap(Node[] a, int i, int j) {
		// 배열에 노드 값을 스왑
		Node temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void printNode(Node[] a) {
		//배열의 노드 값들을 프린트
		int i = 1;
		while (i < size) {
			System.out.println("["+(i)+"] "+a[i].key + ", " + a[i].value);
			i++;
		}
	}

	protected static void resize(Node[] a) {
		// 배열 크기 확장0-]
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
