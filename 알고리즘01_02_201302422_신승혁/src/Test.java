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
		FileWriter fwMerge = new FileWriter("[알고리즘]01_02_201302422_insertion.txt");
		String[] input = new String[50];
		int[] numbers = new int[50];
		int[] result = new int[50];
		
		
		input = br.readLine().split(",");	
		for (int i = 0; i < input.length; i++) {
			numbers[i] = Integer.parseInt(input[i]);
		}

		//InsertionSort
		FileWriter fwInsertion = new FileWriter("[알고리즘]01_02_201302422_insertion.txt");
		InsertionSort is = new InsertionSort(numbers);
		
		double start = System.nanoTime();
		result=is.insertionSort();
		double end = System.nanoTime();
		System.out.println(end - start);
		
		for (int i = 0; i < result.length; i++) {
			fwInsertion.write(String.format("%d,", result[i]));
			if(i==result.length-1){
				fwInsertion.write(String.format("%d", result[i]));
			}
			fwInsertion.flush();
		}
		fwInsertion.close();
		
		
		FileWriter fwBinaryInsertion = new FileWriter("[알고리즘]01_02_201302422_binary_insertion.txt");
		InsertionSort ibs = new InsertionSort(numbers);
		
		
		
		
		
		
	}

}
