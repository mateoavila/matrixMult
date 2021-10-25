package matrixMult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Strassen {
	public static int[][] run(int[][] matrix1, int[][] matrix2) {
		
			int n = matrix1.length;
			
	        int[][] res = new int[n][n];

	        // if the input matrix is 1x1
	        if (n == 1) {
	            res[0][0] = matrix1[0][0] * matrix2[0][0];
	        } else {

	            // first matrix
	            int[][] a = new int[n / 2][n / 2];
	            int[][] b = new int[n / 2][n / 2];
	            int[][] c = new int[n / 2][n / 2];
	            int[][] d = new int[n / 2][n / 2];
	            
	            // second matrix
	            int[][] e = new int[n / 2][n / 2];
	            int[][] f = new int[n / 2][n / 2];
	            int[][] g = new int[n / 2][n / 2];
	            int[][] h = new int[n / 2][n / 2];

	            // dividing matrix A into 4 parts
	            divideArray(matrix1, a, 0, 0);
	            divideArray(matrix1, b, 0, n / 2);
	            divideArray(matrix1, c, n / 2, 0);
	            divideArray(matrix1, d, n / 2, n / 2);

	            // dividing matrix B into 4 parts
	            divideArray(matrix2, e, 0, 0);
	            divideArray(matrix2, f, 0, n / 2);
	            divideArray(matrix2, g, n / 2, 0);
	            divideArray(matrix2, h, n / 2, n / 2);
	            
	            /** 
	              p1 = (a + d)(e + h)
	              p2 = (c + d)e
	              p3 = a(f - h)
	              p4 = d(g - e)
	              p5 = (a + b)h
	              p6 = (c - a) (e + f)
	              p7 = (b - d) (g + h)
	            **/
	           
	            int[][] p1 = run(addMatrices(a, d), addMatrices(e, h));
	            int[][] p2 = run(addMatrices(c,d),e);
	            int[][] p3 = run(a, subMatrices(f, h));           
	            int[][] p4 = run(d, subMatrices(g, e));
	            int[][] p5 = run(addMatrices(a,b), h );
	            int[][] p6 = run(subMatrices(c, a), addMatrices(e, f));
	            int[][] p7 = run(subMatrices(b, d), addMatrices(g, h));

	            
	           /**
	              C11 = p1 + p4 - p5 + p7
	              C12 = p3 + p5
	              C21 = p2 + p4
	              C22 = p1 - p2 + p3 + p6
	            **/
	           
	            int[][] C11 = addMatrices(subMatrices(addMatrices(p1, p4), p5), p7);
	            int[][] C12 = addMatrices(p3, p5);
	            int[][] C21 = addMatrices(p2, p4);
	            int[][] C22 = addMatrices(subMatrices(addMatrices(p1, p3), p2), p6);

	            // adding all sub array back into one
	            copySubArray(C11, res, 0, 0);
	            copySubArray(C12, res, 0, n / 2);
	            copySubArray(C21, res, n / 2, 0);
	            copySubArray(C22, res, n / 2, n / 2);
	        }
	        return res;
	    }
	    
	    
	    // Adding 2 matrices
	    public static int[][] addMatrices(int[][] a, int[][] b) {
	        int n = a.length;
	        int[][] res = new int[n][n];
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                res[i][j] = a[i][j] + b[i][j];
	            }
	        }
	        return res;
	    }

	    // Subtracting 2 matrices
	    public static int[][] subMatrices(int[][] a, int[][] b) {
	        int n = a.length;
	        int[][] res = new int[n][n];
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                res[i][j] = a[i][j] - b[i][j];
	            }
	        }
	        return res;
	    }

	    // print matrix
	    public static void printMatrix(int[][] a) {
	        int n = a.length;
	        System.out.println("Resultant Matrix ");
	        for (int i = 0; i < n; i++) {
	            for (int j = 0; j < n; j++) {
	                System.out.print(a[i][j] + "\t");
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }
	    
	    // divides array
	    public static void divideArray(int[][] P, int[][] C, int iB, int jB) 
	    {
	        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
	            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
	                C[i1][j1] = P[i2][j2];
	    }

	    // copies
	    public static void copySubArray(int[][] C, int[][] P, int iB, int jB) 
	    {
	        for(int i1 = 0, i2 = iB; i1 < C.length; i1++, i2++)
	            for(int j1 = 0, j2 = jB; j1 < C.length; j1++, j2++)
	                P[i2][j2] = C[i1][j1];
	    }  
	    

		
		
	}
}