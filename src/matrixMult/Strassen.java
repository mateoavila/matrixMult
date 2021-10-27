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
			
			//create an nxn matrix half the size of the last matrix
			int halfSize = size/2;
			
			int[][] A11 = new int[halfSize][halfSize];
			int[][] A12 = new int[halfSize][halfSize];
			int[][] A21 = new int[halfSize][halfSize];
			int[][] A22 = new int[halfSize][halfSize];
			int[][] B11 = new int[halfSize][halfSize];
			int[][] B12 = new int[halfSize][halfSize];
			int[][] B21 = new int[halfSize][halfSize];
			int[][] B22 = new int[halfSize][halfSize];

			int[][] P = new int[halfSize][halfSize];
			int[][] Q = new int[halfSize][halfSize];
			int[][] R = new int[halfSize][halfSize];
			int[][] S = new int[halfSize][halfSize];
			int[][] T = new int[halfSize][halfSize];
			int[][] U = new int[halfSize][halfSize];
			int[][] V = new int[halfSize][halfSize];

			tearDown(matrix1, A11, 0, 0);
			tearDown(matrix1, A12, 0, halfSize);
			tearDown(matrix1, A21, halfSize, 0);
			tearDown(matrix1, A22, halfSize, halfSize);
			tearDown(matrix2, B11, 0, 0);
			tearDown(matrix2, B12, 0, halfSize);
			tearDown(matrix2, B21, halfSize, 0);
			tearDown(matrix2, B22, halfSize, halfSize);

			
			// Run strassen recursively for each matrix  P - V
			
			int [][] A11addA22 = add(A11, A22, halfSize);
			int [][] B11addB22 = add(B11, B22, halfSize);
			strassen(A11addA22, B11addB22, P, halfSize);
			
			int [][] A21addA22 = add(A21, A22, halfSize);
			strassen(A21addA22, B11, Q, halfSize);
			
			int [][] B12subB22 = subtract(B12, B22, halfSize);
			strassen(A11, B12subB22, R, halfSize);
			
			int [][] B21subB11 = subtract(B21, B11, halfSize);
			strassen(A22, B21subB11, S, halfSize);
			
			int [][] A11addA12 = add(A11, A12, halfSize);
			strassen(A11addA12, B22, T, halfSize);
			
			int [][] A21subA11 = subtract(A21, A11, halfSize);
			int [][] B11addB12 = add(B11, B12, halfSize);
			strassen(A21subA11, B11addB12, U, halfSize);
			
			int [][] A12subA22 = subtract(A12, A22, halfSize);
			int [][] B21addB22 = add(B21, B22, halfSize);
			strassen(A12subA22, B21addB22 , V, halfSize);

			int[][] PaddS = add(P, S, P.length);
			int[][] C11 = add(subtract(PaddS, T, T.length), V, V.length);
			int[][] C12 = add(R, T, R.length);
			int[][] C21 = add(Q, S, Q.length);
			int[][] PaddR = add(P, R, P.length);
			int[][] C22 = add(subtract(PaddR, Q, Q.length), U, U.length);

			build(C11, resultMatrix, 0, 0);
			build(C12, resultMatrix, 0, halfSize);
			build(C21, resultMatrix, halfSize, 0);
			build(C22, resultMatrix, halfSize, halfSize);
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
	private static void build(int[][] initialMatrix, int[][] newMatrix, int a, int b) {

		int y = b;

		for (int i = 0; i < initialMatrix.length; i++) {
			for (int j = 0; j < initialMatrix.length; j++) {
				newMatrix[a][y++] = initialMatrix[i][j];
			}
			y = b;
			a++;
		}
	}
	private static void tearDown(int[][] initialMatrix, int[][] newMatrix, int a, int b) {

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
