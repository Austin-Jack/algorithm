package sort.mergesort;

/**
 * 求数组中小和
 *
 *
 *
 * @author luolinyuan
 * @since 2025/5/13
 */
public class SmallSum {
	public static int smallSum(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		int mid = (arr.length - 1) >> 1;
		return process(arr, 0, mid)
			+ process(arr, mid + 1, arr.length - 1)
			+ merge(arr, 0, arr.length - 1);
	}
	
	private static int process(int[] arr, int left, int right) {
		if (left == right) {
			return 0;
		}
		int mid = left + ((right - left) >> 1);
		return process(arr, left, mid)
			+ process(arr, mid + 1, right)
			+ merge(arr, left, right);
	}
	
	private static int merge(int[] arr, int left, int right) {
		int mid = left + ((right - left) >> 1);
		int[] help = new int[right - left + 1];
		int p1 = left, p2 = mid + 1, i = 0;
		int res = 0;
		
		while (p1 <= mid && p2 <= right) {
			res += arr[p1] < arr[p2] ? (right - p2 + 1) * arr[p1] : 0;
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
}
