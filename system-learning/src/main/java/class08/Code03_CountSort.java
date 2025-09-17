package class08;

import java.util.Arrays;

public class Code03_CountSort {
	// 第一种： 直接创建 长度为max的数组
	public static void countSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			max = Math.max(max, arr[i]);
		}
		int[] bucket = new int[max + 1];
		for (int i = 0; i < arr.length; i++) {
			bucket[arr[i]]++;
		}
		int i = 0;
		for (int j = 0; j < bucket.length; j++) {
			while (bucket[j]-- > 0) {
				arr[i++] = j;
			}
		}
	}

	// 第二种：创建 max-min+1 长度数组 减少额外空间开销
	public static void countSort2(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int min = arr[0], max = arr[0];
		for(int num : arr) {
			min = Math.min(min, num);
			max = Math.max(max, num);
		}
		int[] count = new int[max - min + 1];
		for (int num : arr) {
			count[num - min]++;
		}
		int i = 0;
		for (int j = 0; j < count.length; j++) {
			while (count[j]-- > 0) {
				arr[i++] = j + min;
			}
		}
	}

	// 第三种引入前缀和 减少内层的while
	public static void countSort3(int[] arr) {
		if (arr == null || arr.length == 0) {
			return;
		}
		int min = arr[0], max = arr[0];
		for(int num : arr) {
			min = Math.min(min, num);
			max = Math.max(max, num);
		}
		int[] count = new int[max - min + 1];
		for (int num : arr) {
			count[num - min]++;
		}
		for (int i = 1; i < count.length; i++) {
			count[i] += count[i - 1];
		}
		// 此时count的含义为在 原始数组中有count[i]个数 <= i + min
		int[] res = new int[arr.length];
		// 从后往前排 保证稳定性
		for (int i = arr.length - 1; i >= 0; i--) {
			int num = arr[i];
			//<= num 的有 count[num - min] 个 -> -1转为索引位置
			int index = count[num - min] - 1;
			res[index] = num;
			count[num - min]--;
		}
		System.arraycopy(res, 0, arr, 0, arr.length);
	}

	// for test
	public static void comparator(int[] arr) {
		Arrays.sort(arr);
	}

	// for test
	public static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random());
		}
		return arr;
	}

	// for test
	public static int[] copyArray(int[] arr) {
		if (arr == null) {
			return null;
		}
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			res[i] = arr[i];
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[] arr1, int[] arr2) {
		if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
			return false;
		}
		if (arr1 == null && arr2 == null) {
			return true;
		}
		if (arr1.length != arr2.length) {
			return false;
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr1[i] != arr2[i]) {
				return false;
			}
		}
		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		if (arr == null) {
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	// for test
	public static void main(String[] args) {
		int testTime = 500000;
		int maxSize = 100;
		int maxValue = 150;
		boolean succeed = true;
		for (int i = 0; i < testTime; i++) {
			int[] arr1 = generateRandomArray(maxSize, maxValue);
			int[] arr2 = copyArray(arr1);
			countSort3(arr1);
			comparator(arr2);
			if (!isEqual(arr1, arr2)) {
				succeed = false;
				printArray(arr1);
				printArray(arr2);
				break;
			}
		}
		System.out.println(succeed ? "Nice!" : "Fucking fucked!");

		int[] arr = generateRandomArray(maxSize, maxValue);
		printArray(arr);
		countSort3(arr);
		printArray(arr);

	}

}
