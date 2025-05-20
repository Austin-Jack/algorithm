package sort.quicksort;

/**
 *
 *
 * @author luolinyuan
 * @since 2025/5/20
 */
public class HollandFlag {
	public static void partition1(int[] arr, int x) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int lessR = -1, i = 0;
		while (i < arr.length) {
			while (i < arr.length && arr[i] < x) {
				swap(arr, i, ++lessR);
				i++;
			}
			if (i > arr.length) {
				return;
			}
			swap(arr, lessR + 1, i++);
		}	
	}
	
	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	private static int[] genRandomArray(int maxSize, int maxVal, boolean exist) {
		int size = (int)(Math.random() * (maxSize  + 1));
		int[] arr = new int[size + 1];
		BitMap bitMap = new BitMap(maxVal);
		for (int i = 0; i < size; i++) {
			int val = (int) (Math.random() * (maxVal + 1));
			if (!exist) {
				bitMap.add(val);
			}
			arr[i] = val;
		}
		if (exist) {
			 arr[size] = arr[(int) (Math.random() * size)];
			 return arr;
		}
		int notExist;
		do {
			notExist = arr[(int) (Math.random() * size)]  + 1;
		} while (bitMap.contains(notExist));
		arr[size] = notExist;
		return arr;
	}
	
	private static boolean judge(int[] arr, int x, boolean exist) {
		if (arr == null || arr.length == 0) {
			return true;
		}
		if (exist) {
			int equalL = -1, equalR = -1;
			for (int i = 0; i < arr.length; i++) {
				if (arr[i] == x) {
					equalL = equalR = i;
					while (equalR < arr.length && arr[equalR++] == x) {}
					break;
				}
			}
			for (int i = 0; i < equalL; i++ ) {
				if (arr[i] >= arr[equalL]) {
					return false;
				}
			}
			for (int i = arr.length - 1; i > equalR; i--) {
				if (arr[i] <= arr[equalR]) {
					return false;
				}
			}
			return true;
		}
		int minLeft = 0;
		for (int i = 0; i < arr.length; i++) {
			minLeft = arr[i] < x ? i : minLeft;
		}
		for (int i = 0; i < minLeft; i++) {
			if (arr[i] >= arr[minLeft]) {
				return false;
			}
		}
		for (int i = arr.length - 1; i > minLeft; i--) {
			if (arr[i] <= arr[minLeft]) {
				return false;
			}
		}
		return true;
	}

	private static void printArray(int[] arr) {
		for (int j : arr) {
			System.out.print(j + " ");
		}
	}
	
	
	public static void main (String[] args) {
		int maxSize = 10;
		int maxVal = 100;
		int testTimes = 10_000;
		boolean exist = false;
		boolean sucess = true;
		for (int i = 0; i < testTimes; i++ ) {
			int[] arr = genRandomArray(maxSize, maxVal, exist);
			int x = arr[arr.length - 1];
			int[] arr2 = new int[arr.length - 1];
			System.arraycopy(arr, 0, arr2, 0, arr.length - 1);
			partition1(arr2, x);
			if (!judge(arr2, x, exist)) {
				System.out.println("Oops!!!");
				System.out.println("array:");
				printArray(arr2);
				System.out.println("x: " + x);
				sucess = false;
				break;
			}
		}
		if (sucess) {
			System.out.println("Nice!!!");
		}
	}
	
	private static class BitMap {
		private final long[] bits;
		
		public BitMap(int maxVal) {
			this.bits = new long[Math.max(1, (maxVal >> 6) + 1)];
		}
		
		private void add(int val) {
			if (val < 0) {
				throw new IllegalStateException("add val must larger than zero!");
			}
			int index = val >> 6;
			int bit = val % 64;
			int num = 1 << bit;
			bits[index] |= num;
		}
		
		private boolean contains(int val) {
			if (val < 0) {
				throw new IllegalStateException("contains val must larger than zero!");
			}
			int index = val >> 6;
			int bit = val % 64;
			return bits[index] >> bit == 1;
		}
	}
}
