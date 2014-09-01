
public class PercolationStats {
			
	private double [] percolationResult;
	
	
		public PercolationStats(int N, int T){
			if(N <= 0){
				throw new IllegalArgumentException();
			}
			
			if(T <= 0){
				throw new IllegalArgumentException();
			}
			percolationResult = new double[T];
			for(int i=0;i<T;i++){
				percolationResult[i] = runExperiment(N);
				percolationResult[i] = percolationResult[i]/(N*N);
			}
		}
				
		private int runExperiment(int N){
			Percolation perco = new Percolation(N);
			int openSites = 0;
			while(openSites < N*N){
				int row = StdRandom.uniform(1, N+1);
				int column = StdRandom.uniform(1, N+1);
				
				
				if(perco.isOpen(row, column)){
					continue;
				}
				perco.open(row, column);
				openSites++;
				
				if(perco.percolates()){
					
					return openSites;
				}
			}
			return N*N;
		}
		
		public double mean(){
			return StdStats.mean(percolationResult);
		}
		
		public double stddev(){
			return StdStats.stddev(percolationResult);
		}
		
		public double confidenceLo(){
			double mean = mean();
			double stddev = stddev();
			double trials = Math.sqrt(percolationResult.length);
			return (double)(mean-(1.96*stddev)/trials);
		}
		
		public double confidenceHi(){
			double mean = mean();
			double stddev = stddev();
			double trials = Math.sqrt(percolationResult.length);
			return (double)(mean+(1.96*stddev)/trials);
		}
		
		public static void main(String [] args){
			//System.out.println(args[0] + "   " + args[1]);
			int matrixSize = Integer.parseInt(args[0]);
			int noOfTrials = Integer.parseInt(args[1]);
			PercolationStats ps = new PercolationStats(matrixSize, noOfTrials);
			System.out.println("Mean Value: " + String.valueOf(ps.mean()));
			System.out.println("Std Deviation: " + String.valueOf(ps.stddev()));
			System.out.println("Confidence Lo: " + String.valueOf(ps.confidenceLo()));
			System.out.println("Confidence Hi: " + String.valueOf(ps.confidenceHi()));
		}
}
