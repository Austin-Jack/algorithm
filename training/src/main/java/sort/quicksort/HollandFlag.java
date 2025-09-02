package sort.quicksort;

/**
 *
 *
 * @author luolinyuan
 * @since 2025/5/20
 */
public class HollandFlag {
	/**
	 * 将数组以x为界限，划分出<=x区在左 >x 在右
	 *
	 * @param arr	数组
	 * @param x		基准值
	 */
	public static void partition1(int[] arr, int x) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int lessR = -1, i = 0;
		while (i < arr.length) {
			// 当前数比 小于等于 x 和小于区下一个交换 index++
			while (i < arr.length && arr[i] <= x) {
				swap(arr, i++, ++lessR);
			}
			if (i > arr.length) {
				return;
			}
			// 如果没有越界 并且能执行到这 说明当前数比x大
			// 直接跳到下一个数字
			i++;
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
			// 如果要控制一个数字不能存在于这个数组中 则往bitMap中加入
			// 用于后面搂那个数字
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
			// 随机选一个数组中的元素 +1 直到这个数字是不存在于arr的
			notExist = arr[(int) (Math.random() * size)]  + 1;
		} while (bitMap.contains(notExist));
		arr[size] = notExist;
		return arr;
	}
	
	private static boolean judge(int[] arr, int x) {
		if (arr == null || arr.length == 0) {
			return true;
		}
		boolean seenGreater = false;
		for (int value : arr) {
			if (value <= x) {
				if (seenGreater) {
					return false;
				}
			} else {
				seenGreater = true;
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
		int maxSize = 10000000;
		int maxVal = 10000;
		int testTimes = 10_00000;
		boolean exist = false;
		boolean sucess = true;
		for (int i = 0; i < testTimes; i++ ) {
			int[] arr = genRandomArray(maxSize, maxVal, exist);
			int x = arr[arr.length - 1];
			int[] arr2 = new int[arr.length - 1];
			System.arraycopy(arr, 0, arr2, 0, arr.length - 1);
			partition1(arr2, x);
			if (!judge(arr2, x)) {
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
