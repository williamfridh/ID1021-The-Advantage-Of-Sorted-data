abstract class ID1021 {

    /**
     * Generates A Unique Sorted Array
     * 
     * @param size of the decired array.
     * @return a sorted array of given length.
     */
	public static int[] generateArray(int size) {
		int[] arr = new int[size];
		int intToAdd = 0;
		for (int i = 0; i < size; i++) {
			intToAdd += (int) (Math.random() * 10 + 1);
			arr[i] = intToAdd;
		}
		return arr;
	}



    /**
     * Create a Shuffelled Version of Given Array
     * 
     * @param arr to shuffle.
     * @return a shuffeled int array.
     */
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



    /**
     * Check if Array Contains Key Using a Linear Algorithm.
     * 
     * @param arr to serach trough.
     * @param key to search for.
     * @return a boolean, true if it contains key, false otherwise.
     */
    public static boolean arrayContainsLinear(int[] arr, int key) {
		for (int i = 0; i < arr.length ; i++) {
			if (arr[i] == key) {
				return true;
			}
		}
		return false;
	}



    /**
     * Check if Sorted Array Contains Key Using a Linear Algorithm.
     * 
     * @param arr to search trough.
     * @param key to search for.
     * @return a boolean value indicating if key exists in array or not.
     */
	public static boolean arrayContainsLinearSorted(int[] arr, int key) {
		for (int i = 0; i < arr.length ; i++) {
			if (arr[i] == key) {
				return true;
			} else if (arr[i] > key) {
				return false;
			}
		}
		return false;
	}



    /**
     * Binary Search For Key In Sorted Array.
     * 
     * @param arr to search trough.
     * @param key to search for.
     * @return true if key is found.
     */
    public static boolean arrayBinarySearch(int[] arr, int key) {
		int min = 0;
		int max = arr.length - 1;

		while (min <= max) {

			int mid = (min + max) / 2 ;

			if (arr[mid] == key) {
				return true;
			} else if (arr[mid] < key) {
				min = mid + 1;
				continue;
			} else if (arr[mid] > key) {
				max = mid - 1;
				continue;
			}

		}
		return false;
	}
    
}

