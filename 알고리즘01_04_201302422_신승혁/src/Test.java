import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("data04.txt")));
		// BufferedReader br = new BufferedReader(new FileReader(new
		// File("testInput.txt")));
		String[] input = new String[500];

		// split -> ¹è¿­Å©±â ¸ÂÃçÁÜ
		input = br.readLine().split(",");
		int count = input.length;
		int[] numbers = new int[count];

		for (int i = 0; i < count; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}
		int[] result = new int[count];

		// Selection Sorting
		FileWriter fwSelection = new FileWriter("data04_Sort_Sel.txt");
		SelectionSort ss = new SelectionSort(numbers);

		// double start = System.nanoTime();
		// double end = System.nanoTime();
		// System.out.println(end - start);

		result = ss.selectionSort();
		/*
		for (int i = 32740; i < 32746; i++) {
			System.out.println(ss.array[i]);
		}
		*/
		for (int i = 0; i < result.length; i++) {
			if (i == result.length - 1) {
				fwSelection.write(String.format("%d", result[i]));
			} else {
				fwSelection.write(String.format("%d,", result[i]));
			}
			fwSelection.flush();
		}
		fwSelection.close();

		for (int i = 0; i < count; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}

		// Bubble Sorting
		FileWriter fwBubble = new FileWriter("data04_Sort_Bub");
		BubbleSort bs = new BubbleSort(numbers);

		
		// double startb = System.nanoTime();
		// double endb = System.nanoTime();
		// System.out.println(endb - startb);

		bs.bubbleSort();
/*
		for (int i = 32740; i <32746; i++) {
			System.out.println(bs.array[i]);
		}
*/
		for (int i = 0; i < result.length; i++) {
			if (i == result.length - 1) {
				fwBubble.write(String.format("%d", result[i]));
			} else {
				fwBubble.write(String.format("%d,", result[i]));
			}
			fwBubble.flush();
		}
		fwBubble.close();

	}
}
