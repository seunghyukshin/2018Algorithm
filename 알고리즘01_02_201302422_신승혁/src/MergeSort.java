
public class MergeSort {
	int count;

	public MergeSort() {
		count = 0;
	}

	public MergeSort(int[] input) {
		count = input.length;
	}

	// Recursive
	public void mergeSort(int[] array, int start, int last) {

		if (start == last) {
			return;
		}
		int mid = (start + last) / 2;
		mergeSort(array, start, mid);
		mergeSort(array, mid + 1, last);
		merge(array, start, mid, last);
	}

	// merge함수가 사용된 횟수를 기록하여 정렬결과를 파일로 출력할때 마지막에 추가
	public void merge(int[] array, int start, int mid, int last) {
		int mergeArr[] = new int[last - start + 1];
		int line = mid;
		int newIndex = 0;
		int newStart = start;

		while ((start <= (line)) && ((mid + 1) <= last)) {
			if (array[start] > array[mid + 1]) {
				mergeArr[newIndex++] = array[mid + 1];
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
		count++;
	}
	public int getCount(){
		return count;
	}
}
