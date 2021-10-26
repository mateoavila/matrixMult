package matrixMult;


public class Classic {
	public static void mulitply(int[][] matrix1, int[][] matrix2, int size) {

	
		final int[][] result = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				result[i][j] = 0;
				for (int k = 0; k < size; k++) {
					result[i][j] += matrix1[i][k] * matrix2[k][j];
				} // end of k loop
			} // end of j loop
		}
		
//		System.out.println("classic" );
//		 for( int i = 0; i<size; i++) {
//		 System.out.println(Arrays.toString(result[i]));
//		 }

	}
}