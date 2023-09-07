import java.util.Arrays;
import java.util.Random;



class App {

	public static void main(String[] args) {

/* 


		System.out.println("Size(k)\t\tBest\t\tAvg.\t\t(Avg./Size(k))\t\tWorst");
		for (int i = 1; i <= 10; i++) { // Size loop.
			int size = i * 100000;
			double bestCaseTime = Double.MAX_VALUE;
			double averageCaseTime = Double.MAX_VALUE;
			double worstCaseTime = Double.MAX_VALUE;
			for (int ii = 0; ii < 1000; ii++) { // Best time loop.
				//System.gc();
				int[] sortedArr = ID1021.generateArray(size);
				
				int bestCaseKey = sortedArr[(sortedArr.length - 1) / 2];
				int worstCaseKey = Integer.MAX_VALUE;
				int averageCaseKey = sortedArr[(int) (Math.random() * (sortedArr.length - 1))];

				double n0 = 0;
				double n1 = 0;
				double n = 0;

				n0 = System.nanoTime();
				for (int iii = 0; iii < 1000; iii++) {
					ID1021.arrayBinarySearch(sortedArr, bestCaseKey);
				}
				n1 = System.nanoTime();
				n = (n1 - n0) / 1000;
				if (n < bestCaseTime) {
					bestCaseTime = n;
				}

				n0 = System.nanoTime();
				for (int iii = 0; iii < 1000; iii++) {
					ID1021.arrayBinarySearch(sortedArr, averageCaseKey);
				}
				n1 = System.nanoTime();
				n = (n1 - n0) / 1000;
				if (n < averageCaseTime) {
					averageCaseTime = n;
				}

				n0 = System.nanoTime();
				for (int iii = 0; iii < 1000; iii++) {
					ID1021.arrayBinarySearch(sortedArr, worstCaseKey);
				}
				n1 = System.nanoTime();
				n = (n1 - n0) / 1000;
				if (n < worstCaseTime) {
					worstCaseTime = n;
				}

			}
			System.out.printf("%d\t&\t%.2f\t&\t%.2f\t&\t%.2f\t&\t\t%.2f\t\n",
			size,
			bestCaseTime,
			averageCaseTime,
			averageCaseTime/(size / 1000),
			worstCaseTime);
		}

*/






/* 
		

		System.out.println("Benchmark (unsorted) (ns)");
		System.out.println("Size(k)\t\tBest\t\tAvg.\t\t(Avg./Size(k))\t\tWorst");
		for (int i = 1; i <= 10; i++) {

			int size = i * 1000;
			int[] sortedArr = ID1021.generateArray(size);
			//int[] unsortedArr  = ID1021.shuffleArray(sortedArr);
			int max = sortedArr[sortedArr.length - 1];

			double bestCaseRes = benchmarkBestCase(sortedArr);
			double worstCaseRes = benchmarkWorstCase(sortedArr);
			double averageCaseRes = benchmarkSortedAverageBinary(sortedArr, max);

			System.out.printf("%d\t&\t%.2f\t&\t%.2f\t&\t%.2f\t&\t\t%.2f\t\n",
			size / 1000,
			bestCaseRes,
			averageCaseRes,
			averageCaseRes/(size / 1000),
			worstCaseRes);
		}

		// System.out.println("Benchmark: Seach using binary search on sorted array 64M: " + benchmarkSortedAverageBinary(ID1021.generateArray(64000000), 64000000));
*/
		System.out.println("Benchmark: Find common values in two arrays (us)");

		int size;
		int[] arrOne;
		int[] arrOneUnordered;
		int[] arrTwo;
		int[] arrTwoUnordered;

		System.out.println("Size\t\tUnordered(key)\t\tOrdered(binary)\t\tOrdered(optimized)\t\tOrdered(optimized)/Ordered(binary)");
		
		for (int i = 1; i <= 10; i++) {
			size = i * 100;

			double bestUnsortedTime = Double.MAX_VALUE;
			double bestSortedLinear = Double.MAX_VALUE;
			double bestSortedBinary = Double.MAX_VALUE;

			double n0, n1;

			for (int ii = 0; ii < 100; ii++) {

				arrOne = ID1021.generateArray(size);
				arrOneUnordered = ID1021.shuffleArray(arrOne);
				arrTwo = ID1021.generateArray(size);
				arrTwoUnordered = ID1021.shuffleArray(arrTwo);

				arrayFindCommonsUnsorted(arrOneUnordered, arrTwoUnordered);
				arrayFindCommonsSortedBinary(arrOne, arrTwo);
				arrayFindCommonsSortedOptimized(arrOne, arrTwo);

			}

			for (int ii = 0; ii < 100; ii++) {

				arrOne = ID1021.generateArray(size);
				arrOneUnordered = ID1021.shuffleArray(arrOne);
				arrTwo = ID1021.generateArray(size);
				arrTwoUnordered = ID1021.shuffleArray(arrTwo);

				n0 = System.nanoTime();
				arrayFindCommonsUnsorted(arrOneUnordered, arrTwoUnordered);
				n1 = System.nanoTime() - n0;
				if (n1 < bestUnsortedTime) {
					bestUnsortedTime = n1;
				}

				n0 = System.nanoTime();
				arrayFindCommonsSortedBinary(arrOne, arrTwo);
				n1 = System.nanoTime() - n0;
				if (n1 < bestSortedLinear) {
					bestSortedLinear = n1;
				}

				n0 = System.nanoTime();
				arrayFindCommonsSortedOptimized(arrOne, arrTwo);
				n1 = System.nanoTime() - n0;
				if (n1 < bestSortedBinary) {
					bestSortedBinary = n1;
				}

			}

			System.out.printf("%d&\t\t%.2f&\t\t\t%.2f&\t\t\t%.2f&\t\t\t%.2f\n", size, bestUnsortedTime/1000, bestSortedLinear/1000, bestSortedBinary/1000, bestSortedBinary/bestSortedLinear);

		}

	}
	

	/**
	 * Benchmark Finding Commons in Two Sorted Arrays Using Key Method.
	 * @param arrOne to compare.
	 * @param arrTwo to compare.
	 */
	public static void arrayFindCommonsSortedKey(int[] arrOne, int[] arrTwo) {
		for (int i = 0; i < arrOne.length; i++) {
			if (ID1021.arrayContainsLinearSorted(arrTwo, arrOne[i])) {
				// Do nothing.
			}
		}
	}
	

	/**
	 * Benchmark Finding Commons in Two Unsorted Arrays Using Key Method.
	 * @param arrOne to compare.
	 * @param arrTwo to compare.
	 */
	public static void arrayFindCommonsUnsorted(int[] arrOne, int[] arrTwo) {
		for (int i = 0; i < arrOne.length; i++) {
			if (ID1021.arrayContainsLinear(arrTwo, arrOne[i])) {
				// Do nothing.
			}
		}
	}

	

	/**
	 * Benchmark Finding Commons in Two Sorted Arrays Using Binary Search.
	 * @param arrOne to compare.
	 * @param arrTwo to compare.
	 */
	public static void arrayFindCommonsSortedBinary(int[] arrOne, int[] arrTwo) {
		for (int i = 0; i < arrOne.length; i++) {
			if (ID1021.arrayBinarySearch(arrTwo, arrOne[i])) {
				// Do nothing.
			}
		}
	}

	

	/**
	 * Benchmark Finding Commons in Two Sorted Arrays Using Optimized Method.
	 * @param arrOne to compare.
	 * @param arrTwo to compare.
	 */
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
				// Do nothing.
				indexOne++;
                indexTwo++;
				continue;
			}
		}
	}


	public static double benchmarkUnsortedAverage(int[] arr, int max) {

		double res = Double.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 10000; i++) {

			// Average case.
			double tmp = 0;
			for (int j = 0; j < 100; j++) {

				int key = (int) (Math.random() * max);

				double t0 = System.nanoTime();
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

	public static double benchmarkSortedAverageIterate(int[] arr, int max) {

		double res = Double.MAX_VALUE;

		// Best and worst case.
		for (int i = 0; i < 1000; i++) {

			// Average case.
			double tmp = 0;
			for (int j = 0; j < 100; j++) {

				int key = (int) (Math.random() * max);

				double t0 = System.nanoTime();
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

	





	public static double benchmarkSortedAverageBinary(int[] arr, int max) {

		double res = Double.MAX_VALUE;

		System.gc();
		//ID1021.arrayBinarySearch(arr, arr[0]); // Warp-up.

		// Best and worst case.
		for (int i = 0; i < 1000; i++) {

			// Average case.
			double tmp = 0;
			for (int j = 0; j < 1000; j++) {
		//System.gc();

				int key = arr[(int) (Math.random() * (arr.length - 1))];
				ID1021.arrayBinarySearch(arr, key);

				double t0 = System.nanoTime();
				ID1021.arrayBinarySearch(arr, key);
				double t1 = System.nanoTime();
				tmp += t1 - t0;

			}
			tmp = tmp / 1000;
			if (tmp < res && tmp > 0) {
				res = tmp;
			}

		}

		return res;

	}





	public static double benchmarkBestCase(int[] arr) {

		double res = Double.MAX_VALUE;

		System.gc();
		//ID1021.arrayBinarySearch(arr, arr[(arr.length-1)/2]); // Warp-up.

		// Best and worst case.
		for (int i = 0; i < 1000; i++) {

			// Best case (First element)
			double tmp = 0;
			for (int j = 0; j < 1000; j++) {
		//System.gc();
				double t0 = System.nanoTime();
				ID1021.arrayBinarySearch(arr, arr[(arr.length-1)/2]);
				double t1 = System.nanoTime();
				tmp += t1 - t0;
			}
			tmp = tmp / 1000;
			//System.out.println(t1);
			if (tmp < res && tmp > 0) { // t1 > 0 prevents too fast cases.
				res = tmp;
			}

		}

		return res;

	}






	public static double benchmarkWorstCase(int[] arr) {

		double res = Double.MAX_VALUE;

		System.gc();
		//ID1021.arrayBinarySearch(arr, arr[arr.length-1]); // Warp-up.

		// Best and worst case.
		for (int i = 0; i < 1; i++) {

		//System.gc();
			// Worst case (doesn't find).
			double tmp = 0;
			for (int j = 0; j < 1; j++) {
		//System.gc();
				double t0 = System.nanoTime();
				ID1021.arrayBinarySearch(arr, Integer.MAX_VALUE); // As MAX_VALUE doesn't exist in the array
				double t1 = System.nanoTime();
				tmp += t1 - t0;
			}
			tmp = tmp / 1;

			if (tmp < res && tmp > 0) {
				res = tmp;
			}

		}

		return res;

	}
	











}