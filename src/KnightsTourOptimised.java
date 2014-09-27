import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A slightly optimised version of KnightsTour.java. KnightsTourOptimised uses a couple of tricks and pruning techniques
 * in order to speed up computation.
 * @author Aaron Craig
 *
 */
public class KnightsTourOptimised {

	private KnightsTourOptimised(){}
	private static List<Point> solution;
	
	/**
	 * Return true if there is a Knights Tour on the n*n board starting at point p.
	 * @param n: width of the board
	 * @param p: starting Point of the knight
	 * @return: true if there exists a knights tour
	 */
	public static boolean knightsTour(int n, Point p){

		solution = new ArrayList<>();
		
		// no solution on odd board when we start on colour with the smaller amount
		if (n % 2 != 0 && (p.x+p.y)%2 != 0){
			return false;
		}
		
		boolean[][] board = new boolean[n][n];
		boolean ans = backtrack(board,p.x,p.y,n,1);
		if (!ans) solution = new ArrayList<>();
		else Collections.reverse(solution);
		return ans;
	}
	
	/**
	 * Return true if there is a Knights Tour on the n*n board
	 * @param n: width of the board
	 * @return: true if there exists a knights tour
	 */
	public static boolean knightsTour(int n){
		boolean odd = n % 2 != 0;
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (odd && (i+j) % 2 != 0) continue;
				boolean[][] board = new boolean[n][n];
				solution = new ArrayList<>();
				if (backtrack(board,i,j,n,1)){
					Collections.reverse(solution);
					return true;
				}
			}
		}
		solution = new ArrayList<>();
		return false;
	}
	
	/**
	 * Recursively explores all possibilities for a knights tour.
	 * @param board: 2d boolean array, where an entry is true if the knight has visited that point.
	 * @param i: x Point of the knight on the board
	 * @param j: y Point of the knight on the board
	 * @param n: width of the board
	 * @param tilesVisited: number of tiles visited; if this = n*n then we have visited every tile
	 * @return: true if there is a knights tour, false if there isn't
	 */
	private static boolean backtrack(boolean[][] board, int i, int j, int n, int tilesVisited){
		board[i][j] = true;
		if (tilesVisited == n*n){
			solution.add(new Point(i,j));
			return true;
		}
		List<Point> validMoves = validMoves(i,j,n,board);
		for (Point p : validMoves){
			if (!board[p.x][p.y]){
				if (backtrack(board,p.x,p.y,n,tilesVisited+1)){
					solution.add(new Point(i,j));
					return true;
				}
			}
		}
		board[i][j] = false;
		return false;
	}
	
	/**
	 * Helper method. Computes the list of valid moves of a knight at the point (i,j) on the n*n board.
	 * The list returned will be sorted in ascending order. One point is greater than another if the knight
	 * can move to more squares from that point.
	 * @param i: x Point of the knight
	 * @param j: y Point of the knight
	 * @param n: width of the board
	 * @return: a list of points to which the knight can move.
	 */
	private static List<Point> validMoves(int i, int j, final int n, final boolean[][] board){

		// compute valid moves
		Point[] points = new Point[8];
		int[] neighbours = new int[8];
		int z = 0;
		for (int k = 0; k < moves.length; k++){
			Point p = new Point(i+moves[k].x,j+moves[k].y);
			if (p.x >= 0 && p.x < n && p.y >= 0 && p.y < n && !board[p.x][p.y]){
				points[z] = p;
				// this point hasn't been visited yet, so it will get counted in countNeighbours. Subtract 1 to compensate.
				neighbours[z] = countNeighbours(p.x,p.y,n,board) - 1;
				z++;
			}
		}
		
		// sort points in array according to their neighbours via insertion sort
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
	
		// construct and return sorted list
		LinkedList<Point> movesSorted = new LinkedList<>();
		for (int k = 0; k < z; k++) movesSorted.add(points[k]);
		return movesSorted;
	}
	
	/**
	 * From the point (i,j) on the n*n board, count the number of surrounding squares to which a knight
	 * can move, and to which it hasn't already moved.
	 * @param i: x position of knight
	 * @param j: y position of knight
	 * @param n: width of board
	 * @param board: 2d array where a cell is true if the knight has been to that square
	 * @return: number of neighbours from that cell.
	 */
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
	
	/**
	 * Returns the last sequence of points constituting a knights tour that this class has calculated.
	 * @return: List of points making up a knights tour, or null if there hasn't been one computed.
	 */
	public static List<Point> getTour(){
		return solution;
	}
	
	/**
	 * An array of valid moves. The knight can add any of the points in this array onto its Point
	 * in order to move around.
	 */
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
	
}
