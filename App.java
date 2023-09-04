import java.util.Arrays;
import java.util.Random;



class App {

	public static void main(String[] args) {

		//int[] arr = generateSortedArr(10);
		//System.out.println(Arrays.toString(arr));
		//System.out.println(Arrays.toString(shuffleArray(arr)));

		System.out.println("Benchmark (unsorted) (ns)");
		System.out.println("Size(k)\t\tBest\tAvg. Unsorted\tAvg. Sorted\tAvg. Sorted (binary)\tWorst");
		//for (int i = 1; i <= 10; i++) {
			int size = 10;
			int[] sortedArr = generateUniqueSortedArr(size);
			System.out.println(Arrays.toString(sortedArr));
			int[] unsortedArr = shuffleArray(sortedArr);
			System.out.println(Arrays.toString(unsortedArr));
			/*System.out.printf("%d\t\t%d\t%d\t\t%d\t\t%d\t\t\t%d\n",
			size/1000,
			benchmarkBestCase(unsortedArr),
			benchmarkUnsortedAverage(unsortedArr),
			benchmarkSortedAverageIterate(sortedArr),
			benchmarkSortedAverageBinary(sortedArr),
			benchmarkWorstCase(unsortedArr));
		}
		/*
		 *	Output: 
		 *  Size(k)         Best    Avg. Unsorted   Avg. Sorted     Avg. Sorted (binary)    Worst
			100             100     33              30              110                     19700
			200             100     36              30              122                     35400
			300             100     37              31              130                     53100
			400             100     37              31              137                     71500
			500             100     38              31              140                     98600
			600             100     38              31              144                     106300
			700             100     38              31              147                     138500
			800             100     38              32              149                     141800
			900             100     39              32              152                     160000
			1000            100     39              32              153                     177200
		 */
		/**
		 * System.out.println("Benchmark: Seach using binary search on sorted array 64M: " + benchmarkSortedAverageBinary(generateSortedArr(64000000)));
		 * 671 ns
		*/


	}

	public static int[] generateUniqueSortedArr(int size) {
		int[] arr = new int[size];
		for (int i = 0; i < size; i++) {
			arr[i] = (int) Math.abs(Math.random() * size * 1.1);
		}
		return arr;
	}

	public static int[] shuffleArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int tmp = arr[i];
			int indexTwo = (int) (Math.random() * arr.length);
			arr[i] = arr[indexTwo];
			arr[indexTwo] = tmp;
		}
		return arr;
	}



	public static boolean binarySearch(int[] arr, int key) {
		int first = 0;
		int last = arr.length - 1;

		while (true) {

			int index = first + (last - first) / 2 ;

			if (arr[index] == key) {
				return true;
			}

			if (arr[index] < key && index < last) {
				first = index + 1;
				continue;
			}

			if (arr[index] > key && index > first) {
				last = index - 1;
				continue;
			}

			return false;
		}
	}




	public static boolean search_unsorted(int[] array, int key) {
		for (int index = 0; index < array.length ; index++) {
			if (array[index] == key) {
				return true;
			}
		}
		return false;
	}

	public static boolean search_sorted(int[] array, int key) {
		for (int index = 0; index < array.length ; index++) {
			if (array[index] == key) {
				return true;
			} else if (array[index] > key) {
				System.out.println("NOT FOUND, SO SKIPPED");
				return false;
			}
		}
		return false;
	}

	public static long benchmarkUnsortedAverage(int[] arr) {

		long res = Long.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 100; i++) {

			// Average case.
			long tmp = 0;
			for (int j = 0; j < 1000; j++) {

				int key = (int) Math.abs(Math.random() * 100 * 1.1);

				long t0 = System.nanoTime();
				search_unsorted(arr, key);
				tmp += System.nanoTime() - t0;

			}
			tmp = tmp / 1000;
			if (tmp < res && tmp > 0) {
				res = tmp;
			}

		}

		return res;

	}

	public static long benchmarkSortedAverageIterate(int[] arr) {

		long res = Long.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 100; i++) {

			// Average case.
			long tmp = 0;
			for (int j = 0; j < 1000; j++) {

				int key = (int) Math.abs(Math.random() * 100 * 1.1);

				long t0 = System.nanoTime();
				search_sorted(arr, key);
				tmp += System.nanoTime() - t0;

			}
			tmp = tmp / 1000;
			if (tmp < res && tmp > 0) {
				res = tmp;
			}

		}

		return res;

	}

	
	public static long benchmarkSortedAverageBinary(int[] arr) {

		long res = Long.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 100; i++) {

			// Average case.
			long tmp = 0;
			for (int j = 0; j < 1000; j++) {

				int key = (int) Math.abs(Math.random() * 100 * 1.1);

				long t0 = System.nanoTime();
				binarySearch(arr, key);
				tmp += System.nanoTime() - t0;

			}
			tmp = tmp / 1000;
			if (tmp < res && tmp > 0) {
				res = tmp;
			}

		}

		return res;

	}

	public static long benchmarkBestCase(int[] arr) {

		long res = Long.MAX_VALUE; // [best case, average case, worst case]

		// Best and worst case.
		for (int i = 0; i < 10000; i++) {

			// Best case (First element)
			long t0 = System.nanoTime();
			search_unsorted(arr, arr[0]);
			long t1 = System.nanoTime() - t0;
			if (t1 < res && t1 > 0) { // t1 > 0 prevents too fast cases.
				res = t1;
			}

		}

		return res;

	}

	public static long benchmarkWorstCase(int[] arr) {

		long res = Long.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 10000; i++) {

			// Worst case (doesn't find).
			long t0 = System.nanoTime();
			search_unsorted(arr, arr[arr.length-1]); // As MAX_VALUE doesn't exist in the array
			long t1 = System.nanoTime() - t0;
			if (t1 < res) {
				res = t1;
			}

		}

		return res;

	}
	

}