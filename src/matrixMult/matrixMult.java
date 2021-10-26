package matrixMult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class matrixMult {

	public static void main(String[] args) {

		String filepath = "MatrixMultResults.csv";
		
		long start;
		long end;

		int dataSets = 100; // how many nxn matrixes will be made
		int e = 8; // highest exponent of 2 to go to
		int loop = 10; //how many times each matrix will be ran
		
		Random rand = new Random();

		// increases the exponent from 1 to desired number
		for (int exponent = 1; exponent <= e; exponent++) {

			int size = (int) Math.pow(2, exponent);

			long cTotal = 0;
			long dacTotal = 0;
			long sTotal = 0;

			int[][] matrix1 = new int[size][size];
			int[][] matrix2 = new int[size][size];

			System.out.println("Array Size: " + size + "x" + size);
			// loops for amount of data sets
			for (int i = 0; i < dataSets; i++) {
				long cCount = 0;
				long dacCount = 0;
				long sCount = 0;

				// fill matrix1 with random numbers;
				for (int j = 0; j < matrix1.length; j++) {
					for (int h = 0; h < matrix1[j].length; h++) {
						int randomInt = rand.nextInt(999);
						matrix1[j][h] = randomInt;
					}
				}

				// fill matrix2 with random numbers
				for (int j = 0; j < matrix2.length; j++) {
					for (int h = 0; h < matrix2[j].length; h++) {
						int randomInt = rand.nextInt(999);
						matrix2[j][h] = randomInt;
					}
				}

				// runs the same data set 'loop' amount of times to get the average
				for (int r = 0; r < loop; r++) {

					// CLASSIC
					start = System.nanoTime();
					Classic.mulitply(matrix1, matrix2, size);
					end = System.nanoTime();
					cCount = cCount + (end - start);

					// Divide and Conquer
					start = System.nanoTime();
					DivideAndConquer.mulitply(matrix1, matrix2, 0, 0, 0, 0, size);
					end = System.nanoTime();
					dacCount = dacCount + (end - start);

					// STRASSEN
					start = System.nanoTime();
					Strassen.mulitply(matrix1, matrix2, size);
					end = System.nanoTime();
					sCount = sCount + (end - start);

				}

				System.out.println("\ndata set " + i + " Classic average = " + cCount / loop + " nanoseconds");
				System.out.println("data set " + i + " D a C average = " + dacCount / loop + " nanoseconds");
				System.out.println("data set " + i + " Strassen average = " + sCount / loop + " nanoseconds");

				cTotal = cTotal + (cCount / loop);
				dacTotal = dacTotal + (dacCount / loop);
				sTotal = sTotal + (sCount / loop);

			}
			
			long cAvg = cTotal / dataSets;
			long dacAvg = dacTotal / dataSets;
			long sAvg = sTotal / dataSets;
			
			System.out.println("\nData Sets: " + dataSets);
			System.out.println("Array Size: " + size + "x" + size);
			System.out.println("Classic Mult average: 		 " + cAvg + " nanoseconds");
			System.out.println("Divide and Conquer Mult average: " + dacAvg + " nanoseconds");
			System.out.println("Strassen Mult average: 		 " + sAvg + " nanoseconds\n\n");
			
			saveRecord((size + "x" + size).toString(), cAvg, dacAvg, sAvg, filepath);
			
		}

		System.out.println("\nDONE");

	}
	
	
	
	public static void saveRecord(String matrixSize, long cAvg, long dacAvg, long sAvg, String filepath) {
		
		try {
			FileWriter fw = new FileWriter(filepath, true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			
			pw.println(matrixSize + "," + cAvg + "," +  dacAvg + "," + sAvg);
			pw.flush();
			pw.close();
			
		}catch(Exception E){
			
		}
		
	}

}

//int[][] test = new int[size][size];
//
//for( int t = 0; t<size; t++) {
//	 System.out.println(Arrays.toString(test[t]));
//	 }
