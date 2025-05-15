package sort.mergesort;

/**
 *
 *
 * @author luolinyuan
 * @since 2025/5/9
 */
public class MergeSort {


	private static void mergeSort(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process(arr, 0, arr.length - 1);
	}

	private static void process(int[] arr, int L, int R) {
		if (L == R) {
			return;
		}
		int M = L + ((R - L) >> 1);
		process(arr, L, M);
		process(arr, M + 1, R);
		merge(arr, L, M, R);
	}

	private static void merge(int[] arr, int L, int M, int R) {
		int[] help = new int[R - L + 1];
		int p1 = L, p2 = M + 1, i = 0;
		while (p1 <= M && p2 <= R) {
			help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= M) {
			help[i++] = arr[p1++];
		}
		while (p2 <= R) {
			help[i++] = arr[p2++];
		}
		for (int j = 0; j < help.length; j++) {
			arr[L + j] = help[j];
		}
	}

	private static void mergeSort2(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int mergeSize = 1;
		int length = arr.length;
		int maxMid = length / 2;
		while (mergeSize < length) {
			int left = 0;
			while (left < length) {
				int mid = left + mergeSize - 1;
				if (mid >= length) {
					break;
				}
				int right = Math.min(mid + mergeSize, length - 1);
				merge(arr, left, mid, right);
				left = right + 1;
			}
			if (mergeSize > maxMid) {
				break;
			}
			mergeSize <<= 1;
		}

	}

	public static void main(String[] args) {
		// 大样本测试 - 生成100亿个随机数
		int size = 1_000_000_00;
		int[] randomArray = generateRandomArray(size, -1000000, 1000000);

		// 复制一份用于验证
		int[] copyForVerify = new int[size];
		System.arraycopy(randomArray, 0, copyForVerify, 0, size);

		// 记录开始时间
		long startTime = System.currentTimeMillis();

		// 执行归并排序
		mergeSort2(randomArray);

		// 记录结束时间
		long endTime = System.currentTimeMillis();

		// 验证排序结果
		boolean sorted = isSorted(randomArray);

		// 输出结果
		System.out.println("排序" + size + "个随机数，耗时：" + (endTime - startTime) + "毫秒");
		System.out.println("排序结果是否正确：" + sorted);

		// 测试更大规模
		testDifferentSizes();
	}

	private static boolean isSorted(int[] arr) {
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i - 1]) {
				return false;
			}
		}
		return true;
	}

	private static int[] generateRandomArray(int size, int min, int max) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = min + (int)(Math.random() * (max - min + 1));
		}
		return arr;
	}

	private static void testDifferentSizes() {
		int[] sizes = {10_000, 100_000, 1_000_000, 5_000_000};

		for (int size : sizes) {
			// 生成随机数组
			int[] arr = generateRandomArray(size, -1000000, 1000000);

			// 记录开始时间
			long startTime = System.currentTimeMillis();

			// 执行归并排序
			mergeSort2(arr);

			// 记录结束时间
			long endTime = System.currentTimeMillis();

			System.out.println("排序" + size + "个随机数，耗时：" + (endTime - startTime) + "毫秒");
		}
	}
}
