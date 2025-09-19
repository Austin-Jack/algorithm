package sort.quicksort;

public class Quicksort {
    public static void quicksort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    public static void process(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        swap(arr, right, ((int) Math.random() * (right - left + 1)) + left);
        int[] range = partition(arr, left, right);
        process(arr, range[1] + 1, right);
        process(arr, left, range[0] - 1);
    }

    public static int[] partition(int[] arr, int left, int right) {
        if (left > right) {
            return new int[] { -1, -1 };
        }
        if (left == right) {
            return new int[] { left, right };
        }
        int lessR = left - 1, moreL = right; // 这里moreL == right 是把基准值排除在外 后面再把这个基准放到等于区
        int index = left;
        // 当前指针 没撞上大于区左边界
        while (index < moreL) {
            if (arr[index] < arr[right]) {
                // 小于基准值 当前数与小于区下一个交换 小于区 向右扩 当前数跳下一个
                swap(arr, index++, ++lessR);
            } else if (arr[index] > arr[right]) {
                // 大于基准值 当前数与大于区下一个交换 大于区向左扩 当前数不动
                swap(arr, index, --moreL);
            } else {
                // 等于基准值 直接跳下一个
                index++;
            }
        }
        // 将基准值放回等于区
        swap(arr, lessR + 1, right);
        return new int[] { lessR + 1, moreL };
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int size = 10;
        int maxVal = 100;
        int minVal = -100;
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] =  (int) ((maxVal - minVal + 1) * Math.random()) - maxVal; 
        }
        quicksort(arr);
        for (int num : arr) {
            System.out.println(num + " ");
        }
    }
}
