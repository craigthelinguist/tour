import java.awt.Point;
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
	
	public static void testClosedTours() throws IOException{
		
		PrintStream ps = new PrintStream(new File("closed-tours.txt"));
		long start, finish;
		
		for (int n = 6; n <= 12; n+= 2){
			start = System.currentTimeMillis();
			OptimisedClosed.knightsTour(n);
			finish = System.currentTimeMillis();
			System.out.println("WITH HEURISTIC START PTS, size " + n + ": " + (finish-start) + "ms");
			
			ps.println("==================");
			ps.println("HEURISTC START PTS");
			ps.println("==================");
			for (Point pt : OptimisedClosed.getTour()){
				ps.println("("+pt.x+","+pt.y+")");
			}
			
			start = System.currentTimeMillis();
			OptimisedClosed.knightsTourBadStartingPts(n);
			finish = System.currentTimeMillis();
			System.out.println("WITHOUT HEURISTIC START PTS: size " + n + ": " + (finish-start) + "ms");
		
			ps.println("======================");
			ps.println("NON-HEURISTC START PTS");
			ps.println("=========-============");
			for (Point pt : OptimisedClosed.getTour()){
				ps.println("("+pt.x+","+pt.y+")");
			}
		
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		testClosedTours();
	}
	
}
