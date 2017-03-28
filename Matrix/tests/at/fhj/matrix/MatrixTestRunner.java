package at.fhj.matrix;

import java.util.Formatter;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import at.fhj.matrix.Matrix;

public class MatrixTestRunner {

	public static void main(String[] args) {
		
		// run all MatrixTest test methods 
		Result result = JUnitCore.runClasses(MatrixTest.class);
		System.out.println("Test Summary:");
		System.out.println("Number of tests: " + result.getRunCount());
		System.out.println("Number of failures: " + result.getFailureCount());
		for (Failure failure: result.getFailures()) {
			System.out.println(failure.getDescription());
			System.out.println(failure.getException());
		}

		if (result.getFailureCount() > 0) {
			return;
		}
		// show Examples
		int[][] aA = { { 1, 2, 3 }, { 4, 5, 6 } }; // A(0,1)=2
		int[][] aB = { { 0, -2, 3 }, { 1, 1, 1 } };
		int[][] aC = { { 1, 2 }, { 0, 1 }, { 4, 0 } };

		Formatter fm = new java.util.Formatter(System.out);
		
		Matrix mA = new Matrix(aA);
		Matrix mB = new Matrix(aB);
		printMatrix(fm, "A = ", mA);
		printMatrix(fm, "B = ", mB);
		printMatrix(fm, "A + B = ", mA.add(mB));
		Matrix mC = new Matrix(aC);
		printMatrix(fm, "C = ", mC);
		printMatrix(fm, "B * C = ", mB.mult(mC));
		
		fm.close();
	}
	
	public static void printMatrix(Formatter fm, String header, Matrix m) {
		if (header == null || m == null)
			return;

		int indent = (header.length() < 10 ? 10 : header.length());

		if (m.numCols() == 0 || m.numRows() == 0) {
			fm.format(header + "EMPTY\n");
		}

		for (int row = 0; row < m.numRows(); row++) {
			if (row == m.numRows() / 2)
				fm.format("%-" + indent + "s|% 4d", header, m.get(row, 0));
			else
				fm.format("%-" + indent + "s|% 4d", "", m.get(row, 0));
			for (int col = 1; col < m.numCols(); col++)
				fm.format("% 4d", m.get(row, col));
			fm.format("|\n");
		}
		fm.format("\n");
	}

}
