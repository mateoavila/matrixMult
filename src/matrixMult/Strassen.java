package matrixMult;

public class Strassen {
	public static int[][] mulitply(int[][] matrix1, int[][] matrix2, int size) {
		int[][] resultMatrix = new int[size][size];
		strassen(matrix1, matrix2, resultMatrix, size);
		return resultMatrix;
	}

	public static void strassen(int[][] matrix1, int[][] matrix2, int[][] resultMatrix, int size) {

		if (size == 2) {
			resultMatrix[0][0] = (matrix1[0][0] * matrix2[0][0]) + (matrix1[0][1] * matrix2[1][0]);
			resultMatrix[0][1] = (matrix1[0][0] * matrix2[0][1]) + (matrix1[0][1] * matrix2[1][1]);
			resultMatrix[1][0] = (matrix1[1][0] * matrix2[0][0]) + (matrix1[1][1] * matrix2[1][0]);
			resultMatrix[1][1] = (matrix1[1][0] * matrix2[0][1]) + (matrix1[1][1] * matrix2[1][1]);
		} else {
			
			//matrix1 = A
			//matrix2 = B
			
			int[][] A11 = new int[size / 2][size / 2];
			int[][] A12 = new int[size / 2][size / 2];
			int[][] A21 = new int[size / 2][size / 2];
			int[][] A22 = new int[size / 2][size / 2];
			int[][] B11 = new int[size / 2][size / 2];
			int[][] B12 = new int[size / 2][size / 2];
			int[][] B21 = new int[size / 2][size / 2];
			int[][] B22 = new int[size / 2][size / 2];

			int[][] C = new int[size / 2][size / 2];
			int[][] D = new int[size / 2][size / 2];
			int[][] E = new int[size / 2][size / 2];
			int[][] F = new int[size / 2][size / 2];
			int[][] G = new int[size / 2][size / 2];
			int[][] H = new int[size / 2][size / 2];
			int[][] I = new int[size / 2][size / 2];

			deconstructMatrix(matrix1, A11, 0, 0);
			deconstructMatrix(matrix1, A12, 0, size / 2);
			deconstructMatrix(matrix1, A21, size / 2, 0);
			deconstructMatrix(matrix1, A22, size / 2, size / 2);
			deconstructMatrix(matrix2, B11, 0, 0);
			deconstructMatrix(matrix2, B12, 0, size / 2);
			deconstructMatrix(matrix2, B21, size / 2, 0);
			deconstructMatrix(matrix2, B22, size / 2, size / 2);

			
			
			strassen(add(A11, A22, size / 2), add(B11, B22, size / 2), C, size / 2);
			strassen(add(A21, A22, size / 2), B11, D, size / 2);
			strassen(A11, subtract(B12, B22, size / 2), E, size / 2);
			strassen(A22, subtract(B21, B11, size / 2), F, size / 2);
			strassen(add(A11, A12, size / 2), B22, G, size / 2);
			strassen(subtract(A21, A11, size / 2), add(B11, B12, size / 2), H, size / 2);
			strassen(subtract(A12, A22, size / 2), add(B21, B22, size / 2), I, size / 2);

			int[][] C11 = add(subtract(add(C, F, C.length), G, G.length), I, I.length);
			int[][] C12 = add(E, G, E.length);
			int[][] C21 = add(D, F, D.length);
			int[][] C22 = add(subtract(add(C, E, C.length), D, D.length), H, H.length);

			constructMatrix(C11, resultMatrix, 0, 0);
			constructMatrix(C12, resultMatrix, 0, size / 2);
			constructMatrix(C21, resultMatrix, size / 2, 0);
			constructMatrix(C22, resultMatrix, size / 2, size / 2);
		}
	}

	

	private static int[][] add(int[][] matrix1, int[][] matrix2, int size) {

		int[][] resultMatrix = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				resultMatrix[i][j] = matrix1[i][j] + matrix2[i][j];
			}
		}
		return resultMatrix;
	}

	private static int[][] subtract(int[][] matrix1, int[][] matrix2, int size) {

		int[][] resultMatrix = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				resultMatrix[i][j] = matrix1[i][j] - matrix2[i][j];
			}
		}
		return resultMatrix;
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
