import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br=new BufferedReader(new FileReader(new File("data02.txt")));
		String[] input = new String[500];
		
		//split -> 배열크기 맞춰줌(50)
		input = br.readLine().split(",");
		int count =input.length;
		int[] numbers = new int[count];
		
		for (int i = 0; i < count; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}
	/*
		for (int i = 0; i < numbers.length; i++) {
			System.out.println(numbers[i]);
			
		}
*/
		int[] result = new int[count];
		
		//Insertion Sorting
		FileWriter fwInsertion = new FileWriter("[알고리즘]01_02_201302422_insertion.txt");
		InsertionSort is = new InsertionSort(numbers);
		
		double start = System.nanoTime();
		result=is.insertionSort();
		double end = System.nanoTime();
		System.out.println(end - start);
/*
		for (int i = 0; i < count; i++) {
			System.out.println(i+":"+result[i]);
			
		}
	*/	
		for (int i = 0; i < result.length; i++) {
			if(i==result.length-1){
				fwInsertion.write(String.format("%d", result[i]));
			}else{
				fwInsertion.write(String.format("%d,", result[i]));
			}
			fwInsertion.flush();
		}
		fwInsertion.close();
		
		for (int i = 0; i < count; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}
		
		
		//Insertion Binary Sorting
		FileWriter fwBinaryInsertion = new FileWriter("[알고리즘]01_02_201302422_binary_insertion.txt");
		InsertionSort ibs = new InsertionSort(numbers);
		
		double startb = System.nanoTime();
		result=ibs.binaryInsertionSort();
		double endb = System.nanoTime();
		System.out.println(endb - startb);
		
			
		for (int i = 0; i < result.length; i++) {
			if(i==result.length-1){
				fwBinaryInsertion.write(String.format("%d", result[i]));
			}else{
				fwBinaryInsertion.write(String.format("%d,", result[i]));
			}
			fwBinaryInsertion.flush();
		}
		fwBinaryInsertion.close();
		
		for (int i = 0; i < count; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}
		
		
		//Merge Sorting
		FileWriter fwMerge = new FileWriter("[알고리즘]01_02_201302422_merge.txt");
		MergeSort ms = new MergeSort();
		
		for (int i = 0; i < result.length; i++) {
			if(i==result.length-1){
				fwMerge.write(String.format("%d", result[i]));
			}else{
				fwMerge.write(String.format("%d,", result[i]));
			}
			//fwMerge.flush();
		}
		fwMerge.write(" "+ms.getCount());
		fwMerge.close();
		
		
		
	}

}
