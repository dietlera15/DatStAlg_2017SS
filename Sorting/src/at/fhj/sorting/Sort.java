package at.fhj.sorting;

public class Sort {

	/**
	 * sort an array with quicksort algorithm
	 * 
	 * @param a
	 *            ... whole array of values to sort
	 * @param l
	 *            ... left boundary; where to start sorting
	 * @param r
	 *            ... right boundary; where to end sorting
	 */
	public static <T extends ICompare<T>> void quicksort(T[] a, int l, int r) {
		// Begin Implementation dietler
		if (r > l) {
			int i = l;
			int j = r;
			while (i < j) {
				while (a[i].lesserEqual(a[r]) && (i < r)) {
					i++;
				}
				while (a[j].greaterEqual(a[r]) && (j > l)) {
					j--;
				}
				if (i < j) {
					T temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
			T temp = a[i];
			a[i] = a[r];
			a[r] = temp;
			
			if (l < j) {
				quicksort(a, l, j);
			}
			if (r > i) {
				quicksort(a, i, r);
			}
		}
		// End Implementation
	}

	/**
	 * sort an array with bubblesort algorithm
	 * 
	 * @param a
	 *            ... whole array of values to sort
	 */
	public static <T extends ICompare<T>> void bubblesort(T[] a) {
		// Begin Implementation
		T temp;
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < a.length - i; j++) {
				if (!a[j].lesserEqual(a[j + 1])) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
		// End Implementation
	}

	// add your private auxiliary methods here
	// Begin Implementation

	// End Implementation

}
