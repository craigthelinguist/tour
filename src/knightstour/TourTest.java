package knightstour;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class TourTest {

	private static final int NUM_TRIALS = 3;
	
	public static void main(String[] args) throws IOException{
		
		PrintStream ps = new PrintStream(new File("Output"));
		
		for (int n = 0; n < 15; n++){
			
			long[] times = new long[3];
			boolean success = false;
			
			for (int i = 0; i < NUM_TRIALS; i++){
				long t1 = System.currentTimeMillis();
				success = KnightsTour.knightsTour(n);
				long t2 = System.currentTimeMillis();
				times[i] = t2 - t1;
			}
			
			double sum = 0;
			for (int k = 0; k < NUM_TRIALS; k++) sum += times[k];
			sum /= NUM_TRIALS;
			
			ps.println("n="+n+" " + success + " " + sum);
			
		}
		
		ps.close();
		
		
	}
	
}
