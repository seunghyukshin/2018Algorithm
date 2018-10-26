import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CountInversion {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new FileReader(new File("data07.txt")));
		// BufferedReader br = new BufferedReader(new FileReader(new
		// File("testInput.txt")));
		String[] input = new String[10000000];

		// split -> 배열크기 맞춰줌
		input = br.readLine().split(",");
		int count = input.length;
		int[] numbers = new int[count];
		for (int i = 0; i < count; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}
		System.out.println(mergeSort(numbers, 0, count - 1));

	}

	public static int mergeSort(int[] array, int start, int last) {

		if (start == last) {
			return 0;
		}
		int mid = (start + last) / 2;
		return mergeSort(array, start, mid) + mergeSort(array, mid + 1, last) + merge(array, start, mid, last);
	}

	// merge함수가 사용된 횟수를 기록하여 정렬결과를 파일로 출력할때 마지막에 추가
	public static int merge(int[] array, int start, int mid, int last) {
		int mergeArr[] = new int[last - start + 1];
		int line = mid;
		int newIndex = 0;
		int newStart = start;
		int count = 0;
		while ((start <= (line)) && ((mid + 1) <= last)) {
			if (array[start] > array[mid + 1]) {
				mergeArr[newIndex++] = array[mid + 1];
				count += (line - start) + 1;
				for (int i = start; i <= line; i++) {
					System.out.print("(" + array[i] + "," + array[mid + 1] + ")");
				}
				System.out.println();
				mid++;
			} else {
				mergeArr[newIndex++] = array[start];
				start++;
			}

		}
		if (start > line) { // rest of right p = line +1
			System.arraycopy(array, mid + 1, mergeArr, newIndex, (last + 1) - (mid + 1));
		} else {// q=r
			System.arraycopy(array, start, mergeArr, newIndex, (line + 1) - (start));

		}
		System.arraycopy(mergeArr, 0, array, newStart, mergeArr.length);
		return count;
	}
}
