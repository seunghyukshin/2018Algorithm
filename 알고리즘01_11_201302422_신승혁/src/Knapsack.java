import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Knapsack {
	static Item[] items;
	static int[][] table;
	public static void main(String[] args) throws Exception {
		Scanner scanFile = new Scanner(new File("data11.txt"));

		Item[] arr = new Item[10000000];
		int count = 0;
		int index = 0;
		while (scanFile.hasNext()) {
			scanFile.useDelimiter(",|\r\n");
			if (scanFile.hasNextInt()) {
				if (count % 3 == 0) {
					arr[index] = new Item();
					arr[index].i = scanFile.nextInt();
					count++;
				} else if (count % 3 == 1) {
					arr[index].v = scanFile.nextInt();
					count++;
				} else {
					arr[index++].w = scanFile.nextInt();
					count++;
				}
			}
		}
		// index =5
		items = new Item[index+1];
		System.arraycopy(arr, 0, items, 1, index); //인덱스 1부터 5까지삽입
		items[0]=new Item();
/*		
		for (int i = 0; i < items.length; i++) {
			System.out.println(items[i].i+","+items[i].w+","+items[i].v);
		}
	*/	
		Scanner scan = new Scanner(System.in);
		System.out.println("배낭의 사이즈를 입력해주세요(0~49)");
		int W = scan.nextInt();
/*
		for (int i = 0; i <= index; i++) {
			for (int j = 0; j <= W; j++) {
				System.out.print(OPT(i, j)+"\t");
			}
			System.out.println();
		}
	*/	
		makeTable(index,W);
		analyzeTable(W);
	}

	private static void analyzeTable(int W) {
		int max = 0;
		int row=0;
		int col=0;
		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if(max<table[i][j]){
					max = table[i][j];
					row = i;  //4
					col = j;  //11
				}
			}
		}		
		System.out.println("max : "+max);
		
		// 무게끼리의 합도 같고 가치끼리의 합도 같아야한다.
		ArrayList<Integer> list = new ArrayList<Integer>();
		int K = W;
		System.out.print("item :");
		for (int i = table.length - 1; i >= 1; i--) {
			if (K >= 0 && table[i][K] != table[i - 1][K]) {
				list.add(i);
				System.out.print(" " + i);
				K = K - items[i].w;
			}
		}
		
		
		//System.out.println("row:"+row+"col"+col);
		
		
	}
	
	private static void makeTable(int index,int W) {
		table = new int[index+1][W+1];
		
		for (int i = 0; i <= index; i++) {
			for (int j = 0; j <= W; j++) {
				table[i][j] = OPT(i,j);
				System.out.printf("%4d",table[i][j]);
			}
			System.out.println();
		}
	}

	private static int OPT(int i, int W) {
		if (i == 0) {
			return 0;
		}
		
		if (items[i].w > W) {
			return OPT(i - 1, W);
		} else {
			return Math.max(OPT(i - 1, W), items[i].v + OPT(i - 1, W - items[i].w));
		}
	}

	private static class Item {
		int i; // 번호
		int w; // 무게
		int v; // 가치

		public Item() {

		}
	}

}
