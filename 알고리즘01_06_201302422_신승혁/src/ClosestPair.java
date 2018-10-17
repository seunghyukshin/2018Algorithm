import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class ClosestPair {
	public static Dot[] minDot;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(new File("data05.txt"));

		double[] arr = new double[10000000];
		int count = 0;
		while (scan.hasNext()) {
			scan.useDelimiter(",|\r\n");
			if (scan.hasNextDouble()) {
				arr[count++] = scan.nextDouble();
			}
		}
		double[] numbers = new double[count];
		System.arraycopy(arr, 0, numbers, 0, count);

		minDot = new Dot[2];
		Dot[] dots = new Dot[count / 2];
		for (int i = 0; i < numbers.length; i++) {
			if (i % 2 == 0) {
				dots[i / 2] = new Dot();
				dots[i / 2].x = numbers[i];
			} else {
				dots[i / 2].y = numbers[i];
			}
		}
		
		sortFromX(dots, 0, dots.length-1);
		System.out.println("최단거리:"+closestPair(dots));
		System.out.println(minDot[0].x + "," + minDot[0].y);
		System.out.println(minDot[1].x + "," + minDot[1].y);
	}

	public static double closestPair(Dot[] dots) {

		if (dots.length <= 3) {
			return minDistanceTriple(dots);
		}

		// 반나눠 최소값찾기
		int half = dots.length / 2;
		double line = dots[half].x;
		Dot[] leftDots = Arrays.copyOfRange(dots, 0, half);
		Dot[] rightDots = Arrays.copyOfRange(dots, half, dots.length);

		double leftMin = closestPair(leftDots);
		double rightMin = closestPair(rightDots);
		double delta = Math.min(leftMin, rightMin);


		// line 주변 최소값 찾기
		Dot[] windowDots = deleteFarDot(dots, line, delta);
		if (windowDots == null) {
			return delta;
		}
		// windowDots을 y축을 기준으로 오름차순 정렬
		sortFromY(windowDots, 0, windowDots.length - 1);

		return minDistanceInWindow(windowDots, delta);
	}

	// windowDot중 가장 짧은 거리
	public static double minDistanceInWindow(Dot[] dots, double delta) {
		double min = delta;
		for (int i = 0; i < dots.length; ++i) {
			for (int j = i + 1; j < dots.length && (dots[j].y - dots[i].y) < delta; ++j) {
				if (distance(dots[i], dots[j]) < min) {
					minDot[0] = dots[i];
					minDot[1] = dots[j];
					min = distance(dots[i], dots[j]);
				}
			}
		}
		return min;
	}
	
	public static void sortFromY(Dot[] array, int start, int last) {
		if (start == last) {
			return;
		}
		int mid = (start + last) / 2;
		sortFromY(array, start, mid);
		sortFromY(array, mid + 1, last);
		mergeY(array, start, mid, last);
	}

	private static void mergeY(Dot[] array, int start, int mid, int last) {
		Dot mergeArr[] = new Dot[last - start + 1];
		int line = mid;
		int newIndex = 0;
		int newStart = start;

		while ((start <= (line)) && ((mid + 1) <= last)) {
			if (array[start].y > array[mid + 1].y) {
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
	}
	
	public static void sortFromX(Dot[] array, int start, int last) {
		if (start == last) {
			return;
		}
		int mid = (start + last) / 2;
		sortFromX(array, start, mid);
		sortFromX(array, mid + 1, last);
		mergeX(array, start, mid, last);
	}

	private static void mergeX(Dot[] array, int start, int mid, int last) {
		Dot mergeArr[] = new Dot[last - start + 1];
		int line = mid;
		int newIndex = 0;
		int newStart = start;

		while ((start <= (line)) && ((mid + 1) <= last)) {
			if (array[start].x > array[mid + 1].x) {
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
	}
	// line으로 부터 delta보다 큰부분에 해당하는 x좌표를 가진 점들은 버림
	public static Dot[] deleteFarDot(Dot[] dots, double line, double delta) {

		Dot[] newDots = new Dot[dots.length];
		int count = 0;
		for (int i = 0; i < dots.length; i++) {
			if ((line - delta) <= dots[i].x && dots[i].x <= (line + delta)) {
				newDots[count] = dots[i];
				count++;
			}
		}
		if (count != 0) {
			Dot[] returnDots = new Dot[count];
			System.arraycopy(newDots, 0, returnDots, 0, count);
			return returnDots;
		} else {
			return null;
		}
	}

	// 세 개 이하의 개수에서 최소거리
	public static double minDistanceTriple(Dot[] dots) {
		if (dots.length != 2) {
			double dist1 = distance(dots[0], dots[1]);
			double dist2 = distance(dots[0], dots[2]);
			double dist3 = distance(dots[1], dots[2]);

			if (dist1 < dist2) {
				if (dist1 < dist3) {
					minDot[0] = dots[0];
					minDot[1] = dots[1];
					return dist1;
				} else {
					minDot[0] = dots[1];
					minDot[1] = dots[2];
					return dist3;
				}
			} else {
				if (dist2 < dist3) {
					minDot[0] = dots[0];
					minDot[1] = dots[2];
					return dist2;
				} else {
					minDot[0] = dots[0];
					minDot[1] = dots[1];
					return dist1;
				}
			}
		} else {
			minDot[0] = dots[0];
			minDot[1] = dots[1];
			return distance(dots[0], dots[1]);
		}
	}

	// 두점사이 거리
	public static double distance(Dot firstDot, Dot secondDot) {
		return Math.sqrt(Math.pow(firstDot.x - secondDot.x, 2) + Math.pow(firstDot.y - secondDot.y, 2));
	}

	public static class Dot {
		double x;
		double y;
	}

}
