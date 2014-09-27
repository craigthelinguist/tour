import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

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
	 * @param p: starting position of the knight
	 * @return: true if there exists a knights tour
	 */
	public static boolean knightsTour(int n, Point p){
		boolean[][] board = new boolean[n][n];
		solution = new ArrayList<>();
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
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
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
	 * @param i: x position of the knight on the board
	 * @param j: y position of the knight on the board
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
		LinkedList<Point> validMoves = validMoves(i,j,n);
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
	 * @param i: x position of the knight
	 * @param j: y position of the knight
	 * @param n: width of the board
	 * @return: a list of points to which the knight can move.
	 */
	private static LinkedList<Point> validMoves(int i, int j, int n){
		LinkedList<Point> validMoves = new LinkedList<>();
		for (int k = 0; k < moves.length; k++){
			Point p = new Point(i+moves[k].x,j+moves[k].y);
			if (p.x >= 0 && p.x < n && p.y >= 0 && p.y < n){
				validMoves.add(p);
			}
		}
		return validMoves;
	}
	
	/**
	 * Returns the last sequence of points constituting a knights tour that this class has calculated.
	 * @return: List of points making up a knights tour, or null if there hasn't been one computed.
	 */
	public static List<Point> getTour(){
		return solution;
	}
	
	/**
	 * An array of valid moves. The knight can add any of the points in this array onto its position
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
