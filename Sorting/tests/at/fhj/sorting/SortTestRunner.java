package at.fhj.sorting;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class SortTestRunner {

	final static int maxPrint = 10;

	public static void main(String[] args) {

		final int minValue = 1; // smallest random number
		final int maxValue = 10000; // largest random number
		final int numSamples = 10000; // number of random numbers

		// run SortTest JUnit tests
		Result result = JUnitCore.runClasses(SortTest.class);
		System.out.println("Test Summary:");
		System.out.println("Number of tests: " + result.getRunCount());
		System.out.println("Number of failures: " + result.getFailureCount());
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.getDescription());
			System.out.println(failure.getException());
		}

		// print sorting samples with Arraysort, quicksort and bubblesort
		System.out.println("*******************");
		arraysort_samples(testSamples(minValue, maxValue, numSamples));
		System.out.println("*******************");
		quicksort_samples(testSamples(minValue, maxValue, numSamples));
		System.out.println("*******************");
		bubblesort_samples(testSamples(minValue, maxValue, numSamples));
		System.out.println("*******************");

	}

	/**
	 * Return a list of integer sorting samples
	 * 
	 * @param min
	 *            smallest random number possible
	 * @param max
	 *            highest random number possible
	 * @param no
	 *            number of random numbers
	 * @return
	 */
	private static Data[][] testSamples(int min, int max, int no) {
		Data[][] tests = new Data[SortTest.standardTests.length + 1][];
		for (int i = 0; i < SortTest.standardTests.length; i++) {
			tests[i] = SortTest.createDataList(SortTest.standardTests[i]);
		}
		// add a random test sample
		tests[SortTest.standardTests.length] = SortTest.randomDataList(min, max, no);
		return tests;
	}

	/**
	 * Sort samples with quicksort and print result
	 * 
	 * @param samples
	 *            List of sorting samples
	 */
	private static void quicksort_samples(Data[][] samples) {
		// Run Quicksort samples
		// sort all samples with quicksort
		System.out.println("Quicksort samples: ");
		for (Data[] test : samples) {
			System.out.print("Test [" + SortTest.printList(test, maxPrint) + " ]");
			Data.comparisions = 0;
			long startTime = System.currentTimeMillis();
			Sort.quicksort(test, 0, test.length - 1);
			long duration = System.currentTimeMillis() - startTime;
			String testResult = (SortTest.isSorted(test) ? " OK" : " FAILED");
			testResult += "(" + duration + "ms, " + Data.comparisions + " Vergleiche): ";
			testResult += "[ " + SortTest.printList(test, maxPrint) + " ]";
			System.out.println(testResult);
		}
	}

	/**
	 * Sort samples with quicksort and print result
	 * 
	 * @param samples
	 *            List of sorting samples
	 */
	private static void bubblesort_samples(Data[][] samples) {
		// Run bubblesort samples
		// sort all samples with bubblesort
		System.out.println("Bubblesort samples: ");
		for (Data[] test : samples) {
			System.out.print("Test [" + SortTest.printList(test, maxPrint) + " ]");
			Data.comparisions = 0;
			long startTime = System.currentTimeMillis();
			Sort.bubblesort(test);
			long duration = System.currentTimeMillis() - startTime;
			String testResult = (SortTest.isSorted(test) ? " OK" : " FAILED");
			testResult += "(" + duration + "ms, " + Data.comparisions + " Vergleiche): ";
			testResult += "[ " + SortTest.printList(test, maxPrint) + " ]";
			System.out.println(testResult);
		}
	}

	/**
	 * Sort samples with java.util.Arrays.sort() and print result
	 * 
	 * @param samples
	 *            List of sorting samples
	 */
	private static void arraysort_samples(Data[][] samples) {
		// arraysort samples
		// sort all samples with arraysort
		System.out.println("Arraysort samples: ");
		for (Data[] test : samples) {
			System.out.print("Test [" + SortTest.printList(test, maxPrint) + " ]");
			Data.comparisions = 0;
			long startTime = System.currentTimeMillis();
			java.util.Arrays.sort(test);
			long duration = System.currentTimeMillis() - startTime;
			String testResult = (SortTest.isSorted(test) ? " OK" : " FAILED");
			testResult += "(" + duration + "ms, " + Data.comparisions + " Vergleiche): ";
			testResult += "[ " + SortTest.printList(test, maxPrint) + " ]";
			System.out.println(testResult);
		}
	}
}
