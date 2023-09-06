import java.util.Arrays;
import java.util.Random;



class App {

	public static void main(String[] args) {

		/*System.out.println("Benchmark (unsorted) (ns)");
		System.out.println("Size(k)\t\tBest\tAvg. Unsorted\tAvg. Sorted\tAvg. Sorted (binary)\tWorst");
		for (int i = 1; i <= 10; i++) {
			int size = i * 1000;
			int[] sortedArr = ID1021.generateArray(size);
			int[] unsortedArr  = ID1021.shuffleArray(sortedArr);
			int max = sortedArr[sortedArr.length - 1];

			System.out.printf("%d\t\t%d\t%d\t\t%d\t\t%d\t\t\t%d\n",
			size/1000,
			benchmarkBestCase(sortedArr),
			benchmarkUnsortedAverage(unsortedArr, max),
			benchmarkSortedAverageIterate(sortedArr, max),
			benchmarkSortedAverageBinary(sortedArr, max),
			benchmarkWorstCase(sortedArr));
		}*/

		// System.out.println("Benchmark: Seach using binary search on sorted array 64M: " + benchmarkSortedAverageBinary(ID1021.generateArray(64000000), 64000000));

		System.out.println("Benchmark: Find common values in two arrays (us)");

		int size;
		int[] arrOne;
		int[] arrOneUnordered;
		int[] arrTwo;
		int[] arrTwoUnordered;

		System.out.println("Size\t\tUnordered(key)\t\tOrdered(binary)\t\tOrdered(optimized)");
		
		for (int i = 1; i <= 10; i++) {
			size = i * 100;
			arrOne = ID1021.generateArray(size);
			arrOneUnordered = ID1021.shuffleArray(arrOne);
			arrTwo = ID1021.generateArray(size);
			arrTwoUnordered = ID1021.shuffleArray(arrTwo);

			long s1, s2, s3 = Long.MAX_VALUE;

			for (int j = 0; j < 100; j++) {
				long n0 = System.nanoTime();
				arrayFindCommonsUnsorted(arrOneUnordered, arrTwoUnordered);
				long n1 = System.nanoTime() - n0;
				if (n1 < s1) {
					s1 = n1;
				}
			}

			for (int j = 0; j < 100; j++) {
				long n0 = System.nanoTime();
				arrayFindCommonsSortedBinary(arrOne, arrTwo);
				long n1 = System.nanoTime() - n0;
				if (n1 < s2) {
					s2 = n1;
				}
			}

			for (int j = 0; j < 100; j++) {
				long n0 = System.nanoTime();
				arrayFindCommonsSortedOptimized(arrOne, arrTwo);
				long n1 = System.nanoTime() - n0;
				if (n1 < s3) {
					s3 = n1;
				}
			}

			System.out.printf("%d\t\t%d\t\t\t%d\t\t\t%d\n", size, s1/1000, s2/1000, s3/1000);

		}

		/**
		 *  Size(k)         Unordered(key)          Ordered(binary)         Sorted(optimized)
			10              25086                   509                     72
			20              97376                   1043                    138
			30              216357                  1586                    196
			40              381210                  2139                    260
			50              581535                  2674                    327
			60              827270                  3287                    390
			70              1126104                 4038                    489
			80              1432989                 4393                    522
			90              1857448                 4954                    591
			100             2293623                 5583                    653
		 */

	}
	

	public static void arrayFindCommonsSortedKey(int[] arrOne, int[] arrTwo) {
		for (int i = 0; i < arrOne.length; i++) {
			if (ID1021.arrayContainsLinearSorted(arrTwo, arrOne[i])) { // Commond found.
				// Do something.
			}
		}
	}

	public static void arrayFindCommonsUnsorted(int[] arrOne, int[] arrTwo) {
		for (int i = 0; i < arrOne.length; i++) {
			if (ID1021.arrayContainsLinear(arrTwo, arrOne[i])) { // Commond found.
				// Do something.
			}
		}
	}

	public static void arrayFindCommonsSortedBinary(int[] arrOne, int[] arrTwo) {
		for (int i = 0; i < arrOne.length; i++) {
			if (ID1021.arrayBinarySearch(arrTwo, arrOne[i])) { // Commond found.
				// Doe something.
			}
		}
	}

	public static void arrayFindCommonsSortedOptimized(int[] arrOne, int[] arrTwo) {
		int indexOne = 0, indexTwo = 0;
		while (indexOne < arrOne.length && indexTwo < arrTwo.length) {
			if (arrOne[indexOne] > arrTwo[indexTwo]) {
				indexTwo++;
				continue;
			} else if (arrOne[indexOne] < arrTwo[indexTwo]) {
				indexOne++;
				continue;
			} else if (arrOne[indexOne] == arrTwo[indexTwo]) {
				// Do something.
				indexOne++;
                indexTwo++;
				continue;
			}
		}
	}


	public static long benchmarkUnsortedAverage(int[] arr, int max) {

		long res = Long.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 1000; i++) {

			// Average case.
			long tmp = 0;
			for (int j = 0; j < 100; j++) {

				int key = (int) (Math.random() * max);

				long t0 = System.nanoTime();
				ID1021.arrayContainsLinear(arr, key);
				tmp += System.nanoTime() - t0;

			}
			tmp = tmp / 100;
			if (tmp < res && tmp > 0) {
				res = tmp;
			}

		}

		return res;

	}

	public static long benchmarkSortedAverageIterate(int[] arr, int max) {

		long res = Long.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 1000; i++) {

			// Average case.
			long tmp = 0;
			for (int j = 0; j < 100; j++) {

				int key = (int) (Math.random() * max);

				long t0 = System.nanoTime();
				ID1021.arrayContainsLinearSorted(arr, key);
				tmp += System.nanoTime() - t0;

			}
			tmp = tmp / 100;
			if (tmp < res && tmp > 0) {
				res = tmp;
			}

		}

		return res;

	}

	
	public static long benchmarkSortedAverageBinary(int[] arr, int max) {

		long res = Long.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 1000; i++) {

			// Average case.
			long tmp = 0;
			for (int j = 0; j < 100; j++) {

				int key = (int) (Math.random() * max);

				long t0 = System.nanoTime();
				ID1021.arrayBinarySearch(arr, key);
				tmp += System.nanoTime() - t0;

			}
			tmp = tmp / 100;
			if (tmp < res && tmp > 0) {
				res = tmp;
			}

		}

		return res;

	}

	public static long benchmarkBestCase(int[] arr) {

		long res = Long.MAX_VALUE;

		ID1021.arrayContainsLinear(arr, arr[0]); // Reset cache.

		// Best and worst case.
		for (int i = 0; i < 10000; i++) {

			// Best case (First element)
			long t0 = System.nanoTime();
			ID1021.arrayContainsLinear(arr, arr[0]);
			long t1 = System.nanoTime() - t0;
			//System.out.println(t1);
			if (t1 < res && t1 > 0) { // t1 > 0 prevents too fast cases.
				res = t1;
			}

		}

		return res;

	}

	public static long benchmarkWorstCase(int[] arr) {

		long res = Long.MAX_VALUE;

		ID1021.arrayContainsLinear(arr, arr[0]); // Reset cache.

		// Best and worst case.
		for (int i = 0; i < 10000; i++) {

			// Worst case (doesn't find).
			long t0 = System.nanoTime();
			ID1021.arrayContainsLinear(arr, arr[arr.length-1]); // As MAX_VALUE doesn't exist in the array
			long t1 = System.nanoTime() - t0;
			if (t1 < res) {
				res = t1;
			}

		}

		return res;

	}
	

}