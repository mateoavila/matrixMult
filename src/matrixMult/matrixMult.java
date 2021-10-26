package matrixMult;

import java.util.Arrays;
import java.util.Random;

public class matrixMult {

	public static void main(String[] args) {

		int start;
		int end;
		int cCount = 0;
		int dacCount = 0;
		int sCount = 0;
		int dataSet = 1000;
		
		int n = 256;
		Random rand = new Random();
		final int[][] matrix1 = new int[n][n];
		final int[][] matrix2 = new int[n][n];

		
		
		for (int i =0; i < dataSet; i++) {
			for (int j = 0; j < matrix1.length; j++) {
				for (int h = 0;  h < matrix1[j].length; h++) {
					int randomInt = rand.nextInt(999);
					matrix1[j][h] = randomInt;
				}
			}

			for (int j = 0; j < matrix1.length; j++) {
				for (int h = 0;  h < matrix1[j].length; h++) {
					int randomInt = rand.nextInt(999);
					matrix1[j][h] = randomInt;
				}
			}
			
			// CLASSIC
			start = System.currentTimeMillis();
			Classic.mulitply(matrix1, matrix2, n);
			end = System.currentTimeMillis();
			cCount = (end - start);
			
			// Divide and Conquer
			start = System.currentTimeMillis();
			DivideAndConquer.mulitply(matrix1, matrix2,0,0,0,0, n);
			end = System.currentTimeMillis();
			dacCount = (end - start);
			
			
			// STRASSEN
			//System.out.println("\nStrassen Mult");
			start = System.currentTimeMillis();
			Strassen.mulitply(matrix1, matrix2,n);
			end = System.currentTimeMillis();
			sCount = (end - start);
		}
		
		System.out.println("data sets: " + dataSet);
		System.out.println("array size: " + n);
		System.out.println("Classic Mult average: " + cCount + " milliseconds");
		System.out.println("Divide and Conquer Mult average: " + dacCount + " milliseconds");
		System.out.println("Strassen Mult average: " + sCount + " milliseconds");
	}

}
