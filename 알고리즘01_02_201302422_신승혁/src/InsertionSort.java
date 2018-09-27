
public class InsertionSort {
	private int[] array;
	int count;

	public InsertionSort() {
		array = new int[count];
		count = 0;
	}

	public InsertionSort(int[] input) {
		count = input.length;
		array = input;
	}

	public int[] insertionSort() {
		int key;

		for (int i = 0; i < array.length - 1; i++) {
			key = array[i + 1];
			for (int j = i; j >= 0; j--) {
				if (array[j] > key) {
					array[j + 1] = array[j];
					array[j] = key;
				}
			}
		}
		return array;
	}

	public int[] binaryInsertionSort() {
		int key;

		for (int i = 0; i < array.length - 1; i++) {
			key = array[i + 1];
			int findIndex = binarySearch(0, i, key);
			if (array[findIndex] > key) {
				System.arraycopy(array, findIndex, array, findIndex + 1, i + 1 - findIndex);
				array[findIndex] = key;
			}
		}

		return array;
	}

	private int binarySearch(int start, int last, int key) {
		if (last >= start) {
			int mid = start + ((last - start) / 2);

			if (array[mid] == key) {
				return mid;
			}
			if (array[mid] > key) {
				return binarySearch(start, mid - 1, key);
			}
			return binarySearch(mid + 1, last, key);
		}
		return start;
	}
}
