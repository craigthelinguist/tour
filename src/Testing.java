import java.io.File;
import java.io.IOException;
import java.io.PrintStream;


public class Testing {

	public static void testStartingPositionHeuristic() throws IOException{
		
		final int MAX_BOARD_WIDTH = 20;
		
		PrintStream ps = new PrintStream(new File("start-pos-heuristic.txt"));
		ps.println("WITH HEURISTIC");
		ps.println("==============");
		ps.println("SIZE,TIME");
		for (int n = 5; n < MAX_BOARD_WIDTH; n++){
			long start = System.currentTimeMillis();
			OptimisedOpen.knightsTour(n);
			long finish = System.currentTimeMillis();
			long time = finish-start;
			ps.println(n + ","+time);
		}
		
		ps.println("WITHOUT HEURISTIC");
		ps.println("=================");
		ps.println("SIZE,TIME");
		for (int n = 5; n < MAX_BOARD_WIDTH; n++){
			long start = System.currentTimeMillis();
			OptimisedOpen.knightsTourBadStartingPts(n);
			long finish = System.currentTimeMillis();
			long time = finish-start;
			ps.println(n + ","+time);
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		testStartingPositionHeuristic();
	}
	
}
