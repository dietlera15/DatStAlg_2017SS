package at.fhj.hashing;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import at.fhj.hashing.HashObject;
import at.fhj.hashing.HashTable;

public class HashingTestRunner {

	public static void main(String[] args) {

		// run HashingTest JUnit tests
		Result result = JUnitCore.runClasses(HashingTest.class);
		System.out.println("Test Summary:");
		System.out.println("Number of tests: " + result.getRunCount());
		System.out.println("Number of failures: " + result.getFailureCount());
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		// test sample with Integer values	
		Integer[] testData1 = {7, 14, 21, 28, 35, 42, 49};		
		HashTable table = new HashTable(7);
		System.out.println("\nSample hash table size: 7");
		printTestData(testData1);
		insertValues(table, testData1);
		printTable(table);

		// test sample with String values	
		String[] testData2 = {"Joe", "Jack", "Alice", "Bob",  "Anne"};		
		table = new HashTable(7);
		System.out.println("\nSample hash table size: 7");
		printTestData(testData2);
		insertValues(table, testData2);
		printTable(table);
	
	}
	
	private static void insertValues(HashTable table, Object[] values) {
		for (Object value: values) {
			table.insert(value);
		}
	}
	
	private static void printTestData(Object[] testData) {
		System.out.print("Insert: ");
		for (Object val: testData) System.out.print(val.toString() + "(" + val.hashCode()+") ");
		System.out.println();
		
	}
	
	private static void printTable(HashTable table) {
		System.out.print("HashTable: ");
		int i=0;
		for (HashObject obj: table.getTable()) { 
			String value;
			if (obj == null) value = "empty";
			else if (obj.getData() == null) value = "free";
			else value = obj.getData().toString();
			System.out.print("["+i+":"+value+"] ");
			i++;
		}
		System.out.println();
	}

}
