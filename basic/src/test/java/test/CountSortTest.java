package test;


import org.junit.Test;
import other.CountSort;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class CountSortTest {

	public int[] generateRandomArray() {
		Random r = new Random();

		int[] arr = new int[10000];

		for (int i = 0; i < arr.length; i++)
			arr[i] = r.nextInt(10);

		return arr;
	}
	

	@Test
	public void testSort() {
		int[] a = generateRandomArray();
		int[] result = CountSort.sort(a);
		Arrays.sort(a);
		boolean same = true;
		
		for (int i = 0; i < a.length; i++) {
			if(result[i] != a[i]) same = false;
		}
		
		assertEquals(true, same);
		
	}

}
