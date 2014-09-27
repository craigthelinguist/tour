import java.awt.Point;
import java.util.LinkedList;
import java.util.List;


public class tester {

	public static void main(String[] args){
		
		int n = 5;
		int i = 3;
		int j = 0;
		boolean[][] board = new boolean[n][n];
		List<Point> moves = validMoves(i,j,n,board);
	}
	
	private static List<Point> validMoves(int i, int j, final int n, final boolean[][] board){
		
		// compute valid moves
		Point[] points = new Point[8];
		int[] neighbours = new int[8];
		int z = 0;
		for (int k = 0; k < moves.length; k++){
			Point p = new Point(i+moves[k].x,j+moves[k].y);
			if (p.x >= 0 && p.x < n && p.y >= 0 && p.y < n && !board[p.x][p.y]){
				points[z] = p;
				neighbours[z] = countNeighbours(p.x,p.y,n,board) - 1;
				z++;
			}
		}
		
		// sort points in array according to their neighbours
		for (int k = 1; k < z; k++){
			Point considering = points[k];
			int idxConsidering = k;
			for (int l = k-1; l >= 0;  l--){
				if (neighbours[l] > neighbours[idxConsidering]){
					Point temp = points[l];
					points[l] = considering;
					points[idxConsidering] = temp;
					int tempInt = neighbours[l];
					neighbours[l] = neighbours[idxConsidering];
					neighbours[idxConsidering] = tempInt;
					idxConsidering = l;
				}
				else break;
			}
		}
	
		// sort moves according to the number of neighbours they have
		LinkedList<Point> movesSorted = new LinkedList<>();
		for (int k = 0; k < z; k++){
			System.out.println(points[k] + " has " + neighbours[k] + " neighbours.");
		}
		
		for (int k = 0; k < z; k++) movesSorted.add(points[k]);
		return movesSorted;
	}
	
	private static Point[] moves = new Point[]{
		new Point(2,1),
		new Point(2,-1),
		new Point(1,2),
		new Point(1,-2),
		new Point(-1,2),
		new Point(-1,-2),
		new Point(-2,1),
		new Point(-2,-1)
	};
	

	private static int countNeighbours(int i, int j, int n, boolean[][] board){
		int number = 0;
		for (int k = 0; k < moves.length; k++){
			Point p = new Point(i+moves[k].x,j+moves[k].y);
			if (p.x >= 0 && p.x < n && p.y >= 0 && p.y < n && !board[p.x][p.y]){
				++number;
			}
		}
		return number;
	}
	
	
}
