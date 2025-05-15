package sort.mergesort;

/**
 * 数组中逆序对个数
 *
 * @author luolinyuan
 * @since 2025/5/13
 */
public class ReverseOrderTupleCount {
	public static int reverseOrderTupleCount(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process(arr, 0, arr.length - 1);
	}
	
	private static int process(int[] arr, int left, int right) {
		if (left == right) {
			return 0;
		}
		int mid = left + ((right - left) >> 1);
		return process(arr, left, mid) + process(arr, mid + 1, right)
				+ merge(arr, left, right);
	}

	private static int merge(int[] arr, int left, int right) {
		int[] help = new int[right - left + 1];
		int mid = left + ((right - left) >> 1);
		int res = 0;
		int windR = mid + 1;
		for (int j = left; j <= mid; j++) {
			while (windR <= right && arr[windR] < arr[j]) {
				windR++;
			}
			res += windR - mid - 1;
		}

		int p1 = left, p2 = mid + 1, i = 0;
		while (p1 <= mid && p2 <= right) {
			help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while (p1 <= mid) {
			help[i++] = arr[p1++];
		}
		while (p2 <= right) {
			help[i++] = arr[p2++];
		}
		System.arraycopy(help, 0, arr, left, help.length);
		return res;
	}

	private static int[] generateRandomArray(int maxSize, int maxValue) {
		int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
		}
		return arr;
	}

	private static int comparator(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					res++;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		int maxSize = 1000;
		int maxValue = 100000;
		int testTimes = 500000;


		boolean success = true;
		for (int i = 0; i < testTimes; i++) {
			int[] arr = generateRandomArray(maxSize, maxValue);
			int[] copyArray = arr.clone();
			if (reverseOrderTupleCount(arr) != comparator(copyArray)) {
				System.out.println("ERROR");
				success = false;
				break;
			}
		}

		if (success) {
			System.out.println("Nice");
		}
	}
}
