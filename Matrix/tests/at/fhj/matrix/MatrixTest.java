package at.fhj.matrix;

import static org.junit.Assert.*;

import org.junit.Test;

import at.fhj.matrix.Matrix;

public class MatrixTest {

	@Test
	public void testMatrixIntInt() {
		int numRows = 5, numCols = 7;
		Matrix m = new Matrix(numRows, numCols);
		checkSize("Matrix(int, int): ", m, numRows, numCols);
	}

	@Test
	public void testMatrixIntIntInt() {
		int numRows = 3, numCols = 4, val = 7;
		Matrix m = new Matrix(numRows, numCols, val);
		checkSize("Matrix(int, int, int): ", m, numRows, numCols);
		// check all elements
		for (int row=0; row<numRows; row++)
			for (int col=0; col<numCols; col++) 
				assertEquals("Matrix(int, int, int): daten["+row+"]["+col+"]mismatch", 
						val, m.get(row, col));
	}

	@Test
	public void testMatrixIntArrayArray() {
		int[][] aA = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; // A(0,1)=2
		int numRows = aA.length, numCols = aA[0].length;
		Matrix m = new Matrix(aA);
		
		checkSize("Matrix(int[][]): ", m, numRows, numCols);
		compare2array("Matrix(int[][]): ", m, aA);
		
		// My Implementation
		for (int i = 0; i < m.numRows; i++) {
			for (int j = 0; j < m.numCols; j++) {
				System.out.print(m.get(i, j));
				System.out.println(aA[i][j]);
			}
		}
		System.out.println(m.get(0, 1));
		// My Implementation
		
		aA[0][1]=9;
		System.out.println(m.get(0, 1));
		if (m.get(0, 1)!=2)
			fail("Matrix(int[][]): daten attribute is not a copy");
	}

	@Test
	public void testAdd() {
		// operands
		int[][] aA = { { 1, 2, 3 }, { 4, 5, 6 } }; // A(0,1)=2
		int[][] aB = { { 0, -2, 3 }, { 1, 1, 1 } }; 
		
		// expected results
		int[][] aExp = { { 1, 0, 6 }, { 5, 6, 7 } }; // A*(-1)
		
		int numRows = aExp.length, numCols = aExp[0].length;
		Matrix mA = testMatrix(aA);
		Matrix mB = testMatrix(aB);
		Matrix mExp = mA.add(mB);
		
		checkSize("add(Matrix): ", mExp, numRows, numCols);
		compare2array("add(Matrix): ", mExp, aExp);
		
	}

	@Test
	public void integrationtestAdd() {
		// operands
		int[][] aA = { { 1, 2, 3 }, { 4, 5, 6 } }; // A(0,1)=2
		int[][] aB = { { 0, -2, 3 }, { 1, 1, 1 } }; 
		
		// expected results
		int[][] aExp = { { 1, 0, 6 }, { 5, 6, 7 } }; // A*(-1)
		
		int numRows = aExp.length, numCols = aExp[0].length;
		Matrix mA = new Matrix(aA);
		Matrix mB = new Matrix(aB);
		Matrix mExp = mA.add(mB);
		
		checkSize("add(Matrix): ", mExp, numRows, numCols);
		compare2array("add(Matrix): ", mExp, aExp);
		
	}

	@Test
	public void testMultMatrix() {
		// operands
		int[][] aA = { { 3, 2, 1 }, { 1, 0, 2 } };
		int[][] aB = { { 1, 2 }, { 0, 1 }, { 4, 0 } };

		// expected result
		int[][] aExp = { { 7, 8 }, { 9, 2 } }; // B*C
		
		int numRows = aExp.length, numCols = aExp[0].length;
		Matrix mA = testMatrix(aA);
		Matrix mB = testMatrix(aB);
		Matrix mExp = mA.mult(mB);
		
		checkSize("mult(Matrix): ", mExp, numRows, numCols);
		compare2array("mult(Matrix): ", mExp, aExp);
	}
	
	@Test
	public void integrationtestMultMatrix() {
		// operands
		int[][] aA = { { 3, 2, 1 }, { 1, 0, 2 } };
		int[][] aB = { { 1, 2 }, { 0, 1 }, { 4, 0 } };

		// expected result
		int[][] aExp = { { 7, 8 }, { 9, 2 } }; // B*C
		
		int numRows = aExp.length, numCols = aExp[0].length;
		Matrix mA = new Matrix(aA);
		Matrix mB = new Matrix(aB);
		Matrix mExp = mA.mult(mB);
		
		checkSize("mult(Matrix): ", mExp, numRows, numCols);
		compare2array("mult(Matrix): ", mExp, aExp);
	}

	@Test
	public void testMultInt() {
		// operands
		int[][] aA = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; // A(0,1)=2

		// expected results
		int[][] aExp = { { -1, -2, -3 }, { -4, -5, -6 }, { -7, -8, -9 } }; // A*(-1)

		int numRows = aExp.length, numCols = aExp[0].length;
		Matrix mA = testMatrix(aA);
		Matrix mExp = mA.mult(-1);
		
		checkSize("mult(int): ", mExp, numRows, numCols);
		compare2array("mult(int): ", mExp, aExp);

	}

	@Test
	public void integrationtestMultInt() {
		// operands
		int[][] aA = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; // A(0,1)=2

		// expected results
		int[][] aExp = { { -1, -2, -3 }, { -4, -5, -6 }, { -7, -8, -9 } }; // A*(-1)

		int numRows = aExp.length, numCols = aExp[0].length;
		Matrix mA = new Matrix(aA);
		Matrix mExp = mA.mult(-1);
		
		checkSize("mult(int): ", mExp, numRows, numCols);
		compare2array("mult(int): ", mExp, aExp);

	}

	@Test
	public void testEqualsMatrix() {
		int[][] aA = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; // A(0,1)=2
		int[][] aB = { { 3, 2, 1 }, { 1, 0, 2 } };

		Matrix mA = testMatrix(aA);
		Matrix mB = testMatrix(aB);
		Matrix mC = testMatrix(aA);
		
		assertEquals("equals(Matrix): different matrices are equal", false, mA.equals(mB));
		assertEquals("equals(Matrix): identical matrices are not equal", true, mA.equals(mC));
		
	}

	@Test
	public void integrationtestEqualsMatrix() {
		int[][] aA = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } }; // A(0,1)=2
		int[][] aB = { { 3, 2, 1 }, { 1, 0, 2 } };

		Matrix mA = new Matrix(aA);
		Matrix mB = new Matrix(aB);
		Matrix mC = new Matrix(aA);
		
		assertEquals("equals(Matrix): different matrices are equal", false, mA.equals(mB));
		assertEquals("equals(Matrix): identical matrices are not equal", true, mA.equals(mC));
		
	}	
	
	// auxiliary methods
	private Matrix testMatrix(int [][]a) {
		Matrix m = new Matrix();
		m.numRows = a.length;
		m.numCols = a[0].length;
		m.daten = a;
		return m;
	}
	
	private void checkSize(String msg, Matrix m, int numRows, int numCols) {
		int daten[][] = m.daten;
		assertNotEquals(daten, null);
		assertEquals(msg+" numRows mismatch ", numRows, m.numRows());
		assertEquals(msg+" numCols mismatch ", numCols, m.numCols());
		assertEquals(msg+" daten.length mismatch ", numRows, daten.length);
		for (int row=0; row<numRows; row++) {
			assertEquals(msg+"daten["+row+"].length mismatch ", numCols, daten[row].length);	
		}
	}
	
	private void compare2array(String msg, Matrix result, int[][] expected) {
		int[][] daten = result.daten;
		assertEquals(msg+" daten.length mismatch ", expected.length, daten.length);
		for (int row=0; row<expected.length; row++) {
			assertEquals(msg+" daten["+row+"].length mismatch ", 
					expected[row].length, daten[row].length);	
			for (int col=0; col<expected[row].length; col++)
				assertEquals(msg +" daten["+row+"]["+col+"] mismatch ",
						expected[row][col], daten[row][col]);
		}
	}

}
