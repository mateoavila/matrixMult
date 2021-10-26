package matrixMult;

import java.util.Arrays;

public class DivideAndConquer {
	
	public static int[][] mulitply(int[][] matrix1, int[][] matrix2, int rowA, int colA, int rowB, int colB, int n) {
		int[][] matrix3 = new int[n][n];

		if (n == 1) {
			matrix3[0][0] = matrix1[rowA][colA] * matrix2[rowB][colB];
		} else {
			int newSize = n / 2;
			// C11 = A11 * B11 + A12 * B21
			add(matrix3, mulitply(matrix1, matrix2, rowA, colA, rowB, colB, newSize), // A11*B11
					mulitply(matrix1, matrix2, rowA, colA + newSize, rowB + newSize, colB, newSize), // A12*B21
					0, 0);// C11
			// C12 = A11 * B12 + A12 * B22
			add(matrix3, mulitply(matrix1, matrix2, rowA, colA, rowB, colB + newSize, newSize), // A11*B12
					mulitply(matrix1, matrix2, rowA, colA + newSize, rowB + newSize, colB + newSize, newSize), // A12*B22
					0, newSize);// C12
			// C21 = A21 * B11 + A22 * B21
			add(matrix3, mulitply(matrix1, matrix2, rowA + newSize, colA, rowB, colB, newSize), // A21*B11
					mulitply(matrix1, matrix2, rowA + newSize, colA + newSize, rowB + newSize, colB, newSize), // A22*B21
					newSize, 0);// C21
			// C22 = A21 * B12 + A22 * B22
			add(matrix3, mulitply(matrix1, matrix2, rowA + newSize, colA, rowB, colB + newSize, newSize), // A21*B12
					mulitply(matrix1, matrix2, rowA + newSize, colA + newSize, rowB + newSize, colB + newSize, newSize), // A22*B22
					newSize, newSize);// C22
		}
		return matrix3;
	}


	private static void add(int[][] matrix3, int[][] matrix1, int[][] matrix2, int rowM3, int colM3) {
		int n = matrix1.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix3[i + rowM3][j + colM3] = matrix1[i][j] + matrix2[i][j];
			}
		}
	}
}