
public class InsertionSort {
	private int[] sortedArray;
	int count ;
	public InsertionSort() {
		sortedArray = new int[50];
		count=0;
	}

	public InsertionSort(int[] input) {
		sortedArray = input;
	}

	public int[] insertionSort() {
		int key;

		for (int i = 0; i < sortedArray.length-1; i++) {
			key = sortedArray[i + 1];
			for (int j = i; j >= 0; j--) {
				if (sortedArray[j] > key) {
					sortedArray[j + 1] = sortedArray[j];
					sortedArray[j] = key;
				}
			}
		}
		return sortedArray;
	}

	public int[] binaryInsertionSort(){
		int key;
		
		
		
		return sortedArray;
	}
}
