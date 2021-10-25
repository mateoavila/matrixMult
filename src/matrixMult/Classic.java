package matrixMult;

import java.util.Arrays;

public class Classic {
	public static void run(int[][] matrix1, int[][] matrix2, int n) {
				
		System.out.println("Classic Mult");
		
		final int[][] result = new int[n][n];
		
		
		for(int i=0;i<n;i++){    
			for(int j=0;j<n;j++){    
				result[i][j]=0;      
					for(int k=0;k<n;k++){      
						result[i][j] += matrix1[i][k] * matrix2[k][j];      
					}//end of k loop  
			}//end of j loop    
		}    
		
		//for( int i = 0; i<n; i++) {
		//	System.out.println(Arrays.toString(result[i]));
		//}
		
		
	}
}