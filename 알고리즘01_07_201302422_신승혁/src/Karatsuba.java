import java.math.BigInteger;
import java.util.Scanner;

public class Karatsuba {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		System.out.print("첫번째 수 입력:");
		String first = scan.next();
		System.out.println();
		System.out.print("두번째 수 입력:");
		String second = scan.next();
		long a = Long.parseLong(first);
		long b = Long.parseLong(second);

		System.out.println(karatsuba(a, b));

	}
	

	// first or second <3
	public static long karatsuba(long first, long second) {
		int firstLength = (int) (Math.log10(first) + 1);
		int secondLength = (int) (Math.log10(second) + 1);
		int n = Math.max(firstLength, secondLength);
		int m = n/ 2;

		int half = (int) Math.pow(10, m);

		long x0 = first >> m ;
		long y0 = second >> m ;
		long x1 = first - (x0 * half);
		long y1 = second - (y0 * half);
		long z0;
		long z2;
		long z1;
		if (n < 3) {
			z2 = x0 * y0;
			z0 = x1 * y1;
			z1 = ((x0 + x1) * (y0 + y1)) - z0 - z2;
		} else {
			z2 = karatsuba(x0, y0);
			z0 = karatsuba(x1, y1);
			z1 = (karatsuba((x1 + x0), (y1 + y0))) - z2 - z0;
		}
		long result = (long) ((z2 * Math.pow(10, m * 2)) + (z1 * Math.pow(10, m)) + z0);
		return result;
	}
}
