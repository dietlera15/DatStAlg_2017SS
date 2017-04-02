package at.fhj.sorting;

import static org.junit.Assert.fail;

import org.junit.Test;

public class SortTest {
	final static int[][] standardTests = { { 5, 8, 13, 1, 4, 9, 7 }, { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
			{ 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 }, { 1, 1, 1, 1, 1 }, {} };
	final static int minRandom = 0; // kleinste Zufallszahl
	final static int maxRandom = 10000000; // groesste Zufallszahl
	final static int numRandom = 1000; // Anzahl der Zufallszahlen
	final static int maxPrint = 10;
	final static Data[] random = randomDataList(minRandom, maxRandom, numRandom);

	@Test
	public void testQuicksort() {
		Data[][] tests = new Data[standardTests.length + 1][];
		for (int i = 0; i < standardTests.length; i++) {
			tests[i] = createDataList(standardTests[i]);
		}
		tests[standardTests.length] = random.clone();
		for (Data[] test : tests) {
			String unsorted = printList(test, maxPrint);
			Sort.quicksort(test, 0, test.length - 1);
			if (!isSorted(test)) {
				fail("Failed to sort " + unsorted + ", result: " + printList(test, maxPrint));
			}
		}
	}

	@Test
	public void testBubblesort() {
		Data[][] tests = new Data[standardTests.length + 1][];
		for (int i = 0; i < standardTests.length; i++) {
			tests[i] = createDataList(standardTests[i]);
		}
		tests[standardTests.length] = random.clone();
		for (Data[] test : tests) {
			String unsorted = printList(test, maxPrint);
			Sort.bubblesort(test);
			if (!isSorted(test)) {
				fail("Failed to sort " + unsorted + ", result: " + printList(test, maxPrint));
			}
		}
	}

	static Data[] createDataList(int[] a) {
		// erstellt einen Data Array aus einem Integer Array
		Data[] ret = new Data[a.length];

		for (int i = 0; i < a.length; i++) {
			ret[i] = new Data(a[i]);
		}
		return ret;
	}

	static Data[] randomDataList(int start, int end, int n) {
		// erstellt einen zufaelligen Data Array der Laenge n mit Zahlen
		// zwischen
		// start und end
		Data[] ret = new Data[n];
		for (int i = 0; i < ret.length; i++) {
			double rand = Math.random();
			ret[i] = new Data((int) Math.round(start + (end - start) * rand));
		}
		return ret;
	}

	static boolean isSorted(Data[] a) {
		// ueberprueft, ob der Array a sortiert vorliegt
		for (int i = 0; i < a.length - 1; i++) {
			if (!a[i].lesserEqual(a[i + 1])) // liegt eine Inversion vor ?
				return false;
		}

		return true;
	}

	static String printList(Data[] list, int start, int end) {
		String ret = "";
		if (start < 0 || start > end || end > list.length)
			return "";
		for (int i = start; i <= end; i++) {
			ret = ret + " " + list[i].toString();
		}
		return ret;
	}

	static String printList(Data[] list, int max) {
		if (list.length <= max) {
			return printList(list, 0, list.length - 1);
		} else {
			return printList(list, 0, max / 2 - 1) + " ... (" + (list.length - max) + " weitere) ..."
					+ printList(list, list.length - max / 2, list.length - 1);
		}
	}

}
