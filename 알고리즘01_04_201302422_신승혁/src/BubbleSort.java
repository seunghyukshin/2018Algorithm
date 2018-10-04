
public class BubbleSort {
	int[] array;
	int size;

	public BubbleSort() {
		array = new int[size];
		size = 0;
	}

	public BubbleSort(int[] numbers) {
		// TODO Auto-generated constructor stub
		size = numbers.length;
		this.array = numbers;
	}

	public void bubbleSort() {
		// TODO Auto-generated method stub
		int loopTime = array.length;
		for (int i = 0; i < array.length - 1; i++) {
			int currentIndex = 0;
			for (; currentIndex < loopTime - 1; currentIndex++) {
				if (array[currentIndex] > array[currentIndex + 1]) {
					swap(currentIndex, currentIndex + 1);
				}
			}
			loopTime--;
		}

	}

	private void swap(int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
