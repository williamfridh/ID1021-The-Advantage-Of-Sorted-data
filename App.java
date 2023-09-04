import java.util.Arrays;
import java.util.Random;



class App {

	public static void main(String[] args) {

		//int[] arr = generateSortedArr(10);
		//System.out.println(Arrays.toString(arr));
		//System.out.println(Arrays.toString(shuffleArray(arr)));

		/*System.out.println("Benchmark (unsorted) (ns)");
		System.out.println("Size(k)\t\tBest\tAvg. Unsorted\tAvg. Sorted\tAvg. Sorted (binary)\tWorst");
		for (int i = 1; i <= 10; i++) {
			int size = i * 1000;
			int[] sortedArr = generateArrey(size);
			int[] unsortedArr  = shuffleArray(sortedArr);
			int max = sortedArr[sortedArr.length - 1];

			System.out.printf("%d\t\t%d\t%d\t\t%d\t\t%d\t\t\t%d\n",
			size/1000,
			benchmarkBestCase(sortedArr),
			benchmarkUnsortedAverage(unsortedArr, max),
			benchmarkSortedAverageIterate(sortedArr, max),
			benchmarkSortedAverageBinary(sortedArr, max),
			benchmarkWorstCase(sortedArr));
		}
		/*
		 *	Output: 
		 *  Size(k)         Best    Avg. Unsorted   Avg. Sorted     Avg. Sorted (binary)    Worst
		 *  1               99      293             343             105                     299
			2               99      521             585             113                     599
			3               99      931             1054            117                     799
			4               99      1218            1126            120                     1099
			5               99      1482            1566            121                     1299
			6               99      1746            1864            126                     1599
			7               99      2089            2106            127                     1799
			8               99      2320            2139            132                     2099
			9               99      2618            2428            130                     2299
		 *  10              99      2285            2141            140                     2599
			20              99      4085            3631            148                     5199
			30              99      6356            7104            148                     7699
			40              99      8669            9683            155                     10299
			50              99      10986           11898           152                     11699
			60              99      9448            9611            112                     10899
			70              99      10646           11263           113                     12599
			80              99      12571           12929           115                     14399
			90              99      13662           15081           117                     16199
			100             99      21704           20380           177                     25599
			200             99      36758           27193           132                     36100
			300             99      59008           51153           134                     54199
			400             99      80376           67347           142                     72199
			500             99      102170          84266           145                     90199
			600             99      123838          104770          159                     108199
			700             99      151489          123218          157                     128299
			800             99      165989          136304          157                     144299
			900             99      188666          156406          173                     163100
			1000            99      226035          170481          177                     180400
		 */
		/*
		System.out.println("Benchmark: Seach using binary search on sorted array 64M: " + benchmarkSortedAverageBinary(generateArrey(64000000), 64000000));
		729ns */


	}

	public static int[] generateArrey(int size) {
		int[] arr = new int[size];
		int intToAdd = 0;
		for (int i = 0; i < size; i++) {
			intToAdd += (int) (Math.random() * 10 + 1);
			arr[i] = intToAdd;
		}
		return arr;
	}

	public static int[] shuffleArray(int[] arr) {
		int[] newArray = new int[arr.length];
		for (int i = 0; i < newArray.length; i++) {
			newArray[i] = arr[i];
		}
		for (int i = 0; i < newArray.length; i++) {
			int tmp = newArray[i];
			int indexTwo = (int) (Math.random() * newArray.length);
			newArray[i] = newArray[indexTwo];
			newArray[indexTwo] = tmp;
		}
		return newArray;
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

	public static boolean searchSorted(int[] array, int key) {
		for (int index = 0; index < array.length ; index++) {
			if (array[index] == key) {
				return true;
			} else if (array[index] > key) {
				return false;
			}
		}
		return false;
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
				search_unsorted(arr, key);
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
				searchSorted(arr, key);
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
				binarySearch(arr, key);
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

		search_unsorted(arr, arr[0]); // Reset cache.

		// Best and worst case.
		for (int i = 0; i < 10000; i++) {

			// Best case (First element)
			long t0 = System.nanoTime();
			search_unsorted(arr, arr[0]);
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

		search_unsorted(arr, arr[0]); // Reset cache.

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