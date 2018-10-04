public class SelectionSort {
	int[] array;
	int size;

	public SelectionSort() {
		array = new int[size];
		size = 0;
	}

	public SelectionSort(int[] array) {
		size = array.length;
		this.array = array;
	}

	public int[] selectionSort() {
		for (int select = 0; select < array.length-1; select++) {
			int minIndex = select;

			for (int i = select + 1; i < array.length; i++) {
				if (array[minIndex] > array[i]) {
					minIndex = i;
				}
			}
			swap(select, minIndex);
		}
		return array;
	}

	private void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
