package matrixMult;

import java.util.Arrays;
import java.util.Random;

public class matrixMult {

	public static void main(String[] args) {
		
		long start;
		long end;
		int n = 128;
		Random rand = new Random();
		final int[][] matrix1 = new int[n][n];
		final int[][] matrix2 = new int[n][n];
		
		for(int i = 0; i < matrix1.length; i++) {
			for(int j = 0; j <matrix1[i].length; j++) {
				int randomInt = rand.nextInt(999);
				matrix1[i][j] = randomInt;
			}
		}
		
		for(int i = 0; i < matrix2.length; i++) {
			for(int j = 0; j <matrix2[i].length; j++) {
				int randomInt = rand.nextInt(999);
				matrix2[i][j] = randomInt;
			}
		}
		
		//CLASSIC
		start =System.currentTimeMillis();
		Classic.run(matrix1, matrix2, n);
		end = System.currentTimeMillis();
		System.out.println("time taken: " + (end - start) + " milliseconds");
		
		
		//Divide and Conquer
		System.out.println("\nDivide and Conquer Mult");
		start = System.currentTimeMillis();
		DivideAndConquer.run(matrix1, matrix2, 0, 0, 0, 0, n);
		end = System.currentTimeMillis();
		System.out.println("time taken: " + (end - start) + " milliseconds");
		
		
		//STRASSEN
		System.out.println("\nStrassen Mult");
		start = System.currentTimeMillis();
		Strassen.run(matrix1, matrix2);
		end = System.currentTimeMillis();
		System.out.println("time taken: " + (end - start) + " milliseconds");
		
		
	}

}
