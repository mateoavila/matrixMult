package matrixMult;

public class Strassen {
	public static int[][] mulitply(int[][] matrix1, int[][] matrix2, int n) {
		int[][] matrix3 = new int[n][n];
		strassen(matrix1, matrix2, matrix3, n);
		return matrix3;
	}

	public static void strassen(int[][] matrix1, int[][] matrix2, int[][] matrix3, int n) {

		if (n == 2) {
			matrix3[0][0] = (matrix1[0][0] * matrix2[0][0]) + (matrix1[0][1] * matrix2[1][0]);
			matrix3[0][1] = (matrix1[0][0] * matrix2[0][1]) + (matrix1[0][1] * matrix2[1][1]);
			matrix3[1][0] = (matrix1[1][0] * matrix2[0][0]) + (matrix1[1][1] * matrix2[1][0]);
			matrix3[1][1] = (matrix1[1][0] * matrix2[0][1]) + (matrix1[1][1] * matrix2[1][1]);
		} else {
			int[][] A11 = new int[n / 2][n / 2];
			int[][] A12 = new int[n / 2][n / 2];
			int[][] A21 = new int[n / 2][n / 2];
			int[][] A22 = new int[n / 2][n / 2];
			int[][] B11 = new int[n / 2][n / 2];
			int[][] B12 = new int[n / 2][n / 2];
			int[][] B21 = new int[n / 2][n / 2];
			int[][] B22 = new int[n / 2][n / 2];

			int[][] P = new int[n / 2][n / 2];
			int[][] Q = new int[n / 2][n / 2];
			int[][] R = new int[n / 2][n / 2];
			int[][] S = new int[n / 2][n / 2];
			int[][] T = new int[n / 2][n / 2];
			int[][] U = new int[n / 2][n / 2];
			int[][] V = new int[n / 2][n / 2];

			deconstructMatrix(matrix1, A11, 0, 0);
			deconstructMatrix(matrix1, A12, 0, n / 2);
			deconstructMatrix(matrix1, A21, n / 2, 0);
			deconstructMatrix(matrix1, A22, n / 2, n / 2);
			deconstructMatrix(matrix2, B11, 0, 0);
			deconstructMatrix(matrix2, B12, 0, n / 2);
			deconstructMatrix(matrix2, B21, n / 2, 0);
			deconstructMatrix(matrix2, B22, n / 2, n / 2);

			strassen(add(A11, A22, n / 2), add(B11, B22, n / 2), P, n / 2);
			strassen(add(A21, A22, n / 2), B11, Q, n / 2);
			strassen(A11, subtract(B12, B22, n / 2), R, n / 2);
			strassen(A22, subtract(B21, B11, n / 2), S, n / 2);
			strassen(add(A11, A12, n / 2), B22, T, n / 2);
			strassen(subtract(A21, A11, n / 2), add(B11, B12, n / 2), U, n / 2);
			strassen(subtract(A12, A22, n / 2), add(B21, B22, n / 2), V, n / 2);

			int[][] C11 = add(subtract(add(P, S, P.length), T, T.length), V, V.length);
			int[][] C12 = add(R, T, R.length);
			int[][] C21 = add(Q, S, Q.length);
			int[][] C22 = add(subtract(add(P, R, P.length), Q, Q.length), U, U.length);

			constructMatrix(C11, matrix3, 0, 0);
			constructMatrix(C12, matrix3, 0, n / 2);
			constructMatrix(C21, matrix3, n / 2, 0);
			constructMatrix(C22, matrix3, n / 2, n / 2);
		}
	}

	

	private static int[][] add(int[][] matrix1, int[][] matrix2, int n) {

		int[][] matrix3 = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix3[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		return matrix3;
	}

	private static int[][] subtract(int[][] matrix1, int[][] matrix2, int n) {

		int[][] matrix3 = new int[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				matrix3[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		return matrix3;
	}
	private static void constructMatrix(int[][] initialMatrix, int[][] newMatrix, int a, int b) {

		int y = b;

		for (int i = 0; i < initialMatrix.length; i++) {
			for (int j = 0; j < initialMatrix.length; j++) {
				newMatrix[a][y++] = initialMatrix[i][j];
			}
			y = b;
			a++;
		}
	}
	private static void deconstructMatrix(int[][] initialMatrix, int[][] newMatrix, int a, int b) {

		int y = b;
		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix.length; j++) {
				newMatrix[i][j] = initialMatrix[a][y++];
			}
			y = b;
			a++;
		}
	}

}
