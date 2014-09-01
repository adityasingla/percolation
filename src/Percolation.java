
/*
 * Class: Percolation class. This class will implement the WeightedQuickUnionUF class
 * present in algs4.jar. Please remember that this is not part 
 * of standard java library
 */
public class Percolation {
	/*
	 * Variable Declaration  - Declare a quickUnion variable and percolationMatrix. 
	 * Matrix range is the value passed to the constructor
	 */
	private WeightedQuickUnionUF quickUnion;
	private Boolean [][] percolationMatrix;
	private int matrixRange;
	
	/*
	 * Constructor : This takes the size of the percolation matrix.
	 * We will initialize N n*N matrix and a WeightedQuickFindUF object of size N*N
	 */
	public Percolation(int N){
		if(N <= 0){
			throw new IllegalArgumentException();
		}
		quickUnion = new WeightedQuickUnionUF(N*N);
		percolationMatrix = new Boolean[N][N];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				percolationMatrix[i][j] = false;
			}
		}	
		matrixRange = N;
	}
	
	/*
	 * Function : takes the row and column index for the percolationMatrix.
	 * We will open a site and also check if the site can be 
	 * connected to another site 
	 */
	public void open(int i, int j){
		
		/*
		 * Check range here. if the indices are out of range throw an exception
		 */
		if(i > matrixRange || i < 1){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(j > matrixRange || j < 1){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		int row = i-1;
		int column = j-1;
		
		/*
		 * If site is already open return
		 */
		if(percolationMatrix[row][column]){
			return;
		}
		
		percolationMatrix[row][column] = true;
		
		
		int index = getIndex(row,column);
		//System.out.println("Site Index : " + String.valueOf(index));
		/*
		 * Connect top cell if open
		 */
		if((row -1) >= 0 && (row -1) < matrixRange){
			if(percolationMatrix[row-1][column]){
				int indexTop = getIndex(row-1, column);
				//System.out.println("Site Index Top: " + String.valueOf(indexTop));
				quickUnion.union(index, indexTop);
			}
		}
		
		/*
		 * connect left cell if open
		 */
		if((column-1) >= 0 && (column-1) < matrixRange){
			if(percolationMatrix[row][column-1]){
				int indexLeft = getIndex(row,column-1);
				//System.out.println("Site Index Left: " + String.valueOf(indexLeft));
				quickUnion.union(index, indexLeft);
			}
		}
		
		/*
		 * connect bottom cell if open
		 */
		if((row +1) >=0 && (row +1) < matrixRange){
			if(percolationMatrix[row+1][column]){
				int indexBottom = getIndex(row+1,column);
				//System.out.println("Site Index Bottom: " + String.valueOf(indexBottom));
				quickUnion.union(index, indexBottom);
			}
		}
		
		/*
		 * connect right cell if open
		 */
		if((column+1) >= 0 && (column+1) < matrixRange){
			if(percolationMatrix[row][column+1]){
				int indexRight = getIndex(row,column+1);
				//System.out.println("Site Index Right: " + String.valueOf(indexRight));
				quickUnion.union(index,indexRight);
			}
		}
	}

	/*
	 * Function : Checks if a particular site is open or not
	 */
	public boolean isOpen(int i, int j){
		if(i > matrixRange || i < 1){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(j > matrixRange || j < 1){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		int row = i-1;
		int column = j-1;
	
		
		return percolationMatrix[row][column];
	}
	
	/*
	 * Function : Checks if a particular site is full. A full site means one which is connected 
	 * from the top site
	 */
	public boolean isFull(int i, int j){
		if(i > matrixRange || i < 1){
			throw new ArrayIndexOutOfBoundsException();
		}
		if(j > matrixRange || j < 1){
			throw new ArrayIndexOutOfBoundsException();
		}
		
		int row = i-1;
		int column = j-1;
		
		if(row == 0){
			return percolationMatrix[row][column];
		}
		
		int index = getIndex(row,column);
		
		for(int k=0;k<matrixRange;k++){
			if(quickUnion.connected(k, index)){
				return true;
			}
		}
		return false;
	}
	
	private int getIndex(int i , int j){
		return i*(matrixRange)+j;
	}
	
	/*
	 * Function : Checks if a particular system percolates. This will only happen of a bottom site is full
	 */
	public boolean percolates(){
		
		for(int j=1;j<=matrixRange;j++){
			if(isFull(matrixRange,j)){
				return true;
			}
		}
		
		return false;
	}
	
	
		
}