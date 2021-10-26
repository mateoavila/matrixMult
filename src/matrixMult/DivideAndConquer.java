package matrixMult;

public class DivideAndConquer {
	
	public static int[][] mulitply(int[][] matrix1, int[][] matrix2, int rowM1, int colM1, int rowM2, int colM2, int size) {
		
		int[][] resultMatrix = new int[size][size];

		if (size == 1) {
			resultMatrix[0][0] = matrix1[rowM1][colM1] * matrix2[rowM2][colM2];
		} else {
			int newSize = size / 2;
			
			// matrix1 = A
			// matrix2 = B
			
			// RM11 = 11xB11 + A12xB21
			int [][] A11xB11 = mulitply(matrix1, matrix2, rowM1, colM1, rowM2, colM2, newSize);
			int [][] A12xB21 = mulitply(matrix1, matrix2, rowM1, colM1 + newSize, rowM2 + newSize, colM2, newSize);
			add(resultMatrix, A11xB11, A12xB21,	0, 0); //RM11
			
			// RM12 = A11xB12 + A12xB22
			int [][] A11xB12 = mulitply(matrix1, matrix2, rowM1, colM1, rowM2, colM2 + newSize, newSize);
			int [][] A12xB22 = mulitply(matrix1, matrix2, rowM1, colM1 + newSize, rowM2 + newSize, colM2 + newSize, newSize);
			add(resultMatrix, A11xB12, A12xB22,	0, newSize);// RM12
			
			// RM21 = A21xB11 + A22xB21
			int [][] A21xB11 = mulitply(matrix1, matrix2, rowM1 + newSize, colM1, rowM2, colM2, newSize);
			int [][] A22xB21 = mulitply(matrix1, matrix2, rowM1 + newSize, colM1 + newSize, rowM2 + newSize, colM2, newSize);
			add(resultMatrix, A21xB11, A22xB21, newSize, 0);// RM21
			
			// RM22 = A21xB12 + A22xB22
			int [][] A21xB12 = mulitply(matrix1, matrix2, rowM1 + newSize, colM1, rowM2, colM2 + newSize, newSize);
			int [][] A22xB22 = mulitply(matrix1, matrix2, rowM1 + newSize, colM1 + newSize, rowM2 + newSize, colM2 + newSize, newSize);
			add(resultMatrix, A21xB12, A22xB22, newSize, newSize);// RM22
		}
		return resultMatrix;
	}


	private static void add(int[][] resultMatrix, int[][] matrix1, int[][] matrix2, int rowRM, int colRM) {
		int size = matrix1.length;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				resultMatrix[i + rowRM][j + colRM] = matrix1[i][j] + matrix2[i][j];
			}
		}
	}
}